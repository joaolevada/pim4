package br.com.pim4.domain.model.chamado;

import java.time.LocalDateTime;

public class EventoEncerramento extends EventoChamado {

    public EventoEncerramento(String descricao) {
        super.dataEvento = LocalDateTime.now();
        super.descricao = descricao;
    }

}