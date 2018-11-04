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
    async handleClick(e) {
        e.preventDefault();
        const valid = [this.state.emailValid, this.state.senhaValid, this.state.cpfValid, this.state.nomeValid]
        if(!valid.includes(false)) {
            this.props.renderProgressBar('w-100 progress');
            const atendente = {
                nome: this.refs.nome.value,
                email: this.refs.email.value,
                cpf: CPF.format(this.refs.cpf.value, 'digits'),
                senha: this.refs.senha.value
            }
            const response = await CadastroServices.create(atendente);
            if(response.status) {
                this.props.renderProgressBar('w-100 progress d-none');
                this.refs.form.reset();
                this.setState({ show: 'show', msg: response.msg, cor:'green'})
                setTimeout(() => this.setState({ show: ''}), 1600);
                setTimeout(() => this.setState({navigate: true}), 2000)
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
    handleValidaNome() {
        if (String(this.refs.nome.value).length != 0 ) {
            this.setState({ nomeIsValid: 'form-control', nomeValid: true })
        } else {
            this.setState({ nomeIsValid: 'form-control is-invalid', nomeValid: false })
        }
    }
    handleValidaCPF() {
        if (CPF.validate(this.refs.cpf.value)) {
            this.setState({ cpfIsValid: 'form-control', cpfValid: true })
        } else {
            this.setState({ cpfIsValid: 'form-control is-invalid', cpfValid: false })
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
        
        if(this.state.navigate) {
            return <Redirect to="/" push={true} />
        } else {
            return (
                <form ref="form">
                    <div className="form-row">
                        <div className="col-md-6">
                            <label htmlFor="nome">Nome</label>
                            <input  type="text" 
                                    onBlur={this.handleValidaNome.bind(this)} 
                                    className={this.state.nomeIsValid} 
                                    id="nome" 
                                    ref="nome" 
                                    required
                                    aria-describedby="nomeHelp" 
                                    placeholder="Seu nome" />
    
                            <small id="nomeHelp" className="invalid-feedback">Este campo é obrigatório !</small>
                        </div>
                        <div className="col-md-6">
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
                    </div>
    
                    <div className="form-row mt-3">
                        <div className="col-md-6">
                            <label htmlFor="cpf">CPF</label>
                            <InputMask mask="999.999.999-99"  type="text" 
                                    onBlur={this.handleValidaCPF.bind(this)} 
                                    className={this.state.cpfIsValid} 
                                    id="cpf" 
                                    ref="cpf" 
                                    aria-describedby="cpfHelp" 
                                    placeholder="Seu CPF" />
                                
                            <small id="cpfHelp" className="invalid-feedback">Insira um CPF valido !</small>
                        </div>
                        <div className="col-md-6">
                            <label htmlFor="senha">Senha</label>
                            <input  type="password" 
                                    onBlur={this.handleValidaSenha.bind(this)} 
                                    className={this.state.senhaIsValid} id="senha" 
                                    ref="senha" 
                                    aria-describedby="senhaHelp" 
                                    placeholder="Seu email" />
                                
                            <small id="senhaHelp" className="invalid-feedback">A senha deve conter no minimo 8 digitos !</small>
                        </div>
                    </div>
                    <div className="W-100 d-flex flex-column justify-content-center mt-4">
                        <button type="button" onClick={this.handleClick.bind(this)} className="btn btn-primary">Efetuar Cadastro</button>
                        <small className="mt-3 align-self-center">Já possui uma conta ? <Link to="/">Entre aqui</Link></small>
                    </div>
                    <Snackbar show={this.state.show} msg={this.state.msg} cor={this.state.cor} />
                </form>
            );
        }

    }
}

export default CadastroForm;
