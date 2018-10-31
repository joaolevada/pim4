package br.unip.ads.pim4.application.chamado;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.ads.pim4.application.AbstractAppService;
import br.unip.ads.pim4.application.chamado.dto.AtualizaChamadoDto;
import br.unip.ads.pim4.application.chamado.dto.ChamadoResumoDto;
import br.unip.ads.pim4.application.chamado.dto.EncerraChamadoDto;
import br.unip.ads.pim4.application.chamado.dto.NovoChamadoDto;
import br.unip.ads.pim4.application.chamado.dto.TransfereChamadoDto;
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
		/* TODO lancar excecao se o Atendente n�o for encontrado. */
		Atendente atendente = atendenteRepo.findById(new Id(novoChamado.getIdAtendente())).get();
		/* TODO lan�ar exce��o se o Cliente n�o for encontrado. */
		Cliente cliente = clienteRepo.findById(new Id(novoChamado.getIdCliente())).get();
		Protocolo novoProtocolo = new Protocolo(Protocolo.proximo());		
		ChamadoBuilder builder = new ChamadoBuilder()
				.comAssunto(novoChamado.getAssunto())
				.comDataAbertura(LocalDateTime.now())
				.comCliente(cliente)		
				.comEventoDeAbertura(atendente, novoChamado.getDescricaoProblema())
				.comProtocolo(novoProtocolo);				
		Chamado chamadoAberto = builder.build();
		// TODO Tratar exce��es
		chamadoRepo.save(chamadoAberto);
		return chamadoAberto.getProtocolo().toString();
	}

	@Override
	public ChamadoResumoDto buscar(String protocolo) {
		Protocolo protocoloBuscado = new Protocolo(protocolo);
		// TODO Tratar exce��es
		Chamado chamadoCompleto = chamadoRepo.findByProtocolo(protocoloBuscado).get();		
		ChamadoResumoDto chamadoResumido = ChamadoDtoAssembly.toDto(chamadoCompleto);
		return chamadoResumido;
	}
	

	@Override
	public void excluir(String protocolo) {
		Protocolo protocoloBuscado = new Protocolo(protocolo);
		// TODO Tratar exce��es
		Chamado chamadoCompleto = chamadoRepo.findByProtocolo(protocoloBuscado).get();
		// TODO Considerar inconsist�ncias que pode ocorrer ap�s a exclus�o de um chamado.
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
		// TODO se n�o encontrar o chamado, lan�ar exce��o
		Chamado chamado = chamadoRepo.findByProtocolo(new Protocolo(dto.getProtocolo())).get();				
		EventoChamado eventoAtualizacao = new EventoChamado(
				LocalDateTime.now(), 
				dto.getDescricao(), 
				chamado.responsavel(), 
				TipoEvento.ATUALIZACAO);
		chamado.getEventos().add(eventoAtualizacao);		
		// TODO tratar exceção
		chamadoRepo.save(chamado);
	}

	@Override
	public void transferirChamado(TransfereChamadoDto dto) {
		// TODO se não encontrar Chamado, levantar exceção
		Chamado chamado = chamadoRepo.findByProtocolo(new Protocolo(dto.getProtocolo())).get();		
		// Criar o evento de transferencia
		// TODO Se não encontrar Atendente, levantar exceção		
		Atendente atendente = atendenteRepo.findById(new Id(dto.getAtendenteId())).get();		
		EventoChamado eventoTransferencia = new EventoChamado(LocalDateTime.now(), dto.getDescricao(), atendente, TipoEvento.TRANSFERENCIA);
		chamado.getEventos().add(eventoTransferencia);		
		// TODO tratar exceção
		chamadoRepo.save(chamado);
	}

	@Override
	public void encerrarChamado(EncerraChamadoDto dto) {
		// TODO se não encontrar chamado, levantar exceção
		Chamado chamado = chamadoRepo.findByProtocolo(new Protocolo(dto.getProtocolo())).get();		
		// Criar o evento de encerramento
		LocalDateTime encerradoEm = LocalDateTime.now();
		EventoChamado eventoEncerramento = new EventoChamado(
				encerradoEm,
				dto.getDescricao(),
				chamado.responsavel(),
				TipoEvento.ENCERRAMENTO
				);
		chamado.getEventos().add(eventoEncerramento);
		// Ajustar o atributo que indica o atendimento encerrado	
		chamado.setDataEncerramento(encerradoEm);
		chamadoRepo.save(chamado);		
	}

}
