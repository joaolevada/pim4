package br.unip.ads.pim4.application.atendente;

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
	
	private String VALIDACAO_CPF_DUPLICADO = "O CPF informado já foi cadastrado.";
	private String VALIDACAO_EMAIL_DUPLICADO = "O e-mail informado já foi cadastrado.";
	private String VALIDACAO_SENHA_NAOVERIFICADA = "A senha anterior informada está incorreta.";

	@Autowired
	private AtendenteRepository atendenteRepo;

	@Override
	public String criar(NovoAtendenteDto dto) throws DomainException { 
		
		Id id = new Id(Id.proximo()); 
		Cpf cpf = new Cpf(dto.getCpf());
		// Não permitir CPF duplicado
		// Validação de CPF está delegada à biblioteca Caelum
		boolean cpfEncontrado = atendenteRepo.findByPessoa_Cpf(cpf).isPresent();
		if (cpfEncontrado) {
			throw new DomainException(VALIDACAO_CPF_DUPLICADO);
		}		
		
		EMail email = new EMail(dto.getEmail());		
		boolean emailEncontrado = atendenteRepo.findByPessoa_Email(email).iterator().hasNext();
		if (emailEncontrado) {
			throw new DomainException(VALIDACAO_EMAIL_DUPLICADO);
		}
		
		Pessoa pessoa = new Pessoa(dto.getNome(), cpf, email);
		
		Atendente atendente = new Atendente(id, pessoa, dto.getSenha());
		atendenteRepo.save(atendente);
		return atendente.getId().toString();
				
	}

	@Override
	public AtendenteResumoDto buscar(String id) {
		Id idBuscado = new Id(id);
		Atendente atendenteEncontrado = atendenteRepo.findById(idBuscado).get();
		return AtendenteDtoAssembler.toDto(atendenteEncontrado); 
	}

	@Override
	public void atualizar(String id, AtualizaAtendenteDto dto) throws DomainException {		
		
		Id idBuscado = new Id(id);
		Atendente atendenteParaAtualizar = atendenteRepo.findById(idBuscado).get();
		boolean alterouCPF = !atendenteParaAtualizar.getPessoa().getCpf().asString().equals(dto.getCpf());
		if (alterouCPF) {
			Atendente outroAtendente = atendenteRepo.findByPessoa_Cpf(new Cpf(dto.getCpf())).get();
			// Se encontrar outro atendente com o mesmo CPF, levantar exceção
			if (!atendenteParaAtualizar.equals(outroAtendente)) {
				throw new DomainException(VALIDACAO_CPF_DUPLICADO);
			}
		}
		boolean alterouEMail = !atendenteParaAtualizar.getPessoa().getEmail().asString().equals(dto.getEmail());
		if (alterouEMail) {
			Atendente outroAtendente = atendenteRepo.findByPessoa_Email(new EMail(dto.getEmail())).iterator().next();
			// Se encontrar outro atendente com o mesmo email, levantar exceção
			if (!atendenteParaAtualizar.equals(outroAtendente)) {
				throw new DomainException(VALIDACAO_EMAIL_DUPLICADO);
			}
		}
		
		// Validar a alteração da senha. Verificar se a senha antiga confere
		if (!atendenteParaAtualizar.getSenha().equals(dto.getSenhaAntiga())) {
			throw new DomainException(VALIDACAO_SENHA_NAOVERIFICADA);
		}
		
		Cpf novoCpf = new Cpf(dto.getCpf());
		EMail novoEmail = new EMail(dto.getEmail());
		Pessoa novaPessoa = new Pessoa(dto.getNome(), novoCpf, novoEmail);
		atendenteParaAtualizar.setPessoa(novaPessoa);
		atendenteParaAtualizar.setSenha(dto.getSenha());		
		
		atendenteRepo.save(atendenteParaAtualizar);
		
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
			Atendente atendenteCompleto = atendenteRepo.findByPessoa_Email(emailProcurado).iterator().next();
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
		/* TODO A exclusao de um Atendente tem de passar por validações.
		 * A exclusão de um atendente, por exemplo, vai deixar chamados orfãos.
		 * Esta situação deve ser considerada.
		 */
		Id idProcurado = new Id(id);
		Atendente atendente = atendenteRepo.findById(idProcurado).get();
		atendenteRepo.delete(atendente);
	}	
	

}
