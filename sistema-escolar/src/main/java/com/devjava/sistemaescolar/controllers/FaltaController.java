package com.devjava.sistemaescolar.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.devjava.sistemaescolar.dto.request.FaltaRequest;
import com.devjava.sistemaescolar.dto.response.FaltaResponse;
import com.devjava.sistemaescolar.services.FaltaService;

@RestController
@RequestMapping("/faltas")
public class FaltaController {
	private final FaltaService faltaService;
	
	public FaltaController(FaltaService faltaService) {
		this.faltaService = faltaService;
	}
	
	@PostMapping
	public ResponseEntity<FaltaResponse> salvar(@RequestBody FaltaRequest request) {
		FaltaResponse falta = faltaService.salvar(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(falta);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FaltaResponse> buscarPorId(@PathVariable Integer id) {
		FaltaResponse falta = faltaService.buscarPorId(id);
		return ResponseEntity.ok().body(falta);
	}
	
	@GetMapping
	public ResponseEntity<List<FaltaResponse>> buscarTodos() {
		List<FaltaResponse> faltas = faltaService.buscarTodos();
		return ResponseEntity.ok().body(faltas);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
		faltaService.deletarPorId(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<FaltaResponse> atualizar(@PathVariable Integer id, @RequestBody FaltaRequest request) {
		FaltaResponse falta = faltaService.atualizar(id, request);
		return ResponseEntity.ok().body(falta);
	}
}
