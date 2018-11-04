import React, { Component } from 'react';
import { validaEmail } from '../helper/validaEmail';
import { LoginServices } from '../services/LoginServices';
import Snackbar from './Snackbar';
import { Link } from 'react-router-dom'

class LoginForm extends Component {
    constructor() {
        super()
        this.state = {
            emailIsValid: 'form-control', emailValid: false, senhaValid: false, senhaIsValid: 'form-control',
            show: '', msg: '', cor: '',
        }
    }

    async handleClick(e) {
        e.preventDefault();
        if(this.state.emailValid && this.state.senhaValid) {
            this.props.renderProgressBar('w-100 progress');
            const response = await LoginServices.getUser(this.refs.email.value, this.refs.senha.value);
            if(response.status) {
                window.location = 'http://localhost:8080';
                this.props.renderProgressBar('w-100 progress d-none');
            } else {
                this.props.renderProgressBar('w-100 progress d-none');
                this.setState({ show: 'show', msg: response.msg, cor:'red'})
                setTimeout(() => this.setState({ show: ''}), 5000);
            }
        } else {
            this.setState({ show: 'show', msg: 'Preencha os campos !', cor:'red'})
            setTimeout(() => this.setState({ show: ''}), 5000);
        }
    }

    handleValidaSenha() {
        if (String(this.refs.senha.value).length >= 6 ) {
            this.setState({ senhaIsValid: 'form-control', senhaValid: true })
        } else {
            this.setState({ senhaIsValid: 'form-control is-invalid', senhaValid: false })
        }
    }

    handleValidaEmail() {
        if (!validaEmail(this.refs.email.value)) {
            this.setState({ emailIsValid: 'form-control is-invalid', emailValid: false })
        } else {
            this.setState({ emailIsValid: 'form-control', emailValid: true  })
        }
    }

    render() {
        return (
            <form>
                <div className="form-group">
                    <label htmlFor="email">Email</label>
                    <input  type="email" 
                            onBlur={this.handleValidaEmail.bind(this)} 
                            className={this.state.emailIsValid} 
                            id="email" 
                            ref="email" 
                            aria-describedby="emailHelp" 
                            placeholder="Seu email" />

                    <small id="emailHelp" className="invalid-feedback">Insira um email valido !</small>
                </div>
                <div className="form-group">
                    <label htmlFor="senha">Senha</label>
                    <input  type="password" 
                            onBlur={this.handleValidaSenha.bind(this)} 
                            className={this.state.senhaIsValid} id="senha" 
                            ref="senha" 
                            aria-describedby="senhaHelp" 
                            placeholder="Seu email" />
                            
                    <small id="senhaHelp" className="invalid-feedback">Insira uma senha valida !</small>
                </div>
                <div className="W-100 d-flex flex-column justify-content-center">
                    <button type="button" onClick={this.handleClick.bind(this)} className="btn btn-primary">Entrar</button>
                    <small className="mt-3 align-self-center">Não possui uma conta ? <Link to="/cadastro">Cadastre-se aqui</Link></small>
                </div>
                <Snackbar show={this.state.show} msg={this.state.msg} cor={this.state.cor} />
            </form>
        );
    }
}

export default LoginForm;
