package br.unip.ads.pim4.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.unip.ads.pim4.domain.DomainException;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChamadoRepositoryTest {

	@Autowired
	private ChamadoRepository chamadoRepo;
	@Autowired
	private ClienteRepository clienteRepo;
	@Autowired
	private AtendenteRepository atendenteRepo;
	
	private Protocolo protocolo;
	private Id atendenteId;
	private Id outroAtendenteId;
	private Id clienteId;

	@Test
	public void a1createChamado() {
		
		// Criar um Atendente
		Atendente atendente = criarAtendente();		
		
		// Criar um Cliente
		Pessoa pessoa;
		pessoa = new Pessoa("Teste da Silva", new Cpf(Cpf.gerarCpf()), new EMail("testesilva@pimquatro.com"));
		Cliente cliente = new Cliente(new Id(Id.proximo()), pessoa, null, null);
		clienteRepo.save(cliente);
		clienteId = cliente.getId();
		
		// Criando um Chamado
		protocolo = new Protocolo(Protocolo.proximo());
		LocalDateTime dataAbertura = LocalDateTime.now();
		Set<EventoChamado> eventos = new HashSet<>();
		EventoChamado eventoAbertura = new EventoChamado(
				LocalDateTime.now(), 
				"Teste de descrição detalhada do evento.", 
				atendente, TipoEvento.ABERTURA);
		eventos.add(eventoAbertura);
		Chamado novoChamado = new Chamado(protocolo, dataAbertura, null, "Assunto teste", cliente, eventos);
		chamadoRepo.save(novoChamado);
		
	}

	private Atendente criarAtendente() {
		// Criando um Atendente
		Pessoa pessoa = new Pessoa("Atendente Sauro", new Cpf(Cpf.gerarCpf()), new EMail("sauro@pimquatro.com"));
		Atendente atendente = new Atendente(new Id(Id.proximo()), pessoa, "123456");
		atendenteRepo.save(atendente);
		atendenteId = atendente.getId();
		return atendente;
	}
	
	@Test
	public void aReadFindByProtocolo() {
		Chamado chamado = chamadoRepo.findByProtocolo(protocolo).get();
		assertNotNull("Nennum chamado encontrado.", chamado);
	}
	
	@Test
	public void bUpdateAtualizarChamado() throws DomainException {
		
		assertNotNull("Execute o teste de criar chamado.", protocolo);
		
		// Recuperar o chamado
		Chamado chamado = chamadoRepo.findByProtocolo(protocolo).get();
		assertNotNull("Nenhum chamado encontrado.", chamado);
		
		chamado.atualizar("Teste de atualização de chamado");		
		chamadoRepo.save(chamado);
		
		chamado = null;
		chamado = chamadoRepo.findByProtocolo(protocolo).get();
		assertEquals(1, chamado.getEventos().size());
	}
	
	@Test
	public void cUpdateTransferirChamado() throws DomainException {
		
		Atendente outroAtendente = criarAtendente();
		outroAtendente.getPessoa().setNome("Outro Atendente Souza");
		atendenteRepo.save(outroAtendente);
		outroAtendenteId = outroAtendente.getId();
		
		Chamado chamado = chamadoRepo.findByProtocolo(protocolo).get();
		assertNotNull("Execute o teste de criar chamado.", chamado);
		chamado.transferir(outroAtendente, "Teste de transferência de chamado.");
		chamadoRepo.save(chamado);
		chamado = null;
		chamado = chamadoRepo.findByProtocolo(protocolo).get();
		assertTrue("O chamado deveria ter mais de um evento.", chamado.getEventos().size() > 1);
		
	}
	
	@Test
	public void dUpdateEncerrarChamado() throws DomainException {
		
		assertNotNull("Execute o teste de criar chamado.", protocolo);
		Chamado chamado = chamadoRepo.findByProtocolo(protocolo).get();
		assertNotNull("Não recuperou o chamado pelo protocolo.", chamado);
		chamado.encerrar("Teste de encerradomento de chamado.");
		chamadoRepo.save(chamado);
		
		chamado = null;
		chamado = chamadoRepo.findByProtocolo(protocolo).get();
		assertTrue("O chamado deveria ter mais de dois eventos.", chamado.getEventos().size() > 2);		
		
	}
	
	@Test
	public void fReadFindByCliente() {
		assertNotNull("Execute o teste de criar chamado.", protocolo);
		assertNotNull("Execute o teste de criar chamado.", clienteId);
		Cliente cliente = clienteRepo.findById(clienteId).get();
		assertNotNull("Não recuperou o cliente pelo ID.", cliente);
		Chamado chamado = chamadoRepo.findByCliente(cliente).iterator().next();
		assertNotNull("Não recuperou chamado por cliente.", chamado);
	}
	
	@Test
	public void gDeleteChamado() {
		assertNotNull("Execute o teste de criar chamado.", protocolo);
		Chamado chamado = chamadoRepo.findByProtocolo(protocolo).get();
		assertNotNull("Não recuperou o chamado pelo protocolo.", chamado);
		
		chamadoRepo.delete(chamado);
		
		// Não deve mais encontraro chamado pelo protocolo
		chamado = null;
		chamado = chamadoRepo.findByProtocolo(protocolo).get();
		assertNull("O chamado não foi excluído da persistência.", chamado);
		
		// O Atendente não deve ser excluído
		Atendente atendente = atendenteRepo.findById(atendenteId).get();
		assertNotNull("O atendente não foi recuperado pelo ID.", atendente);
		
		// O cliente não deve ser excluido
		Cliente cliente = clienteRepo.findById(clienteId).get();
		assertNotNull("O cliente não foi recuperado pelo ID.", cliente);
	}	

}
