package br.unip.ads.pim4.domain.model.chamado.evento;

import br.unip.ads.pim4.domain.model.Id;

public class EventoAbertura extends EventoChamado {
	
	public EventoAbertura() {
		// Persistence
	}
	
	public EventoAbertura(Id idAtendente, String descricao) {
		setIdAtendente(idAtendente);
		setDescricao(descricao);
	}

}
