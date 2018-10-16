import { Slim } from 'slim-js';
import { tag, template } from 'slim-js/Decorators';

const tpl = require('./layout.component.html');

@tag('app-layout')
@template(tpl)

class Layout extends Slim {
  onBeforeCreated() {
    this.arroz = '';
    window.jQuery = doc;
    window.$ = window.jQuery;
  }

  onRender() {

    this.sidebarCollapse.addEventListener('click', (e) => {

      doc('#sidebar').toggleClass('active');

      if (doc('#toRigth').attr('class') === 'svg-inline--fa fa-chevron-left fa-w-10 align-middle') {
        doc('#toRigth').toggleClass('fas fa-chevron-right');
      } else doc('#toRigth').toggleClass('fas fa-chevron-left');

    });
  }
}

export default Layout;
