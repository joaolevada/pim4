package br.com.pim4.domain.model.chamado;

import java.util.ArrayList;
import java.util.List;

public class Chamado {
	
	private final Protocolo protocolo;
	private final String assunto;
	private List<EventoChamado> eventos;

	// public Chamado() {}
	
	public Chamado(Protocolo protocolo, String assunto) {
		this.protocolo = protocolo;
		this.assunto = assunto;
		eventos = new ArrayList<>();
	}	

	/*
	*  Cria um chamado com o evento de abertura
	*/
	public static Chamado abrirChamado(String assunto, String descricaoProblema) {
		Protocolo novoProtocolo = new Protocolo(Protocolo.proximoId());
		Chamado novoChamado = new Chamado(novoProtocolo, assunto);		
		novoChamado.eventos.add(new EventoAbertura(descricaoProblema));
		return novoChamado;
	}

    private EventoChamado abertura() {
        for (EventoChamado e : eventos) {
            if (e.getClass() == EventoAbertura) {
                return e;
            }
        }
        return null;
	}
	
	private EventoChamado encerramento() {
		for (EventoChamado e : eventos ) {
			if (e.getClass() == EventoEncerramento) {
				return e;
			}
		}
		return null;
	}

    /*
    * Obtem a descricao do problema a partir do evento de abertura do chamado.
    */
    public String descricaoDoProblema() {
        return abertura().getDescricao();
	}
	
	/*
	* Obtem a data de abertura do chamado a partir da data de seu evento de abertura.
	*/
	public LocalDateTime dataAbertura() {
		return abertura().getDataEvento();
	}

	/*
	* Obtem a data de abertura de encerramento a partir da data de seu evento de encerramento.
	*/
	public String dataEncerramento() {
		return encerramento().getDataEvento();
	}

	/*
	* Retorna verdadeiro se o houver evento de encerramento para o chamado.
	*/
	public boolean encerrado() {
		return encerramento() != null;
	}


	
	public Atendente abertoPor() {
		return abertura().getResponsavel();
	}

}
