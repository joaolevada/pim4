package br.unip.ads.pim4.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unip.ads.pim4.application.cliente.AtualizaClienteDto;
import br.unip.ads.pim4.application.cliente.ClienteAppService;
import br.unip.ads.pim4.application.cliente.ClienteResumoDto;
import br.unip.ads.pim4.application.cliente.NovoClienteDto;
import br.unip.ads.pim4.application.cliente.assembly.ClienteResumoDtoAssembler;
import br.unip.ads.pim4.config.SwaggerConfig;
import br.unip.ads.pim4.domain.model.Cliente;
import br.unip.ads.pim4.domain.model.Id;
import br.unip.ads.pim4.repository.ClienteRepository;
import io.swagger.annotations.Api;

@Api(tags = SwaggerConfig.TAG_CLIENTE)
@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController extends AbstractRestController {
	
	@Autowired
	private ClienteRepository clienteRepo;
	@Autowired
	private ClienteAppService clienteAppService;
	
	@GetMapping()
	public ResponseEntity<Iterable<ClienteResumoDto>> findAll() {
		
		Iterable<Cliente> osClientes = clienteRepo.findAll();			
		return ResponseEntity.ok(ClienteResumoDtoAssembler.toDtoList(osClientes));
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ClienteResumoDto> findById(@PathVariable("id") String id) {
		
		Cliente clienteEncontrado = clienteRepo.findById(new Id(id));
		if (clienteEncontrado == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(ClienteResumoDtoAssembler.toDto(clienteEncontrado));
		
	}
	
	@PostMapping
	public ResponseEntity<URI> create(@RequestBody NovoClienteDto dto) {
		
		Cliente novoCliente = clienteAppService.criarCliente(dto);
		clienteRepo.save(novoCliente);
		return ResponseEntity.created(super.criarUriPorId(novoCliente.getId())).build();
				
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody AtualizaClienteDto dto) {
		
		Cliente clienteParaAtualizar = clienteRepo.findById(new Id(id));
		if (clienteParaAtualizar == null) {
			return ResponseEntity.notFound().build();
		}
		
		clienteAppService.atualizarCliente(clienteParaAtualizar, dto);
		return ResponseEntity.ok().build();	
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		
		/*
		 * TODO Há verificações de consistência que devem ser consideradas antes de 
		 * excluir um Cliente. Se houver chamados aberto em nome do Cliente, por exemplo...
		 */
		
		Cliente clienteParaRemover = clienteRepo.findById(new Id(id));
		if (clienteParaRemover == null) {
			return ResponseEntity.notFound().build();
		}
		clienteRepo.delete(clienteParaRemover);
		return ResponseEntity.ok().build();
		
	}
	

}
