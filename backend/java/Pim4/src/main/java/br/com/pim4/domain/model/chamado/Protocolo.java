package br.com.pim4.domain.model.chamado;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Protocolo {
	
	private String protocolo;
	
	public Protocolo(String protocolo) {
		this.protocolo = protocolo;
	}
	
	public static String gerarProtocolo() {		
		
		LocalDateTime agora = LocalDateTime.now();
		String novoProtocolo = "CHD-";
		novoProtocolo.concat(agora.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
		byte[] array = new byte[6]; // length is bounded by 6
	    new Random().nextBytes(array);
	    String randomStr = new String(array, Charset.forName("UTF-8"));
		novoProtocolo.concat(randomStr);
		return novoProtocolo;
		
	}

}
