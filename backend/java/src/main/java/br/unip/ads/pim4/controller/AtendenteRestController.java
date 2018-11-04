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
import br.unip.ads.pim4.application.atendente.dto.AtendenteResumoDto;
import br.unip.ads.pim4.application.atendente.dto.AtualizaAtendenteDto;
import br.unip.ads.pim4.application.atendente.dto.NovoAtendenteDto;
import br.unip.ads.pim4.config.SwaggerConfig;
import br.unip.ads.pim4.domain.DomainException;
import io.swagger.annotations.Api;

@Api(tags = SwaggerConfig.TAG_ATENDENTE)
@RestController
@RequestMapping("/api/atendentes")
public class AtendenteRestController extends AbstractRestController {

	@Autowired
	private AtendenteAppService atendenteAppService;

	@PostMapping
	public ResponseEntity<URI> criar(@RequestBody NovoAtendenteDto novoAtendente) throws DomainException {

		String novoId;
		novoId = atendenteAppService.criar(novoAtendente);
		URI novoAtendenteUri = super.criarUriPorId(novoId);
		return ResponseEntity.created(novoAtendenteUri).build();

	}

	@GetMapping
	public ResponseEntity<Iterable<AtendenteResumoDto>> buscarTodos() {

		Iterable<AtendenteResumoDto> todosAtendentes = atendenteAppService.buscarTodos();
		return ResponseEntity.ok().body(todosAtendentes);

	}

	@GetMapping("{id}")
	public ResponseEntity<AtendenteResumoDto> buscar(@PathVariable("id") String id) {

		AtendenteResumoDto atendenteEncontrado = atendenteAppService.buscar(id);
		return ResponseEntity.ok(atendenteEncontrado);

	}

	@PutMapping("{id}")
	public ResponseEntity<Void> atualizar(@PathVariable("id") String id,
			@RequestBody AtualizaAtendenteDto dadosAtualizados) {

		atendenteAppService.atualizar(id, dadosAtualizados);
		return ResponseEntity.ok().build();

	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {

		atendenteAppService.excluir(id);
		return ResponseEntity.ok().build();

	}

}
