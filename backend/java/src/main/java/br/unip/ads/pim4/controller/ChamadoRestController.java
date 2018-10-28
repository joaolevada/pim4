package br.unip.ads.pim4.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import br.unip.ads.pim4.application.chamado.ChamadoAppService;
import br.unip.ads.pim4.application.chamado.dto.ChamadoResumoDto;
import br.unip.ads.pim4.application.chamado.dto.NovoChamadoDto;
import br.unip.ads.pim4.config.SwaggerConfig;
import io.swagger.annotations.Api;

@Api(tags = SwaggerConfig.TAG_CHAMADO)
@RestController
@RequestMapping("/api/chamados")
public class ChamadoRestController extends AbstractRestController {
	
	@Autowired
	private ChamadoAppService chamadoAppService;
	
	@PostMapping
	public ResponseEntity<String> criar(NovoChamadoDto novoChamado) {
		String protocolo = chamadoAppService.criar(novoChamado);
		URI location = super.criarUriPorId(protocolo);
		// TODO Tratar exceção
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("{protocolo}")
	public ResponseEntity<ChamadoResumoDto> buscar(@PathVariable("protocolo") String protocolo) {
		ChamadoResumoDto chamadoEncontrado = chamadoAppService.buscar(protocolo);
		return ResponseEntity.ok().body(chamadoEncontrado);
	}
	
	@GetMapping
	public ResponseEntity<Iterable<ChamadoResumoDto>> buscarTodos() {
		Iterable<ChamadoResumoDto> todosChamados = chamadoAppService.buscarTodos();
		return ResponseEntity.ok().body(todosChamados);
	}
	
	// TODO Atualizar chamado - PutMapping
	// TODO Transferir chamado - PutMapping
	// TODO Encerrar chamado - PutMapping
	
	@DeleteMapping("{protocolo}")
	public ResponseEntity<Void> excluir(@PathVariable("protocolo") String protocolo) {
		// TODO Tratar exceção
		chamadoAppService.excluir(protocolo);
		return ResponseEntity.ok().build();
	}
	

}
