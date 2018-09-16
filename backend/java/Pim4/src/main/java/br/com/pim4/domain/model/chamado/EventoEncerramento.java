package br.com.pim4.domain.model.chamado;

public class EventoEncerramento extends EventoChamado {

    public EventoEncerramento(String descricao) {
        super.data = LocalDateTime.now();
        super.descricao = descricao;
    }

}