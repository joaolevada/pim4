package br.unip.ads.pim4.domain.model.chamado;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Protocolo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false, updatable = false, length = 36)
	private String numero;
	
	public Protocolo() {
		// Persistence
	}
	
	public Protocolo(String numero) {
		this.numero = numero;
	}
	
	public String toString() {
		return this.numero;
	}
	
	public static String proximo() {		
		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String agoraString = agora.format(formatter);
		String aleatoria = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
		return agoraString + aleatoria; 
	}
	
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

}
