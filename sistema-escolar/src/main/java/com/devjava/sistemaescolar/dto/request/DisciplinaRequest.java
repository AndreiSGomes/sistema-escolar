package com.devjava.sistemaescolar.dto.request;

import com.devjava.sistemaescolar.entities.Disciplina;

public record DisciplinaRequest(String nome, Integer cargaHoraria) {
	
	public static Disciplina toDisciplina(DisciplinaRequest request) {
		return new Disciplina(request.nome(), request.cargaHoraria());
	}
}
