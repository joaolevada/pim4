package br.unip.ads.pim4.domain.model;

import javax.persistence.Embeddable;
import javax.persistence.Column;

@Embeddable
public class EMail {

	@Column(length = 255)
	private String endereco;
	
	public EMail() {
		// Persistence
	}
	
	public EMail(String endereco) {
		this.endereco = endereco;
	}
	
	public String asString() {
		return this.endereco;
	}
	
	public Boolean isValid() {
		/* TODO implementar valida��o do endere�o de e-mail. A verifica��o deve ser o mais simples 
		 * poss�vel. N�o usar verifica��es complexas com horr�veis, e imposs�veis de manter, express�es 
		 * regulares.
		 * Tamb�m podemos terceirizar esta responsabilidade para alguma biblioteca.
		 * Se um email v�lido for realmente importante para aplica��o, usar valida��o de email por link.
		 */
		return true;		
	}
}
