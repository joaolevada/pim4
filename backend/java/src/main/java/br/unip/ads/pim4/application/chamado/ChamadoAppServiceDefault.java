package br.unip.ads.pim4.application.chamado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.ads.pim4.application.AbstractAppService;
import br.unip.ads.pim4.application.chamado.dto.AtualizaChamadoDto;
import br.unip.ads.pim4.application.chamado.dto.ChamadoResumoDto;
import br.unip.ads.pim4.application.chamado.dto.EncerraChamadoDto;
import br.unip.ads.pim4.application.chamado.dto.NovoChamadoDto;
import br.unip.ads.pim4.application.chamado.dto.TransfereChamadoDto;
import br.unip.ads.pim4.application.chamado.dto.assembly.ChamadoDtoAssembly;
import br.unip.ads.pim4.domain.DomainException;
import br.unip.ads.pim4.domain.model.Atendente;
import br.unip.ads.pim4.domain.model.Cliente;
import br.unip.ads.pim4.domain.model.Id;
import br.unip.ads.pim4.domain.model.chamado.Chamado;
import br.unip.ads.pim4.domain.model.chamado.Protocolo;
import br.unip.ads.pim4.repository.AtendenteRepository;
import br.unip.ads.pim4.repository.ChamadoRepository;
import br.unip.ads.pim4.repository.ClienteRepository;

@Service
public class ChamadoAppServiceDefault extends AbstractAppService implements ChamadoAppService {

	private String VALIDACAO_ATENDENTE_NAO_ENCONTRADO = "O atendente informado não foi encontrado.";
	private String VALIDACAO_CLIENTE_NAO_ENCONTRADO = "O cliente informado não foi encontrado.";
	private String VALIDACAO_CHAMADO_NAO_ENCONTRADO = "O chamado informado não foi encontrado.";
	private String VALIDACAO_CHAMADO_ENCERRADO_TRA = "Chamado encerrado não pode ser transferido.";
	private String VALIDACAO_CHAMADO_ENCERRADO_ATU = "Chamado encerrado não pode receber atualizações.";
	private String VALIDACAO_CHAMADO_ENCERRADO_ENC = "O chamado já foi encerrado.";

	@Autowired
	private ChamadoRepository chamadoRepo;
	@Autowired
	private AtendenteRepository atendenteRepo;
	@Autowired
	private ClienteRepository clienteRepo;

	@Override
	public String criar(NovoChamadoDto dto) throws DomainException {

		Atendente atendente = atendenteRepo.findById(new Id(dto.getIdAtendente()))
				.orElseThrow(() -> new DomainException(VALIDACAO_ATENDENTE_NAO_ENCONTRADO));

		Cliente cliente = clienteRepo.findById(new Id(dto.getIdCliente()))
				.orElseThrow(() -> new DomainException(VALIDACAO_CLIENTE_NAO_ENCONTRADO));
		
		Chamado chamado = new Chamado();
		chamado.abrir(atendente, cliente, dto.getAssunto(), dto.getDescricaoProblema());
		chamadoRepo.save(chamado);
		
		return chamado.getProtocolo().asString();
	}

	@Override
	public ChamadoResumoDto buscar(String protocolo) {		
		Chamado chamadoCompleto = chamadoRepo.findById(new Protocolo(protocolo)).get();
		ChamadoResumoDto chamadoResumido = ChamadoDtoAssembly.toDto(chamadoCompleto);
		return chamadoResumido;
	}

	@Override
	public void excluir(String protocolo) throws DomainException {
		Protocolo protocoloBuscado = new Protocolo(protocolo);
		Chamado chamadoCompleto = chamadoRepo.findById(protocoloBuscado)
				.orElseThrow(() -> new DomainException(VALIDACAO_CHAMADO_NAO_ENCONTRADO));
		/* TODO: Só permitir exclusão de chamados encerrados? */
		chamadoRepo.delete(chamadoCompleto);
	}

	@Override
	public Iterable<ChamadoResumoDto> buscarTodos() {
		Iterable<Chamado> todosChamadosCompletos = chamadoRepo.findAll();
		Iterable<ChamadoResumoDto> chamadosResumidos = ChamadoDtoAssembly.toDtoList(todosChamadosCompletos);
		return chamadosResumidos;
	}

	@Override
	public void atualizarChamado(String protocolo, AtualizaChamadoDto dto) throws DomainException {

		Chamado chamado = chamadoRepo.findById(new Protocolo(protocolo))
				.orElseThrow(() -> new DomainException(VALIDACAO_CHAMADO_NAO_ENCONTRADO));

		if (chamado.isEncerrado()) {
			throw new DomainException(VALIDACAO_CHAMADO_ENCERRADO_ATU);
		}

		chamado.atualizar(dto.getDescricao());
		chamadoRepo.save(chamado);

	}

	@Override
	public void transferirChamado(String protocolo, TransfereChamadoDto dto) throws DomainException {

		Chamado chamado = chamadoRepo.findById(new Protocolo(protocolo))
				.orElseThrow(() -> new DomainException(VALIDACAO_CHAMADO_NAO_ENCONTRADO));

		Atendente atendente = atendenteRepo.findById(new Id(dto.getAtendenteId()))
				.orElseThrow(() -> new DomainException(VALIDACAO_ATENDENTE_NAO_ENCONTRADO));

		if (chamado.isEncerrado()) {
			throw new DomainException(VALIDACAO_CHAMADO_ENCERRADO_TRA);
		}

		chamado.transferir(atendente, dto.getDescricao());
		chamadoRepo.save(chamado);

	}

	@Override
	public void encerrarChamado(String protocolo, EncerraChamadoDto dto) throws DomainException {

		Chamado chamado = chamadoRepo.findById(new Protocolo(protocolo))
				.orElseThrow(() -> new DomainException(VALIDACAO_CHAMADO_NAO_ENCONTRADO));

		if (chamado.isEncerrado()) {
			throw new DomainException(VALIDACAO_CHAMADO_ENCERRADO_ENC);
		}

		chamado.encerrar(dto.getDescricao());
		chamadoRepo.save(chamado);
	}

	@Override
	public Iterable<ChamadoResumoDto> buscarDoAtendente(String atendenteId) throws DomainException {
		Atendente responsavel = atendenteRepo.findById(new Id(atendenteId))
				.orElseThrow(() -> new DomainException(VALIDACAO_ATENDENTE_NAO_ENCONTRADO));
		Iterable<Chamado> chamadosDoAtendente = chamadoRepo.findByResponsavel(responsavel);
		Iterable<ChamadoResumoDto> dtoList = ChamadoDtoAssembly.toDtoList(chamadosDoAtendente);
		return dtoList;
	}

}
