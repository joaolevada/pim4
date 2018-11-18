import { Slim } from 'slim-js';
import { tag, template } from 'slim-js/Decorators';
import CPF from 'gerador-validador-cpf';
import 'jquery-mask-plugin';
import Snackbar from '../../components/snackbar/snackbar.component';
import Select from '../../components/select/select.component';


const tpl = require('./novoChamado.component.html');

@tag('novo-chamado')
@template(tpl)

class NovoChamado extends Slim {
  onBeforeCreated() {
    this.formControl = 'form-control';
    this.formInvalid = 'form-control is-invalid';
    this.formValid = 'form-control is-valid';
    this.sucess = '#28a746e5';
    this.danger = '#dc3546e3';
    this.loading = false;

    this.descricaoIsValid = this.formControl;
    this.assuntoIsValid = this.formControl;

  }

  onRender() {
    // eslint-disable-next-line
    const { abrir, assunto, descricao, sucess, danger, formInvalid, formValid } = this;
    // eslint-disable-next-line

    assunto.addEventListener('blur', () => {
      if (assunto.value) this.assuntoIsValid = formValid;
      else this.assuntoIsValid = formInvalid;
    });

    descricao.addEventListener('blur', () => {
      if (descricao.value) this.descricaoIsValid = formValid;
      else this.descricaoIsValid = formInvalid;
    });

    abrir.addEventListener('click', async () => {
      this.loading = true;
      abrir.setAttribute('disabled', 'true');
      const clienteIsValid = this.select.isValid();
      const verifica = [clienteIsValid, this.descricaoIsValid, this.assuntoIsValid];

      if (verifica.includes('form-control is-invalid')) {
        this.loading = false;
        abrir.removeAttribute('disabled');
        this.snackbar.show('Preencha os campos corretamente !', danger);
      } else if (verifica.includes('form-control is-valid')) {

        const { ChamadoServices } = await import('./services/ChamadoServices');

        const chamado = await this.chamadoBuilder();

        const chamadoService = new ChamadoServices();

        console.log(chamado);

        const response = await chamadoService.create(chamado);

        if (response.ok) {
          this.loading = false;
          this.form.reset();
          this.descricaoIsValid = this.formControl;
          this.assuntoIsValid = this.formControl;
          this.snackbar.show(response.msg, sucess);
        } else {
          this.loading = false;
          this.form.reset();
          this.descricaoIsValid = this.formControl;
          this.assuntoIsValid = this.formControl;
          this.snackbar.show(response.msg, danger);
        }
        abrir.removeAttribute('disabled');
      } else {
        this.loading = false;
        this.snackbar.show('Preencha os campos corretamente !', danger);
        abrir.removeAttribute('disabled');
      }

    });
  }

  async chamadoBuilder() {
    const { Chamado } = await import('./model/Chamado');
    const cliente = this.select.getData()[0];
    const atendente = JSON.parse(localStorage.getItem('user'))[0];
    const chamado = new Chamado(
      cliente.id,
      atendente.id,
      this.assunto.value,
      this.descricao.value,
    );
    return chamado;
  }
}

export default NovoChamado;
