const doc = document.querySelector.bind(document);
function validaForm(form) {
    form.addEventListener('input', (e) => {
        
        bootstrapValidate('#email', 'email:Entre com um Email Válido !', (v) => {
            v ? toggleAttr(form.email, 'invalid', 'valid') : toggleAttr(form.email, 'valid', 'invalid')
        });

        bootstrapValidate('#password', 'min:8:A senha deve conter no minimo 8 digitos !', (v) => {
            v ? toggleAttr(form.password, 'invalid', 'valid') : toggleAttr(form.password, 'valid', 'invalid')
        });

        if (isValid(form.email) && isValid(form.password)) {
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

async function getUser(URL, form) {

    addAttribute(btnLogar, 'disabled');

    const progress = doc('.progress');
    const emailHelp = doc('#emailHelp');

    progress.classList.remove('d-none');

    try {
        const userToken = btoa(`${form.email.value}:${form.password.value}`);

        const HEADER = {
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': userToken
            }
        };

        const response = await fetch(URL, HEADER);

        if (response.ok) {
            const user = await response.json();
            localStorage.setItem('token', user.token);
            progress.classList.add('d-none');
            window.location = URL_APP;

        } else {
            form.reset()
            progress.classList.add('d-none');
            form.email.classList.add('is-invalid');
            form.password.classList.add('is-invalid');
            emailHelp.classList.remove('d-none');
        }

    } catch (err) {
        console.error('Erro:' + err); // Dev only
        progress.classList.add('d-none');
        $(document).ready(function() {
            $('#alert').modal('show');
        })

    }
}

