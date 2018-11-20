package br.unip.ads.pim4.domain.model.chamado.evento;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import br.unip.ads.pim4.domain.model.Atendente;

@Embeddable
public class EventoChamado implements Comparable<EventoChamado> {

//	@EmbeddedId	
//	private Id id;

	@Column(nullable = false)	
	private LocalDateTime data;

	@Column()
	@Lob
	private String descricao;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER, optional = false)
	private Atendente atendente;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoEvento tipo;

//	@Column(nullable=false)
//	private Protocolo protocoloChamado;	

	public EventoChamado() {
		// Persistence
	}

	public EventoChamado(LocalDateTime data, String descricao, Atendente atendente, TipoEvento tipo) {
		super();
		// this.id = id;
		this.data = data;
		this.descricao = descricao;
		this.atendente = atendente;
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

//	public Id getId() {
//		return id;
//	}
//	
//	public void setId(Id id) {
//		this.id = id;
//	}

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

	public Atendente getAtendente() {
		return atendente;
	}

	public void setAtedente(Atendente atendente) {
		this.atendente = atendente;
	}

	public TipoEvento getTipo() {
		return tipo;
	}

	public void setTipo(TipoEvento tipo) {
		this.tipo = tipo;
	}

	@Override
	public int compareTo(EventoChamado outro) {		
		
		if (getData().isBefore(outro.getData())) {
			return -1;
		} else if (getData().equals(outro.getData())) {
			return 0;
		} else {
			return 1;
		}
		
	}

}
