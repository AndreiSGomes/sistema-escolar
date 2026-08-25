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

import com.devjava.sistemaescolar.entities.Aluno;
import com.devjava.sistemaescolar.services.AlunoService;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {
	
	private final AlunoService alunoService;
	

	AlunoController(AlunoService alunoService) {
		this.alunoService = alunoService;
	}
	
	@GetMapping
	public ResponseEntity<List<Aluno>> findAll() {
		List<Aluno> alunos = alunoService.findAll();
		return ResponseEntity.ok().body(alunos);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Aluno> findById(@PathVariable Integer id) {
		Aluno aluno = alunoService.findById(id);
		return ResponseEntity.ok().body(aluno);
	}
	
	@PostMapping
	public ResponseEntity<Aluno> save(@RequestBody Aluno aluno) {
		aluno = alunoService.save(aluno);
		return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
	}
	
}
