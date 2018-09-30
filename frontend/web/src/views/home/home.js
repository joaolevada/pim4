import { Slim } from 'slim-js';
import { tag, template } from 'slim-js/Decorators';

import('../../components/graficos/grafico.component');

const tpl = require('./home.html');

@tag('home-teste')
@template(tpl)

class Home extends Slim {
  onBeforeCreated() {
    this.text = 'gui';
    this.img = 'src/assets/imgs/man.svg';
  }
}

export default Home;
