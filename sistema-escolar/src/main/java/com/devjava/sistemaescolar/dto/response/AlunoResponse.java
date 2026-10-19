package com.devjava.sistemaescolar.dto.response;

import java.time.LocalDate;
import com.devjava.sistemaescolar.entities.Aluno;
import com.devjava.sistemaescolar.entities.Turma;

public record AlunoResponse(
		Integer id,
		String nome,
		LocalDate dataNascimento, 
		String endereco, 
		String nomeResponsavel, 
		String cpfResponsavel,
		String celularResponsavel,
		String emailResponsavel,
		Turma turma
		) {
	
	public static AlunoResponse toAlunoResponse(Aluno aluno) {
		return new AlunoResponse(
				aluno.getId(),
				aluno.getNome(),
				aluno.getDataNascimento(),
				aluno.getEndereco(),
				aluno.getNomeResponsavel(),
				aluno.getCpfResponsavel(),
				aluno.getCelularResponsavel(),
				aluno.getEmailResponsavel(),
				aluno.getTurma());
	}
}
