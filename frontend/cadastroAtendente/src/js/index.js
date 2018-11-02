const form = doc('form');
const btnLogar = doc('#logar');
$('#cpf').mask('000.000.000-00', { reverse: true });

validaForm(form)

document.addEventListener('keypress', function (e) {
    if (e.which == 13) {

        if (!btnLogar.hasAttribute('disabled')) {

            cadastrar(API_URL, form);

        } else {

        }
    }
}, false);

btnLogar.addEventListener('click', e => {
    e.preventDefault();

    if (!btnLogar.hasAttribute('disabled')) {

        cadastrar(API_URL, form);

    } else {

    }
});

