package br.unip.ads.pim4.application.chamado.dto;

public class EncerraChamadoDto {
	
	private String protocolo;
	private String descricao;
	
	public EncerraChamadoDto() {
		// REST
	}

	public EncerraChamadoDto(String protocolo, String atendenteId, String descricao) {
		super();
		this.protocolo = protocolo;
		this.descricao = descricao;
	}

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
