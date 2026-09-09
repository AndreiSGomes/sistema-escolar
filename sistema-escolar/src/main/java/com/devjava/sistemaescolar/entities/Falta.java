package com.devjava.sistemaescolar.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_falta")
public class Falta  implements Serializable {
	private static final long serialVersionUID= 1L; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	private boolean justificada;

	@ManyToOne
	@JoinColumn(name = "aluno_id")
	private Aluno aluno;
	
	public Falta() {}

	public Falta(Integer id, LocalDate data, boolean justificada, Aluno aluno) {
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
	
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
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
