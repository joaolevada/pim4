package br.com.pim4.domain.model.chamado;

import java.time.LocalDateTime;

import br.com.pim4.domain.model.pessoa.Atendente;

public abstract class EventoChamado {

    protected LocalDateTime dataEvento;
    protected String descricao;
    protected Atendente responsavel;
    
	public LocalDateTime getDataEvento() {
		return dataEvento;
	}
	
	public void setDataEvento(LocalDateTime dataEvento) {
		this.dataEvento = dataEvento;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Atendente getResponsavel() {
		return responsavel;
	}
	
	public void setResponsavel(Atendente responsavel) {
		this.responsavel = responsavel;
	}    
    
}
