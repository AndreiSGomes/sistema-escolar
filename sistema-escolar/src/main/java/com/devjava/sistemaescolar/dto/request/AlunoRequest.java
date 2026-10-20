package com.devjava.sistemaescolar.dto.request;

import java.time.LocalDate;

import com.devjava.sistemaescolar.entities.Aluno;
import com.devjava.sistemaescolar.entities.Turma;

public record AlunoRequest(
		String nome,
		LocalDate dataNascimento, 
		String endereco, 
		String nomeResponsavel, 
		String cpfResponsavel,
		String celularResponsavel,
		String emailResponsavel,
		Integer turmaId
		) {
	
	
	public static Aluno toAluno(AlunoRequest request, Turma turma) {
		return new Aluno(
				request.nome(),
				request.dataNascimento(),
				request.endereco(),
				request.nomeResponsavel(),
				request.cpfResponsavel(),
				request.celularResponsavel(),
				request.emailResponsavel(),
				turma);
	}
}
