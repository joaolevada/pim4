package br.com.pim4.domain.model.chamado;

import java.time.LocalDateTime;

public class Protocolo {
	
	private String protocolo;
	
	public Protocolo(String protocolo) {
		this.protocolo = protocolo;
	}
	
	public String gerarProtocolo() {
		LocalDateTime agora = LocalDateTime.now();
		String novoProtocolo = agora.format()
		return novoProtocolo;
	}

}
