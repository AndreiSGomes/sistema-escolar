package com.devjava.sistemaescolar.entities;

import java.io.Serializable;
import java.util.Objects;

public class Nota implements Serializable {
	private static final long serialVersionUID= 1L; 
	
	private Integer id;
	private Integer bimestre;
	private String descricao;
	
	private Aluno aluno;
	private Disciplina disciplina;
	
	public Nota() {}
	
	public Nota(Integer id, Integer bimestre, String descricao, Aluno aluno, Disciplina disciplina) {
		this.id = id;
		this.bimestre = bimestre;
		this.descricao = descricao;
		this.aluno = aluno;
		this.disciplina = disciplina;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBimestre() {
		return bimestre;
	}

	public void setBimestre(Integer bimestre) {
		this.bimestre = bimestre;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
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
		Nota other = (Nota) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
