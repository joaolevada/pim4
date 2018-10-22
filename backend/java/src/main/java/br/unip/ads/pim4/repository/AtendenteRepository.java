package br.unip.ads.pim4.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.unip.ads.pim4.domain.model.Atendente;
import br.unip.ads.pim4.domain.model.EMail;
import br.unip.ads.pim4.domain.model.Id;

@Repository
public interface AtendenteRepository extends CrudRepository<Atendente, String> {
	
	Iterable<Atendente> findAll();
	
	Optional<Atendente> findById(Id id);
	
	Optional<Atendente> findByPessoaEmailAndSenha(EMail email, String senha);
	
}
