package br.unip.ads.pim4.repository;

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

		Id novoId = new Id(Id.proximo());
		Cpf novoCpf = new Cpf(Cpf.gerarCpf());
		EMail novoEmail = new EMail("contato@isp.com.br");
		Pessoa novaPessoa = new Pessoa("Atendente Stub da Silva", novoCpf, novoEmail);
		Atendente novoAtendente = new Atendente(novoId, novaPessoa, "123456");
		atendenteRepo.save(novoAtendente);
		
	}

}
