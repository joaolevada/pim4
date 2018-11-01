package br.unip.ads.pim4.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import br.unip.ads.pim4.application.chamado.ChamadoAppService;
import br.unip.ads.pim4.application.chamado.dto.AtualizaChamadoDto;
import br.unip.ads.pim4.application.chamado.dto.ChamadoResumoDto;
import br.unip.ads.pim4.application.chamado.dto.EncerraChamadoDto;
import br.unip.ads.pim4.application.chamado.dto.NovoChamadoDto;
import br.unip.ads.pim4.application.chamado.dto.TransfereChamadoDto;
import br.unip.ads.pim4.config.SwaggerConfig;
import io.swagger.annotations.Api;

@Api(tags = SwaggerConfig.TAG_CHAMADO)
@RestController
@RequestMapping("/api/chamados")
public class ChamadoRestController extends AbstractRestController {
	
	@Autowired
	private ChamadoAppService chamadoAppService;
	
	@PostMapping
	public ResponseEntity<String> criar(@RequestBody NovoChamadoDto dto) {
		String protocolo = chamadoAppService.criar(dto);
		URI location = super.criarUriPorId(protocolo);
		// TODO Tratar exceï¿½ï¿½o
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

	@PutMapping("{protocolo}/atualizar")
	public ResponseEntity<Void> atualizarChamado(@PathParam("protocolo") String protocolo, @RequestBody AtualizaChamadoDto dto) {
		// TODO Tratar exceções
		chamadoAppService.atualizarChamado(protocolo, dto);
		return ResponseEntity.ok().build();
	}	
	
	@PutMapping("{protocolo}/transferir")
	public ResponseEntity<Void> transferirChamado(@PathParam("protocolo") String protocolo, @RequestBody TransfereChamadoDto dto) {
		// TODO Tratar exceções
		chamadoAppService.transferirChamado(protocolo, dto);
		return ResponseEntity.ok().build();
	}	

	@PutMapping("{protocolo}/encerrar")
	public ResponseEntity<Void> encerrarChamado(@PathParam("protocolo") String protocolo, @RequestBody EncerraChamadoDto dto) {
		// TODO Tratar exceções
		chamadoAppService.encerrarChamado(protocolo, dto);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("{protocolo}")
	public ResponseEntity<Void> excluir(@PathVariable("protocolo") String protocolo) {
		// TODO Tratar exceÃ§Ãµes
		chamadoAppService.excluir(protocolo);
		return ResponseEntity.ok().build();
	}
	

}
