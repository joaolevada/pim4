import React, { Component } from 'react';
import { validaEmail } from '../helper/validaEmail';
import { LoginServices } from '../services/LoginServices';
import Snackbar from './Snackbar';
import { Link } from 'react-router-dom'

class LoginForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      emailIsValid: 'form-control',
      senhaIsValid: 'form-control',
      emailValid: false,
      senhaValid: false,
      show: '', msg: '', cor: '',
    };
  }

  handleClick = async (e) => {
    e.preventDefault();

    const { emailValid, senhaValid } = this.state;
    const { renderProgressBar } = this.props;
    const { email, senha } = this.refs;

    if (emailValid && senhaValid) {
      renderProgressBar('w-100 progress');
      const response = await LoginServices.getUser(email.value, senha.value);

      if (response.status) {
        renderProgressBar('w-100 progress d-none');
      } else {
        renderProgressBar('w-100 progress d-none');
        this.setState({ show: 'show', msg: response.msg, cor: '#d9534f' })
        setTimeout(() => this.setState({ show: '' }), 3000);
      }

    } else {
      this.setState({ show: 'show', msg: 'Preencha os campos !', cor: '#d9534f' })
      setTimeout(() => this.setState({ show: '' }), 3000);
    }
  }

  handleValidaSenha = () => {
    const { senha } = this.refs;
    (String(senha.value).length >= 6) ?
      this.setState({ senhaIsValid: 'form-control', senhaValid: true })
      : this.setState({ senhaIsValid: 'form-control is-invalid', senhaValid: false })
  }

  handleValidaEmail = () => {
    const { email } = this.refs;
    (!validaEmail(email.value)) ?
      this.setState({ emailIsValid: 'form-control is-invalid', emailValid: false })
      : this.setState({ emailIsValid: 'form-control', emailValid: true })
  }

  render() {
    const { emailIsValid, senhaIsValid, show, msg, cor, } = this.state;
    return (
      <form>
        <div className="form-group">
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
        <div className="form-group">
          <label htmlFor="senha">Senha</label>
          <input type="password"
            onBlur={ this.handleValidaSenha } 
            className={ senhaIsValid } id="senha"
            ref="senha"
            aria-describedby="senhaHelp"
            placeholder="Sua Senha" />

          <small id="senhaHelp" className="invalid-feedback">Insira uma senha valida !</small>
        </div>
        <div className="W-100 d-flex flex-column justify-content-center">
          <button type="button" onClick={this.handleClick} className="btn btn-primary">Entrar</button>
          <small className="mt-3 align-self-center">Não possui uma conta ? <Link to="/cadastro">Cadastre-se aqui</Link></small>
        </div>
        <Snackbar show={show} msg={msg} cor={cor} />
      </form>
    );
  }
}

export default LoginForm;
