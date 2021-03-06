import { HttpAuth } from '../../../services/httpServicesAuth';
import API_URL from '../../../lib/variables';

export class ClienteServices {
  constructor() {
    // eslint-disable-next-line
    this.token = `Basic ${btoa('guilherme11.gr@gmail.com:mesmerize')}`;
    console.log(this.token);
    // this.token = localStorage.getItem('token');
  }

  async create(Cliente) {
    const res = await HttpAuth.post(`${API_URL}/clientes`, this.token, Cliente);
    console.log(res);
    return res;
  }
}
