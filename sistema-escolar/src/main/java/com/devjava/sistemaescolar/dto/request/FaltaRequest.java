package com.devjava.sistemaescolar.dto.request;

import java.time.LocalDate;

import com.devjava.sistemaescolar.entities.Aluno;
import com.devjava.sistemaescolar.entities.Falta;

public record FaltaRequest(LocalDate data, boolean justificada, Integer alunoId) {

	public static Falta toFalta(FaltaRequest request, Aluno aluno) {
		return new Falta(request.data(), request.justificada, aluno);
	}
}
