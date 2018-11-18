export class Atendente {
  constructor(nome, sobrenome, cpf, email, senha) {
    this.nome = `${nome} ${sobrenome}`;
    this.cpf = cpf;
    this.email = email;
    this.senha = senha;
  }
}
