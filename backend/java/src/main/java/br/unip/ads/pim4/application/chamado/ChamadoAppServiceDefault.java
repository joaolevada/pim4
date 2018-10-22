package br.unip.ads.pim4.application.chamado;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.ads.pim4.application.AbstractAppService;
import br.unip.ads.pim4.application.chamado.dto.ChamadoResumoDto;
import br.unip.ads.pim4.application.chamado.dto.NovoChamadoDto;
import br.unip.ads.pim4.application.chamado.dto.assembly.ChamadoDtoAssembly;
import br.unip.ads.pim4.domain.model.Id;
import br.unip.ads.pim4.domain.model.chamado.Chamado;
import br.unip.ads.pim4.domain.model.chamado.Protocolo;
import br.unip.ads.pim4.domain.model.chamado.builder.ChamadoBuilder;
import br.unip.ads.pim4.repository.AtendenteRepository;
import br.unip.ads.pim4.repository.ChamadoRepository;
import br.unip.ads.pim4.repository.ClienteRepository;

@Service
public class ChamadoAppServiceDefault extends AbstractAppService implements ChamadoAppService {
	
	@Autowired
	private ChamadoRepository chamadoRepo;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private AtendenteRepository atendenteRepo;

	@Override
	public String criar(NovoChamadoDto novoChamado) {
		Id idAtendente = new Id(novoChamado.getIdAtendente());
		Id idCliente = new Id(novoChamado.getIdCliente());
		Protocolo novoProtocolo = new Protocolo(Protocolo.proximo());		
		ChamadoBuilder builder = new ChamadoBuilder()
				.comAssunto(novoChamado.getAssunto())
				.comDataAbertura(LocalDateTime.now())
				.comIdCliente(idCliente)		
				.comEventoDeAbertura(idAtendente, novoChamado.getDescricaoProblema())
				.comProtocolo(novoProtocolo);				
		Chamado chamadoAberto = builder.build();
		// TODO Tratar exceções
		chamadoRepo.save(chamadoAberto);
		return chamadoAberto.getProtocolo().toString();
	}

	@Override
	public ChamadoResumoDto buscar(String protocolo) {
		Protocolo protocoloBuscado = new Protocolo(protocolo);
		// TODO Tratar exceções
		Chamado chamadoCompleto = chamadoRepo.findByProtocolo(protocoloBuscado).get();
		ChamadoDtoAssembly chamadoDtoAsm = new ChamadoDtoAssembly(atendenteRepo, clienteRepo);
		ChamadoResumoDto chamadoResumido = chamadoDtoAsm.toDto(chamadoCompleto);
		return chamadoResumido;
	}
	

	@Override
	public void excluir(String protocolo) {
		Protocolo protocoloBuscado = new Protocolo(protocolo);
		// TODO Tratar exceções
		Chamado chamadoCompleto = chamadoRepo.findByProtocolo(protocoloBuscado).get();
		// TODO Considerar inconsistências que pode ocorrer após a exclusão de um chamado.
		chamadoRepo.delete(chamadoCompleto);
	}

	@Override
	public Iterable<ChamadoResumoDto> buscarTodos() {
		Iterable<Chamado> todosChamadosCompletos = chamadoRepo.findAll();
		ChamadoDtoAssembly chamadoDtoAsm = new ChamadoDtoAssembly(atendenteRepo, clienteRepo);
		Iterable<ChamadoResumoDto> chamadosResumidos = chamadoDtoAsm.toDtoList(todosChamadosCompletos);
		return chamadosResumidos;
	}

}
