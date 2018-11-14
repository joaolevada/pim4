package br.unip.ads.pim4.domain.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;

@Embeddable
public class Cpf {

	@Column(length = 12)
	@CPF
	@NotEmpty
	@Length(min = 12, max = 12)
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

	public static Cpf gerarCpf() {

		return new Cpf("30086440071");

	}

}
