package br.unip.ads.pim4.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.unip.ads.pim4.domain.model.Cliente;
import br.unip.ads.pim4.domain.model.Id;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, String> {
	
	Iterable<Cliente> findAll();
	
	Optional<Cliente> findById(Id id);
	
	// Cliente findByCpfNumero();
	
	// Cliente findByEmailEndereco();
	
}
