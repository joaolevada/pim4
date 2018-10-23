import API_URL from '../../../lib/variables';

export class ClienteServices {
  constructor(Cliente) {
    this.Cliente = Cliente;
  }

  async cria(token) {
    this.token = token;
    this.isOk = '';
    this.header = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: this.token,
      },
      body: JSON.stringify(this.Cliente),
    };
    try {

      this.response = await fetch(API_URL, this.header);

      if (this.response.ok) {

        this.isOk = true;

      } else {

        this.isOk = false;

      }

    } catch (error) {
      console.error(error);
    }
    return this.isOk;
  }
}
