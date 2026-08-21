package com.devjava.sistemaescolar.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Falta  implements Serializable {
	private static final long serialVersionUID= 1L; 
	
	private Integer id;
	private Date data;
	private boolean justificada;
	
	private Aluno aluno;
	
	public Falta() {}

	public Falta(Integer id, Date data, boolean justificada, Aluno aluno) {
		this.id = id;
		this.data = data;
		this.justificada = justificada;
		this.aluno = aluno;	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean getJustificada() {
		return justificada;
	}

	public void setJustificada(boolean justificada) {
		this.justificada = justificada;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
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
		Falta other = (Falta) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
