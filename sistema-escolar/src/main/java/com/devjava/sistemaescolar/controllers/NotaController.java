package com.devjava.sistemaescolar.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devjava.sistemaescolar.entities.Nota;
import com.devjava.sistemaescolar.services.NotaService;

@RestController
@RequestMapping(value = "/notas")
public class NotaController {
	
	private final NotaService notaService;
	
	public NotaController(NotaService notaService) {
		this.notaService = notaService;
	}
	
	@PostMapping
	public ResponseEntity<Nota> insert(Nota obj){
		Nota nota = notaService.insert(obj);
		return ResponseEntity.status(HttpStatus.CREATED).body(nota);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Nota> findById(@PathVariable Integer id){
		Nota nota =  notaService.findById(id);
		return ResponseEntity.ok().body(nota);
	}
	
	@GetMapping
	public ResponseEntity<List<Nota>> findAll(){
		List<Nota> notas = notaService.findAll();
		return ResponseEntity.ok().body(notas);
	}

	
}
