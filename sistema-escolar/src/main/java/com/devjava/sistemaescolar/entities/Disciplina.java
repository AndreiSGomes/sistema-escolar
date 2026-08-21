package com.devjava.sistemaescolar.entities;

import java.io.Serializable;
import java.util.Objects;

public class Disciplina implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private Integer id;
	private String nome;
	private Integer cargaHoraria;
	
	public Disciplina() {}
	
	public Disciplina(Integer id, String nome, Integer cargaHoraria) {
		this.id = id;
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
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
		Disciplina other = (Disciplina) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
