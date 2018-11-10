package br.unip.ads.pim4.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;

@Embeddable
public class Pessoa {	
	
	@Column(nullable = false, length = 50)
	@NotNull
	private String nome;	
	
	@Embedded
	@NotNull
	private Cpf cpf;
	
	@Embedded
	@NotNull	
	private EMail email;		
	
	public Pessoa() {
		// Persistence
	}
	
	public Pessoa(String nome, Cpf cpf, EMail email) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Cpf getCpf() {
		return this.cpf;
	}
	
	public void setCpf(Cpf cpf) {
		this.cpf = cpf;
	}
	
	public EMail getEmail() {
		return this.email;
	}
	
	public void setEmail(EMail email) {
		this.email = email;
	}
	
	
}
