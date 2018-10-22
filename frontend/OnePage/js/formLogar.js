var btn_logar = document.querySelector('#logar');
btn_logar.addEventListener('click',function(event){
    event.preventDefault();
    
    let form = document.querySelector(".formulario_logar");
    let User = obtemUsuarioeSenha(form);
    console.log(User);
    form.reset();
});

 function obtemUsuarioeSenha(form){
     let Usuario ={
            usuario: form.user.value,
            senha: form.senha.value
    }
    return Usuario;
}

