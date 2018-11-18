import { Slim } from 'slim-js';
import { tag, template } from 'slim-js/Decorators';
import CPF from 'gerador-validador-cpf';
import 'jquery-mask-plugin';
import validateEmail from '../../lib/validate';
import Snackbar from '../../components/snackbar/snackbar.component';

const tpl = require('./novoAtendente.html');

@tag('novo-atendente')
@template(tpl)

class NovoAtendente extends Slim {
  onBeforeCreated() {
    this.formControl = 'form-control';
    this.formInvalid = 'form-control is-invalid';
    this.formValid = 'form-control is-valid';
    this.sucess = '#28a746e5';
    this.danger = '#dc3546e3';
    this.loading = false;

    this.cpfIsValid = this.formControl;
    this.emailIsValid = this.formControl;
    this.nomeIsValid = this.formControl;
    this.sobrenomeIsValid = this.formControl;
    this.cellIsValid = this.formControl;
    this.senhaIsValid = this.formControl;
  }

  onRender() {
    doc('#cpf').mask('000.000.000-00', { reverse: true });

    this.nome.addEventListener('blur', () => {
      if (this.nome.value) this.nomeIsValid = this.formValid;
      else this.nomeIsValid = this.formInvalid;
    });

    this.senha.addEventListener('blur', () => {
      if (String(this.senha.value).length >= 6) this.senhaIsValid = this.formValid;
      else this.senhaIsValid = this.formInvalid;
    });

    this.sobrenome.addEventListener('blur', () => {
      if (this.sobrenome.value) this.sobrenomeIsValid = this.formValid;
      else this.sobrenomeIsValid = this.formInvalid;
    });

    this.validaCPF();
    this.validaEmail();

    this.salvar.addEventListener('click', async (e) => {
      this.loading = true;
      this.salvar.setAttribute('disabled', 'true');
      const verifica = [this.cpfIsValid, this.emailIsValid, this.sobrenomeIsValid, this.nomeIsValid, this.senhaIsValid];

      if (verifica.includes('form-control is-invalid')) {
        this.loading = false;
        this.salvar.removeAttribute('disabled');
        this.snackbar.show('Preencha os campos corretamente !', this.danger);

      } else if (verifica.includes('form-control is-valid')) {

        const { AtendenteServices } = await import('./services/AtendenteServices');

        const atendenteValid = await this.atendenteBuilder();

        const atendenteService = new AtendenteServices();

        const response = await atendenteService.create(atendenteValid);

        if (response.ok) {
          this.loading = false;
          this.form.reset();
          this.cpfIsValid = this.formControl;
          this.emailIsValid = this.formControl;
          this.nomeIsValid = this.formControl;
          this.sobrenomeIsValid = this.formControl;
          this.senhaIsValid = this.formControl;
          this.salvar.removeAttribute('disabled');
          this.snackbar.show(response.msg, this.sucess);

        } else {
          this.loading = false;
          this.salvar.removeAttribute('disabled');
          this.form.reset();
          this.cpfIsValid = this.formControl;
          this.emailIsValid = this.formControl;
          this.nomeIsValid = this.formControl;
          this.sobrenomeIsValid = this.formControl;
          this.senhaIsValid = this.formControl;
          this.snackbar.show(response.msg, this.danger);

        }

      } else {
        this.loading = false;
        this.salvar.removeAttribute('disabled');
        this.snackbar.show('Preencha os campos corretamente !', this.danger);
      }
    });
  }

  async atendenteBuilder() {
    const { Atendente } = await import('./model/Atendente');
    const atendente = new Atendente(
      this.nome.value,
      this.sobrenome.value,
      CPF.format(this.cpf.value, 'digits'),
      this.email.value,
      this.senha.value,
    );
    return atendente;
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
}

export default NovoAtendente;
