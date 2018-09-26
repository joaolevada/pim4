import { Slim } from 'slim-js';
import { tag, template } from 'slim-js/Decorators';

const tpl = require('./novoChamado.component.html');

@tag('novo-chamado')
@template(tpl)

class NovoChamado extends Slim {
  onBeforeCreated() {
    this.text = 'gui';
    this.img = 'src/assets/imgs/man.svg';
  }
}
