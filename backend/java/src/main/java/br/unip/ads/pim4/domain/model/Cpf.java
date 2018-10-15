package br.unip.ads.pim4.domain.model;

import javax.persistence.Embeddable;
import javax.persistence.Column;

@Embeddable
public class Cpf {
	
	@Column(length = 12)
	private String numero;
	
	public Cpf() {
		// persistence
	}
	
	public Cpf(String numero) {
		this.numero = numero;
	}
	
	public String toString() {
	  return this.numero;
	}
	
	public Boolean isValido() {
		/* TODO implementar validacao do CPF. Recomendo delegarmos esta inteligencia para alguma
		 * biblioteca pública e disponível no repositório Maven. Não deve ser difícil de encontrar. 
		*/
		return true;
	}
	
	public static String gerarCpf() {
		
		/* TODO Implementar a geração de CPFs válidos aleatoriamente.
		 * 
		 */
		
		return "30086440071";
		
	}
	
}
