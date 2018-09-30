import { Slim } from 'slim-js';
import { tag, template } from 'slim-js/Decorators';

const tpl = require('./novo-cliente.html');

@tag('novo-cliente')
@template(tpl)

class NovoCliente extends Slim {
  onBeforeCreated() {
    this.isvalid = 'form-control';
  }

  onRender() {

    this.cep.addEventListener('keyup', async () => {

      if (String(this.cep.value).length === 8) {

        const cep = this.cep.value;

        try {

          const response = await fetch(`https://viacep.com.br/ws/${cep}/json/`);

          const res = await response.json();

          if (res.erro === true) {
            this.erro = true;
            this.isvalid = 'form-control is-invalid';
          } else {
            this.isvalid = 'form-control is-valid';
            this.endereco.value = res.logradouro;
            this.bairro.value = res.bairro;
            this.cidade.value = res.localidade;
            this.estado.value = res.uf;
          }

        } catch (err) {
          // this.cep.value = 'CEP Inválido';
        }

      } else {
        this.isvalid = 'form-control';
        this.endereco.value = '';
        this.bairro.value = '';
        this.cidade.value = '';
        this.estado.value = '';
      }
    });
  }
}

export default NovoCliente;
