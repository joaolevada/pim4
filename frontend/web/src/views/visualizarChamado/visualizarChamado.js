import { Slim } from 'slim-js';
import { tag, template } from 'slim-js/Decorators';
import CardComponent from '../../components/card/card.component';
import { VisualizarServices } from './services/visualizarServices';
import CPF from 'gerador-validador-cpf';

const tpl = require('./visualizarChamado.html');

@tag('visualizar-chamado')
@template(tpl)

class VisualizarChamado extends Slim {
  async onBeforeCreated() {
    const visualizarServices = new VisualizarServices();
    this.response = await visualizarServices.read();
    this.chamados = await this.response.data;
    this.cardChamados.setData(this.chamados)
  }

  onRender() {
    const { filtro } = this;
    filtro.addEventListener('keyup', () => {
      const cardHero = document.getElementById('cardHero')
      const a = cardHero.querySelectorAll('a');
      const filt = filtro.value.toUpperCase();
      const nodeArr = [...a]
      nodeArr.forEach((a) => {
        const protocolo = a.querySelector('.protocolo').innerHTML.toUpperCase().split(' ')[1];
        const cpf = CPF.format(a.querySelector('.cpf').innerHTML.split(' ')[1], 'digits');

        if (protocolo.indexOf(filt) > -1 || cpf.toString().indexOf(filt) > -1) {
          a.style.display = "";
        } else {
          a.style.display = "none";
        }
      })
    });
  }
}

export default VisualizarChamado;
