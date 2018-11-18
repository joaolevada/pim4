import { HttpAuth } from '../../../services/httpServicesAuth';
import API_URL from '../../../lib/variables';

export class DashboardServices {
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
}
