package com.devjava.sistemaescolar.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_turma")
public class Turma implements Serializable {
	private static final long serialVersionUID= 1L; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer serie;
	private String turno;
	@Column(name = "ano_letivo")
	private Integer anoLetivo;
	private Character complemento;
	
	@JsonIgnore
	@OneToMany(mappedBy = "turma")
	private Set<Aluno> alunos = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "turma")
	private List<TurmaDisciplinaProfessor> turmaDisciplinaProfessor = new ArrayList<>();
	
	public Turma() {}

	public Turma(Integer id, Integer serie, String turno, Integer anoLetivo, Character complemento) {
		super();
		this.id = id;
		this.serie = serie;
		this.turno = turno;
		this.anoLetivo = anoLetivo;
		this.complemento = complemento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSerie() {
		return serie;
	}

	public void setSerie(Integer serie) {
		this.serie = serie;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public Integer getAnoLetivo() {
		return anoLetivo;
	}

	public void setAnoLetivo(Integer anoLetivo) {
		this.anoLetivo = anoLetivo;
	}

	public char getComplemento() {
		return complemento;
	}

	public void setComplemento(Character complemento) {
		this.complemento = complemento;
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
		Turma other = (Turma) obj;
		return Objects.equals(id, other.id);
	}
	
}
