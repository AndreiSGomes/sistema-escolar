package com.devjava.sistemaescolar.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.devjava.sistemaescolar.dto.request.NotaRequest;
import com.devjava.sistemaescolar.dto.response.NotaResponse;
import com.devjava.sistemaescolar.entities.Aluno;
import com.devjava.sistemaescolar.entities.Disciplina;
import com.devjava.sistemaescolar.entities.Nota;
import com.devjava.sistemaescolar.exceptions.NaoEncontradoException;
import com.devjava.sistemaescolar.repositories.AlunoRepository;
import com.devjava.sistemaescolar.repositories.DisciplinaRepository;
import com.devjava.sistemaescolar.repositories.NotaRepository;

@Service
public class NotaService {
	
	private final NotaRepository notaRepository;
	private final AlunoRepository alunoRepository;
	private final DisciplinaRepository disciplinaRepository;
	
	public NotaService(NotaRepository notaRepository, AlunoRepository alunoRepository, DisciplinaRepository disciplinaRepository) {
		this.notaRepository = notaRepository;
		this.alunoRepository = alunoRepository;
		this.disciplinaRepository = disciplinaRepository;
	}
	
	public NotaResponse salvar(NotaRequest request) {
		Aluno aluno = alunoRepository.findById(request.alunoId()).orElseThrow(() -> new NaoEncontradoException(request.alunoId()));
		Disciplina disciplina = disciplinaRepository.findById(request.disciplinaId()).orElseThrow(() -> new NaoEncontradoException(request.disciplinaId()));
		
		return NotaResponse.toNotaResponse(notaRepository.save(new Nota(request.valor(), request.bimestre(), request.descricao(), aluno, disciplina)));
	}
	
	public NotaResponse buscarPorId(Integer id) {
		return NotaResponse.toNotaResponse(notaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(id)));
	}
	
	public List<NotaResponse> buscarTodos() {
		List<NotaResponse> notas = new ArrayList<>();
		for(Nota nota : notaRepository.findAll()) {
			notas.add(NotaResponse.toNotaResponse(nota));
		}
		
		return notas;
	}
	
	public void deletarPorId(Integer id) {
		buscarPorId(id);
		notaRepository.deleteById(id);
	}
	
	public NotaResponse atualizar(Integer id, NotaRequest request) {
		Nota nota = notaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(id));
		Aluno aluno = alunoRepository.findById(request.alunoId()).orElseThrow(() -> new NaoEncontradoException(request.alunoId()));
		Disciplina disciplina = disciplinaRepository.findById(request.disciplinaId()).orElseThrow(() -> new NaoEncontradoException(request.disciplinaId()));
				
		nota.setValor(request.valor());
		nota.setBimestre(request.bimestre());
		nota.setDescricao(request.descricao());
		nota.setAluno(aluno);
		nota.setDisciplina(disciplina);
		
		return NotaResponse.toNotaResponse(notaRepository.save(nota));
	}
	
}
