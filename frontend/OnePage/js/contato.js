var btn_enviar = document.querySelector('#Enviar');

btn_enviar.addEventListener('click',function(event){
    event.preventDefault();

    let form_contato = document.querySelector('.formcontato');
    
    let contato = validaform(form_contato);
    
    console.log(contato);
    form_contato.reset();
    
});

function validaform(form){
    
    if(!form.email.value || !form.nome.value|| !form.assunto.value|| !form.mensagem.value ){
            alert("Preencha todos os campos");
    }else{
       return obtemNomeEmailAssuntoMensagem(form)
    }
}

function obtemNomeEmailAssuntoMensagem(form_contato){
    let contato = {
        nome: form_contato.nome.value,
        email:form_contato.email.value,
        assunto:form_contato.assunto.value,
        mensagem:form_contato.mensagem.value
    }

    return contato;
}

