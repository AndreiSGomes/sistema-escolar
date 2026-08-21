package com.devjava.sistemaescolar.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class Aluno implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Date dataNascimento;
	private String endereco;
	private String nomeResponsavel;
	private String cpfResponsavel;
	private String celularResponsavel;
	private String emailResponsavel;
	
	private Turma turma;
	
	public Aluno() { }
	
	public Aluno(Integer id, String nome, Date dataNascimento, String endereco, String nomeResponsavel,
			String cpfResponsavel, String celularResponsavel, String emailResponsavel, Turma turma) {
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.nomeResponsavel = nomeResponsavel;
		this.cpfResponsavel = cpfResponsavel;
		this.celularResponsavel = celularResponsavel;
		this.emailResponsavel = emailResponsavel;
		this.turma = turma;
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

	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}
	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getCpfResponsavel() {
		return cpfResponsavel;
	}
	public void setCpfResponsavel(String cpfResponsavel) {
		this.cpfResponsavel = cpfResponsavel;
	}

	public String getCelularResponsavel() {
		return celularResponsavel;
	}
	public void setCelularResponsavel(String celularResponsavel) {
		this.celularResponsavel = celularResponsavel;
	}

	public String getEmailResponsavel() {
		return emailResponsavel;
	}
	public void setEmailResponsavel(String emailResponsavel) {
		this.emailResponsavel = emailResponsavel;
	}

	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
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
		Aluno other = (Aluno) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return nome;
	}
	

}
