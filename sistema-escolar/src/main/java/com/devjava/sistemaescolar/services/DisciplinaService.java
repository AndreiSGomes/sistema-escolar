package com.devjava.sistemaescolar.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.devjava.sistemaescolar.entities.Disciplina;
import com.devjava.sistemaescolar.exceptions.NaoEncontradoException;
import com.devjava.sistemaescolar.repositories.DisciplinaRepository;

@Service
public class DisciplinaService {
	
	private final DisciplinaRepository disciplinaRepository;
	
	public DisciplinaService(DisciplinaRepository disciplinaRepository) {
		this.disciplinaRepository = disciplinaRepository;
	}
	
	public Disciplina salvar(Disciplina obj) {
		Disciplina disciplina = new Disciplina();
		disciplina.setNome(obj.getNome());
		disciplina.setCargaHoraria(obj.getCargaHoraria());
		
		return disciplinaRepository.save(disciplina);
	}
	
	public Disciplina buscarPorId(Integer id) {
		Disciplina disciplina = disciplinaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(id));
		return disciplina;
	}
	
	public List<Disciplina> buscarTodos() {
		List<Disciplina> disciplinas = disciplinaRepository.findAll();
		return disciplinas;
	}
	
	public void deletarPorId(Integer id) {
		buscarPorId(id);
		disciplinaRepository.deleteById(id);
	}
	
	public Disciplina atualizar(Integer id, Disciplina obj) {
		Disciplina disciplina = buscarPorId(id);
		disciplina.setNome(obj.getNome());
		disciplina.setCargaHoraria(obj.getCargaHoraria());
		
		return disciplinaRepository.save(disciplina);		
	}
}
