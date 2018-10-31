package br.unip.ads.pim4.application.chamado.dto;

public class TransfereChamadoDto {
	
	private String protocolo;
	private String atendenteId;
	private String descricao;
	
	public TransfereChamadoDto() {
		// REST
	}

	public TransfereChamadoDto(String protocolo, String atendenteId, String descricao) {
		super();
		this.protocolo = protocolo;
		this.atendenteId = atendenteId;
		this.descricao = descricao;
	}

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	public String getAtendenteId() {
		return atendenteId;
	}

	public void setAtendenteId(String atendenteId) {
		this.atendenteId = atendenteId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
