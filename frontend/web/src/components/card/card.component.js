import { Slim } from 'slim-js';
import { tag, template } from 'slim-js/Decorators';

const tpl = require('./card.component.html');

@tag('card-component')
@template(tpl)

class CardComponent extends Slim {
  async onBeforeCreated() {
    this.teste = 'On BeforeCreated';
    // alert(this.teste);
    try {
      const URL = 'https://reqres.in/api/users?per_page=12';

      const response = await fetch(URL);
      const res = await response.json();
      this.data = res.data;
      // console.log(this.data)
    } catch (err) {
      doc('#app').html(`<h3 class="text-center mt-5">Houve um erro ao carregar o conteudo <br> Provavelmente você esta offline :/ <br> ${err}</h3>`);
    }
  }

  onCreated() {
    this.teste = 'On Created';
    this.url = window.location.href;
    // alert(this.url);
  }

  onRender() {
    this.teste = 'On Render';
    // alert(this.teste);
  }

  onAdded() {
    this.teste = 'On Added';
    // alert(this.teste);
  }

  onRemoved() {
    this.teste = 'On Removed';
    // alert(this.teste);
  }
}
