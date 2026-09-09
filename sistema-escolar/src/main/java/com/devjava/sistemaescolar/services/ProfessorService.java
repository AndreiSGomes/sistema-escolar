package com.devjava.sistemaescolar.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.devjava.sistemaescolar.entities.Professor;
import com.devjava.sistemaescolar.repositories.ProfessorRepository;

@Service
public class ProfessorService {
	
	private final ProfessorRepository professorRepository;
	
	public ProfessorService(ProfessorRepository professorRepository) {
		this.professorRepository = professorRepository;
	}
	
	public Professor insert(Professor obj) {
		Professor professor = new Professor();
		professor.setNome(obj.getNome());
		professor.setDataNascimento(obj.getDataNascimento());
		professor.setCpf(obj.getCpf());
		professor.setEndereco(obj.getEndereco());
		professor.setCelular(obj.getCelular());
		professor.setEmail(obj.getEmail());
		
		professorRepository.save(professor);
		return professor;
	}
	
	public Professor findById(Integer id) {
		Optional<Professor> professor = professorRepository.findById(id);
		return professor.get();
	}
	
	public List<Professor> findAll() {
		List<Professor> professores = professorRepository.findAll();
		return professores;
	}
}
