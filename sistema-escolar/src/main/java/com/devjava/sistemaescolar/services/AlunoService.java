package com.devjava.sistemaescolar.services;

import java.util.List;
import org.springframework.stereotype.Service;
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
	
	public Aluno salvar(Aluno obj) {
		Turma turma = turmaRepository.findById(obj.getTurma().getId()).orElseThrow(() -> new NaoEncontradoException(obj.getTurma().getId()));	
		
		Aluno aluno = new Aluno();
		aluno.setNome(obj.getNome());
		aluno.setDataNascimento(obj.getDataNascimento());
		aluno.setEndereco(obj.getEndereco());
		aluno.setNomeResponsavel(obj.getNomeResponsavel());
		aluno.setCpfResponsavel(obj.getCpfResponsavel());
		aluno.setCelularResponsavel(obj.getCelularResponsavel());
		aluno.setEmailResponsavel(obj.getEmailResponsavel());
		aluno.setTurma(turma);
		
		return alunoRepository.save(aluno);
	}
	
	public Aluno buscarPorId(Integer id) {
		Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(id));
		return aluno;
	}
	
	public List<Aluno> buscarTodos() {
		List<Aluno> alunos = alunoRepository.findAll();
		return alunos;
	}
	
	public void deletarPorId(Integer id) {
		buscarPorId(id);
		alunoRepository.deleteById(id);
	}
	
	public Aluno atualizar(Integer id, Aluno obj) {
		Aluno aluno = buscarPorId(id);
		Turma turma = turmaRepository.findById(obj.getTurma().getId()).orElseThrow(() -> new NaoEncontradoException(obj.getTurma().getId())); 
		
		aluno.setNome(obj.getNome());
		aluno.setDataNascimento(obj.getDataNascimento());
		aluno.setEndereco(obj.getEndereco());
		aluno.setNomeResponsavel(obj.getNomeResponsavel());
		aluno.setCpfResponsavel(obj.getCpfResponsavel());
		aluno.setCelularResponsavel(obj.getCelularResponsavel());
		aluno.setEmailResponsavel(obj.getEmailResponsavel());
		aluno.setTurma(turma);
		
		return alunoRepository.save(aluno);
	}
	
}
