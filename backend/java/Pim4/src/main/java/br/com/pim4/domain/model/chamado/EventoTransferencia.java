package br.com.pim4.domain.model.chamado;

import java.time.LocalDateTime;

import br.com.pim4.domain.model.pessoa.Atendente;

public class EventoTransferencia extends EventoChamado {

    public EventoTransferencia(Atendente novoResponsavel, String descricao) {
        super.dataEvento = LocalDateTime.now();
        super.responsavel = novoResponsavel;
        super.descricao = descricao;
    }

}