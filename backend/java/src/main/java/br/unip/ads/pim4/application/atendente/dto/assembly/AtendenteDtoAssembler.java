package br.unip.ads.pim4.application.atendente.dto.assembly;

import java.util.ArrayList;

import br.unip.ads.pim4.application.atendente.dto.AtendenteResumoDto;
import br.unip.ads.pim4.domain.model.Atendente;

public class AtendenteDtoAssembler {
	
	public static AtendenteResumoDto toDto(Atendente atendenteCompleto) {
		AtendenteResumoDto atendenteResumido = new AtendenteResumoDto(atendenteCompleto.getId().asString(), atendenteCompleto.getPessoa().getNome(), atendenteCompleto.getPessoa().getEmail().asString());
		return atendenteResumido;
	}
	
	public static Iterable<AtendenteResumoDto> toResumoDtoList(Iterable<Atendente> atendentes) {
		
		ArrayList<AtendenteResumoDto> atendentesDto = new ArrayList<>();
		
		for (Atendente atendenteCompleto : atendentes) {
			AtendenteResumoDto atendenteResumido = toDto(atendenteCompleto); 
			atendentesDto.add(atendenteResumido);
		}
		
		return atendentesDto;
		
	}	

}
