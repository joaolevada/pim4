package br.unip.ads.pim4.application.atendente;

import br.unip.ads.pim4.application.atendente.dto.AtendenteResumoDto;
import br.unip.ads.pim4.application.atendente.dto.AtualizaAtendenteDto;
import br.unip.ads.pim4.application.atendente.dto.NovoAtendenteDto;

public interface AtendenteAppService {	
	
	public String criar(NovoAtendenteDto novoAtendente);
	
	public AtendenteResumoDto buscar(String id);
	
	public void atualizar(String id, AtualizaAtendenteDto dadosAtualizados);
	
	public void excluir(String id);
	
	public Iterable<AtendenteResumoDto> buscarTodos();
	
	public AtendenteResumoDto buscarPorEmailESenha(String email, String senha);		

}
