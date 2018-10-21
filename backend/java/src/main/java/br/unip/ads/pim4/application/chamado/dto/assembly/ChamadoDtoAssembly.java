package br.unip.ads.pim4.application.chamado.dto.assembly;

import java.util.ArrayList;

import br.unip.ads.pim4.application.chamado.dto.ChamadoResumoDto;
import br.unip.ads.pim4.application.chamado.dto.EventoChamadoDto;
import br.unip.ads.pim4.application.cliente.dto.ClienteResumoDto;
import br.unip.ads.pim4.application.cliente.dto.assembly.ClienteDtoAssembler;
import br.unip.ads.pim4.domain.model.Cliente;
import br.unip.ads.pim4.domain.model.chamado.Chamado;
import br.unip.ads.pim4.repository.AtendenteRepository;
import br.unip.ads.pim4.repository.ClienteRepository;

public class ChamadoDtoAssembly {
	
	private final AtendenteRepository atendenteRepo;
	private final ClienteRepository clienteRepo;	

	public ChamadoDtoAssembly(AtendenteRepository atendenteRepo, ClienteRepository clienteRepo) {
		super();
		this.atendenteRepo = atendenteRepo;
		this.clienteRepo = clienteRepo;
	}

	public ChamadoResumoDto toDto(Chamado chamadoCompleto) {
		// TODO Tratar caso onde o cliente não foi mais encontrado. Criar um "fantasma" 
		Cliente cliente = clienteRepo.findById(chamadoCompleto.getIdCliente()).get();
		ClienteResumoDto clienteDto = ClienteDtoAssembler.toDto(cliente);
		EventoChamadoDtoAssembly eventoDtoAsm = new EventoChamadoDtoAssembly(atendenteRepo);
		Iterable<EventoChamadoDto> eventosDto = eventoDtoAsm.toDtoList(chamadoCompleto.getEventos());		
		return new ChamadoResumoDto(chamadoCompleto.getProtocolo().toString(), chamadoCompleto.getDataAbertura(),
				chamadoCompleto.getDataEncerramento(), chamadoCompleto.getAssunto(), clienteDto, eventosDto);
	}
	
	public Iterable<ChamadoResumoDto> toDtoList(Iterable<Chamado> chamadosCompletos) {
		ArrayList<ChamadoResumoDto> dtoList = new ArrayList<>();
		for (Chamado chamadoCompleto : chamadosCompletos) {
			dtoList.add(toDto(chamadoCompleto));
		}
		return dtoList;
	}

}
