package br.unip.ads.pim4.application.cliente;

public class NovoClienteDto {
	
	private String nome;
	private String cpf;
	private String email;
	
	public NovoClienteDto() {
		// rest
	}

	public NovoClienteDto(String nome, String cpf, String email) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	

}
