package br.unip.ads.pim4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import br.unip.ads.pim4.config.SwaggerConfig;
import io.swagger.annotations.Api;

@Api(tags = SwaggerConfig.TAG_CHAMADO)
@RestController
@RequestMapping("/api/chamados")
public class ChamadoRestController extends AbstractRestController {
	
	@GetMapping
	public ResponseEntity<String> helloWorld() {

		return ResponseEntity.ok("Hello World!!");
		
	}

}
