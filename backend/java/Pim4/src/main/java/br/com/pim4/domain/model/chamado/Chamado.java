package br.com.pim4.domain.model.chamado;

public class Chamado {
	
	private final Protocolo protocolo;
	private final String assunto;
	private List<EventoChamado> eventos;

	public Chamado() { }

	public Chamado(Protocolo protocolo, String assunto) {
		this.protocolo = protocolo;
		this.assunto = assunto;
		eventos = new ArraList<>();
	}

	/*
	*  Cria um chamado com o evento de abertura
	*/
	public static Chamado abrirChamado(String assunto, String descricaoProblema) {
		Chamado novoChamado = new Chamado(novoProtocolo, assunto);		
		novoChamado.eventos.add(new EventoAbertura(descricaoDoProblema));
		return novoChamado;
	}

    private EventoChamado abertura() {
        for (Evento e : eventos) {
            if (e.class == EventoAbertura) {
                return e;
            }
        }
        return null;
	}
	
	private EventoChamado encerramento() {
		for (Evento e : eventos ) {
			if (e.class == EventoEncerramento) {
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
