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
    const response = {};
    try {
      const res = await fetch(url, header);

      response.data = await res.ok ? res.json() : null;
      response.res = res;

    } catch (error) {
      // console.error(error)
    }
    return response;
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
    const header = { method: 'POST', headers: { 'Content-Type': 'application/json', Authorization: token }, body: JSON.stringify(object) };
    const response = {};
    try {
      const res = await fetch(url, header);
      const r = await !res.ok ? await res.json() : { message: 'Cadastro efetuado com sucesso !' };
      response.ok = res.ok;
      response.msg = r.message;
    } catch (error) {
      // console.error(error);
    }
    return response;
  }

  static async put(url, token, object) {
    const header = { method: 'PUT', headers: { 'Content-Type': 'application/json', Authorization: token }, body: JSON.stringify(object) };
    const response = {};
    try {
      const res = await fetch(url, header);
      const r = await !res.ok ? await res.json() : { message: 'Cadastro efetuado com sucesso !' };
      response.ok = res.ok;
      response.msg = r.message;
    } catch (error) {
      // console.error(error);
    }
    return response;
  }
}
