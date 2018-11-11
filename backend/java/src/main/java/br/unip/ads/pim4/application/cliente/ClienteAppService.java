package br.unip.ads.pim4.application.cliente;

import br.unip.ads.pim4.application.cliente.dto.AtualizaClienteDto;
import br.unip.ads.pim4.application.cliente.dto.ClienteResumoDto;
import br.unip.ads.pim4.application.cliente.dto.NovoClienteDto;
import br.unip.ads.pim4.domain.DomainException;

public interface ClienteAppService {
	
	public String criar(NovoClienteDto novoCliente) throws DomainException;
	
	public ClienteResumoDto buscar(String id);
	
	public void atualizar(String id, AtualizaClienteDto dadosAtualizados) throws DomainException;
	
	public void excluir(String id);
	
	public Iterable<ClienteResumoDto> buscarTodos();

}
