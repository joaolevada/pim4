import { HttpAuth } from '../../../services/httpServicesAuth';
import API_URL from '../../../lib/variables';

export class ChamadoServices {
  constructor() {
    // eslint-disable-next-line
    this.token = `Basic ${btoa('guilherme11.gr@gmail.com:mesmerize')}`;
    // this.token = localStorage.getItem('token');
  }

  async create(Chamado) {
    const res = await HttpAuth.post(`${API_URL}/chamados`, this.token, Chamado);
    console.log(res);
    return res;
  }
}