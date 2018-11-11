package br.unip.ads.pim4.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.Length;

@Embeddable
public class TelefoneFixo {

	@Column(length = 30)
	@Length(max = 30)
	private String fixo;

	public TelefoneFixo() {
		// Persistence
	}
	
	public TelefoneFixo(String fixo) {
		this.fixo = fixo;
	}

	public String getFixo() {
		return fixo;
	}

	public void setFixo(String fixo) {
		this.fixo = fixo;
	}
	
	public String asString() {
		return this.getFixo();
	}
	
	
	
	

}
