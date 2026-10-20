package com.devjava.sistemaescolar.dto.request;

import java.time.LocalDate;
import com.devjava.sistemaescolar.entities.Professor;

public record ProfessorRequest(
		String cpf, String nome, LocalDate dataNascimento, String endereco, String celular, String email)
{
	public static Professor toProfessor(ProfessorRequest request) {
		
		return new Professor(request.cpf(), request.nome(), request.dataNascimento(), request.endereco(), request.celular(), request.email());
	}
	
}
