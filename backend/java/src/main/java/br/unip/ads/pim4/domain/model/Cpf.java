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
	
	public String asString() {
	  return this.numero;
	}
	
	public Boolean isValido() {
		/* TODO implementar validacao do CPF. Recomendo delegarmos esta inteligencia para alguma
		 * biblioteca p�blica e dispon�vel no reposit�rio Maven. N�o deve ser dif�cil de encontrar. 
		*/
		return true;
	}
	
	public static String gerarCpf() {
		
		/* TODO Implementar a gera��o de CPFs v�lidos aleatoriamente.
		 * 
		 */
		
		return "30086440071";
		
	}
	
}
