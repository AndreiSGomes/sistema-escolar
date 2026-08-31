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
		professor.setCpf(obj.getCpf());
		professor.setNome(obj.getNome());
		professor.setDataNascimento(obj.getDataNascimento());
		professor.setEndereco(obj.getEndereco());
		professor.setCelular(obj.getCelular());
		professor.setEmail(obj.getEmail());
		return professorRepository.save(professor);
	}
	
	public List<Professor> findAll() {
		return professorRepository.findAll();
	}
	
	public Professor findById(Integer id) {
		Optional<Professor> professor = professorRepository.findById(id);
		return professor.get();
	}
	
	/*
	public void delete(Integer id) {
		
	}
	
	
	public Professor update(Integer id) {
		Professor professor = professorRepository.findById(id).get();
		Professor professorNovo = new Professor();
		professorNovo.setNome(professor)
		
		return professor;
	}
	*/
	
	
	
}
