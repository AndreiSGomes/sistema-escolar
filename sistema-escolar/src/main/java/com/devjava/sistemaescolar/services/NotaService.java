package com.devjava.sistemaescolar.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.devjava.sistemaescolar.entities.Nota;
import com.devjava.sistemaescolar.repositories.NotaRepository;

@Service
public class NotaService {
	
	private final NotaRepository notaRepository;

	public NotaService(NotaRepository notaRepository) {
		this.notaRepository = notaRepository;
	}

	public Nota insert(Nota obj) {
		Nota nota = notaRepository.save(obj);
		return nota;
	}

	public Nota findById(Integer id) {
		Optional<Nota> nota = notaRepository.findById(id);
		return nota.get();
	}

	public List<Nota> findAll() {
		List<Nota> notas = notaRepository.findAll();
		return notas;
	}
}
