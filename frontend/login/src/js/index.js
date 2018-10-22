const form = doc('form');
const btnLogar = doc('#logar');

validaForm(form)

document.addEventListener('keypress', function (e) {
    if (e.which == 13) {

        if (!btnLogar.hasAttribute('disabled')) {

            getUser(API_URL, form);

            // console.log(localStorage.getItem('token'))

        } else {

        }
    }
}, false);

btnLogar.addEventListener('click', e => {
    e.preventDefault();

    if (!btnLogar.hasAttribute('disabled')) {

        getUser(API_URL, form);

        // console.log(localStorage.getItem('token'))

    } else {

    }
});

