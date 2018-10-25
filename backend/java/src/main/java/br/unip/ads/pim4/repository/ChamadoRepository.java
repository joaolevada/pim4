package br.unip.ads.pim4.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.unip.ads.pim4.domain.model.chamado.Chamado;
import br.unip.ads.pim4.domain.model.chamado.Protocolo;

public interface ChamadoRepository extends CrudRepository<Chamado, Protocolo> {
	
	Iterable<Chamado> findAll();
	
	Optional<Chamado> findByProtocolo(Protocolo protocolo);

}
