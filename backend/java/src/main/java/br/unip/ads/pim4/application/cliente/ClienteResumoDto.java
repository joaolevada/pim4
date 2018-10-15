package br.unip.ads.pim4.application.cliente;

public class ClienteResumoDto {
	
	private String id;
	private String nome;	
	private String email;	
	private String cpf;
	
	public ClienteResumoDto() {
		// rest
	}
	
	public ClienteResumoDto(String id, String nome, String email, String cpf) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}
	
	 

}
