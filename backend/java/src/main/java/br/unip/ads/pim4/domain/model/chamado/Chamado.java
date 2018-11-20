package br.unip.ads.pim4.domain.model.chamado;

import java.time.LocalDateTime;
import java.util.Objects;
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

	// @OneToMany(cascade= CascadeType.ALL, fetch=FetchType.EAGER,
	// orphanRemoval=true)
	// @JoinColumn(name="protocoloChamado")
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
		return eventos;
	}

	public void setEventos(SortedSet<EventoChamado> eventos) {
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

	public boolean isEncerrado() {
		return !Objects.isNull(getDataEncerramento());
	}

	public void abrir(Atendente atendente, String descricao) throws DomainException {
		if (isEncerrado()) {
			throw new DomainException("Chamado encerrado não pode ser aberto.");
		}
		LocalDateTime abertoEm = LocalDateTime.now();
		setDataAbertura(abertoEm);
		EventoChamado eventoAbertura = new EventoChamado(abertoEm, descricao, atendente, TipoEvento.ABERTURA);
		getEventos().add(eventoAbertura);
	}

	public void atualizar(String descricao) throws DomainException {
		if (isEncerrado()) {
			throw new DomainException("Chamado encerrado não pode receber atualizações.");
		}
		EventoChamado eventoAtualizacao = new EventoChamado(LocalDateTime.now(), descricao, this.responsavel(),
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
	}

	public void encerrar(String descricao) throws DomainException {
		if (isEncerrado()) {
			throw new DomainException("O chamdo já foi encerrado.");
		}
		LocalDateTime dataEncerramento = LocalDateTime.now();
		EventoChamado eventoEncerramento = new EventoChamado(dataEncerramento, descricao, this.responsavel(),
				TipoEvento.ENCERRAMENTO);
		this.setDataEncerramento(dataEncerramento);
		this.getEventos().add(eventoEncerramento);
	}

}
