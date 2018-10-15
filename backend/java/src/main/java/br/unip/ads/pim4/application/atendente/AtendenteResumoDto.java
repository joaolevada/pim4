package br.unip.ads.pim4.application.atendente;

public class AtendenteResumoDto {
	
	private String id;
	private String nome;
	private String email;
	
	public AtendenteResumoDto() {
		// rest
	}
	
	public AtendenteResumoDto(String id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	

}
