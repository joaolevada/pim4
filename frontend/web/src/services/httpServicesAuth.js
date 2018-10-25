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
    const header = { method: 'GET', headers: { 'Content-Type': 'application/json', Authorization: token } };
    let data;
    try {
      const res = await fetch(url, header);
      data = await res.ok ? res.json() : false;
    } catch (error) {
      // console.error(error)
    }
    return data;
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
    let isOk;
    const header = { method: 'POST', headers: { 'Content-Type': 'application/json', Authorization: token }, body: JSON.stringify(object) };
    try {
      const res = await fetch(url, header);
      isOk = !!res.ok;

    } catch (error) {
      // console.error(error);
    }
    return isOk;
  }
}
