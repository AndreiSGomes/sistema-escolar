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
import com.devjava.sistemaescolar.entities.TurmaDisciplinaProfessor;
import com.devjava.sistemaescolar.services.TurmaDisciplinaProfessorService;

@RestController
@RequestMapping("/turmaDisciplinaProfessor")
public class TurmaDisciplinaProfessorController {

	private final TurmaDisciplinaProfessorService turmaDisciplinaProfessorService;
	
	public TurmaDisciplinaProfessorController(TurmaDisciplinaProfessorService turmaDisciplinaProfessorService) {
		this.turmaDisciplinaProfessorService = turmaDisciplinaProfessorService;
	}
	
	@PostMapping
	public ResponseEntity<TurmaDisciplinaProfessor> insert(@RequestBody TurmaDisciplinaProfessor obj) {
		TurmaDisciplinaProfessor turmaDisciplinaProfessor = turmaDisciplinaProfessorService.salvar(obj);
		return ResponseEntity.status(HttpStatus.CREATED).body(turmaDisciplinaProfessor);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TurmaDisciplinaProfessor> findById(@PathVariable Integer id) {
		TurmaDisciplinaProfessor turmaDisciplinaProfessor = turmaDisciplinaProfessorService.buscarPorId(id);
		return ResponseEntity.ok().body(turmaDisciplinaProfessor);
	}
	
	@GetMapping
	public ResponseEntity<List<TurmaDisciplinaProfessor>> findAll() {
		List<TurmaDisciplinaProfessor> turmaDisciplinaProfessor = turmaDisciplinaProfessorService.buscarTodos();
		return ResponseEntity.ok().body(turmaDisciplinaProfessor);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
		turmaDisciplinaProfessorService.deletarPorId(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TurmaDisciplinaProfessor> atualizar(@PathVariable Integer id, @RequestBody TurmaDisciplinaProfessor obj) {
		TurmaDisciplinaProfessor turmaDisciplinaProfessor = turmaDisciplinaProfessorService.atualizar(id, obj);
		return ResponseEntity.ok().body(turmaDisciplinaProfessor);
	}
}
