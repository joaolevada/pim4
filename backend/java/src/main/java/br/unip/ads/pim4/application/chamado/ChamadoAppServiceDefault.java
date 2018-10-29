package br.unip.ads.pim4.application.chamado;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.ads.pim4.application.AbstractAppService;
import br.unip.ads.pim4.application.chamado.dto.AtualizaChamadoDto;
import br.unip.ads.pim4.application.chamado.dto.ChamadoResumoDto;
import br.unip.ads.pim4.application.chamado.dto.NovoChamadoDto;
import br.unip.ads.pim4.application.chamado.dto.assembly.ChamadoDtoAssembly;
import br.unip.ads.pim4.domain.model.Atendente;
import br.unip.ads.pim4.domain.model.Cliente;
import br.unip.ads.pim4.domain.model.Id;
import br.unip.ads.pim4.domain.model.chamado.Chamado;
import br.unip.ads.pim4.domain.model.chamado.Protocolo;
import br.unip.ads.pim4.domain.model.chamado.builder.ChamadoBuilder;
import br.unip.ads.pim4.domain.model.chamado.evento.EventoChamado;
import br.unip.ads.pim4.domain.model.chamado.evento.TipoEvento;
import br.unip.ads.pim4.repository.AtendenteRepository;
import br.unip.ads.pim4.repository.ChamadoRepository;
import br.unip.ads.pim4.repository.ClienteRepository;

@Service
public class ChamadoAppServiceDefault extends AbstractAppService implements ChamadoAppService {
	
	@Autowired
	private ChamadoRepository chamadoRepo;
	@Autowired
	private AtendenteRepository atendenteRepo;
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Override
	public String criar(NovoChamadoDto novoChamado) {
		/* TODO lancar excecao se o Atendente não for encontrado. */
		Atendente atendente = atendenteRepo.findById(new Id(novoChamado.getIdAtendente())).get();
		/* TODO lançar exceção se o Cliente não for encontrado. */
		Cliente cliente = clienteRepo.findById(new Id(novoChamado.getIdCliente())).get();
		Protocolo novoProtocolo = new Protocolo(Protocolo.proximo());		
		ChamadoBuilder builder = new ChamadoBuilder()
				.comAssunto(novoChamado.getAssunto())
				.comDataAbertura(LocalDateTime.now())
				.comCliente(cliente)		
				.comEventoDeAbertura(atendente, novoChamado.getDescricaoProblema())
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
		ChamadoResumoDto chamadoResumido = ChamadoDtoAssembly.toDto(chamadoCompleto);
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
		Iterable<ChamadoResumoDto> chamadosResumidos = ChamadoDtoAssembly.toDtoList(todosChamadosCompletos);
		return chamadosResumidos;
	}

	@Override
	public void atualizarChamado(AtualizaChamadoDto dto) {
		// TODO se não encontrar o chamado, lançar exceção
		Chamado chamado = chamadoRepo.findByProtocolo(new Protocolo(dto.getProtocolo())).get();
		Set<EventoChamado> eventos = chamado.getEventos();
		LocalDateTime data = null;
		Atendente atendenteDoUltimoEvento = null;
		for (EventoChamado e : eventos) {
			
			if (data == null || data.isBefore(e.getData())) {
				data = e.getData();
				atendenteDoUltimoEvento = e.getAtendente();
			}
			
		}
		EventoChamado eventoAtualizacao = new EventoChamado(
				LocalDateTime.now(), 
				dto.getDescricao(), 
				atendenteDoUltimoEvento, 
				TipoEvento.ATUALIZACAO);
		eventos.add(eventoAtualizacao);
		chamado.setEventos(eventos);
		// TODO tratar exceção
		chamadoRepo.save(chamado);
	}

}
