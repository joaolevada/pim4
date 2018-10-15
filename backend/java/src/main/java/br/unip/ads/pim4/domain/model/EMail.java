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
	
	public String toString() {
		return this.endereco;
	}
	
	public Boolean isValid() {
		/* TODO implementar validação do endereço de e-mail. A verificação deve ser o mais simples 
		 * possível. Não usar verificações complexas com horríveis, e impossíveis de manter, expressões 
		 * regulares.
		 * Também podemos terceirizar esta responsabilidade para alguma biblioteca.
		 * Se um email válido for realmente importante para aplicação, usar validação de email por link.
		 */
		return true;		
	}
}
