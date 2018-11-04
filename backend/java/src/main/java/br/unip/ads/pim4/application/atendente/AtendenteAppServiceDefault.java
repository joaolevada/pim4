package br.unip.ads.pim4.application.atendente;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.ads.pim4.application.AbstractAppService;
import br.unip.ads.pim4.application.atendente.dto.AtendenteResumoDto;
import br.unip.ads.pim4.application.atendente.dto.AtualizaAtendenteDto;
import br.unip.ads.pim4.application.atendente.dto.NovoAtendenteDto;
import br.unip.ads.pim4.application.atendente.dto.assembly.AtendenteDtoAssembler;
import br.unip.ads.pim4.domain.DomainException;
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
	public String criar(NovoAtendenteDto dto) throws DomainException {
		
		// TODO regras de valida��o para criar um Atendente ser�o implementadas aqui.
		// TODO levantar exce��o caso alguma das regras de valida��o seja violada.
		// TODO valida��o de CPF pode ser chamada aqui.
		// TODO valida��o de email (simples, por favor) pode ser chamada aqui.
		// TODO n�o permitir atendente sem nome.
		// TODO validar o tamanho das colunas
		
		Id novoId = new Id(Id.proximo());
		
 
		Cpf novoCpf = new Cpf(dto.getCpf());
		// Não permitir CPF duplicado
		// Validação de CPF está delegada à biblioteca Caelum
		boolean cpfEncontrado = atendenteRepo.findByPessoa_Cpf(novoCpf).isPresent();
		if (cpfEncontrado) {
			throw new DomainException(String.format(
					"O CPF \"%s\" já foi cadastrado.", dto.getCpf()));
		}		
		
		EMail novoEmail = new EMail(dto.getEmail());		
		boolean emailEncontrado = atendenteRepo.findByPessoa_Email(novoEmail).isPresent();
		if (emailEncontrado) {
			throw new DomainException(String.format(
					"O E-mail \"%s\" já foi cadastrado.", dto.getEmail()));
		}
		
		Pessoa novaPessoa = new Pessoa(dto.getNome(), novoCpf, novoEmail);
		
		Atendente atendentePersist = new Atendente(novoId, novaPessoa, dto.getSenha());
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
		
		// TODO Considerar valida��es nos dados. Levantar exce��es em caso de dados inv�lidos.
		// TODO Verificar o tamanho das colunas.
		// TODO Verificar se o novo email digitado n�o est� duplicado.
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
		try {
			Atendente atendenteCompleto = atendenteRepo.findByPessoa_Email(emailProcurado).get();
			if (atendenteCompleto == null) {
				return null;
			}
			if (atendenteCompleto.getSenha().compareTo(senha) == 0) {
				return AtendenteDtoAssembler.toDto(atendenteCompleto);
			} else {
				return null;
			}
		} catch (Exception e) {			
			throw e;
		}		
	}

	@Override
	public void excluir(String id) {
		/* TODO A exclusao de um Atendente tem de passar por valida��es.
		 * A exclus�o de um atendente, por exemplo, vai deixar chamados �rf�os.
		 * Esta situa��o deve ser considerada.
		 */
		Id idProcurado = new Id(id);
		Atendente atendente = atendenteRepo.findById(idProcurado).get();
		atendenteRepo.delete(atendente);
	}	
	

}
