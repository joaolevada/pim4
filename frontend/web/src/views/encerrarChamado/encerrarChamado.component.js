import { Slim } from 'slim-js';
import { tag, template } from 'slim-js/Decorators';

import('../../components/card/card.component');

const tpl = require('./encerrarChamado.html');

@tag('encerrar-chamado')
@template(tpl)

class EncerrarChamado extends Slim {
  onBeforeCreated() {
    this.text = 'gui';
    this.img = 'src/assets/imgs/man.svg';
  }
}

export default EncerrarChamado;
