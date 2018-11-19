package br.unip.ads.pim4.application.chamado.dto;

public class EncerraChamadoDto {
	
	private String descricao;
	
	public EncerraChamadoDto() {
		// REST
	}

	public EncerraChamadoDto(String descricao) {
		super();		
		this.descricao = descricao;
	}	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
