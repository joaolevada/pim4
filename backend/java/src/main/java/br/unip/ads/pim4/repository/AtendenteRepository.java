package br.unip.ads.pim4.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.unip.ads.pim4.domain.model.Atendente;
import br.unip.ads.pim4.domain.model.EMail;
import br.unip.ads.pim4.domain.model.Id;

@Repository
public interface AtendenteRepository extends CrudRepository<Atendente, String> {
	
	Iterable<Atendente> findAll();
	
	Atendente findById(Id id);
	
	Atendente findByPessoaEmailAndSenha(EMail email, String senha);
	
	// Cliente findByCpfNumero();
	
	// Cliente findByEmailEndereco();
	
}
