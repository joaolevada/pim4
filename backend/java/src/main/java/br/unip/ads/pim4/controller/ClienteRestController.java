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

import br.unip.ads.pim4.application.cliente.ClienteAppService;
import br.unip.ads.pim4.application.cliente.dto.AtualizaClienteDto;
import br.unip.ads.pim4.application.cliente.dto.ClienteResumoDto;
import br.unip.ads.pim4.application.cliente.dto.NovoClienteDto;
import br.unip.ads.pim4.config.SwaggerConfig;
import io.swagger.annotations.Api;

@Api(tags = SwaggerConfig.TAG_CLIENTE)
@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController extends AbstractRestController {
	
	@Autowired
	private ClienteAppService clienteAppService;
	
	@PostMapping
	public ResponseEntity<URI> criar(@RequestBody NovoClienteDto dto) {
		
		/*
		 * TODO Tratar exceções
		 */
		
		String novoId = clienteAppService.criar(dto);		
		return ResponseEntity.created(super.criarUriPorId(novoId)).build();
				
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ClienteResumoDto> buscar(@PathVariable("id") String id) {		
		
		/*
		 * TODO Tratar exceções
		 */
		
		return ResponseEntity.ok(clienteAppService.buscar(id));
		
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody AtualizaClienteDto dadosAtualizados) {
		
		/*
		 * TODO Tratar exceções
		 */
		
		clienteAppService.atualizar(id, dadosAtualizados);
		return ResponseEntity.ok().build();	
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		
		/*
		 * TODO Tratar exceções
		 */
		
		clienteAppService.excluir(id);		
		return ResponseEntity.ok().build();
		
	}
	
	@GetMapping()
	public ResponseEntity<Iterable<ClienteResumoDto>> buscarTodos() {		
					
		return ResponseEntity.ok(clienteAppService.buscarTodos());
		
	}
	
	
	
	
	
	
	
	
	

}
