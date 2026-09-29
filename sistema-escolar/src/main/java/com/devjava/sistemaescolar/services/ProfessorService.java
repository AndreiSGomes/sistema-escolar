package com.devjava.sistemaescolar.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.devjava.sistemaescolar.entities.Professor;
import com.devjava.sistemaescolar.exceptions.NaoEncontradoException;
import com.devjava.sistemaescolar.repositories.ProfessorRepository;

@Service
public class ProfessorService {
	
	private final ProfessorRepository professorRepository;
	
	public ProfessorService(ProfessorRepository professorRepository) {
		this.professorRepository = professorRepository;
	}
	
	public Professor salvar(Professor obj) {
		Professor professor = new Professor();
		professor.setNome(obj.getNome());
		professor.setDataNascimento(obj.getDataNascimento());
		professor.setCpf(obj.getCpf());
		professor.setEndereco(obj.getEndereco());
		professor.setCelular(obj.getCelular());
		professor.setEmail(obj.getEmail());
		
		return professorRepository.save(professor);
	}
	
	public Professor buscarPorId(Integer id) {
		Professor professor = professorRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(id));
		return professor;
	}
	
	public List<Professor> buscarTodos() {
		List<Professor> professores = professorRepository.findAll();
		return professores;
	}
	
	public void deletarPorId(Integer id) {
		buscarPorId(id);
		professorRepository.deleteById(id);
	}
	
	public Professor atualizar(Integer id, Professor obj) {
		Professor professor = buscarPorId(id);
		
		professor.setNome(obj.getNome());
		professor.setDataNascimento(obj.getDataNascimento());
		professor.setEndereco(obj.getEndereco());
		professor.setCpf(obj.getCpf());
		professor.setCelular(obj.getCelular());
		professor.setEmail(obj.getEmail());
		
		return professorRepository.save(professor);
	}
}
