import { Slim } from 'slim-js';
import { tag, template } from 'slim-js/Decorators';
import { HttpAuth } from '../../services/httpServicesAuth';
import API_URL from '../../lib/variables';


const tpl = require('./select.component.html');

@tag('select-input')
@template(tpl)

class Select extends Slim {
  async onBeforeCreated() {
    this.valid = 'form-control';
    this.getProps();
    this.label = this.props.label;
    this.token = `Basic ${btoa('guilherme11.gr@gmail.com:mesmerize')}`;
    this.res = await HttpAuth.get(`${API_URL}${this.props.url}`, this.token)
    this.response = await this.res.data;
    this.SelectData = '';
    this.data = this.response.map((item) => {
      const data = {
        cpf: item.cpf,
        nome: item.nome,
        id: item.id,
        email: item.email,
        nomeCpf: `${item.nome} ${item.cpf}`,
      }
      return data;
    })
  }

  onRender() {
    this.input.addEventListener('focusout', () => {
      if(this.input.value) {
        this.valid = 'form-control is-valid'
        const input = this.input.value;
        this.SelectData = this.data.filter((data) => {
          const item = data.nomeCpf == input ? data : false;
          return item;
        })
      } else {
        this.valid = 'form-control is-invalid'
      }
    })
  }
  getData() {
    return this.SelectData;
  }

  isValid() {
    return this.valid === 'form-control is-valid' ? true : false;
  }
  getProps() {
    const attrsNames = this.getAttributeNames();
    this.props = {};
    attrsNames.forEach((name) => {
      this.props[name.replace('bind:', '')] = this.getAttribute(name);
    });
  }
}

export default Select;
