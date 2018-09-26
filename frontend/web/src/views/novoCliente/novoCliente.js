import { Slim } from 'slim-js';
import { tag, template } from 'slim-js/Decorators';


const tpl = require('./novo-cliente.html');

@tag('novo-cliente')
@template(tpl)

class NovoCliente extends Slim {
  onCreated() {
    setTimeout(() => {
      this.cep.addEventListener('keyup', async () => {
        if (String(this.cep.value).length === 8) {
          const cep = this.cep.value;
          try {
            const response = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
            const res = await response.json();
            this.endereco.value = res.logradouro;
            this.bairro.value = res.bairro;
            this.cidade.value = res.localidade;
            this.estado.value = res.uf;
          } catch (err) {
            this.cep.value = 'CEP Inválido';
          }
        } else {
          this.endereco.value = '';
          this.bairro.value = '';
          this.cidade.value = '';
          this.estado.value = '';
        }
      });
    }, 200);
  }
}
