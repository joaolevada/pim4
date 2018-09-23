package br.com.pim4.domain.model.chamado;

import java.time.LocalDateTime;

import br.com.pim4.domain.model.pessoa.Atendente;

public class EventoAbertura extends EventoChamado {

    public EventoAbertura(String descricao, Atendente responsavel) {
        super.dataEvento = LocalDateTime.now();
        super.descricao = descricao;
        super.responsavel = responsavel;
    }

}