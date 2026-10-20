package com.devjava.sistemaescolar.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devjava.sistemaescolar.dto.request.ProfessorRequest;
import com.devjava.sistemaescolar.dto.response.ProfessorResponse;
import com.devjava.sistemaescolar.entities.Professor;
import com.devjava.sistemaescolar.exceptions.NaoEncontradoException;
import com.devjava.sistemaescolar.repositories.ProfessorRepository;

@Service
public class ProfessorService {
	
	private final ProfessorRepository professorRepository;
	
	public ProfessorService(ProfessorRepository professorRepository) {
		this.professorRepository = professorRepository;
	}
	
	public ProfessorResponse salvar(ProfessorRequest request) {	
		return ProfessorResponse.toProfessorResponse(professorRepository.save(ProfessorRequest.toProfessor(request)));
	}
	
	public ProfessorResponse buscarPorId(Integer id) {
		return ProfessorResponse.toProfessorResponse(professorRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(id)));
	}
	
	public List<ProfessorResponse> buscarTodos() {
		List<ProfessorResponse> professores = new ArrayList<>();
		
		for(Professor professor : professorRepository.findAll()) {
			professores.add(ProfessorResponse.toProfessorResponse(professor));
		}
		
		return professores;
	}
	
	public void deletarPorId(Integer id) {
		buscarPorId(id);
		professorRepository.deleteById(id);
	}
	
	public ProfessorResponse atualizar(Integer id, ProfessorRequest request) {
		Professor professor = professorRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(id));
		
		professor.setNome(request.nome());
		professor.setDataNascimento(request.dataNascimento());
		professor.setEndereco(request.endereco());
		professor.setCpf(request.cpf());
		professor.setCelular(request.celular());
		professor.setEmail(request.email());
		
		return ProfessorResponse.toProfessorResponse(professorRepository.save(professor));
	}
}
