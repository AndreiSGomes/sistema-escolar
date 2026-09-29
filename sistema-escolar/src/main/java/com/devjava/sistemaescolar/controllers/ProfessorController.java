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
import com.devjava.sistemaescolar.entities.Professor;
import com.devjava.sistemaescolar.services.ProfessorService;

@RestController
@RequestMapping("/professores")
public class ProfessorController {
	
	private final ProfessorService professorService;
	
	public ProfessorController(ProfessorService professorService) {
		this.professorService = professorService;
	}
	
	@PostMapping
	public ResponseEntity<Professor> salvar(@RequestBody Professor obj) {
		Professor professor = professorService.salvar(obj);
		return ResponseEntity.status(HttpStatus.CREATED).body(professor);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Professor> buscarPorId(@PathVariable Integer id) {
		Professor professor = professorService.buscarPorId(id);
		return ResponseEntity.ok().body(professor);
	}
	
	@GetMapping
	public ResponseEntity<List<Professor>> buscarTodos() {
		List<Professor> professores = professorService.buscarTodos();
		return ResponseEntity.ok().body(professores);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
		professorService.deletarPorId(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Professor> atualizar(@PathVariable Integer id, @RequestBody Professor obj) {
		Professor professor = professorService.atualizar(id, obj);
		return ResponseEntity.ok().body(professor);
	}
	
}
