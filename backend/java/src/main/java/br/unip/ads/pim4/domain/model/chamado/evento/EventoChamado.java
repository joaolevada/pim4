package br.unip.ads.pim4.domain.model.chamado.evento;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

import br.unip.ads.pim4.domain.model.Id;

@Embeddable
@MappedSuperclass
public abstract class EventoChamado {
	
	@Column(nullable = false)
	private LocalDateTime data;
	
	@Column()
	@Lob
	private String descricao;
	
	@Column(nullable = false)
	private Id idAtendente;

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
	
	
}
