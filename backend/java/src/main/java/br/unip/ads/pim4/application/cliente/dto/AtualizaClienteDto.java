package br.unip.ads.pim4.application.cliente.dto;

public class AtualizaClienteDto {
	
	private String nome;
	private String cpf;
	private String email;
	private String telefoneMovel;
	private String telefoneFixo;
	
	public AtualizaClienteDto() {
		// rest
	}	
	
	public AtualizaClienteDto(String nome, String cpf, String email, String telefoneMovel, String telefoneFixo) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefoneMovel = telefoneMovel;
		this.telefoneFixo = telefoneFixo;
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

	public String getTelefoneMovel() {
		return telefoneMovel;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}	
	
	

}
