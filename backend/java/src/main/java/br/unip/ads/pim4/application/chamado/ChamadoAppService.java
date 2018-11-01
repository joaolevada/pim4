package br.unip.ads.pim4.application.chamado;

import br.unip.ads.pim4.application.chamado.dto.AtualizaChamadoDto;
import br.unip.ads.pim4.application.chamado.dto.ChamadoResumoDto;
import br.unip.ads.pim4.application.chamado.dto.EncerraChamadoDto;
import br.unip.ads.pim4.application.chamado.dto.NovoChamadoDto;
import br.unip.ads.pim4.application.chamado.dto.TransfereChamadoDto;

public interface ChamadoAppService {
	
	public String criar(NovoChamadoDto novoChamado);
	
	public ChamadoResumoDto buscar(String protocolo);	
	
	public void excluir(String protocolo);
	
	public Iterable<ChamadoResumoDto> buscarTodos();

	public void atualizarChamado(String protocolo, AtualizaChamadoDto dto);
	
	public void transferirChamado(String protocolo, TransfereChamadoDto dto);
	
	public void encerrarChamado(String protocolo, EncerraChamadoDto dto);
	
}
