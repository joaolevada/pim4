package br.unip.ads.pim4.repository;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.unip.ads.pim4.domain.model.Atendente;
import br.unip.ads.pim4.domain.model.Cpf;
import br.unip.ads.pim4.domain.model.EMail;
import br.unip.ads.pim4.domain.model.Id;
import br.unip.ads.pim4.domain.model.Pessoa;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtendenteRepositoryTest {
	
	@Autowired
	private AtendenteRepository atendenteRepo;
	
	@Test
	public void createAtendente() {
		
		EMail novoEmail = new EMail("atendente@pimquatro.com");
		if (atendenteRepo.findByPessoa_Email(novoEmail).isPresent()) {
			fail("Atendente já cadastrado neste banco de dados.");
		}

		Id novoId = Id.proximo();
		Cpf novoCpf = Cpf.gerarCpf();
		
		Pessoa novaPessoa = new Pessoa("Atendente Stub da Silva", novoCpf, novoEmail);
		Atendente novoAtendente = new Atendente(novoId, novaPessoa, "123456");
		atendenteRepo.save(novoAtendente);
		
	}

}
