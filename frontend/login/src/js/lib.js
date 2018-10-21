function validaCampos(form) {
    form.password.addEventListener('keyup', e => {

        if (form.email.value && String(form.password.value).length >= 8) {
            btnLogar.removeAttribute('disabled');
        }
        else {
            addAttribute(btnLogar,'disabled');
        }
    });
}

function addAttribute(element, attr, value) {
    let att = document.createAttribute(attr);
    value ? att.value = value : null;
    element.setAttributeNode(att);
}

async function getUser (URL , form) {

    addAttribute(btnLogar,'disabled');

    const progress = document.querySelector('.progress');
    const emailHelp = document.querySelector('#emailHelp');

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

        if(response.ok) {
            const user = await response.json();
            localStorage.setItem('token',user.token);
            progress.classList.add('d-none');
            window.location = "https://pimquatro.github.io/App/";
            
        } else {
            progress.classList.add('d-none');
            form.email.classList.add('is-invalid');
            form.password.classList.add('is-invalid');
            emailHelp.classList.remove('d-none');
            btnLogar.removeAttribute('disabled');
        }

    } catch (err) {
        console.error(err); // Dev only
    }
}
