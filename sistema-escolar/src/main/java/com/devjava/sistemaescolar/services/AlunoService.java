package com.devjava.sistemaescolar.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.devjava.sistemaescolar.entities.Aluno;
import com.devjava.sistemaescolar.entities.Turma;
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
	
	public Aluno insert(Aluno obj) {
		Optional<Turma> turmaOpt = turmaRepository.findById(obj.getTurma().getId());	
		Turma turma = turmaOpt.get();
		
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
	
	public List<Aluno> findAll() {
		List<Aluno> alunos = alunoRepository.findAll();
		return alunos;
	}
	
	public Aluno findById(Integer id) {
		Optional<Aluno> obj = alunoRepository.findById(id);
		return obj.get();
	}
	
}
