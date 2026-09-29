package com.devjava.sistemaescolar.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.devjava.sistemaescolar.entities.Disciplina;
import com.devjava.sistemaescolar.entities.Professor;
import com.devjava.sistemaescolar.entities.Turma;
import com.devjava.sistemaescolar.entities.TurmaDisciplinaProfessor;
import com.devjava.sistemaescolar.exceptions.NaoEncontradoException;
import com.devjava.sistemaescolar.repositories.DisciplinaRepository;
import com.devjava.sistemaescolar.repositories.ProfessorRepository;
import com.devjava.sistemaescolar.repositories.TurmaDisciplinaProfessorRepository;
import com.devjava.sistemaescolar.repositories.TurmaRepository;

@Service
public class TurmaDisciplinaProfessorService {

	private final TurmaDisciplinaProfessorRepository turmaDisciplinaProfessorRepository;
	private final TurmaRepository turmaRepository;
	private final DisciplinaRepository disciplinaRepository;
	private final ProfessorRepository professorRepository;
	
	public TurmaDisciplinaProfessorService(TurmaDisciplinaProfessorRepository turmaDisciplinaProfessorRepository, TurmaRepository turmaRepository, DisciplinaRepository disciplinaRepository, ProfessorRepository professorRepository) {
		this.turmaDisciplinaProfessorRepository = turmaDisciplinaProfessorRepository;
		this.turmaRepository = turmaRepository;
		this.disciplinaRepository = disciplinaRepository;
		this.professorRepository = professorRepository;
	}
	
	public TurmaDisciplinaProfessor salvar(TurmaDisciplinaProfessor obj) {
		Turma turma = turmaRepository.findById(obj.getTurma().getId()).orElseThrow(() -> new NaoEncontradoException(obj.getTurma().getId()));
		Disciplina disciplina = disciplinaRepository.findById(obj.getDisciplina().getId()).orElseThrow(() -> new NaoEncontradoException(obj.getDisciplina().getId()));
		Professor professor = professorRepository.findById(obj.getProfessor().getId()).orElseThrow(() -> new NaoEncontradoException(obj.getProfessor().getId()));
		
		TurmaDisciplinaProfessor turmaDisciplinaProfessor = new TurmaDisciplinaProfessor();
		turmaDisciplinaProfessor.setTurma(turma);
		turmaDisciplinaProfessor.setDisciplina(disciplina);
		turmaDisciplinaProfessor.setProfessor(professor);
		
		return turmaDisciplinaProfessorRepository.save(turmaDisciplinaProfessor);
	}
	
	public List<TurmaDisciplinaProfessor> buscarTodos() {
		List<TurmaDisciplinaProfessor> turmaDisciplinaProfessor = turmaDisciplinaProfessorRepository.findAll();
		return turmaDisciplinaProfessor;
	}
	
	public TurmaDisciplinaProfessor buscarPorId(Integer id) {
		TurmaDisciplinaProfessor turmaDisciplinaProfessor = turmaDisciplinaProfessorRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(id));
		return turmaDisciplinaProfessor;
	}
	
	public void deletarPorId(Integer id) {
		buscarPorId(id);
		turmaDisciplinaProfessorRepository.deleteById(id);
	}
	
	public TurmaDisciplinaProfessor atualizar(Integer id, TurmaDisciplinaProfessor obj) {
		TurmaDisciplinaProfessor turmaDisciplinaProfessor = buscarPorId(id);
		
		Turma turma = turmaRepository.findById(obj.getTurma().getId()).orElseThrow(() -> new NaoEncontradoException(obj.getTurma().getId()));
		Disciplina disciplina = disciplinaRepository.findById(obj.getDisciplina().getId()).orElseThrow(() -> new NaoEncontradoException(obj.getDisciplina().getId()));
		Professor professor = professorRepository.findById(obj.getProfessor().getId()).orElseThrow(() -> new NaoEncontradoException(obj.getProfessor().getId()));
		
		turmaDisciplinaProfessor.setTurma(turma);
		turmaDisciplinaProfessor.setDisciplina(disciplina);
		turmaDisciplinaProfessor.setProfessor(professor);
		
		return turmaDisciplinaProfessorRepository.save(turmaDisciplinaProfessor);
	}
	
}
