package br.com.pim4.domain.model.chamado;

public class EventoTransferencia extends EventoChamado {

    public EventoTransferencia(Atendente novoResponsavel, String descricao) {
        super.dataEvento = LocalDateTime.now();
        super.responsavel = novoResponsavel;
        super.descricao = descricao;
    }

}