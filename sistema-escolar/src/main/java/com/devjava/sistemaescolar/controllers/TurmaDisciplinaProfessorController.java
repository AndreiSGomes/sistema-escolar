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
import com.devjava.sistemaescolar.dto.request.TurmaDisciplinaProfessorRequest;
import com.devjava.sistemaescolar.dto.response.TurmaDisciplinaProfessorResponse;
import com.devjava.sistemaescolar.services.TurmaDisciplinaProfessorService;

@RestController
@RequestMapping("/turmaDisciplinaProfessor")
public class TurmaDisciplinaProfessorController {

	private final TurmaDisciplinaProfessorService turmaDisciplinaProfessorService;
	
	public TurmaDisciplinaProfessorController(TurmaDisciplinaProfessorService turmaDisciplinaProfessorService) {
		this.turmaDisciplinaProfessorService = turmaDisciplinaProfessorService;
	}
	
	@PostMapping
	public ResponseEntity<TurmaDisciplinaProfessorResponse> insert(@RequestBody TurmaDisciplinaProfessorRequest request) {
		TurmaDisciplinaProfessorResponse turmaDisciplinaProfessorResponse = turmaDisciplinaProfessorService.salvar(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(turmaDisciplinaProfessorResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TurmaDisciplinaProfessorResponse> findById(@PathVariable Integer id) {
		TurmaDisciplinaProfessorResponse turmaDisciplinaProfessorResponse = turmaDisciplinaProfessorService.buscarPorId(id);
		return ResponseEntity.ok().body(turmaDisciplinaProfessorResponse);
	}
	
	@GetMapping
	public ResponseEntity<List<TurmaDisciplinaProfessorResponse>> findAll() {
		List<TurmaDisciplinaProfessorResponse> turmaDisciplinaProfessorResponse = turmaDisciplinaProfessorService.buscarTodos();
		return ResponseEntity.ok().body(turmaDisciplinaProfessorResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
		turmaDisciplinaProfessorService.deletarPorId(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TurmaDisciplinaProfessorResponse> atualizar(@PathVariable Integer id, @RequestBody TurmaDisciplinaProfessorRequest request) {
		TurmaDisciplinaProfessorResponse turmaDisciplinaProfessorResponse = turmaDisciplinaProfessorService.atualizar(id, request);
		return ResponseEntity.ok().body(turmaDisciplinaProfessorResponse);
	}
}
