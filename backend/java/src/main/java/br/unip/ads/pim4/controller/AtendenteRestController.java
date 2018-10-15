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

import br.unip.ads.pim4.application.atendente.AtendenteAppService;
import br.unip.ads.pim4.application.atendente.AtendenteResumoDto;
import br.unip.ads.pim4.application.atendente.NovoAtendenteDto;
import br.unip.ads.pim4.application.atendente.AtualizaAtendenteDto;
import br.unip.ads.pim4.application.atendente.assembly.AtendenteDtoAssembly;
import br.unip.ads.pim4.config.SwaggerConfig;
import br.unip.ads.pim4.domain.model.Atendente;
import br.unip.ads.pim4.domain.model.Id;
import br.unip.ads.pim4.repository.AtendenteRepository;

import io.swagger.annotations.Api;

@Api(tags = SwaggerConfig.TAG_ATENDENTE)
@RestController
@RequestMapping("/api/atendentes")
public class AtendenteRestController extends AbstractRestController {
	
	@Autowired
	private AtendenteRepository atendenteRepo;
	
	@Autowired
	private AtendenteAppService atendenteAppService;
	
	@GetMapping
	public ResponseEntity<Iterable<AtendenteResumoDto>> findAll() {
		
		Iterable<Atendente> todosAtendentes = atendenteRepo.findAll(); 
		Iterable<AtendenteResumoDto> atendentesResumido = AtendenteDtoAssembly.toResumoDtoList(todosAtendentes);		
		return ResponseEntity.ok(atendentesResumido);
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Atendente> findById(@PathVariable("id") String id) {
		
		Atendente atendenteEncontrado = atendenteRepo.findById(new Id(id));
		return ResponseEntity.ok(atendenteEncontrado);
		
	}
	
	@PostMapping
	public ResponseEntity<URI> create(@RequestBody NovoAtendenteDto dto) {		
		
		Atendente novoAtendente = atendenteAppService.criarAtendente(dto);
		atendenteRepo.save(novoAtendente);
		URI novoAtendenteUri = super.criarUriPorId(novoAtendente.getId());
		return ResponseEntity.created(novoAtendenteUri).build();
		
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody AtualizaAtendenteDto dto) {
		
		Atendente atendenteParaAtualizar = atendenteRepo.findById(new Id(id));
		if (atendenteParaAtualizar == null) {
			return ResponseEntity.notFound().build();
		}
		atendenteAppService.atualizarAtendente(atendenteParaAtualizar, dto);		
		return ResponseEntity.ok().build();
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		
		/* TODO Há verificações de consistência que devem ser consideradas antes da exclusão de um
		 * Atendente. Se houver, por exemplo, Chamado que tenha sido designado para o atendente, 
		 * sua remoção vai implicar em tornar os Chamado inconsistentes. A não ser que algum tipo de 
		 * artifício seja implementado para indicar que o Chamado foi de um atendente que não está mais disponível
		 * por ter sido removido.
		 */		
		
		Atendente atendenteParaExcluir = atendenteRepo.findById(new Id(id));
		if (atendenteParaExcluir == null) {
			return ResponseEntity.notFound().build();
		}
		atendenteRepo.delete(atendenteParaExcluir);
		return ResponseEntity.ok().build();
		
	}

}
