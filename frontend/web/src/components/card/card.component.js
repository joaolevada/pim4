import { Slim } from 'slim-js';
import { tag, template } from 'slim-js/Decorators';
import CPF from 'gerador-validador-cpf';

const tpl = require('./card.component.html');

@tag('card-component')
@template(tpl)

class CardComponent extends Slim {
  async onBeforeCreated() {
    this.data = '';
  }

  setData(data) {
    this.data = data.map((item) => {
      const dataFormatada = new Date(item.dataAbertura);
      const data = `${dataFormatada.getDate()} / ${dataFormatada.getMonth() + 1} / ${dataFormatada.getFullYear()}`;
      item['data'] = data;
      const cpf = CPF.format(item.cliente.cpf);
      item.cliente.cpf = cpf;
      return item;
    })
  }
}

export default CardComponent;
