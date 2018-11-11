package br.unip.ads.pim4.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.Length;

@Embeddable
public class TelefoneMovel {

	@Column(length = 30)
	@Length(max = 30)
	private String movel;

	public TelefoneMovel() {
		// Persistence
	}
	
	public TelefoneMovel(String movel) {
		this.movel = movel;
	}

	public String getMovel() {
		return movel;
	}

	public void setMovel(String movel) {
		this.movel = movel;
	}
	
	public String asString() {
		return this.getMovel();
	}
	
	
	
	

}
