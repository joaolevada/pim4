package br.unip.ads.pim4.domain.model.chamado;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import br.unip.ads.pim4.domain.model.Id;
import br.unip.ads.pim4.domain.model.chamado.evento.EventoChamado;

@Entity
public class Chamado {
	
	@EmbeddedId
	private Protocolo protocolo;
	
	@Column(nullable = false)
	private LocalDateTime dataAbertura;
	
	@Column
	private LocalDateTime dataEncerramento;
	
	@Column(nullable = false)
	private String assunto;
	
	@Embedded
	private Id idCliente;
	
	@OneToMany(cascade= CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
	@JoinColumn(name="protocolo_chamado")
	private Set<EventoChamado> eventos;
	
	public Chamado() {
		// Persistence
	}

	public Chamado(Protocolo protocolo, LocalDateTime dataAbertura, LocalDateTime dataEncerramento, String assunto,
			Id idCliente, Set<EventoChamado> eventos) {
		super();
		this.protocolo = protocolo;
		this.dataAbertura = dataAbertura;
		this.dataEncerramento = dataEncerramento;
		this.assunto = assunto;
		this.idCliente = idCliente;
		this.eventos = eventos;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(LocalDateTime dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Id getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Id idCliente) {
		this.idCliente = idCliente;
	}

	public Iterable<EventoChamado> getEventos() {
		return eventos;
	}

	public void setEventos(Set<EventoChamado> eventos) {
		this.eventos = eventos;
	}

	public Protocolo getProtocolo() {
		return protocolo;
	}	

}
