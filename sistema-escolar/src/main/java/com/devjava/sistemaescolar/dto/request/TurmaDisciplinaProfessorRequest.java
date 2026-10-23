package com.devjava.sistemaescolar.dto.request;

import com.devjava.sistemaescolar.entities.Disciplina;
import com.devjava.sistemaescolar.entities.Professor;
import com.devjava.sistemaescolar.entities.Turma;
import com.devjava.sistemaescolar.entities.TurmaDisciplinaProfessor;

public record TurmaDisciplinaProfessorRequest(Integer turmaId, Integer disciplinaId, Integer professorId) {

	public static TurmaDisciplinaProfessor toTurmaDisciplinaProfessor(Turma turma, Disciplina disciplina, Professor professor) {
		return new TurmaDisciplinaProfessor(turma, disciplina, professor);
				
				
	}
}
