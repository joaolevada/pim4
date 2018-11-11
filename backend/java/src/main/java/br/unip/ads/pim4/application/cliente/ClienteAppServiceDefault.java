package br.unip.ads.pim4.application.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.ads.pim4.application.AbstractAppService;
import br.unip.ads.pim4.application.cliente.dto.AtualizaClienteDto;
import br.unip.ads.pim4.application.cliente.dto.ClienteResumoDto;
import br.unip.ads.pim4.application.cliente.dto.NovoClienteDto;
import br.unip.ads.pim4.application.cliente.dto.assembly.ClienteDtoAssembler;
import br.unip.ads.pim4.domain.DomainException;
import br.unip.ads.pim4.domain.model.Cliente;
import br.unip.ads.pim4.domain.model.Cpf;
import br.unip.ads.pim4.domain.model.EMail;
import br.unip.ads.pim4.domain.model.Id;
import br.unip.ads.pim4.domain.model.Pessoa;
import br.unip.ads.pim4.domain.model.TelefoneFixo;
import br.unip.ads.pim4.domain.model.TelefoneMovel;
import br.unip.ads.pim4.repository.ClienteRepository;

@Service
public class ClienteAppServiceDefault extends AbstractAppService implements ClienteAppService {

	private String VALIDACAO_TELEFONE = "O cliente precisa ter, pelo menos, um telefone para contato.";
	private String VALIDACAO_CPF = "Já existe um cliente cadastrado com este CPF.";
	private String VALIDACAO_EMAIL = "Já existe um cliente cadastrado com este e-mail.";

	@Autowired
	private ClienteRepository clienteRepo;

	public void atualizarCliente(Cliente clienteParaAtualizar, AtualizaClienteDto dto) {

		Cpf novoCpf = new Cpf(dto.getCpf());
		EMail novoEmail = new EMail(dto.getEmail());
		Pessoa novaPessoa = new Pessoa(dto.getNome(), novoCpf, novoEmail);
		clienteParaAtualizar.setPessoa(novaPessoa);

	}

	public Cliente criarCliente(NovoClienteDto dto) {

		Id novoId = new Id(Id.proximo());
		EMail novoEmail = new EMail(dto.getEmail());
		Cpf novoCpf = new Cpf(dto.getCpf());
		Pessoa novaPessoa = new Pessoa(dto.getNome(), novoCpf, novoEmail);
		Cliente novoCliente = new Cliente(novoId, novaPessoa, null, null);

		return novoCliente;

	}

	@Override
	public String criar(NovoClienteDto novoCliente) throws DomainException {
		Id proximoId = new Id(Id.proximo());
		Cpf novoCpf = new Cpf(novoCliente.getCpf());
		EMail novoEMail = new EMail(novoCliente.getEmail());
		Pessoa novaPessoa = new Pessoa(novoCliente.getNome(), novoCpf, novoEMail);
		TelefoneMovel movel = novoCliente.getTelefoneMovel().trim().isEmpty() ? null
				: new TelefoneMovel(novoCliente.getTelefoneMovel());
		TelefoneFixo fixo = novoCliente.getTelefoneFixo().trim().isEmpty() ? null
				: new TelefoneFixo(novoCliente.getTelefoneFixo());
		Cliente clientePersist = new Cliente(proximoId, novaPessoa, movel, fixo);
		if (!clientePersist.temTelefone()) {
			throw new DomainException(VALIDACAO_TELEFONE);
		}

		// Não permitir CPF duplicado
		if (clienteRepo.findByPessoaCpf(novoCpf).isPresent()) {
			throw new DomainException(VALIDACAO_CPF);
		}
		// Não permitir email duplicado
		if (clienteRepo.findByPessoaEmail(novoEMail).isPresent()) {
			throw new DomainException(VALIDACAO_EMAIL);
		}

		clienteRepo.save(clientePersist);
		return clientePersist.getId().toString();
	}

	@Override
	public ClienteResumoDto buscar(String id) {
		Id idProcurado = new Id(id);
		Cliente clienteCompleto = clienteRepo.findById(idProcurado).get();
		ClienteResumoDto clienteResumido = ClienteDtoAssembler.toDto(clienteCompleto);
		return clienteResumido;
	}

	@Override
	public void atualizar(String id, AtualizaClienteDto dadosAtualizados) throws DomainException {
		Id idProcurado = new Id(id);
		Cliente clientePersist = clienteRepo.findById(idProcurado).get();
		Cpf novoCpf = new Cpf(dadosAtualizados.getCpf());
		EMail novoEmail = new EMail(dadosAtualizados.getEmail());
		Pessoa novaPessoa = new Pessoa(dadosAtualizados.getNome(), novoCpf, novoEmail);
		clientePersist.setPessoa(novaPessoa);

		// Não permirtir duplicidade de email
		boolean alterouEmail = !clientePersist.getPessoa().getEmail().asString().equals(dadosAtualizados.getEmail());
		if (alterouEmail) {
			Cliente outroCliente = clienteRepo.findByPessoaEmail(novoEmail).get();
			// Encontrou outro cliente com o mesmo email
			if (!outroCliente.getId().equals(idProcurado)) {
				throw new DomainException(VALIDACAO_EMAIL);
			}
		}

		// Não permirtir duplicidade de email
		boolean alterouCpf = !clientePersist.getPessoa().getCpf().asString().equals(dadosAtualizados.getCpf());
		if (alterouCpf) {
			Cliente outroCliente = clienteRepo.findByPessoaCpf(clientePersist.getPessoa().getCpf()).get();
			// Encontrou outro cliente com o mesmo cpf
			if (!outroCliente.getId().equals(idProcurado)) {
				throw new DomainException(VALIDACAO_CPF);
			}
		}

		clienteRepo.save(clientePersist);
	}

	@Override
	public void excluir(String id) {
		Id idProcurado = new Id(id);
		Cliente clientePersist = clienteRepo.findById(idProcurado).get();
		/*
		 * TODO Considerar consist�ncia dos dados. Como ficam os chamados de um cliente
		 * exclu�do?
		 */
		clienteRepo.delete(clientePersist);
	}

	@Override
	public Iterable<ClienteResumoDto> buscarTodos() {
		Iterable<Cliente> todosClientes = clienteRepo.findAll();
		Iterable<ClienteResumoDto> todosClientesResumido = ClienteDtoAssembler.toDtoList(todosClientes);
		return todosClientesResumido;
	}

}
