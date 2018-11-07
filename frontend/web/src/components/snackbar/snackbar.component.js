import { Slim } from 'slim-js';
import { tag, template } from 'slim-js/Decorators';

const tpl = require('./snackbar.component.html');

@tag('snack-bar')
@template(tpl)

class Snackbar extends Slim {
  onBeforeCreated() {
    this.getProps();
  }

  show(msg, cor) {
    this.msg = msg;
    this.cor = `background-color:${cor}`;
    this.snackbar.classList.add('show');
    setTimeout(() => this.snackbar.classList.remove('show'), 3000);
  }

  getProps() {
    const attrsNames = this.getAttributeNames();
    this.props = {};
    attrsNames.forEach((name) => {
      this.props[name.replace('bind:', '')] = this.getAttribute(name);
    });
  }
}

export default Snackbar;
