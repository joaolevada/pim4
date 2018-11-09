import { HttpServices } from "./httpServices";

export class LoginServices {
  static async getUser(email, senha) {
    const response = {};
    const token = `Basic ${btoa(`${email}:${senha}`)}`;
    const res = await HttpServices.get('http://localhost:8080/api/atendentes', token);
    console.log(res)
    if (res.ok) {
      response.status = true;
      localStorage.setItem('token', token);
      window.location = 'http://localhost:3100';
    } else {
      response.msg = res.data.message === 'Unauthorized' ? 'Email ou senha inválidos !' : res.data.message;
      response.status = false;
    }
    return response;
  }
}