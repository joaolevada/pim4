import { Slim } from 'slim-js';
import { tag, template } from 'slim-js/Decorators';
import Navigo from 'navigo';

const tpl = require('./login.html');

@tag('app-login')
@template(tpl)

class Login extends Slim {
  onBeforeCreated() {
    this.senhaValida = 'teste';
    this.emailValido = 'teste@teste';
    window.jQuery = doc;
    window.$ = window.jQuery;

  }

  onRender() {
    const router = new Navigo(null, true, '#!'); // Puta segurança em ?
    this.logar.addEventListener('click', (e) => {
      if (this.email.value === this.emailValido && this.senha.value === this.senhaValida) {
        router.navigate('/#!/app');
      } else {
        alert('Usuario ou senha Invalidos');
      }
    });
  }
}

export default Login;
