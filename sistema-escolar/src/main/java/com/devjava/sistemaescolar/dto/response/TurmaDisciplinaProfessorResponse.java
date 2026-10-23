package com.devjava.sistemaescolar.dto.response;

import com.devjava.sistemaescolar.entities.Disciplina;
import com.devjava.sistemaescolar.entities.Professor;
import com.devjava.sistemaescolar.entities.Turma;
import com.devjava.sistemaescolar.entities.TurmaDisciplinaProfessor;

public record TurmaDisciplinaProfessorResponse(Integer id, Turma turma, Disciplina disciplina, Professor professor) {

	public static TurmaDisciplinaProfessorResponse toTurmaDisciplinaProfessorResponse(TurmaDisciplinaProfessor turmaDisciplinaProfessor) {
		return new TurmaDisciplinaProfessorResponse(turmaDisciplinaProfessor.getId(), turmaDisciplinaProfessor.getTurma(), turmaDisciplinaProfessor.getDisciplina(), turmaDisciplinaProfessor.getProfessor());
				
				
	}
}