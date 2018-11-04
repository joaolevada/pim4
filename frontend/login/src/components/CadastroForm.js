import React, { Component } from 'react';
import { validaEmail } from '../helper/validaEmail';
import { Redirect } from 'react-router'
import Snackbar from './Snackbar';
import { Link } from 'react-router-dom'
import CPF from 'gerador-validador-cpf';
import InputMask from 'react-input-mask';
import { CadastroServices } from '../services/CadastroServices';


class CadastroForm extends Component {
  constructor() {
    super()
    this.state = {
      emailIsValid: 'form-control', emailValid: false, senhaValid: false, senhaIsValid: 'form-control',
      nomeIsValid: 'form-control', nomeValid: false, cpfValid: false, cpfIsValid: 'form-control',
      show: '', msg: '', cor: '', navigate: false
    }
  }

  handleClick = async (e) => {
    e.preventDefault();
    const { emailValid, senhaValid, cpfValid, nomeValid } = this.state;
    const valid = [emailValid, senhaValid, cpfValid, nomeValid]
    const { renderProgressBar } = this.props;
    const { nome, email, cpf, senha, form } = this.refs;

    if (!valid.includes(false)) {

      renderProgressBar('w-100 progress');
      const atendente = {
        nome: nome.value,
        email: email.value,
        cpf: CPF.format(cpf.value, 'digits'),
        senha: senha.value
      }

      const response = await CadastroServices.create(atendente);

      if (response.status) {
        renderProgressBar('w-100 progress d-none');
        form.reset();
        this.setState({ show: 'show', msg: response.msg, cor: 'green' });
        setTimeout(() => this.setState({ show: '' }), 1600);
        setTimeout(() => this.setState({ navigate: true }), 2000);

      } else {
        renderProgressBar('w-100 progress d-none');
        this.setState({ show: 'show', msg: response.msg, cor: 'red' })
        setTimeout(() => this.setState({ show: '' }), 5000);
      }

    } else {
      this.setState({ show: 'show', msg: 'Preencha os campos !', cor: 'red' })
      setTimeout(() => this.setState({ show: '' }), 5000);
    }
  }

  handleValidaSenha = () => {
    const { senha } = this.refs;
    (String(senha.value).length >= 6) ?
      this.setState({ senhaIsValid: 'form-control', senhaValid: true })
      : this.setState({ senhaIsValid: 'form-control is-invalid', senhaValid: false })
  }

  handleValidaNome = () => {
    const { nome } = this.refs;
    // eslint-disable-next-line
    (String(nome.value).length != 0) ?
      this.setState({ nomeIsValid: 'form-control', nomeValid: true })
      : this.setState({ nomeIsValid: 'form-control is-invalid', nomeValid: false })
  }

  handleValidaCPF = () => {
    const { cpf } = this.refs;
    (CPF.validate(cpf.value)) ?
      this.setState({ cpfIsValid: 'form-control', cpfValid: true })
      : this.setState({ cpfIsValid: 'form-control is-invalid', cpfValid: false })
  }

  handleValidaEmail = () => {
    const { email } = this.refs;
    (!validaEmail(email.value)) ?
      this.setState({ emailIsValid: 'form-control is-invalid', emailValid: false })
      : this.setState({ emailIsValid: 'form-control', emailValid: true })
  }

  render() {
    const { navigate, nomeIsValid, emailIsValid, cpfIsValid, senhaIsValid, show, cor, msg } = this.state;
    if (navigate) {
      return <Redirect to="/" push={true} />
    } else {
      return (
        <form ref="form">
          <div className="form-row">
            <div className="col-md-6">
              <label htmlFor="nome">Nome</label>
              <input type="text"
                onBlur={this.handleValidaNome}
                className={nomeIsValid}
                id="nome"
                ref="nome"
                required
                aria-describedby="nomeHelp"
                placeholder="Seu nome" />

              <small id="nomeHelp" className="invalid-feedback">Este campo é obrigatório !</small>
            </div>
            <div className="col-md-6">
              <label htmlFor="email">Email</label>
              <input type="email"
                onBlur={this.handleValidaEmail}
                className={emailIsValid}
                id="email"
                ref="email"
                aria-describedby="emailHelp"
                placeholder="Seu email" />

              <small id="emailHelp" className="invalid-feedback">Insira um email valido !</small>
            </div>
          </div>

          <div className="form-row mt-3">
            <div className="col-md-6">
              <label htmlFor="cpf">CPF</label>
              <InputMask mask="999.999.999-99" type="text"
                onBlur={this.handleValidaCPF}
                className={cpfIsValid}
                id="cpf"
                ref="cpf"
                aria-describedby="cpfHelp"
                placeholder="Seu CPF" />

              <small id="cpfHelp" className="invalid-feedback">Insira um CPF valido !</small>
            </div>
            <div className="col-md-6">
              <label htmlFor="senha">Senha</label>
              <input type="password"
                onBlur={this.handleValidaSenha}
                className={senhaIsValid} id="senha"
                ref="senha"
                aria-describedby="senhaHelp"
                placeholder="Sua senha" />

              <small id="senhaHelp" className="invalid-feedback">A senha deve conter no minimo 6 digitos !</small>
            </div>
          </div>
          <div className="W-100 d-flex flex-column justify-content-center mt-4">
            <button type="button" onClick={this.handleClick} className="btn btn-primary">Efetuar Cadastro</button>
            <small className="mt-3 align-self-center">Já possui uma conta ? <Link to="/">Entre aqui</Link></small>
          </div>
          <Snackbar show={show} msg={msg} cor={cor} />
        </form>
      );
    }
  }
}

export default CadastroForm;
