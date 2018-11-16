import { Slim } from 'slim-js';
import { tag, template } from 'slim-js/Decorators';
import CPF from 'gerador-validador-cpf';
import CardComponent from '../../components/card/card.component';
import { VisualizarServices } from './services/visualizarServices';
import VisualizarChamadoID from './visualizarChamadoID/visualizarChamadoID';


const tpl = require('./visualizarChamado.html');

@tag('visualizar-chamado')
@template(tpl)

class VisualizarChamado extends Slim {
  async onBeforeCreated() {
    const visualizarServices = new VisualizarServices();
    this.response = await visualizarServices.read();
    this.chamados = await this.response.data;
    this.cardChamados.setData(this.chamados);
  }

  onRender() {
    const { filtro } = this;
    filtro.addEventListener('keyup', () => {
      // eslint-disable-next-line
      const cardHero = document.getElementById('cardHero');
      const a = cardHero.querySelectorAll('a');
      const filt = filtro.value.toUpperCase();
      const nodeArr = [...a];
      nodeArr.forEach((anode) => {
        const protocolo = anode.querySelector('.protocolo').innerHTML.toUpperCase().split(' ')[1];
        const cpf = CPF.format(anode.querySelector('.cpf').innerHTML.split(' ')[1], 'digits');

        if (protocolo.indexOf(filt) > -1 || cpf.toString().indexOf(filt) > -1) { // eslint-disable-next-line
          anode.style.display = '';
        } else { // eslint-disable-next-line
          anode.style.display = 'none';
        }
      });
    });
  }
}

export default VisualizarChamado;
