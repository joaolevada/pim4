package br.unip.ads.pim4.domain.model.chamado;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import br.unip.ads.pim4.domain.model.Atendente;
import br.unip.ads.pim4.domain.model.Cliente;
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
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER, optional=false)	
	private Cliente cliente;
	
	// @OneToMany(cascade= CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
	// @JoinColumn(name="protocoloChamado")
	@ElementCollection(fetch=FetchType.EAGER)	
	private Set<EventoChamado> eventos;
	
	public Chamado() {
		// Persistence
	}

	public Chamado(Protocolo protocolo, LocalDateTime dataAbertura, LocalDateTime dataEncerramento, String assunto,
			Cliente cliente, Set<EventoChamado> eventos) {
		super();
		this.protocolo = protocolo;
		this.dataAbertura = dataAbertura;
		this.dataEncerramento = dataEncerramento;
		this.assunto = assunto;
		this.cliente = cliente;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<EventoChamado> getEventos() {
		/** 
		 * TODO: Retornar os eventos em ordem cronológica
		 */
		return eventos;
	}

	public void setEventos(Set<EventoChamado> eventos) {
		this.eventos = eventos;
	}

	public Protocolo getProtocolo() {
		return protocolo;
	}
	
	public Atendente responsavel() {
		
		if (getEventos().isEmpty()) {
			return null;
		}
		
		// Retornar o atendente do evento mais recente;
		LocalDateTime data = null;
		Atendente atendenteDoUltimoEvento = null;
		for (EventoChamado e : getEventos()) {			
			if (data == null || data.isBefore(e.getData())) {
				data = e.getData();
				atendenteDoUltimoEvento = e.getAtendente();
			}			
		}
		
		return atendenteDoUltimoEvento;
		
	}

}
