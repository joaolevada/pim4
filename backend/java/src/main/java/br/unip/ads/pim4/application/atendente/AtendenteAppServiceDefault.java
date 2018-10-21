package br.unip.ads.pim4.application.atendente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.ads.pim4.application.AbstractAppService;
import br.unip.ads.pim4.application.atendente.dto.AtendenteResumoDto;
import br.unip.ads.pim4.application.atendente.dto.AtualizaAtendenteDto;
import br.unip.ads.pim4.application.atendente.dto.NovoAtendenteDto;
import br.unip.ads.pim4.application.atendente.dto.assembly.AtendenteDtoAssembler;
import br.unip.ads.pim4.domain.model.Atendente;
import br.unip.ads.pim4.domain.model.Cpf;
import br.unip.ads.pim4.domain.model.EMail;
import br.unip.ads.pim4.domain.model.Id;
import br.unip.ads.pim4.domain.model.Pessoa;
import br.unip.ads.pim4.repository.AtendenteRepository;

@Service
public class AtendenteAppServiceDefault extends AbstractAppService implements AtendenteAppService {

	@Autowired
	private AtendenteRepository atendenteRepo;

	@Override
	public String criar(NovoAtendenteDto novoAtendente) {
		
		// TODO regras de validação para criar um Atendente serão implementadas aqui.
		// TODO levantar exceção caso alguma das regras de validação seja violada.
		// TODO validação de CPF pode ser chamada aqui.
		// TODO validação de email (simples, por favor) pode ser chamada aqui.
		// TODO não permitir atendente sem nome.
		// TODO validar o tamanho das colunas
		
		Id novoId = new Id(Id.proximo());
		Cpf novoCpf = new Cpf(novoAtendente.getCpf());
		EMail novoEmail = new EMail(novoAtendente.getEmail());		
		Pessoa novaPessoa = new Pessoa(novoAtendente.getNome(), novoCpf, novoEmail);		
		
		Atendente atendentePersist = new Atendente(novoId, novaPessoa, novoAtendente.getSenha());
		atendenteRepo.save(atendentePersist);
		return atendentePersist.getId().toString();
				
	}

	@Override
	public AtendenteResumoDto buscar(String id) {
		Id idBuscado = new Id(id);
		Atendente atendenteEncontrado = atendenteRepo.findById(idBuscado).get();
		return AtendenteDtoAssembler.toDto(atendenteEncontrado); 
	}

	@Override
	public void atualizar(String id, AtualizaAtendenteDto dadosAtualizados) {
		
		Id idBuscado = new Id(id);
		Atendente atendentePersist = atendenteRepo.findById(idBuscado).get();		
		
		Cpf novoCpf = new Cpf(dadosAtualizados.getCpf());
		EMail novoEmail = new EMail(dadosAtualizados.getEmail());
		Pessoa novaPessoa = new Pessoa(dadosAtualizados.getNome(), novoCpf, novoEmail);
		atendentePersist.setPessoa(novaPessoa);
		atendentePersist.setSenha(dadosAtualizados.getSenha());
		
		// TODO Considerar validações nos dados. Levantar exceções em caso de dados inválidos.
		// TODO Verificar o tamanho das colunas.
		// TODO Verificar se o novo email digitado não está duplicado.
		atendenteRepo.save(atendentePersist);
		
	}

	@Override
	public Iterable<AtendenteResumoDto> buscarTodos() {
		
		Iterable<Atendente> atendentes = atendenteRepo.findAll();
		Iterable<AtendenteResumoDto> todosAtendentes = AtendenteDtoAssembler.toResumoDtoList(atendentes);		
		return todosAtendentes;
		
	}

	@Override
	public AtendenteResumoDto buscarPorEmailESenha(String email, String senha) {
		EMail emailProcurado = new EMail(email);
		Atendente atendenteCompleto = atendenteRepo.findByPessoaEmailAndSenha(emailProcurado, senha).get();
		return AtendenteDtoAssembler.toDto(atendenteCompleto);
	}

	@Override
	public void excluir(String id) {
		/* TODO A exclusao de um Atendente tem de passar por validações.
		 * A exclusão de um atendente, por exemplo, vai deixar chamados órfãos.
		 * Esta situação deve ser considerada.
		 */
		Id idProcurado = new Id(id);
		Atendente atendente = atendenteRepo.findById(idProcurado).get();
		atendenteRepo.delete(atendente);
	}	
	

}
