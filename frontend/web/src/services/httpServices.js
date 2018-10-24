/**
 * @classdesc Classe estatica para requisições http simples ( sem Auth );
 * @constructor Http é uma classe statica e não deve ser inicializada !
 *
 */
export class Http {
  constructor() {
    throw new Error('http é uma classe statica e não deve ser inicializada !');
  }

  /**
   * Retorna o resultado da requisição em formato JSON.
   * @static
   * @async
   * @param {string} url - Url para a requisição http.
   */
  static async get(url) {
    this.url = url;
    this.header = { method: 'GET', headers: { 'Content-Type': 'application/json' } };
    try {

      this.res = await fetch(this.url, this.header);
      this.data = await this.res.ok ? this.res.json() : false;

    } catch (error) {
      // console.error(error)
    }
    return this.data;
  }
}
