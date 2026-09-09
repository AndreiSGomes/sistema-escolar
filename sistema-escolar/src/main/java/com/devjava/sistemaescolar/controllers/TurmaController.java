package com.devjava.sistemaescolar.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.devjava.sistemaescolar.entities.Turma;
import com.devjava.sistemaescolar.services.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaController {
	
	private final TurmaService turmaService;
	
	TurmaController(TurmaService turmaService) {
		this.turmaService = turmaService;
	}
	
	@GetMapping
	public ResponseEntity<List<Turma>> findAll() {
		List<Turma> turmas = turmaService.findAll();
		return ResponseEntity.ok().body(turmas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Turma> findById(@PathVariable Integer id) {
		Turma turma = turmaService.findById(id);
		return ResponseEntity.ok().body(turma);
	}
	
	
	@PostMapping
	public ResponseEntity<Turma> save(@RequestBody Turma turma) {
		turma = turmaService.insert(turma);
		return ResponseEntity.status(HttpStatus.CREATED).body(turma);
		
	}
	
	
	
}
