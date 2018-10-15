package br.unip.ads.pim4.domain.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Column;

@Embeddable
public class Pessoa {	
	
	@Column(nullable = false, length = 50)
	private String nome;	
	
	@Embedded
	private Cpf cpf;
	
	@Embedded
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
	
	public Cpf getCpf() {
		return this.cpf;
	}
	
	public EMail getEmail() {
		return this.email;
	}
	
	
}
