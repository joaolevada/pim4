import React, { Component } from 'react';
import './App.css';
import support from './support.svg';
import LoginForm from './components/LoginForm';

class App extends Component {
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
        <div className="hero-container shadow-1 bg-light fade-in p-3">
        <div className={this.state.progress}>
            <div className="progress-bar"></div>
          </div>
          <img className="img-center" src={ support } width="80" height="80" alt="Logo" />
          <hr />
          <LoginForm renderProgressBar={this.renderProgressBar.bind(this)} />
        </div>  
    </div>
    );
  }
}

export default App;
