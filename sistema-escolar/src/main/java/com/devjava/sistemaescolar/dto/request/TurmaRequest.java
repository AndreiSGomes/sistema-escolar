package com.devjava.sistemaescolar.dto.request;

import com.devjava.sistemaescolar.entities.Turma;

public record TurmaRequest(Integer serie, String turno, Integer anoLetivo, Character complemento) {

	public static Turma toTurma(TurmaRequest request) {
		return new Turma(request.serie(), request.turno(), request.anoLetivo(), request.complemento());
	}
}
