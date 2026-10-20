package com.devjava.sistemaescolar.dto.response;

import com.devjava.sistemaescolar.entities.Aluno;
import com.devjava.sistemaescolar.entities.Disciplina;
import com.devjava.sistemaescolar.entities.Nota;

public record NotaResponse(Integer id, Float valor, Integer bimestre, String descricao, Aluno aluno, Disciplina disciplina) {
	
	public static NotaResponse toNotaResponse(Nota nota) {
		return new NotaResponse(nota.getId(), nota.getValor(), nota.getBimestre(), nota.getDescricao(), nota.getAluno(), nota.getDisciplina());
	}
}
