package com.devjava.sistemaescolar.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.devjava.sistemaescolar.entities.Falta;
import com.devjava.sistemaescolar.repositories.FaltaRepository;

@Service
public class FaltaService {

	private final FaltaRepository faltaRepository;
	
	public FaltaService(FaltaRepository faltaRepository) {
		this.faltaRepository = faltaRepository;
	}
	
	public Falta insert(Falta obj) {
		Falta falta = faltaRepository.save(obj);
		return falta;
	}
	
	public Falta findById(Integer id) {
		Optional<Falta> falta = faltaRepository.findById(id);
		return falta.get();
	}
	
	public List<Falta> findAll() {
		List<Falta> faltas = faltaRepository.findAll();
		return faltas;
	}
}
