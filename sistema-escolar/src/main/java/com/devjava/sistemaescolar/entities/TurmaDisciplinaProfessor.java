package com.devjava.sistemaescolar.entities;

import java.io.Serializable;
import java.util.Objects;

public class TurmaDisciplinaProfessor implements Serializable {
	private static final long serialVersionUID= 1L; 
	
	private Integer id;
	private Turma turma;
	private Disciplina disciplina;
	private Professor professor;
	
	public TurmaDisciplinaProfessor() {}

	public TurmaDisciplinaProfessor(Integer id, Turma turma, Disciplina disciplina, Professor professor) {
		super();
		this.id = id;
		this.turma = turma;
		this.disciplina = disciplina;
		this.professor = professor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TurmaDisciplinaProfessor other = (TurmaDisciplinaProfessor) obj;
		return Objects.equals(id, other.id);
	}
	
}
