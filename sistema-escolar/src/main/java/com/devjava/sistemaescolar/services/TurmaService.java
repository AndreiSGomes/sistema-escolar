package com.devjava.sistemaescolar.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.devjava.sistemaescolar.entities.Turma;
import com.devjava.sistemaescolar.exceptions.NaoEncontradoException;
import com.devjava.sistemaescolar.repositories.TurmaRepository;

@Service
public class TurmaService {

	private final TurmaRepository turmaRepository;

	TurmaService(TurmaRepository turmaRepository) {
		this.turmaRepository = turmaRepository;
	}

	public Turma salvar(Turma obj) {
		Turma turma = new Turma();
		turma.setSerie(obj.getSerie());
		turma.setTurno(obj.getTurno());
		turma.setAnoLetivo(obj.getAnoLetivo());
		turma.setComplemento(obj.getComplemento());
		
		return turmaRepository.save(turma);
	}
	
	public Turma buscarPorId(Integer id) {
		Turma turma = turmaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(id));
		return turma;
	}
	
	public List<Turma> buscarTodos() {
		List<Turma> turmas = turmaRepository.findAll();
		return turmas;
	}	
	
	public void deletarPorId(Integer id) {
		buscarPorId(id);
		turmaRepository.deleteById(id);
	}
	
	public Turma atualizar(Integer id, Turma obj) {
		Turma turma = buscarPorId(id);
		
		turma.setSerie(obj.getSerie());
		turma.setTurno(obj.getTurno());
		turma.setAnoLetivo(obj.getAnoLetivo());
		turma.setComplemento(obj.getComplemento());
		
		return turmaRepository.save(turma);
	}
}
