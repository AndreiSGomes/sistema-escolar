package com.devjava.sistemaescolar.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.devjava.sistemaescolar.dto.request.TurmaDisciplinaProfessorRequest;
import com.devjava.sistemaescolar.dto.response.TurmaDisciplinaProfessorResponse;
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
	
	public TurmaDisciplinaProfessorResponse salvar(TurmaDisciplinaProfessorRequest request) {
		Turma turma = turmaRepository.findById(request.turmaId()).orElseThrow(() -> new NaoEncontradoException(request.turmaId()));
		Disciplina disciplina = disciplinaRepository.findById(request.disciplinaId()).orElseThrow(() -> new NaoEncontradoException(request.disciplinaId()));
		Professor professor = professorRepository.findById(request.professorId()).orElseThrow(() -> new NaoEncontradoException(request.professorId()));
		TurmaDisciplinaProfessor turmaDisciplinaProfessor = new TurmaDisciplinaProfessor(turma, disciplina, professor);
		
		return TurmaDisciplinaProfessorResponse.toTurmaDisciplinaProfessorResponse(turmaDisciplinaProfessorRepository.save(turmaDisciplinaProfessor));
	}
	
	public List<TurmaDisciplinaProfessorResponse> buscarTodos() {
		List<TurmaDisciplinaProfessorResponse> turmasDisciplinasProfessores = new ArrayList<>();
		for(TurmaDisciplinaProfessor turmaDisciplinaProfessor : turmaDisciplinaProfessorRepository.findAll()) {
			turmasDisciplinasProfessores.add(TurmaDisciplinaProfessorResponse.toTurmaDisciplinaProfessorResponse(turmaDisciplinaProfessor));
		}
		return turmasDisciplinasProfessores;
	}
	
	public TurmaDisciplinaProfessorResponse buscarPorId(Integer id) {
		return TurmaDisciplinaProfessorResponse.toTurmaDisciplinaProfessorResponse(turmaDisciplinaProfessorRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(id)));
	}
	
	public void deletarPorId(Integer id) {
		buscarPorId(id);
		turmaDisciplinaProfessorRepository.deleteById(id);
	}
	
	public TurmaDisciplinaProfessorResponse atualizar(Integer id, TurmaDisciplinaProfessorRequest request) {
		TurmaDisciplinaProfessor turmaDisciplinaProfessor = turmaDisciplinaProfessorRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(id));
		
		Turma turma = turmaRepository.findById(request.turmaId()).orElseThrow(() -> new NaoEncontradoException(request.turmaId()));
		Disciplina disciplina = disciplinaRepository.findById(request.disciplinaId()).orElseThrow(() -> new NaoEncontradoException(request.disciplinaId()));
		Professor professor = professorRepository.findById(request.professorId()).orElseThrow(() -> new NaoEncontradoException(request.professorId()));
		
		turmaDisciplinaProfessor.setTurma(turma);
		turmaDisciplinaProfessor.setDisciplina(disciplina);
		turmaDisciplinaProfessor.setProfessor(professor);
		
		return TurmaDisciplinaProfessorResponse.toTurmaDisciplinaProfessorResponse(turmaDisciplinaProfessorRepository.save(turmaDisciplinaProfessor));
	}
	
}
