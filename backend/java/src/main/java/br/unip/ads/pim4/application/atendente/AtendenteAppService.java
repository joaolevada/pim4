package br.unip.ads.pim4.application.atendente;

import br.unip.ads.pim4.application.atendente.dto.AtendenteResumoDto;
import br.unip.ads.pim4.application.atendente.dto.AtualizaAtendenteDto;
import br.unip.ads.pim4.application.atendente.dto.NovoAtendenteDto;
import br.unip.ads.pim4.domain.DomainException;

public interface AtendenteAppService {	
	
	public String criar(NovoAtendenteDto dto) throws DomainException;
	
	public AtendenteResumoDto buscar(String id);
	
	public void atualizar(String id, AtualizaAtendenteDto dto) throws DomainException;
	
	public void excluir(String id);
	
	public Iterable<AtendenteResumoDto> buscarTodos();
	
	public AtendenteResumoDto buscarPorEmailESenha(String email, String senha);		

}
