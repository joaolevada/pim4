package br.unip.ads.pim4.domain.model.chamado.evento;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import br.unip.ads.pim4.domain.model.Id;
import br.unip.ads.pim4.domain.model.chamado.Protocolo;

@Entity
public class EventoChamado {	

	@EmbeddedId	
	private Id id;
	
	@Column(nullable = false)
	private LocalDateTime data;
	
	@Column()
	@Lob
	private String descricao;
	
	@Column(nullable = false)
	private Id idAtendente;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoEvento tipo;
	
	@Column(nullable=false)
	private Protocolo protocoloChamado;	
	
	
	public EventoChamado() {
		// Persistence
	}	
	
	
	public EventoChamado(Id id, LocalDateTime data, String descricao, Id idAtendente, TipoEvento tipo) {
		super();
		this.id = id;
		this.data = data;
		this.descricao = descricao;
		this.idAtendente = idAtendente;
		this.tipo = tipo;
	}



	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}



	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	public Id getId() {
		return id;
	}
	
	public void setId(Id id) {
		this.id = id;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Id getIdAtendente() {
		return idAtendente;
	}

	public void setIdAtendente(Id idAtendente) {
		this.idAtendente = idAtendente;
	}	
	
	public TipoEvento getTipo() {
		return tipo;
	}

	public void setTipo(TipoEvento tipo) {
		this.tipo = tipo;
	}
	
}
