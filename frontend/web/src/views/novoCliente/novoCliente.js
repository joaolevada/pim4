import { Slim } from 'slim-js';
import { tag, template } from 'slim-js/Decorators';
import CPF from 'gerador-validador-cpf';
import 'jquery-mask-plugin';
import validateEmail from '../../lib/validate';
import Snackbar from '../../components/snackbar/snackbar.component';

const tpl = require('./novo-cliente.html');

@tag('novo-cliente')
@template(tpl)

class NovoCliente extends Slim {
  onBeforeCreated() {
    this.formControl = 'form-control';
    this.formInvalid = 'form-control is-invalid';
    this.formValid = 'form-control is-valid';
    this.sucess = '#28a746e5';
    this.danger = '#dc3546e3';

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
      if (this.nome.value) this.nomeIsValid = this.formValid;
      else this.nomeIsValid = this.formInvalid;
    });

    this.cell.addEventListener('blur', () => {
      this.cellIsValid = this.formValid;
    });

    this.tell.addEventListener('blur', () => {
      this.tellIsValid = this.formValid;
    });

    this.sobrenome.addEventListener('blur', () => {
      if (this.sobrenome.value) this.sobrenomeIsValid = this.formValid;
      else this.sobrenomeIsValid = this.formInvalid;
    });

    this.validaCPF();
    this.validaEmail();

    this.salvar.addEventListener('click', async (e) => {
      this.salvar.setAttribute('disabled', 'true');
      const verifica = [this.cpfIsValid, this.emailIsValid, this.sobrenomeIsValid, this.nomeIsValid];

      if (verifica.includes('form-control is-invalid')) {
        this.salvar.removeAttribute('disabled');
        this.snackbar.show('Preencha os campos corretamente !', this.danger);

      } else if (verifica.includes('form-control is-valid')) {

        const { ClienteServices } = await import('./services/ClienteServices');

        const clienteValid = await this.clienteBuilder();

        const clienteService = new ClienteServices();

        const response = await clienteService.create(clienteValid);

        if (response.ok) {
          this.form.reset();
          this.cpfIsValid = this.formControl;
          this.emailIsValid = this.formControl;
          this.nomeIsValid = this.formControl;
          this.sobrenomeIsValid = this.formControl;
          this.cellIsValid = this.formControl;
          this.tellIsValid = this.formControl;
          this.salvar.removeAttribute('disabled');
          this.snackbar.show(response.msg, this.sucess);
        } else {
          this.salvar.removeAttribute('disabled');
          this.cpfIsValid = this.formControl;
          this.emailIsValid = this.formControl;
          this.nomeIsValid = this.formControl;
          this.sobrenomeIsValid = this.formControl;
          this.cellIsValid = this.formControl;
          this.tellIsValid = this.formControl;
          this.snackbar.show(response.msg, this.danger);
        }

      } else {
        this.salvar.removeAttribute('disabled');
        this.snackbar.show('Preencha os campos corretamente !', this.danger);
      }
    });

  }

  async clienteBuilder() {
    const { Cliente } = await import('./model/Cliente');
    const cliente = new Cliente(
      this.nome.value,
      this.sobrenome.value,
      CPF.format(this.cpf.value, 'digits'),
      this.email.value,
      this.formatTell(this.tell.value),
      this.formatTell(this.cell.value),
    );
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
}

export default NovoCliente;
