package br.unip.ads.pim4.domain.model;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Cliente {	

	@EmbeddedId
	private Id id;
	
	@Embedded
	@NotNull
	private Pessoa pessoa;
	
	public Cliente() {
		// persistence
	}
	
	public Cliente(Id id, Pessoa pessoa) {
		this.id = id;
		this.pessoa = pessoa;
	}

	public Id getId() {
		return id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
