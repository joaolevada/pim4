package br.unip.ads.pim4.domain.model.chamado.evento;

public enum TipoEvento {
	
	ABERTURA("Abertura"), 
	ATUALIZACAO("Atualização"), 
	TRANSFERENCIA("Transferência"), 
	ENCERRAMENTO("Encerramento");
	
	private final String descricao;
	
	private TipoEvento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}
