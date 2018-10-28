package br.unip.ads.pim4.repository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.unip.ads.pim4.domain.model.Atendente;
import br.unip.ads.pim4.domain.model.Cliente;
import br.unip.ads.pim4.domain.model.Cpf;
import br.unip.ads.pim4.domain.model.EMail;
import br.unip.ads.pim4.domain.model.Id;
import br.unip.ads.pim4.domain.model.Pessoa;
import br.unip.ads.pim4.domain.model.chamado.Chamado;
import br.unip.ads.pim4.domain.model.chamado.Protocolo;
import br.unip.ads.pim4.domain.model.chamado.evento.EventoChamado;
import br.unip.ads.pim4.domain.model.chamado.evento.TipoEvento;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChamadoRepositoryTest {

	@Autowired
	private ChamadoRepository chamadoRepo;
	@Autowired
	private ClienteRepository clienteRepo;
	@Autowired
	private AtendenteRepository atendenteRepo;

	@Test
	public void createChamado() {
		
		Pessoa pessoa = new Pessoa("Atendente Sauro", new Cpf(Cpf.gerarCpf()), new EMail("sauro@pimquatro.com"));
		Atendente atendente = new Atendente(new Id(Id.proximo()), pessoa, "1234456");
		atendenteRepo.save(atendente);
				
		Protocolo protocolo = new Protocolo(Protocolo.proximo());
		LocalDateTime dataAbertura = LocalDateTime.now();
		pessoa = new Pessoa("Teste da Silva", new Cpf(Cpf.gerarCpf()), new EMail("testesilva@pimquatro.com"));
		Cliente cliente = new Cliente(new Id(Id.proximo()), pessoa);
		clienteRepo.save(cliente);
		
		Set<EventoChamado> eventos = new HashSet<>();
		eventos.add(new EventoChamado(new Id(Id.proximo()), LocalDateTime.now(), "Teste de descrição detalhada do problema", atendente.getId(), TipoEvento.ABERTURA));

		Chamado novoChamado = new Chamado(protocolo, dataAbertura, null, "Assunto teste", cliente.getId(), eventos);
		chamadoRepo.save(novoChamado);
	}

}
