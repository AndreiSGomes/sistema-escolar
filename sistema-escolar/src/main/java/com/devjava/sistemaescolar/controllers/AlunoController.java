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
import com.devjava.sistemaescolar.entities.Aluno;
import com.devjava.sistemaescolar.services.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
	
	private final AlunoService alunoService;
	

	AlunoController(AlunoService alunoService) {
		this.alunoService = alunoService;
	}
	
	@PostMapping
	public ResponseEntity<Aluno> salvar(@RequestBody Aluno obj) {
		Aluno aluno = alunoService.salvar(obj);
		return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> buscarPorId(@PathVariable Integer id) {
		Aluno aluno = alunoService.buscarPorId(id);
		return ResponseEntity.ok().body(aluno);
	}
	
	@GetMapping
	public ResponseEntity<List<Aluno>> buscarTodos() {
		List<Aluno> alunos = alunoService.buscarTodos();
		return ResponseEntity.ok().body(alunos);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
		alunoService.deletarPorId(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Aluno> atualizar(@PathVariable Integer id, @RequestBody Aluno obj) {
		Aluno aluno = alunoService.atualizar(id, obj);
		return ResponseEntity.ok().body(aluno);
	}
	
}
