package com.devjava.sistemaescolar.entities;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_turma_disciplina_professor")
public class TurmaDisciplinaProfessor implements Serializable {
	private static final long serialVersionUID= 1L; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "turma_id")
	private Turma turma;
	@ManyToOne
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;
	@ManyToOne
	@JoinColumn(name = "professor_id")
	private Professor professor;
	
	public TurmaDisciplinaProfessor() {}

	public TurmaDisciplinaProfessor(Integer id, Turma turma, Disciplina disciplina, Professor professor) {
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
