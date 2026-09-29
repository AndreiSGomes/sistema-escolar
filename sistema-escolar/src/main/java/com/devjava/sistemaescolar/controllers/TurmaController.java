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
import com.devjava.sistemaescolar.entities.Turma;
import com.devjava.sistemaescolar.services.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaController {
	
	private final TurmaService turmaService;
	
	TurmaController(TurmaService turmaService) {
		this.turmaService = turmaService;
	}
	
	
	@PostMapping
	public ResponseEntity<Turma> salvar(@RequestBody Turma turma) {
		turma = turmaService.salvar(turma);
		return ResponseEntity.status(HttpStatus.CREATED).body(turma);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Turma> buscarPorId(@PathVariable Integer id) {
		Turma turma = turmaService.buscarPorId(id);
		return ResponseEntity.ok().body(turma);
	}
	
	@GetMapping
	public ResponseEntity<List<Turma>> buscarTodos() {
		List<Turma> turmas = turmaService.buscarTodos();
		return ResponseEntity.ok().body(turmas);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPorid(@PathVariable Integer id) {
		turmaService.deletarPorId(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Turma> atualizar(@PathVariable Integer id, @RequestBody Turma obj) {
		Turma turma = turmaService.atualizar(id, obj);
		return ResponseEntity.ok().body(turma);
	}
	
	
	
}
