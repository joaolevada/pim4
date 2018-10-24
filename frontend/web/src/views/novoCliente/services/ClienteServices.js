import { HttpAuth } from '../../../services/httpServicesAuth';

export class ClienteServices {
  constructor() {
    this.token = localStorage.getItem('token');
  }

  async create(url, Cliente) {
    const isOk = await HttpAuth.post(url, this.token, Cliente);
    return isOk;
  }
}
