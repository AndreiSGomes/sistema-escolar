package com.devjava.sistemaescolar.dto.request;
import com.devjava.sistemaescolar.entities.Aluno;
import com.devjava.sistemaescolar.entities.Disciplina;
import com.devjava.sistemaescolar.entities.Nota;

public record NotaRequest(Float valor, Integer bimestre, String descricao, Integer alunoId, Integer disciplinaId) {
	
	public static Nota toNota(Nota nota, Aluno aluno, Disciplina disciplina) {
		return new Nota(nota.getValor(), nota.getBimestre(), nota.getDescricao(), aluno, disciplina);
	}
}
