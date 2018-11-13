package br.unip.ads.pim4.domain.model;

import java.util.Objects;

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
	
	@Embedded	
	private TelefoneMovel telefoneMovel;
	
	@Embedded	
	private TelefoneFixo telefoneFixo;
	
	public Cliente() {
		// persistence
	}
	
	public Cliente(Id id, Pessoa pessoa, TelefoneMovel telefoneMovel, TelefoneFixo telefoneFixo) {
		this.id = id;
		this.pessoa = pessoa;
		this.telefoneMovel = telefoneMovel;
		this.telefoneFixo = telefoneFixo;		
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
	
	public TelefoneMovel getTelefoneMovel() {
		return this.telefoneMovel;
	}
	
	public void setTelefoneMovel(TelefoneMovel telefoneMovel) {
		this.telefoneMovel = telefoneMovel; 
	}

	public TelefoneFixo getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(TelefoneFixo telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}
	
	public boolean temTelefone() {
		return Objects.nonNull(getTelefoneFixo()) || Objects.nonNull(getTelefoneMovel()); 
	}

}
