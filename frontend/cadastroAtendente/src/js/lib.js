const doc = document.querySelector.bind(document);
const docAll = document.querySelectorAll.bind(document);
function validaForm(form) {
    form.addEventListener('input', (e) => {

        bootstrapValidate('#nome', 'required:Campo Obrigatório !', (v) => {
            v ? toggleAttr(form.nome, 'invalid', 'valid') : toggleAttr(form.nome, 'valid', 'invalid')
        });
        bootstrapValidate('#email', 'email:Entre com um Email Válido !', (v) => {
            v ? toggleAttr(form.email, 'invalid', 'valid') : toggleAttr(form.email, 'valid', 'invalid')
        });

        bootstrapValidate('#senha', 'min:6:A senha deve conter no minimo 6 digitos !', (v) => {
            v ? toggleAttr(form.senha, 'invalid', 'valid') : toggleAttr(form.senha, 'valid', 'invalid')
        });

        form.cpf.addEventListener('keyup', (e) => {
            if (CPF.validate(form.cpf.value)) {
                toggleAttr(form.cpf, 'invalid', 'valid')
                form.cpf.classList.remove('is-invalid');
            } else {
                toggleAttr(form.cpf, 'valid', 'invalid')
                form.cpf.classList.add('is-invalid');
                cpfHelp.classList.remove('d-none');
            }
        });

        if (isValid(form.email) && isValid(form.senha) && isValid(form.nome) && isValid(form.cpf)) {
            btnLogar.removeAttribute('disabled');
        } else {
            addAttribute(btnLogar, 'disabled');
        }
    });
}

function toggleAttr(element, oldAttr, newAttr) {
    if (element.hasAttribute(oldAttr)) {
        element.removeAttribute(oldAttr)
        addAttribute(element, newAttr);
    } else {
        addAttribute(element, newAttr);
    }
}

function isValid(element) {
    if (element.hasAttribute('valid')) {
        return true;
    } else {
        return false;
    }
}

function addAttribute(element, attr, value) {
    let att = document.createAttribute(attr);
    value ? att.value = value : null;
    element.setAttributeNode(att);
}

async function post(url, object) {
    let isOk;
    const header = { method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(object) };
    try {
        const res = await fetch(url, header);
        console.log(res);
        isOk = !!res.ok;

    } catch (error) {
        // console.error(error);
    }
    return isOk;
}

async function cadastrar(URL, form) {

    addAttribute(btnLogar, 'disabled');

    const progress = doc('.progress');
    // const emailHelp = doc('#emailHelp');

    progress.classList.remove('d-none');

    try {

        const atendente = {
            nome: form.nome.value,
            cpf: CPF.format(form.cpf.value, 'digits'),
            email: form.email.value,
            senha: form.senha.value,
        };

        const response = await post(URL, atendente);
        if (response) {
            console.log(response);
            progress.classList.add('d-none');
            docAll('input').forEach( (input) => toggleAttr(input, 'valid', 'invalid'));
            form.reset()
            showSnackbar('Cadastro  efetuado com sucesso, Redirecionando ...', 'green', 5000);
            setTimeout(() => window.location = LOGIN_URL ,5300)
        } else {
            console.log(response)
            showSnackbar('O cadastro não pode ser efetuado com sucesso', 'red', 5000)
            docAll('input').forEach( (input) => toggleAttr(input, 'valid', 'invalid'));
            form.reset()
            progress.classList.add('d-none');
        }

    } catch (err) {
        console.error('Erro:' + err); // Dev only
        progress.classList.add('d-none');
        $(document).ready(function () {
            $('#alert').modal('show');
        })

    }
}

function showSnackbar(msg, color, time) {

    // Get the snackbar DIV
    var x = document.getElementById("snackbar");

    x.style.backgroundColor = color;

    // Add a msg 
    x.textContent = msg;

    // Add the "show" class to DIV
    x.className = "show";

    // After 3 seconds, remove the show class from DIV
    setTimeout(function () { x.className = x.className.replace("show", ""); }, time);

}

