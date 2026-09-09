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
import com.devjava.sistemaescolar.entities.Disciplina;
import com.devjava.sistemaescolar.services.DisciplinaService;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

	private final DisciplinaService disciplinaService;
	
	public DisciplinaController(DisciplinaService disciplinaService) {
		this.disciplinaService = disciplinaService;
	}
	
	@PostMapping
	public ResponseEntity<Disciplina> insert(@RequestBody Disciplina obj) {
		Disciplina disciplina = disciplinaService.insert(obj);
		return ResponseEntity.status(HttpStatus.CREATED).body(disciplina);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Disciplina> findById(@PathVariable Integer id) {
		Disciplina disciplina = disciplinaService.findById(id);
		return ResponseEntity.ok().body(disciplina);
	}
	
	@GetMapping
	public ResponseEntity<List<Disciplina>> findAll() {
		List<Disciplina> disciplinas = disciplinaService.findAll();
		return ResponseEntity.ok().body(disciplinas);
	}
	
	
}
