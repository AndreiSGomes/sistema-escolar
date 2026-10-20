package com.devjava.sistemaescolar.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.devjava.sistemaescolar.dto.request.DisciplinaRequest;
import com.devjava.sistemaescolar.dto.response.DisciplinaResponse;
import com.devjava.sistemaescolar.entities.Disciplina;
import com.devjava.sistemaescolar.exceptions.NaoEncontradoException;
import com.devjava.sistemaescolar.repositories.DisciplinaRepository;

@Service
public class DisciplinaService {
	
	private final DisciplinaRepository disciplinaRepository;
	
	public DisciplinaService(DisciplinaRepository disciplinaRepository) {
		this.disciplinaRepository = disciplinaRepository;
	}
	
	public DisciplinaResponse salvar(DisciplinaRequest request) {
		return DisciplinaResponse.toDisciplinaResponse(disciplinaRepository.save(DisciplinaRequest.toDisciplina(request)));
	}
	
	public DisciplinaResponse buscarPorId(Integer id) {
		return DisciplinaResponse.toDisciplinaResponse(disciplinaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(id)));
	}
	
	public List<DisciplinaResponse> buscarTodos() {
		List<DisciplinaResponse> disciplinas = new ArrayList<>();
		
		for(Disciplina disciplina : disciplinaRepository.findAll()) {
			disciplinas.add(DisciplinaResponse.toDisciplinaResponse(disciplina));
		}
		
		return disciplinas;
	}
	
	public void deletarPorId(Integer id) {
		buscarPorId(id);
		disciplinaRepository.deleteById(id);
	}
	
	public DisciplinaResponse atualizar(Integer id, DisciplinaRequest request) {
		Disciplina disciplina = disciplinaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(id));
		
		disciplina.setNome(request.nome());
		disciplina.setCargaHoraria(request.cargaHoraria());
		
		return DisciplinaResponse.toDisciplinaResponse(disciplinaRepository.save(disciplina));		
	}
}
