package br.unip.ads.pim4.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.unip.ads.pim4.domain.model.Cliente;
import br.unip.ads.pim4.domain.model.Cpf;
import br.unip.ads.pim4.domain.model.EMail;
import br.unip.ads.pim4.domain.model.Id;
import br.unip.ads.pim4.domain.model.Pessoa;
import br.unip.ads.pim4.domain.model.TelefoneFixo;
import br.unip.ads.pim4.domain.model.TelefoneMovel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteRepositoryTest {
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	private Cliente internalCreateCliente() {
		Id id = Id.proximo();
		Cpf cpf = Cpf.gerarCpf();
		EMail email = new EMail("atendente@pimquatro.com");
		Pessoa pessoa = new Pessoa("Atendente da Silva", cpf, email);
		TelefoneMovel movel = new TelefoneMovel("16993069000");
		TelefoneFixo fixo = new TelefoneFixo("1633069000");
		Cliente cliente = new Cliente(id, pessoa, movel, fixo);
		return cliente;
	}
	
	@Test
	public void createCliente() {
		// Cliente cliente = this.internalCreateCliente();
		// if (encontrado) {
			
		// }
		clienteRepo.save(this.internalCreateCliente());		
	}
	
	@Test
	public void readFindByEMail() {
		Cliente cliente = this.internalCreateCliente();
		cliente.getPessoa().setEmail(new EMail("wowthatscool@server.com"));
		clienteRepo.save(cliente);
		
		cliente = null;
		assertNotNull("Não recuperou cliente.",
				clienteRepo.findByPessoaEmail(new EMail("wowthatscool@server.com")).get());
		
	}
	
	@Test
	public void readFindByCpf() {
		Cliente cliente = this.internalCreateCliente();
		cliente.getPessoa().setCpf(new Cpf("65375877007"));
		clienteRepo.save(cliente);
		cliente = null;
		assertNotNull("Não recuperou cliente.",
				clienteRepo.findByPessoaCpf(new Cpf("65375877007")).get());
	}
	
	

}
