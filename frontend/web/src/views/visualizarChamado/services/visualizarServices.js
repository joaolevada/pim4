import { HttpAuth } from '../../../services/httpServicesAuth';
import API_URL from '../../../lib/variables';

export class VisualizarServices {
  constructor() {
    // eslint-disable-next-line
    this.token = `Basic ${btoa('guilherme11.gr@gmail.com:mesmerize')}`;
    // console.log(this.token);
    // this.token = localStorage.getItem('token');
  }

  async read() {
    const res = await HttpAuth.get(`${API_URL}/chamados`, this.token);
    console.log(res);
    return res;
  }

  async readByID(protocolo) {
    const res = await HttpAuth.get(`${API_URL}/chamados/${protocolo}`, this.token);
    console.log(res);
    return res;
  }

  async transferir(protocolo, chamado) {
    // console.log(protocolo)
    const res = await HttpAuth.put(`${API_URL}/chamados/${protocolo}/transferir`, this.token, chamado);
    console.log(res);
    return res;
  }

  async atualizar(protocolo, chamado) {
    // console.log(protocolo)
    const res = await HttpAuth.put(`${API_URL}/chamados/${protocolo}/atualizar`, this.token, chamado);
    console.log(res);
    return res;
  }

  async encerrar(protocolo, chamado) {
    // console.log(protocolo)
    const res = await HttpAuth.put(`${API_URL}/chamados/${protocolo}/encerrar`, this.token, chamado);
    console.log(res);
    return res;
  }
}
