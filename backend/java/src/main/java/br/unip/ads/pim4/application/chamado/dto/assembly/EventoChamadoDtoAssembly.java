package br.unip.ads.pim4.application.chamado.dto.assembly;

import java.util.ArrayList;

import br.unip.ads.pim4.application.atendente.dto.AtendenteResumoDto;
import br.unip.ads.pim4.application.atendente.dto.assembly.AtendenteDtoAssembler;
import br.unip.ads.pim4.application.chamado.dto.EventoChamadoDto;
import br.unip.ads.pim4.domain.model.Atendente;
import br.unip.ads.pim4.domain.model.chamado.evento.EventoChamado;

public class EventoChamadoDtoAssembly {	

	public static EventoChamadoDto toDto(EventoChamado eventoChamadoCompleto) {
		/* TODO Tratar casos onde o Cliente ou o Atendente não estejam mais disponíveis */
		Atendente atendenteCompleto = eventoChamadoCompleto.getAtendente();
		AtendenteResumoDto atendenteDto = AtendenteDtoAssembler.toDto(atendenteCompleto);
		EventoChamadoDto eventoChamadoDto = new EventoChamadoDto(eventoChamadoCompleto.getData(),
				eventoChamadoCompleto.getDescricao(), atendenteDto, eventoChamadoCompleto.getTipo().getDescricao());
		return eventoChamadoDto;
	}
	
	public static Iterable<EventoChamadoDto> toDtoList(Iterable<EventoChamado> eventosChamadoCompletos) {
		ArrayList<EventoChamadoDto> dtoList = new ArrayList<>();
		for (EventoChamado eventoCompleto : eventosChamadoCompletos) {
			EventoChamadoDto eventoDto = toDto(eventoCompleto);
			dtoList.add(eventoDto);
		}
		return dtoList;
	}

}
