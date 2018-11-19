package br.unip.ads.pim4.application.chamado.dto;

public class AtualizaChamadoDto {	
	
	private String descricao;
	
	public AtualizaChamadoDto() {
		// REST
	}

	public AtualizaChamadoDto(String descricao) {
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
