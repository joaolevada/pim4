package br.unip.ads.pim4.application.chamado;

import br.unip.ads.pim4.application.chamado.dto.ChamadoResumoDto;
import br.unip.ads.pim4.application.chamado.dto.NovoChamadoDto;

public interface ChamadoAppService {
	
	public String criar(NovoChamadoDto novoChamado);
	
	public ChamadoResumoDto buscar(String protocolo);	
	
	public void excluir(String protocolo);
	
	public Iterable<ChamadoResumoDto> buscarTodos();

}
