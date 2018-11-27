package br.unip.ads.pim4.domain.model.chamado;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;

import br.unip.ads.pim4.domain.DomainException;
import br.unip.ads.pim4.domain.model.Atendente;
import br.unip.ads.pim4.domain.model.Cliente;
import br.unip.ads.pim4.domain.model.chamado.evento.EventoChamado;
import br.unip.ads.pim4.domain.model.chamado.evento.TipoEvento;

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

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER, optional = false)
	private Cliente cliente;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER, optional = false)
	private Atendente responsavel;

	@ElementCollection(fetch = FetchType.EAGER)
	@OrderBy("data")
	private SortedSet<EventoChamado> eventos = new TreeSet<>();

	public Chamado() {
		// Persistence
	}

	public Chamado(Protocolo protocolo, String assunto, Cliente cliente) {
		super();
		this.protocolo = protocolo;
		this.assunto = assunto;
		this.cliente = cliente;
	}	

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}	

	public Optional<LocalDateTime> getDataEncerramento() {
		return Optional.ofNullable(dataEncerramento);
	}	

	public String getAssunto() {
		return assunto;
	}

	public Cliente getCliente() {
		return cliente;
	}	

	public Set<EventoChamado> getEventos() {
		return eventos;
	}	

	public Protocolo getProtocolo() {
		return protocolo;
	}	

	public Atendente getResponsavel() {
		return responsavel;
	}	

	public boolean isEncerrado() {
		return getDataEncerramento().isPresent();
	}

	public void abrir(Atendente atendente, Cliente cliente, String assunto, String descricao) throws DomainException {
		if (isEncerrado()) {
			throw new DomainException("Chamado já encerrado.");
		}
		
		LocalDateTime abertoEm = LocalDateTime.now();
		
		protocolo = Protocolo.proximo();
		responsavel = atendente;
		dataAbertura = abertoEm;
		this.cliente = cliente;
		this.assunto = assunto;		
		EventoChamado eventoAbertura = new EventoChamado(abertoEm, descricao, atendente, TipoEvento.ABERTURA);
		getEventos().add(eventoAbertura);		 
				
	}

	public void atualizar(String descricao) throws DomainException {
		if (isEncerrado()) {
			throw new DomainException("Chamado encerrado não pode receber atualizações.");
		}
		EventoChamado eventoAtualizacao = new EventoChamado(LocalDateTime.now(), descricao, this.getResponsavel(),
				TipoEvento.ATUALIZACAO);
		this.getEventos().add(eventoAtualizacao);
	}

	public void transferir(Atendente novoAtendente, String descricao) throws DomainException {
		if (isEncerrado()) {
			throw new DomainException("Chamado encerrado não pode ser transferido.");
		}
		EventoChamado eventoTransferencia = new EventoChamado(LocalDateTime.now(), descricao, novoAtendente,
				TipoEvento.TRANSFERENCIA);
		this.getEventos().add(eventoTransferencia);
		responsavel = novoAtendente;
	}

	public void encerrar(String descricao) throws DomainException {
		if (isEncerrado()) {
			throw new DomainException("O chamado já foi encerrado.");
		}
		LocalDateTime dataEncerramento = LocalDateTime.now();
		EventoChamado eventoEncerramento = new EventoChamado(dataEncerramento, descricao, this.getResponsavel(),
				TipoEvento.ENCERRAMENTO);
		this.dataEncerramento = dataEncerramento;
		this.getEventos().add(eventoEncerramento);
	}

}
