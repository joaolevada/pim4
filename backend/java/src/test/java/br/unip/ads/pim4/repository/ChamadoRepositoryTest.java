package br.unip.ads.pim4.repository;

import static org.junit.Assert.fail;

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
		Atendente atendente = new Atendente(new Id(Id.proximo()), pessoa, "123456");
		atendenteRepo.save(atendente);
				
		Protocolo protocolo = new Protocolo(Protocolo.proximo());
		LocalDateTime dataAbertura = LocalDateTime.now();
		pessoa = new Pessoa("Teste da Silva", new Cpf(Cpf.gerarCpf()), new EMail("testesilva@pimquatro.com"));
		Cliente cliente = new Cliente(new Id(Id.proximo()), pessoa);
		clienteRepo.save(cliente);
		
		Set<EventoChamado> eventos = new HashSet<>();
		EventoChamado eventoAbertura = new EventoChamado(
				LocalDateTime.now(), 
				"Teste de descriÃ§Ã£o detalhada do evento.", 
				atendente, TipoEvento.ABERTURA);
		eventos.add(eventoAbertura);

		Chamado novoChamado = new Chamado(protocolo, dataAbertura, null, "Assunto teste", cliente, eventos);
		chamadoRepo.save(novoChamado);
	}
	
	@Test
	public void updateAtualizarChamado() {
		this.createChamado();
		
		// Criar um evento de atualizacao para o primeiro chamado retornado pelo repositório
		Iterable<Chamado> todosChamados = chamadoRepo.findAll();
		Chamado c = todosChamados.iterator().next();
		if (c == null) {
			fail("Nenhum chamado na coleção.");
		}
		Set<EventoChamado> evs = c.getEventos();
		EventoChamado eventoAtualizacao = new EventoChamado(
				LocalDateTime.now(), 
				"Teste de descrição da atualização do evento", 
				evs.iterator().next().getAtendente(), 
				TipoEvento.ATUALIZACAO
				);
		evs.add(eventoAtualizacao);
		c.setEventos(evs);
		chamadoRepo.save(c);
	}
	
	@Test
	public void updateTransferirChamado() {
		this.createChamado();
		// TODO Implementar teste de transferencia de chamado!
		// Criar um evento de transferencia para o primeiro chamado retornado pelo repositório
	}

}
