package com.devjava.sistemaescolar.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.devjava.sistemaescolar.dto.request.FaltaRequest;
import com.devjava.sistemaescolar.dto.response.FaltaResponse;
import com.devjava.sistemaescolar.entities.Aluno;
import com.devjava.sistemaescolar.entities.Falta;
import com.devjava.sistemaescolar.exceptions.NaoEncontradoException;
import com.devjava.sistemaescolar.repositories.AlunoRepository;
import com.devjava.sistemaescolar.repositories.FaltaRepository;

@Service
public class FaltaService {

	private final FaltaRepository faltaRepository;
	private final AlunoRepository alunoRepository;
	
	public FaltaService(FaltaRepository faltaRepository, AlunoRepository alunoRepository) {
		this.faltaRepository = faltaRepository;
		this.alunoRepository = alunoRepository;
	}
	
	public FaltaResponse salvar(FaltaRequest request) {
		Aluno aluno = alunoRepository.findById(request.alunoId()).orElseThrow(() -> new NaoEncontradoException(request.alunoId()));
		
		return FaltaResponse.toFaltaResponse(faltaRepository.save(new Falta(request.data(), request.justificada(), aluno)));
	}
	
	public FaltaResponse buscarPorId(Integer id) {
		return FaltaResponse.toFaltaResponse(faltaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(id)));
	}
	
	public List<FaltaResponse> buscarTodos() {
		List<FaltaResponse> faltas = new ArrayList<>();
		
		for(Falta falta : faltaRepository.findAll()) {
			faltas.add(FaltaResponse.toFaltaResponse(falta));
		}
		
		return faltas;
	}
	
	public void deletarPorId(Integer id) {
		buscarPorId(id);
		faltaRepository.deleteById(id);
	}
	
	public FaltaResponse atualizar(Integer id, FaltaRequest request) {
		Falta falta = faltaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(id));
		
		Aluno aluno = alunoRepository.findById(request.alunoId()).orElseThrow(() -> new NaoEncontradoException(request.alunoId()));
		
		falta.setData(request.data());
		falta.setJustificada(request.justificada());
		falta.setAluno(aluno);
		
		return FaltaResponse.toFaltaResponse(faltaRepository.save(falta));
	}
}
