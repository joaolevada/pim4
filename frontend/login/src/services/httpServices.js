export class HttpServices {
  static async get(url, token) {
    const HEADER = {
      method: "GET",
      headers: {
        'Content-Type': 'application/json',
        'Authorization': token
      }
    };
    const response = await fetch(url, HEADER);
    const res = await response.json();
    const r = {
      ok: response.ok,
      data: res,
      status: response.status,
    }
    return r;
  }

  static async post(url, object) {
    const header = { method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(object) };
    try {
      const response = await fetch(url, header);
      const res = !response.ok ? await response.json() : { message: 'Cadastro efetuado com sucesso !'};
      console.log(res);
      const r = {
        ok: response.ok,
        msg: res.message,
      }
      return r;
    }
    catch (err) {
      console.error(err)
    }
  }
}