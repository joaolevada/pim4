import { Slim } from 'slim-js';
import { tag, template } from 'slim-js/Decorators';

const tpl = require('./erro.html');

@tag('erro-404')
@template(tpl)

class Erro extends Slim {
  onBeforeCreated() {
    this.text = 'gui';
    this.img = 'src/assets/imgs/man.svg';
  }
}

export default Erro;
