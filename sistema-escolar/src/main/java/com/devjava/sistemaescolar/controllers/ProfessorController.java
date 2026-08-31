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

import com.devjava.sistemaescolar.entities.Professor;
import com.devjava.sistemaescolar.services.ProfessorService;

@RestController
@RequestMapping(value = "/professores")
public class ProfessorController {
	
	private final ProfessorService professorService;
	
	public ProfessorController(ProfessorService professorService) {
		this.professorService = professorService;
	}
	
	@PostMapping
	public ResponseEntity<Professor> insert(@RequestBody Professor obj) {
		Professor professor = professorService.insert(obj);
		return ResponseEntity.status(HttpStatus.CREATED).body(professor);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Professor> findById(@PathVariable Integer id) {
		Professor professor = professorService.findById(id);
		if(professor != null) {
			return ResponseEntity.ok().body(professor);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(professor);
	}
	
	@GetMapping
	public ResponseEntity<List<Professor>> findAll() {
		List<Professor> professores = professorService.findAll();
		return ResponseEntity.ok().body(professores);
	}
	
}
