package br.unip.ads.pim4.application.chamado.dto.assembly;

import java.util.ArrayList;

import br.unip.ads.pim4.application.atendente.dto.AtendenteResumoDto;
import br.unip.ads.pim4.application.atendente.dto.assembly.AtendenteDtoAssembler;
import br.unip.ads.pim4.application.chamado.dto.EventoChamadoDto;
import br.unip.ads.pim4.domain.model.Atendente;
import br.unip.ads.pim4.domain.model.chamado.evento.EventoChamado;
import br.unip.ads.pim4.repository.AtendenteRepository;

public class EventoChamadoDtoAssembly {

	private final AtendenteRepository atendenteRepo;

	public EventoChamadoDtoAssembly(AtendenteRepository atendenteRepo) {
		this.atendenteRepo = atendenteRepo;
	}

	public EventoChamadoDto toDto(EventoChamado eventoChamadoCompleto) {
		// TODO tratar casos onde o atendente não for encontrado. Criar um atendente
		// "fantasma"
		Atendente atendenteCompleto = atendenteRepo.findById(eventoChamadoCompleto.getIdAtendente()).get();
		AtendenteResumoDto atendenteDto = AtendenteDtoAssembler.toDto(atendenteCompleto);
		EventoChamadoDto eventoChamadoDto = new EventoChamadoDto(eventoChamadoCompleto.getData(),
				eventoChamadoCompleto.getDescricao(), atendenteDto);
		return eventoChamadoDto;
	}
	
	public Iterable<EventoChamadoDto> toDtoList(Iterable<EventoChamado> eventosChamadoCompletos) {
		ArrayList<EventoChamadoDto> dtoList = new ArrayList<>();
		for (EventoChamado eventoCompleto : eventosChamadoCompletos) {
			EventoChamadoDto eventoDto = toDto(eventoCompleto);
			dtoList.add(eventoDto);
		}
		return dtoList;
	}

}
