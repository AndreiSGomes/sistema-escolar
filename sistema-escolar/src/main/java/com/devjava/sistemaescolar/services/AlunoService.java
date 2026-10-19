package com.devjava.sistemaescolar.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devjava.sistemaescolar.dto.request.AlunoRequest;
import com.devjava.sistemaescolar.dto.response.AlunoResponse;
import com.devjava.sistemaescolar.entities.Aluno;
import com.devjava.sistemaescolar.entities.Turma;
import com.devjava.sistemaescolar.exceptions.NaoEncontradoException;
import com.devjava.sistemaescolar.repositories.AlunoRepository;
import com.devjava.sistemaescolar.repositories.TurmaRepository;

@Service
public class AlunoService {
	
	private final AlunoRepository alunoRepository;
	private final TurmaRepository turmaRepository;

	AlunoService(AlunoRepository alunoRepository, TurmaRepository turmaRepository) {
		this.alunoRepository = alunoRepository;
		this.turmaRepository = turmaRepository;
	}
	
	public AlunoResponse salvar(AlunoRequest request) {
		Turma turma = turmaRepository.findById(request.turmaId()).orElseThrow(() -> new NaoEncontradoException(request.turmaId()));
		Aluno aluno = AlunoRequest.toAluno(request, turma);
		
		return AlunoResponse.toAlunoResponse(alunoRepository.save(aluno));
	}
	
	public AlunoResponse buscarPorId(Integer id) {
		return AlunoResponse.toAlunoResponse(alunoRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(id)));
	}
	
	public List<AlunoResponse> buscarTodos() {
		List<AlunoResponse> alunos = new ArrayList<>();
		for(Aluno aluno : alunoRepository.findAll()) {
			alunos.add(AlunoResponse.toAlunoResponse(aluno));
		}
		
		return alunos;
	}
	
	public void deletarPorId(Integer id) {
		buscarPorId(id);
		alunoRepository.deleteById(id);
	}
	
	public AlunoResponse atualizar(Integer id, AlunoRequest request) {
		Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(id));
		Turma turma = turmaRepository.findById(aluno.getTurma().getId()).orElseThrow(() -> new NaoEncontradoException(aluno.getTurma().getId())); 
		
		aluno.setNome(request.nome());
		aluno.setDataNascimento(request.dataNascimento());
		aluno.setEndereco(request.endereco());
		aluno.setNomeResponsavel(request.nomeResponsavel());
		aluno.setCpfResponsavel(request.cpfResponsavel());
		aluno.setCelularResponsavel(request.celularResponsavel());
		aluno.setEmailResponsavel(request.emailResponsavel());
		aluno.setTurma(turma);
		
		return AlunoResponse.toAlunoResponse(alunoRepository.save(aluno));
	}
	
}
