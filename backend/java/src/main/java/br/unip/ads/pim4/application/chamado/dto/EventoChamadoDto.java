package br.unip.ads.pim4.application.chamado.dto;

import java.time.LocalDateTime;

import br.unip.ads.pim4.application.atendente.dto.AtendenteResumoDto;

public class EventoChamadoDto {
	
	private LocalDateTime data;
	private String descricao;
	private AtendenteResumoDto atendente;
	private String tipo;
	
	public EventoChamadoDto() {
		// REST
	}	

	public EventoChamadoDto(LocalDateTime data, String descricao, AtendenteResumoDto atendente, String tipo) {
		super();
		this.data = data;
		this.descricao = descricao;
		this.atendente = atendente;
		this.tipo = tipo;
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

	public AtendenteResumoDto getAtendente() {
		return atendente;
	}

	public void setAtendente(AtendenteResumoDto atendente) {
		this.atendente = atendente;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}	

}
