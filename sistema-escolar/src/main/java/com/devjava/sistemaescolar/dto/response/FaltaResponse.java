package com.devjava.sistemaescolar.dto.response;

import java.time.LocalDate;

import com.devjava.sistemaescolar.entities.Aluno;
import com.devjava.sistemaescolar.entities.Falta;

public record FaltaResponse(Integer id, LocalDate data, boolean justificada, Aluno aluno) {

	
	public static FaltaResponse toFaltaResponse(Falta falta) {
		return new FaltaResponse(falta.getId(), falta.getData(), falta.getJustificada(), falta.getAluno());
	}
}
