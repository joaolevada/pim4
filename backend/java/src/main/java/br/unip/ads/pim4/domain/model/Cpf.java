package br.unip.ads.pim4.domain.model;

import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;

@Embeddable
public class Cpf {
	
	@Column(length = 12)
	@CPF
	private String numero;
	
	public Cpf() {
		// persistence
	}
	
	public Cpf(String numero) {
		this.numero = numero;
	}
	
	public String asString() {
	  return this.numero;
	}	
	
	public static String gerarCpf() {
		
		return "30086440071";
		
	}
	
}
