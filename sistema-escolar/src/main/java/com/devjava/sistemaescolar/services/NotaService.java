package com.devjava.sistemaescolar.services;

import java.util.List;
import org.springframework.stereotype.Service;
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
	
	public Nota salvar(Nota obj) {
		Aluno aluno = alunoRepository.findById(obj.getAluno().getId()).orElseThrow(() -> new NaoEncontradoException(obj.getAluno().getId()));
		Disciplina disciplina = disciplinaRepository.findById(obj.getDisciplina().getId()).orElseThrow(() -> new NaoEncontradoException(obj.getDisciplina().getId()));
				
		Nota nota = new Nota();
		nota.setValor(obj.getValor());
		nota.setBimestre(obj.getBimestre());
		nota.setDescricao(obj.getDescricao());
		nota.setAluno(aluno);
		nota.setDisciplina(disciplina);
		
		return notaRepository.save(nota);
	}
	
	public Nota buscarPorId(Integer id) {
		Nota nota = notaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(id));
		return nota;
	}
	
	public List<Nota> buscarTodos() {
		List<Nota> notas = notaRepository.findAll();
		return notas;
	}
	
	public void deletarPorId(Integer id) {
		buscarPorId(id);
		notaRepository.deleteById(id);
	}
	
	public Nota atualizar(Integer id, Nota obj) {
		Nota nota = buscarPorId(id);
		Aluno aluno = alunoRepository.findById(obj.getAluno().getId()).orElseThrow(() -> new NaoEncontradoException(obj.getAluno().getId()));
		Disciplina disciplina = disciplinaRepository.findById(obj.getDisciplina().getId()).orElseThrow(() -> new NaoEncontradoException(obj.getDisciplina().getId()));
				
		nota.setValor(obj.getValor());
		nota.setBimestre(obj.getBimestre());
		nota.setDescricao(obj.getDescricao());
		nota.setAluno(aluno);
		nota.setDisciplina(disciplina);
		
		return notaRepository.save(nota);
	}
	
}
