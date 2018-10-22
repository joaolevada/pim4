import { Slim } from 'slim-js';
import { tag, template } from 'slim-js/Decorators';
import CPF from 'gerador-validador-cpf';
import 'jquery-mask-plugin';
import validateEmail from '../../lib/validate';
import { Cliente } from './model/Cliente';
import { ClienteServices } from './services/ClienteServices';

const tpl = require('./novo-cliente.html');

@tag('novo-cliente')
@template(tpl)

class NovoCliente extends Slim {
  onBeforeCreated() {
    this.formControl = 'form-control';
    this.formInvalid = 'form-control is-invalid';
    this.formValid = 'form-control is-valid';

    this.cpfIsValid = this.formControl;
    this.emailIsValid = this.formControl;
    this.nomeIsValid = this.formControl;
    this.sobrenomeIsValid = this.formControl;
    this.cellIsValid = this.formControl;
    this.tellIsValid = this.formControl;
  }

  onRender() {
    doc('#cell').mask('(00) 0 0000-0000');
    doc('#tell').mask('(00) 0000-0000');
    doc('#cpf').mask('000.000.000-00', { reverse: true });

    this.nome.addEventListener('blur', () => {
      this.nomeIsValid = this.formValid;
    });

    this.sobrenome.addEventListener('blur', () => {
      this.sobrenomeIsValid = this.formValid;
    });

    this.validaCPF();
    this.validaEmail();
    this.validaCell();
    this.validaTell();

    this.salvar.addEventListener('click', (e) => {
      const verifica = [this.cpfIsValid, this.emailIsValid, this.nomeIsValid, this.sobrenomeIsValid, this.cellIsValid, this.tellIsValid];

      if (verifica.includes('form-control is-invalid')) {

        this.showSnackbar(3000, 'Preencha os campos corretamente !');

      } else if (verifica.includes('form-control is-valid')) {

        // const { ClienteServices } = await import('./services/ClienteServices');

        // const ClientValid = this.criaCliente();

        // const clienteService = new ClienteServices(ClientValid);

        const ClientValid = this.criaCliente();

        const clienteService = new ClienteServices(ClientValid);

        clienteService.cria('token');
        console.log(clienteService);

      } else {
        this.showSnackbar(3000, 'Preencha todos os campos !');
      }
    });

  }

  criaCliente() {

    // const { Cliente } = await import('./model/Cliente');

    const cliente = new Cliente(this.nome.value, this.sobrenome.value, this.cpf.value,
      this.email.value, this.formatTell(this.tell.value), this.formatTell(this.cell.value));
    return cliente;
  }

  validaCPF() {
    this.cpf.addEventListener('blur', (e) => {
      const cpf = this.cpf.value;
      if (CPF.validate(cpf)) {
        this.cpfIsValid = this.formValid;
      } else {
        this.cpfIsValid = this.formInvalid;
      }
    });
  }

  validaEmail() {
    this.email.addEventListener('blur', (e) => {
      if (validateEmail(this.email.value)) {
        this.emailIsValid = this.formValid;
      } else {
        this.emailIsValid = this.formInvalid;
      }
    });
  }

  formatTell(tell) {
    this.tellFotmat = tell.replace('(', '').replace(')', '').replace(' ', '').replace('-', '');
    return this.tellFotmat;
  }

  validaCell() {
    this.cell.addEventListener('blur', () => {
      if (String(this.cell.value).length === 16) {
        this.cellIsValid = this.formValid;
        // this.tell.value.replace('(', '').replace(')', '').replace(' ', '').replace('-', '');
      } else {
        this.cellIsValid = this.formInvalid;
      }
    });
  }

  validaTell() {
    this.tell.addEventListener('blur', () => {
      if (String(this.tell.value).length === 14) {
        this.tellIsValid = this.formValid;
        // this.tell.value.replace('(', '').replace(')', '').replace(' ', '').replace('-', '');
      } else {
        this.tellIsValid = this.formInvalid;
      }
    });
  }

  showSnackbar(time, msg) {
    this.msg = msg;
    this.snackbar.className = 'show';
    setTimeout(() => {
      this.snackbar.className = this.snackbar.className.replace('show', '');
    }, time);
  }
}

export default NovoCliente;
