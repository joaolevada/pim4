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
    this.loading = false;
    const visualizarServices = new VisualizarServices();
    this.response = await visualizarServices.readByID(this.props.id);
    this.data = this.formataDados(await this.response.data);
    this.eventos = this.data.eventos;
  }

  formataDados = (data) => {  /* eslint-disable */
    data.cliente.cpf = CPF.format(data.cliente.cpf);
    data.cliente.telefoneFixo = this.formataNumeroTelefone(String(data.cliente.telefoneFixo))
    data.cliente.telefoneMovel = this.formataNumeroTelefone(String(data.cliente.telefoneMovel))
    data.eventos = data.eventos.map((evento) => {
      const dataFormatada = new Date(evento.data);
      const date = `${dataFormatada.getDate()} / ${dataFormatada.getMonth() + 1} / ${dataFormatada.getFullYear()}`;
      evento.data = date;
      return evento;
    })
    return data;
  }

  onRender() {
    this.transferir.addEventListener('click', this.tranferirChamado);
    this.atualizar.addEventListener('click', this.atualizarChamado);
    this.encerrar.addEventListener('click', this.encerrarChamado);
  }

  encerrarChamado = async () => {
    this.loading = true;
    this.encerrar.setAttribute('disabled', 'true');
    if (this.descricaoEncerrar.value) {
      const chamado = {
        descricao: this.descricaoEncerrar.value,
        protocolo: this.props.id,
      };

      const visualizarServices = new VisualizarServices();

      const response = await visualizarServices.encerrar(this.props.id, chamado);

      if (response.ok) {
        this.loading = false;
        this.formEncerrar.reset();
        this.snackbar.show(response.msg, this.sucess);
        this.encerrar.removeAttribute('disabled');
        this.onBeforeCreated();
      } else {
        this.loading = false;
        this.snackbar.show(response.msg, this.danger);
        this.encerrar.removeAttribute('disabled');
      }
    } else {
      this.loading = false;
      this.snackbar.show('Para Encerrar é necessário preencher o campo  Descrição', this.danger);
      this.encerrar.removeAttribute('disabled');
    }
  }

  atualizarChamado = async () => {
    this.loading = true;
    this.atualizar.setAttribute('disabled', 'true');
    if (this.descricaoAtualizar.value) {
      const chamado = {
        descricao: this.descricaoAtualizar.value,
      };

      const visualizarServices = new VisualizarServices();

      const response = await visualizarServices.atualizar(this.props.id, chamado);

      if (response.ok) {
        this.loading = false;
        this.formAtualizar.reset();
        this.snackbar.show(response.msg, this.sucess);
        this.atualizar.removeAttribute('disabled');
        this.onBeforeCreated();
      } else {
        this.loading = false;
        this.snackbar.show(response.msg, this.danger);
        this.atualizar.removeAttribute('disabled');
      }
    } else {
      this.loading = false;
      this.snackbar.show('Para atualizar é necessário preencher o campo  Descrição', this.danger);
      this.atualizar.removeAttribute('disabled');
    }
  }

  tranferirChamado = async () => {
    this.loading = true;
    this.transferir.setAttribute('disabled', 'true');
    const selectData = this.select.getData();

    if (selectData && this.descricaoTransferir.value) {
      // console.log(selectData);
      const chamado = {
        atendenteId: selectData[0].id,
        descricao: this.descricaoTransferir.value,
      };

      const visualizarServices = new VisualizarServices();

      const response = await visualizarServices.transferir(this.props.id, chamado);

      if (response.ok) {
        this.loading = false;
        this.formTransferir.reset();
        this.snackbar.show(response.msg, this.sucess);
        this.transferir.removeAttribute('disabled');
        this.onBeforeCreated();
      } else {
        this.loading = false;
        this.snackbar.show(response.msg, this.danger);
        this.transferir.removeAttribute('disabled');
      }
    } else {
      this.loading = false;
      this.snackbar.show('Para transferir é necessário preencher o campo atendente e Descrição', this.danger);
      this.transferir.removeAttribute('disabled');
    }
  }

  formataNumeroTelefone = (numero) => {
    if (numero != 'null') {
      let numeroFormatado;
      if (numero.length === 10) {
        const ddd = numero.substring(0, 2);
        const parte1 = numero.substring(2, 6);
        const parte2 = numero.substring(6, 12);
        numeroFormatado = `(${ddd}) ${parte1} - ${parte2}`;
      } else {
        const ddd = numero.substring(0, 2);
        const parte1 = numero.substring(2, 3);
        const parte2 = numero.substring(3, 7);
        const parte3 = numero.substring(7, 13);
        numeroFormatado = `(${ddd}) ${parte1}  ${parte2} - ${parte3}`;
      }
      return numeroFormatado;
    } else {
      return '';
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
