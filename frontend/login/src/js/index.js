const form = document.querySelector('form');
const btnLogar = document.querySelector('#logar');
const URL = '';

validaCampos(form);

btnLogar.addEventListener('click', e => {
    e.preventDefault();

    if (!btnLogar.hasAttribute('disabled')) {
     
        getUser(URL, form);

        // console.log(localStorage.getItem('token'))

    } else {

    }
});

