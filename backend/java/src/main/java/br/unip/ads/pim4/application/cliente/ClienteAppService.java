package br.unip.ads.pim4.application.cliente;

import org.springframework.stereotype.Service;

import br.unip.ads.pim4.application.AbstractAppService;
import br.unip.ads.pim4.domain.model.Cliente;
import br.unip.ads.pim4.domain.model.Cpf;
import br.unip.ads.pim4.domain.model.EMail;
import br.unip.ads.pim4.domain.model.Id;
import br.unip.ads.pim4.domain.model.Pessoa;

@Service
public class ClienteAppService extends AbstractAppService {
	
	public Cliente criarCliente(NovoClienteDto dto) {
		
		Id novoId = new Id(Id.proximo());
		EMail novoEmail = new EMail(dto.getEmail());
		Cpf novoCpf = new Cpf(dto.getCpf());
		Pessoa novaPessoa = new Pessoa(dto.getNome(), novoCpf, novoEmail);
		Cliente novoCliente = new Cliente(novoId, novaPessoa);
		
		return novoCliente;
		
	}
	
	public void atualizarCliente(Cliente clienteParaAtualizar, AtualizaClienteDto dto) {
		
		/*
		 * TODO Considerar validações de cpf e email antes de atualizar os dados do Cliente.
		 */

		Cpf novoCpf = new Cpf(dto.getCpf());
		EMail novoEmail = new EMail(dto.getEmail());
		Pessoa novaPessoa = new Pessoa(dto.getNome(), novoCpf, novoEmail);
		clienteParaAtualizar.setPessoa(novaPessoa);
		
	}

}
