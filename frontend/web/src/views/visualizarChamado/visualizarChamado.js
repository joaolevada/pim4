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
    const { filtro, checkAberto, checkEncerrado } = this;

    checkAberto.addEventListener('change', (e) => {  /* eslint-disable */
      // eslint-disable-next-line
      const cardHero = document.getElementById('cardHero');
      const a = cardHero.querySelectorAll('a');
      const nodeArr = [...a];
      if (checkAberto.checked) {
        nodeArr.forEach((node) => {
          node.getAttribute('status') == 'Aberto' ? node.style.display = '' : null;
        });
      } else {
        nodeArr.forEach((node) => {
          node.getAttribute('status') == 'Aberto' ? node.style.display = 'none' : null;
        });
      }
    });

    checkEncerrado.addEventListener('change', (e) => { /* eslint-disable */
      // eslint-disable-next-line
      const cardHero = document.getElementById('cardHero');
      const a = cardHero.querySelectorAll('a');
      const nodeArr = [...a];
      if (checkEncerrado.checked) {
        nodeArr.forEach((node) => {
          node.getAttribute('status') == 'Encerrado' ? node.style.display = '' : null;
        });
      } else {
        nodeArr.forEach((node) => {
          node.getAttribute('status') == 'Encerrado' ? node.style.display = 'none' : null;
        });
      }
    });

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
