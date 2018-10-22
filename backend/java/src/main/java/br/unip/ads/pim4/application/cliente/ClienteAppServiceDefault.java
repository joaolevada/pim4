package br.unip.ads.pim4.application.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.ads.pim4.application.AbstractAppService;
import br.unip.ads.pim4.application.cliente.dto.AtualizaClienteDto;
import br.unip.ads.pim4.application.cliente.dto.ClienteResumoDto;
import br.unip.ads.pim4.application.cliente.dto.NovoClienteDto;
import br.unip.ads.pim4.application.cliente.dto.assembly.ClienteDtoAssembler;
import br.unip.ads.pim4.domain.model.Cliente;
import br.unip.ads.pim4.domain.model.Cpf;
import br.unip.ads.pim4.domain.model.EMail;
import br.unip.ads.pim4.domain.model.Id;
import br.unip.ads.pim4.domain.model.Pessoa;
import br.unip.ads.pim4.repository.ClienteRepository;

@Service
public class ClienteAppServiceDefault extends AbstractAppService implements ClienteAppService {
	
	@Autowired
	private ClienteRepository clienteRepo;

	public void atualizarCliente(Cliente clienteParaAtualizar, AtualizaClienteDto dto) {
		
		/*
		 * TODO Considerar validações de cpf e email antes de atualizar os dados do Cliente.
		 */
	
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
		Cliente novoCliente = new Cliente(novoId, novaPessoa);
		
		return novoCliente;
		
	}

	@Override
	public String criar(NovoClienteDto novoCliente) {
		Id proximoId = new Id(Id.proximo());
		Cpf novoCpf = new Cpf(novoCliente.getCpf());
		EMail novoEMail = new EMail(novoCliente.getEmail());
		Pessoa novaPessoa = new Pessoa(novoCliente.getNome(), novoCpf, novoEMail);
		Cliente clientePersist = new Cliente(proximoId, novaPessoa);
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
	public void atualizar(String id, AtualizaClienteDto dadosAtualizados) {
		Id idProcurado = new Id(id);
		Cliente clientePersist = clienteRepo.findById(idProcurado).get();
		Cpf novoCpf = new Cpf(dadosAtualizados.getCpf());
		EMail novoEmail = new EMail(dadosAtualizados.getEmail());
		Pessoa novaPessoa = new Pessoa(dadosAtualizados.getNome(), novoCpf, novoEmail);
		clientePersist.setPessoa(novaPessoa);
		/*
		 * TODO Aplicar regras de validação. Inclusive considerando a possível duplicidade de 
		 * EMail.		
		 */
		clienteRepo.save(clientePersist);		
	}

	@Override
	public void excluir(String id) {
		Id idProcurado = new Id(id);
		Cliente clientePersist = clienteRepo.findById(idProcurado).get();
		/*
		 * TODO Considerar consistência dos dados. Como ficam os chamados de um cliente excluído?
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
