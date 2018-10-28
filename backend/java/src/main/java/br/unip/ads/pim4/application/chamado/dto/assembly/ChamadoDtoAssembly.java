package br.unip.ads.pim4.application.chamado.dto.assembly;

import java.util.ArrayList;

import br.unip.ads.pim4.application.chamado.dto.ChamadoResumoDto;
import br.unip.ads.pim4.application.chamado.dto.EventoChamadoDto;
import br.unip.ads.pim4.application.cliente.dto.ClienteResumoDto;
import br.unip.ads.pim4.application.cliente.dto.assembly.ClienteDtoAssembler;
import br.unip.ads.pim4.domain.model.chamado.Chamado;

public class ChamadoDtoAssembly {	

	public static ChamadoResumoDto toDto(Chamado chamadoCompleto) {
		// TODO Tratar caso onde o cliente não foi mais encontrado. Criar um "fantasma"		
		ClienteResumoDto clienteDto = ClienteDtoAssembler.toDto(chamadoCompleto.getCliente());		
		Iterable<EventoChamadoDto> eventosDto = EventoChamadoDtoAssembly.toDtoList(chamadoCompleto.getEventos());		
		return new ChamadoResumoDto(chamadoCompleto.getProtocolo().toString(), chamadoCompleto.getDataAbertura(),
				chamadoCompleto.getDataEncerramento(), chamadoCompleto.getAssunto(), clienteDto, eventosDto);
	}
	
	public static Iterable<ChamadoResumoDto> toDtoList(Iterable<Chamado> chamadosCompletos) {
		ArrayList<ChamadoResumoDto> dtoList = new ArrayList<>();
		for (Chamado chamadoCompleto : chamadosCompletos) {
			dtoList.add(toDto(chamadoCompleto));
		}
		return dtoList;
	}

}
