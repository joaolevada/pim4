const form = document.querySelector('form');
const btnLogar = document.querySelector('#logar');
const URL = 'url/da/api';

validaLogin(form);

btnLogar.addEventListener('click', e => {
    e.preventDefault();

    if (!btnLogar.classList.contains('disabled')) {

        const login = {
            email: form.email.value,
            senha: form.password.value,
        };

        const header = {
            method: "POST",
            body: login
        };

        fetch(URL, header)
            .then(response => response.json()) // Espero que venha um hash como token
            .then(data => {
                if (data.erro) {
                    alert('Usuario ou Senha Inválidos')
                } else {
                    localStorage.setItem('token', data.token);
                }
            })
            .catch()

        // console.log(localStorage.getItem('token'))

    } else {

    }
});
