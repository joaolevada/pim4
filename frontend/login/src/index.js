import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import { BrowserRouter, Switch, Route } from 'react-router-dom'
import Cadastro from './Cadastro';


ReactDOM.render(
    <BrowserRouter >
        <Switch>
            <Route path="/" exact={true} component={App} />
            <Route path="/cadastro" component={Cadastro} />
        </Switch>
    </BrowserRouter>
,document.getElementById('root'));

serviceWorker.unregister();
