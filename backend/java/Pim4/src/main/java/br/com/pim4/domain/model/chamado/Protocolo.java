package br.com.pim4.domain.model.chamado;

import java.time.LocalDateTime;

public class Protocolo {
	
	private String protocolo;
	
	public Protocolo(String protocolo) {
		this.protocolo = protocolo;
	}
	
	public String gerarProtocolo() throws Exception {
		
		// todo Gerar um numero de protocolo usando o formato
		// aaaammdd-seiscaracteresaleatorios
		
		throw new Exception("Ainda não implementado!");
		
		// LocalDateTime agora = LocalDateTime.now();
		// String novoProtocolo = agora.format()
		// return novoProtocolo;
	}

}
