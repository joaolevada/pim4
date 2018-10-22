package br.unip.ads.pim4.application.cliente;

import br.unip.ads.pim4.application.cliente.dto.AtualizaClienteDto;
import br.unip.ads.pim4.application.cliente.dto.ClienteResumoDto;
import br.unip.ads.pim4.application.cliente.dto.NovoClienteDto;

public interface ClienteAppService {
	
	public String criar(NovoClienteDto novoCliente);
	
	public ClienteResumoDto buscar(String id);
	
	public void atualizar(String id, AtualizaClienteDto dadosAtualizados);
	
	public void excluir(String id);
	
	public Iterable<ClienteResumoDto> buscarTodos();

}
