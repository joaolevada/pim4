import { HttpAuth } from '../../../services/httpServicesAuth';

export class ClienteServices {
  constructor() {
    this.token = localStorage.getItem('token');
  }

  async criar(url, Cliente) {
    this.Cliente = Cliente;
    this.isOk = await HttpAuth.post(url, this.token, Cliente);
    return this.isOk;
  }
}
