package br.unip.ads.pim4.domain.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;

@Embeddable
public class EMail {

	@Column(length = 255)
	@Email
	@NotEmpty
	@Length(max = 255)
	private String endereco;
	
	public EMail() {
		// Persistence
	}
	
	public EMail(String endereco) {
		this.endereco = endereco;
	}
	
	public String asString() {
		return this.endereco;
	}	
	
}
