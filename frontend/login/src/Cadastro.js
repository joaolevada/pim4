import React, { Component } from 'react';
import './App.css';
import support from './support.svg';
import CadastroForm from './components/CadastroForm';


class Cadastro extends Component {
  constructor(props) {
    super(props);
    this.state = {
      progress: 'w-100 progress d-none'
    }
  }
  
  renderProgressBar(show) {
    this.setState({progress: show})
  }

  render() {
    return (
    <div className="col-md-12 w-100 d-flex justify-content-center">
       <div className="hero-container-cadastro shadow-1 bg-light fade-in p-3">
        <div className={this.state.progress}>
            <div className="progress-bar"></div>
          </div>
          <img className="img-center-cadastro" src={ support } width="80" height="80" alt="Logo" />
          <hr />
          <CadastroForm renderProgressBar={this.renderProgressBar.bind(this)} />
        </div>  
    </div>
    );
  }
}

export default Cadastro;
