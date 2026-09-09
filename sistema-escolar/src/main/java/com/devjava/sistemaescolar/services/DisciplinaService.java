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
		Disciplina disciplina = new Disciplina();
		disciplina.setNome(obj.getNome());
		disciplina.setCargaHoraria(obj.getCargaHoraria());
		return disciplinaRepository.save(disciplina);
	}
	
	public Disciplina findById(Integer id) {
		Optional<Disciplina> falta = disciplinaRepository.findById(id);
		return falta.get();
	}
	
	public List<Disciplina> findAll() {
		List<Disciplina> disciplinas = disciplinaRepository.findAll();
		return disciplinas;
	}
}
