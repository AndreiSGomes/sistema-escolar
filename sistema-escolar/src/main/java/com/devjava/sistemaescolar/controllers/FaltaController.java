package com.devjava.sistemaescolar.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devjava.sistemaescolar.entities.Falta;
import com.devjava.sistemaescolar.services.FaltaService;

@RestController

@RequestMapping(value = "/faltas")
public class FaltaController {
	private final FaltaService faltaService;
	
	public FaltaController(FaltaService faltaService) {
		this.faltaService = faltaService;
	}
	
	@PostMapping
	public ResponseEntity<Falta> insert(Falta obj) {
		Falta falta = faltaService.insert(obj);
		return ResponseEntity.status(HttpStatus.CREATED).body(falta);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Falta> findById(@PathVariable Integer id) {
		Falta falta = faltaService.findById(id);
		return ResponseEntity.ok().body(falta);
	}
	
	@GetMapping
	public ResponseEntity<List<Falta>> findAll() {
		List<Falta> faltas = faltaService.findAll();
		return ResponseEntity.ok().body(faltas);
	}
}
