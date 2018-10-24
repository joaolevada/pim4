/**
 * @classdesc Classe estatica para requisições http autenticadas;
 * @constructor httpAuth é uma classe statica e não deve ser inicializada !
 *
 */
export class HttpAuth {
  constructor() {
    throw new Error('httpAuth é uma classe statica e não deve ser inicializada !');
  }

  /**
     * Retorna o resultado da requisição get em formato JSON.
     * @static
     * @async
     * @param {string} url - Url para a requisição http.
     * @param {string} token - Token de autenticação
     */
  static async get(url, token) {
    this.url = url;
    this.token = token;
    this.header = { method: 'GET', headers: { 'Content-Type': 'application/json', Authorization: this.token } };

    try {

      this.res = await fetch(this.url, this.header);
      this.data = await this.res.ok ? this.res.json() : false;

    } catch (error) {
      // console.error(error)
    }

    return this.data;
  }

  /**
     * Retorna true se a requisição for sucesso e false se falha.
     * @static
     * @async
     * @param {string} url - Url para a requisição http.
     * @param {string} token - Token de autenticação.
     * @param {Object} object - Objeto para a inserção.
     * @returns {boolean}  Status da requisição ( true ou false).
     */

  static async post(url, token, object) {
    this.url = url;
    this.token = token;
    this.object = object;
    this.isOk = '';
    this.header = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: this.token,
      },
      body: JSON.stringify(this.object),
    };
    try {

      this.res = await fetch(this.url, this.header);
      this.isOk = !!this.res.ok;

    } catch (error) {
    //   console.error(error);
    }
    return this.isOk;
  }
}
