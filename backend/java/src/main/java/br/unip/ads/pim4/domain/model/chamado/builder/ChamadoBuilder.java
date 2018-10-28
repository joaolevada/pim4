package br.unip.ads.pim4.domain.model.chamado.builder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import br.unip.ads.pim4.domain.model.Atendente;
import br.unip.ads.pim4.domain.model.Cliente;
import br.unip.ads.pim4.domain.model.chamado.Chamado;
import br.unip.ads.pim4.domain.model.chamado.Protocolo;
import br.unip.ads.pim4.domain.model.chamado.evento.EventoChamado;
import br.unip.ads.pim4.domain.model.chamado.evento.TipoEvento;

public class ChamadoBuilder {
	
	private String assunto;
	private LocalDateTime dataAbertura;
	private LocalDateTime dataEncerramento;
	private Cliente cliente;
	private Set<EventoChamado> eventos;
	private Protocolo protocolo;	
	
	public ChamadoBuilder comAssunto(String assunto) {
		this.assunto = assunto;
		return this;
	}
	
	public ChamadoBuilder comDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
		return this;
	}
	
	public ChamadoBuilder comDataEncerramento(LocalDateTime dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
		return this;
	}
	
	public ChamadoBuilder comCliente(Cliente cliente) {
		this.cliente = cliente;
		return this;
	}
	
	public ChamadoBuilder comEventos(Set<EventoChamado> eventos) {
		this.eventos = eventos;
		return this;
	}
	
	public ChamadoBuilder comProtocolo(Protocolo protocolo) {
		this.protocolo = protocolo;
		return this;
	}
	
	public ChamadoBuilder comEventoDeAbertura(Atendente atendente, String descricaoProblema) {
		if (eventos == null) {
			eventos = new HashSet<>();
		}
		EventoChamado eventoAbertura = new EventoChamado(
				LocalDateTime.now(), 
				descricaoProblema, 
				atendente, 
				TipoEvento.ABERTURA);
		((HashSet<EventoChamado>) eventos).add(eventoAbertura);
		return this;
	}
	
	public Chamado build() {
		Chamado chamado = new Chamado(protocolo, dataAbertura, dataEncerramento, assunto, cliente, eventos);
		return chamado;
	}

}
