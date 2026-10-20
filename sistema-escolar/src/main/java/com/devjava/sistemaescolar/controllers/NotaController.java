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
import com.devjava.sistemaescolar.dto.request.NotaRequest;
import com.devjava.sistemaescolar.dto.response.NotaResponse;
import com.devjava.sistemaescolar.services.NotaService;

@RestController
@RequestMapping("/notas")
public class NotaController {
	
	private final NotaService notaService;
	
	public NotaController(NotaService notaService) {
		this.notaService = notaService;
	}
	
	@PostMapping
	public ResponseEntity<NotaResponse> salvar(@RequestBody NotaRequest request){
		NotaResponse nota = notaService.salvar(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(nota);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<NotaResponse> buscarPorId(@PathVariable Integer id){
		NotaResponse nota =  notaService.buscarPorId(id);
		return ResponseEntity.ok().body(nota);
	}
	
	@GetMapping
	public ResponseEntity<List<NotaResponse>> buscarTodos(){
		List<NotaResponse> notas = notaService.buscarTodos();
		return ResponseEntity.ok().body(notas);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
		notaService.deletarPorId(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<NotaResponse> atualizar(@PathVariable Integer id, @RequestBody NotaRequest request) {
		NotaResponse nota = notaService.atualizar(id, request);
		return ResponseEntity.ok().body(nota);
	}

	
}
