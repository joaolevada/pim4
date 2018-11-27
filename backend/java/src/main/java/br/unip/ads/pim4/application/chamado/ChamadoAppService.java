package br.unip.ads.pim4.application.chamado;

import br.unip.ads.pim4.application.chamado.dto.AtualizaChamadoDto;
import br.unip.ads.pim4.application.chamado.dto.ChamadoResumoDto;
import br.unip.ads.pim4.application.chamado.dto.EncerraChamadoDto;
import br.unip.ads.pim4.application.chamado.dto.NovoChamadoDto;
import br.unip.ads.pim4.application.chamado.dto.TransfereChamadoDto;
import br.unip.ads.pim4.domain.DomainException;

public interface ChamadoAppService {
	
	public String criar(NovoChamadoDto novoChamado) throws DomainException;
	
	public ChamadoResumoDto buscar(String protocolo);	
	
	public void excluir(String protocolo) throws DomainException;
	
	public Iterable<ChamadoResumoDto> buscarTodos();

	public void atualizarChamado(String protocolo, AtualizaChamadoDto dto) throws DomainException;
	
	public void transferirChamado(String protocolo, TransfereChamadoDto dto) throws DomainException;
	
	public void encerrarChamado(String protocolo, EncerraChamadoDto dto) throws DomainException;

	public Iterable<ChamadoResumoDto> buscarDoAtendente(String atendenteId) throws DomainException;
	
}
