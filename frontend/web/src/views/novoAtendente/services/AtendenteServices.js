import { HttpAuth } from '../../../services/httpServicesAuth';
import API_URL from '../../../lib/variables';

export class AtendenteServices {
  constructor() {
    // eslint-disable-next-line
    this.token = `Basic ${btoa('guilherme11.gr@gmail.com:mesmerize')}`;
    // console.log(this.token);
    // this.token = localStorage.getItem('token');
  }

  async create(Atendente) {
    const res = await HttpAuth.post(`${API_URL}/atendentes`, this.token, Atendente);
    // console.log(res);
    return res;
  }
}
