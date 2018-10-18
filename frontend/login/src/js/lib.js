function validaLogin(form){
    form.password.addEventListener('keyup', e => {

        if(form.email.value && String(form.password.value).length >= 8) {
            btnLogar.classList.remove('disabled')
        }
        else {
            btnLogar.classList.add('disabled');
        }
    });
}