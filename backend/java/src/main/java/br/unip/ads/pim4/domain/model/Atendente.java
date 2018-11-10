package br.unip.ads.pim4.domain.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Atendente {
	
	@EmbeddedId
	private Id id;
	
	@Embedded
	private Pessoa pessoa;
	
	@Column(nullable = false, length = 36)
	@NotNull
	@NotEmpty
	@Length(min = 6, max = 36)
	private String senha;
	
	public Atendente() {
		// persistence
	}
	
	public Atendente(Id id, Pessoa pessoa, String senha) {
		this.id = id;
		this.pessoa = pessoa;
		this.senha = senha;
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}	

}
