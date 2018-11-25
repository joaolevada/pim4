import { HttpAuth } from '../../../services/httpServicesAuth';
import API_URL from '../../../lib/variables';

export class AtendenteServices {
  constructor() {
    this.token = localStorage.getItem('token');
    // console.log(this.token);
  }

  async create(Atendente) {
    const res = await HttpAuth.post(`${API_URL}/atendentes`, this.token, Atendente);
    // console.log(res);
    return res;
  }
}
