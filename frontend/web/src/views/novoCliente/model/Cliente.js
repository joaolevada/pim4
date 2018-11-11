export class Cliente {
  constructor(nome, sobrenome, cpf, email, telefone, celular) {
    this.nome = `${nome} ${sobrenome}`;
    this.cpf = cpf;
    this.email = email;
    this.telefone = telefone;
    this.celular = celular;
  }
}
