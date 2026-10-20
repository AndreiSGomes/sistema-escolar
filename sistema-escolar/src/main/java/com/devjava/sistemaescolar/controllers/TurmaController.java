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
import com.devjava.sistemaescolar.dto.request.TurmaRequest;
import com.devjava.sistemaescolar.dto.response.TurmaResponse;
import com.devjava.sistemaescolar.services.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaController {
	
	private final TurmaService turmaService;
	
	TurmaController(TurmaService turmaService) {
		this.turmaService = turmaService;
	}
	
	
	@PostMapping
	public ResponseEntity<TurmaResponse> salvar(@RequestBody TurmaRequest request) {
		TurmaResponse turma = turmaService.salvar(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(turma);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TurmaResponse> buscarPorId(@PathVariable Integer id) {
		TurmaResponse turma = turmaService.buscarPorId(id);
		return ResponseEntity.ok().body(turma);
	}
	
	@GetMapping
	public ResponseEntity<List<TurmaResponse>> buscarTodos() {
		List<TurmaResponse> turmas = turmaService.buscarTodos();
		return ResponseEntity.ok().body(turmas);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPorid(@PathVariable Integer id) {
		turmaService.deletarPorId(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TurmaResponse> atualizar(@PathVariable Integer id, @RequestBody TurmaRequest request) {
		TurmaResponse turma = turmaService.atualizar(id, request);
		return ResponseEntity.ok().body(turma);
	}
	
	
	
}
