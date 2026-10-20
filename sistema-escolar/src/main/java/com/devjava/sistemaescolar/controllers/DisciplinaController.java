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
import com.devjava.sistemaescolar.dto.request.DisciplinaRequest;
import com.devjava.sistemaescolar.dto.response.DisciplinaResponse;
import com.devjava.sistemaescolar.services.DisciplinaService;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

	private final DisciplinaService disciplinaService;
	
	public DisciplinaController(DisciplinaService disciplinaService) {
		this.disciplinaService = disciplinaService;
	}
	
	@PostMapping
	public ResponseEntity<DisciplinaResponse> salvar(@RequestBody DisciplinaRequest request) {
		DisciplinaResponse disciplina = disciplinaService.salvar(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(disciplina);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DisciplinaResponse> buscarPorId(@PathVariable Integer id) {
		DisciplinaResponse disciplina = disciplinaService.buscarPorId(id);
		return ResponseEntity.ok().body(disciplina);
	}
	
	@GetMapping
	public ResponseEntity<List<DisciplinaResponse>> buscarTodos() {
		List<DisciplinaResponse> disciplinas = disciplinaService.buscarTodos();
		return ResponseEntity.ok().body(disciplinas);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
		disciplinaService.deletarPorId(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DisciplinaResponse> atualizar(@PathVariable Integer id, @RequestBody DisciplinaRequest request) {
		DisciplinaResponse disciplina = disciplinaService.atualizar(id, request);
		return ResponseEntity.ok().body(disciplina);
	}
}
