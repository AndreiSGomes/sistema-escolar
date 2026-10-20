package com.devjava.sistemaescolar.dto.response;

import com.devjava.sistemaescolar.entities.Disciplina;

public record DisciplinaResponse(Integer id, String nome, Integer cargaHoraria) {

	public static DisciplinaResponse toDisciplinaResponse(Disciplina disciplina) {
		return new DisciplinaResponse(disciplina.getId(), disciplina.getNome(), disciplina.getCargaHoraria());
	}
}
