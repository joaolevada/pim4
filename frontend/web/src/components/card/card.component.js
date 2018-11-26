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
      const date = `${dataFormatada.getDate()} / ${dataFormatada.getMonth() + 1} / ${dataFormatada.getFullYear()}`;
      // eslint-disable-next-line
      item['data'] = date;
      // eslint-disable-next-line
      item['urlProtocolo'] = `/#!/visualizar-chamado/${item.protocolo}`;
      // eslint-disable-next-line
      item['status'] = item.dataEncerramento ? 'border-left: solid 3px #d9534f;' : 'border-left: solid 3px #5cb85c;';
      // eslint-disable-next-line
      item['statusFiltro'] = item.dataEncerramento ? 'Encerrado' : 'Aberto';
      const cpf = CPF.format(item.cliente.cpf);
      // eslint-disable-next-line
      item.cliente.cpf = cpf;
      return item;
    });
    console.log(this.data);
  }
}

export default CardComponent;
