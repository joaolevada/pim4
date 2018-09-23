package br.com.pim4.domain.model.chamado;

import java.time.LocalDateTime;

public class EventoAtualizacao extends EventoChamado {

    public EventoAtualizacao(String descricao) {
        super.dataEvento = LocalDateTime.now();
        super.descricao = descricao;
    }
    
}