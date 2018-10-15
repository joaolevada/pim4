package br.unip.ads.pim4.application.atendente;

import org.springframework.stereotype.Service;

import br.unip.ads.pim4.application.AbstractAppService;
import br.unip.ads.pim4.domain.model.Atendente;
import br.unip.ads.pim4.domain.model.Cpf;
import br.unip.ads.pim4.domain.model.EMail;
import br.unip.ads.pim4.domain.model.Id;
import br.unip.ads.pim4.domain.model.Pessoa;

@Service
public class AtendenteAppService extends AbstractAppService {	
	
	public Atendente criarAtendente(NovoAtendenteDto novoAtendente) {
		
		// TODO regras de valida��o para criar um Atendente ser�o implementadas aqui.
		// TODO levantar exce��o caso alguma das regras de valida��o sejam violadas.
		// TODO valida��o de CPF pode ser chamada aqui.
		// TODO valida��o de email (simples, por favor) pode ser chamada aqui.
		
		Id novoId = new Id(Id.proximo());
		Cpf novoCpf = new Cpf(novoAtendente.getCpf());
		EMail novoEmail = new EMail(novoAtendente.getEmail());		
		Pessoa novaPessoa = new Pessoa(novoAtendente.getNome(), novoCpf, novoEmail);
		
		// TODO n�o permitir atendente sem nome.
		
		return new Atendente(novoId, novaPessoa, novoAtendente.getSenha());
		
	}
	
	public void atualizarAtendente(Atendente atendente, AtualizaAtendenteDto dto) {		
		
		Cpf novoCpf = new Cpf(dto.getCpf());
		EMail novoEmail = new EMail(dto.getEmail());
		Pessoa novaPessoa = new Pessoa(dto.getNome(), novoCpf, novoEmail);
		atendente.setPessoa(novaPessoa);
		atendente.setSenha(dto.getSenha());
		
	}

}
