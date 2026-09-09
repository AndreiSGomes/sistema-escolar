package com.devjava.sistemaescolar.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.devjava.sistemaescolar.entities.Disciplina;
import com.devjava.sistemaescolar.entities.Professor;
import com.devjava.sistemaescolar.entities.Turma;
import com.devjava.sistemaescolar.entities.TurmaDisciplinaProfessor;
import com.devjava.sistemaescolar.repositories.DisciplinaRepository;
import com.devjava.sistemaescolar.repositories.ProfessorRepository;
import com.devjava.sistemaescolar.repositories.TurmaDisciplinaProfessorRepository;
import com.devjava.sistemaescolar.repositories.TurmaRepository;

@Service
public class TurmaDisciplinaProfessorService {

	private TurmaDisciplinaProfessorRepository turmaDisciplinaProfessorRepository;
	private TurmaRepository turmaRepository;
	private DisciplinaRepository disciplinaRepository;
	private ProfessorRepository professorRepository;
	
	public TurmaDisciplinaProfessorService(TurmaDisciplinaProfessorRepository turmaDisciplinaProfessorRepository,
											TurmaRepository turmaRepository,
											DisciplinaRepository disciplinaRepository,
											ProfessorRepository professorRepository) {
		
		this.turmaDisciplinaProfessorRepository = turmaDisciplinaProfessorRepository;
		this.turmaRepository = turmaRepository;
		this.disciplinaRepository = disciplinaRepository;
		this.professorRepository = professorRepository;
	}
	
	public TurmaDisciplinaProfessor insert(TurmaDisciplinaProfessor obj) {
		TurmaDisciplinaProfessor turmaDisciplinaProfessor = new TurmaDisciplinaProfessor();
		
		Optional<Turma> turmaOpt = turmaRepository.findById(obj.getTurma().getId());
		Turma turma = turmaOpt.get();
		
		Optional<Disciplina> disciplinaOpt = disciplinaRepository.findById(obj.getDisciplina().getId());
		Disciplina disciplina = disciplinaOpt.get();
		
		Optional<Professor> professorOpt = professorRepository.findById(obj.getProfessor().getId());
		Professor professor = professorOpt.get();
		
		turmaDisciplinaProfessor.setTurma(turma);
		turmaDisciplinaProfessor.setDisciplina(disciplina);
		turmaDisciplinaProfessor.setProfessor(professor);
		turmaDisciplinaProfessorRepository.save(turmaDisciplinaProfessor);
		
		return turmaDisciplinaProfessor;
	}
	
	public List<TurmaDisciplinaProfessor> findAll() {
		List<TurmaDisciplinaProfessor> turmaDisciplinaProfessor = turmaDisciplinaProfessorRepository.findAll();
		return turmaDisciplinaProfessor;
	}
	
	public TurmaDisciplinaProfessor findById(Integer id) {
		Optional<TurmaDisciplinaProfessor> turmaDisciplinaProfessor = turmaDisciplinaProfessorRepository.findById(id);
		return turmaDisciplinaProfessor.get();
	}
	
}
