package com.devjava.sistemaescolar.dto.response;

import com.devjava.sistemaescolar.entities.Turma;

public record TurmaResponse(Integer id, Integer serie, String turno, Integer anoLetivo, Character complemento) {

	public static TurmaResponse toTurmaResponse(Turma turma) {
		return new TurmaResponse(turma.getId(), turma.getSerie(), turma.getTurno(), turma.getAnoLetivo(), turma.getComplemento());
	}
}
