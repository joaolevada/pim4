package br.unip.ads.pim4.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestControllerAdvice
public abstract class AbstractRestController {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleUnknowException(Exception ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	protected URI criarUriPorId(Object id) {
		return ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(id)
				.toUri();
	}

}
