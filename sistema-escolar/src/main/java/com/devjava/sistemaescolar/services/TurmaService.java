package com.devjava.sistemaescolar.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.devjava.sistemaescolar.entities.Turma;
import com.devjava.sistemaescolar.repositories.TurmaRepository;

@Service
public class TurmaService {

	private final TurmaRepository turmaRepository;

	TurmaService(TurmaRepository turmaRepository) {
		this.turmaRepository = turmaRepository;
	}
	


	public Turma insert(Turma obj) {
		Turma turma = new Turma();
		turma.setSerie(obj.getSerie());
		turma.setTurno(obj.getTurno());
		turma.setAnoLetivo(obj.getAnoLetivo());
		turma.setComplemento(obj.getComplemento());
		return turmaRepository.save(turma);
	}
	
	public Turma findById(Integer id) {
		Optional<Turma> obj = turmaRepository.findById(id);
		return obj.get();
	}
	
	public List<Turma> findAll() {
		List<Turma> turmas = turmaRepository.findAll();
		return turmas;
	}	
	
}
