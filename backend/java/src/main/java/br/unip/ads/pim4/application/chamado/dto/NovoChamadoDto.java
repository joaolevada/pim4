package br.unip.ads.pim4.application.chamado.dto;

public class NovoChamadoDto {

	private String idCliente;
	private String assunto;
	private String idAtendente;
	private String descricaoProblema;
	
	public NovoChamadoDto() {
		// REST
	}
	
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	
	public String getIdAtendente() {
		return idAtendente;
	}
	public void setIdAtendente(String idAtendente) {
		this.idAtendente = idAtendente;
	}

	public String getDescricaoProblema() {
		return descricaoProblema;
	}

	public void setDescricaoProblema(String descricaoProblema) {
		this.descricaoProblema = descricaoProblema;
	}	
	
}
