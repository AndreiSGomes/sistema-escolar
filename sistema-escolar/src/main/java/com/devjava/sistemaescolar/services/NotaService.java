package com.devjava.sistemaescolar.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.devjava.sistemaescolar.entities.Aluno;
import com.devjava.sistemaescolar.entities.Disciplina;
import com.devjava.sistemaescolar.entities.Nota;
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
	
	public Nota insert(Nota obj) {
		Optional<Aluno> aluno = alunoRepository.findById(obj.getAluno().getId());
		Optional<Disciplina> disciplina = disciplinaRepository.findById(obj.getDisciplina().getId());
				
		Nota nota = new Nota();
		nota.setValor(obj.getValor());
		nota.setBimestre(obj.getBimestre());
		nota.setDescricao(obj.getDescricao());
		nota.setAluno(aluno.get());
		nota.setDisciplina(disciplina.get());
		
		return notaRepository.save(nota);
	}
	
	public Nota findById(Integer id) {
		Optional<Nota> obj = notaRepository.findById(id);
		return obj.get();
	}
	
	public List<Nota> findAll() {
		List<Nota> notas = notaRepository.findAll();
		return notas;
	}
	
}
