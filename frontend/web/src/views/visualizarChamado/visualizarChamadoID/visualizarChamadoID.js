import { Slim } from 'slim-js';
import { tag, template } from 'slim-js/Decorators';
import CPF from 'gerador-validador-cpf';
import { VisualizarServices } from '../services/visualizarServices';
import Snackbar from '../../../components/snackbar/snackbar.component';

const tpl = require('./visualizarChamadoID.html');

@tag('visualizar-chamado-id')
@template(tpl)

class VisualizarChamadoID extends Slim {
  async onBeforeCreated() {
    this.getProps();
    this.sucess = '#28a746e5';
    this.danger = '#dc3546e3';
    const visualizarServices = new VisualizarServices();
    this.response = await visualizarServices.readByID(this.props.id);
    this.data = await this.response.data;
    this.eventos = this.data.eventos;
  }

  onRender() {
    this.transferir.addEventListener('click', this.tranferirChamado);
    this.atualizar.addEventListener('click', this.atualizarChamado);
    this.encerrar.addEventListener('click', this.encerrarChamado);
  }

  encerrarChamado = async () => {
    this.encerrar.setAttribute('disabled', 'true');
    if (this.descricao.value) {
      // console.log(selectData);
      const chamado = {
        descricao: this.descricao.value,
        protocolo: this.props.id,
      };

      const visualizarServices = new VisualizarServices();

      const response = await visualizarServices.encerrar(this.props.id, chamado);

      if (response.ok) {
        this.form.reset();
        this.snackbar.show(response.msg, this.sucess);
        this.encerrar.removeAttribute('disabled');
      } else {
        this.snackbar.show(response.msg, this.danger);
        this.encerrar.removeAttribute('disabled');
      }
    } else {
      this.snackbar.show('Para Encerrar é necessário preencher o campo  Descrição', this.danger);
      this.encerrar.removeAttribute('disabled');
    }
  }

  atualizarChamado = async () => {
    this.atualizar.setAttribute('disabled', 'true');
    if (this.descricao.value) {
      // console.log(selectData);
      const chamado = {
        descricao: this.descricao.value,
      };

      const visualizarServices = new VisualizarServices();

      const response = await visualizarServices.atualizar(this.props.id, chamado);

      if (response.ok) {
        this.form.reset();
        this.snackbar.show(response.msg, this.sucess);
        this.atualizar.removeAttribute('disabled');
      } else {
        this.snackbar.show(response.msg, this.danger);
        this.atualizar.removeAttribute('disabled');
      }
    } else {
      this.snackbar.show('Para transferir é necessário preencher o campo  Descrição', this.danger);
      this.atualizar.removeAttribute('disabled');
    }
  }

  tranferirChamado = async () => {
    this.transferir.setAttribute('disabled', 'true');
    const selectData = this.select.getData();

    if (selectData && this.descricao.value) {
      // console.log(selectData);
      const chamado = {
        atendenteId: selectData[0].id,
        descricao: this.descricao.value,
      };

      const visualizarServices = new VisualizarServices();

      const response = await visualizarServices.transferir(this.props.id, chamado);

      if (response.ok) {
        this.form.reset();
        this.snackbar.show(response.msg, this.sucess);
        this.transferir.removeAttribute('disabled');
      } else {
        this.snackbar.show(response.msg, this.danger);
        this.transferir.removeAttribute('disabled');
      }
    } else {
      this.snackbar.show('Para transferir é necessário preencher o campo atendente e Descrição', this.danger);
      this.transferir.removeAttribute('disabled');
    }
  }

  getProps() {
    const attrsNames = this.getAttributeNames();
    this.props = {};
    attrsNames.forEach((name) => {
      this.props[name.replace('bind:', '')] = this.getAttribute(name);
    });

  }
}

export default VisualizarChamadoID;
