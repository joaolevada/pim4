package br.unip.ads.pim4.application.chamado.dto;

import java.time.LocalDateTime;

import br.unip.ads.pim4.application.cliente.dto.ClienteResumoDto;

public class ChamadoResumoDto {
	
	private String protocolo;	
	private LocalDateTime dataAbertura;	
	private LocalDateTime dataEncerramento;
	private String assunto;
	private ClienteResumoDto cliente;
	private Iterable<EventoChamadoDto> eventos;
	
	public ChamadoResumoDto() {
		// REST
	}

	public ChamadoResumoDto(String protocolo, LocalDateTime dataAbertura, LocalDateTime dataEncerramento,
			String assunto, ClienteResumoDto cliente, Iterable<EventoChamadoDto> eventos) {
		super();
		this.protocolo = protocolo;
		this.dataAbertura = dataAbertura;
		this.dataEncerramento = dataEncerramento;
		this.assunto = assunto;
		this.cliente = cliente;
		this.eventos = eventos;
	}

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
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

	public ClienteResumoDto getCliente() {
		return cliente;
	}

	public void setCliente(ClienteResumoDto cliente) {
		this.cliente = cliente;
	}

	public Iterable<EventoChamadoDto> getEventos() {
		return eventos;
	}

	public void setEventos(Iterable<EventoChamadoDto> eventos) {
		this.eventos = eventos;
	}	
	
	

}
