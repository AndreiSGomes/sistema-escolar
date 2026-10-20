package com.devjava.sistemaescolar.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.devjava.sistemaescolar.dto.request.TurmaRequest;
import com.devjava.sistemaescolar.dto.response.TurmaResponse;
import com.devjava.sistemaescolar.entities.Turma;
import com.devjava.sistemaescolar.exceptions.NaoEncontradoException;
import com.devjava.sistemaescolar.repositories.TurmaRepository;

@Service
public class TurmaService {

	private final TurmaRepository turmaRepository;

	TurmaService(TurmaRepository turmaRepository) {
		this.turmaRepository = turmaRepository;
	}

	public TurmaResponse salvar(TurmaRequest request) {
		return TurmaResponse.toTurmaResponse(turmaRepository.save(TurmaRequest.toTurma(request)));
	}
	
	public TurmaResponse buscarPorId(Integer id) {
		return TurmaResponse.toTurmaResponse(turmaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(id)));
	}
	
	public List<TurmaResponse> buscarTodos() {
		List<TurmaResponse> turmas = new ArrayList<>();
		
		for(Turma turma : turmaRepository.findAll()) {
			turmas.add(TurmaResponse.toTurmaResponse(turma));
		}
		
		return turmas;
	}	
	
	public void deletarPorId(Integer id) {
		buscarPorId(id);
		turmaRepository.deleteById(id);
	}
	
	public TurmaResponse atualizar(Integer id, TurmaRequest request) {
		Turma turma = turmaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(id));
		
		turma.setSerie(request.serie());
		turma.setTurno(request.turno());
		turma.setAnoLetivo(request.anoLetivo());
		turma.setComplemento(request.complemento());
		
		return TurmaResponse.toTurmaResponse(turmaRepository.save(turma));
	}
}
