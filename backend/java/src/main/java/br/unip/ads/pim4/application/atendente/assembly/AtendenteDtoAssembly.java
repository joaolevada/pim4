package br.unip.ads.pim4.application.atendente.assembly;

import java.util.ArrayList;

import br.unip.ads.pim4.application.atendente.AtendenteResumoDto;
import br.unip.ads.pim4.domain.model.Atendente;

public class AtendenteDtoAssembly {
	
	public static Iterable<AtendenteResumoDto> toResumoDtoList(Iterable<Atendente> atendentes) {
		
		ArrayList<AtendenteResumoDto> atendentesDto = new ArrayList<>();
		
		for (Atendente atendenteCompleto : atendentes) {
			AtendenteResumoDto atendenteResumido = new AtendenteResumoDto(atendenteCompleto.getId().toString(), atendenteCompleto.getPessoa().getNome(), atendenteCompleto.getPessoa().getEmail().toString());
			atendentesDto.add(atendenteResumido);
		}
		
		return atendentesDto;
		
	}

}
