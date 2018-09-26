import { Slim } from 'slim-js';
import { tag, template } from 'slim-js/Decorators';

const tpl = require('./breadcrumb.component.html');

@tag('bread-crumb')
@template(tpl)

class BreadCrumb extends Slim {
  async onBeforeCreated() {
    this.url = window.location.href;
    this.url = this.url.split('/');
    this.current = this.url[4];
  }
}
