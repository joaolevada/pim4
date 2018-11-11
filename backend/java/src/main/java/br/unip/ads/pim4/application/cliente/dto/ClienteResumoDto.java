package br.unip.ads.pim4.application.cliente.dto;

public class ClienteResumoDto {
	
	private String id;
	private String nome;	
	private String email;	
	private String cpf;
	private String telefoneMovel;
	private String telefoneFixo;
	
	public ClienteResumoDto() {
		// rest
	}

	public ClienteResumoDto(String id, String nome, String email, String cpf, String telefoneMovel,
			String telefoneFixo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefoneMovel = telefoneMovel;
		this.telefoneFixo = telefoneFixo;
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

	public String getTelefoneMovel() {
		return telefoneMovel;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}
	 

}
