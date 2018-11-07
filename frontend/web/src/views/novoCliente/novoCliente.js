import {
  Slim,
} from 'slim-js';
import {
  tag,
  template,
} from 'slim-js/Decorators';
import CPF from 'gerador-validador-cpf';
import 'jquery-mask-plugin';
import validateEmail from '../../lib/validate';
import Snackbar from '../../components/snackbar/snackbar.component';
// import { Http } from '../../services/httpServices';


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
      const verifica = [this.cpfIsValid, this.emailIsValid, this.sobrenomeIsValid, this.nomeIsValid];

      if (verifica.includes('form-control is-invalid')) {

        // const a = await Http.get('https://viacep.com.br/ws/01001000/json/');
        // console.log(a);
        this.showSnackbar(3000, 'Preencha os campos corretamente !', this.danger);

      } else if (verifica.includes('form-control is-valid')) {

        const {
          ClienteServices,
        } = await import('./services/ClienteServices');

        const clienteValid = await this.clienteBuilder();

        const clienteService = new ClienteServices();

        // console.log(clienteValid);

        const isOk = await clienteService.create(clienteValid);

        if (isOk) {
          this.snackbar.show('Cadastro efetuado com sucesso !', this.sucess);
        } else {
          this.snackbar.show('Cadastro não pode ser realizado com sucesso !', this.danger);
        }

      } else {
        this.snackbar.show('Preencha os campos corretamente !', this.danger);
      }
    });

  }

  async clienteBuilder() {
    const { Cliente } = await import('./model/Cliente');
    const cliente = new Cliente(
      this.nome.value,
      this.sobrenome.value, this.cpf.value,
      this.email.value, this.formatTell(this.tell.value),
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
