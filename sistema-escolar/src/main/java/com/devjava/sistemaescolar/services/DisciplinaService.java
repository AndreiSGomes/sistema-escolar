package com.devjava.sistemaescolar.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.devjava.sistemaescolar.entities.Disciplina;
import com.devjava.sistemaescolar.repositories.DisciplinaRepository;

@Service
public class DisciplinaService {

	private final DisciplinaRepository disciplinaRepository;
	
	public DisciplinaService(DisciplinaRepository disciplinaRepository) {
		this.disciplinaRepository = disciplinaRepository;
	}
	
	public Disciplina insert(Disciplina obj) {
		Disciplina disciplina = disciplinaRepository.save(obj);
		return disciplina;
	}
	
	public Disciplina findById(Integer id) {
		Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
		return disciplina.get();
	}
	
	public List<Disciplina> findAll() {
		List<Disciplina> disciplinas = disciplinaRepository.findAll();
		return disciplinas;
	}
}
