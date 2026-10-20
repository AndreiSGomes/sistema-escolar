package com.devjava.sistemaescolar.dto.response;

import java.time.LocalDate;
import com.devjava.sistemaescolar.entities.Professor;

public record ProfessorResponse(Integer id, String cpf, String nome, LocalDate dataNascimento, String endereco, String celular,String email)
{
	public static ProfessorResponse toProfessorResponse(Professor professor) {
		
		return new ProfessorResponse(professor.getId(), professor.getCpf(), professor.getNome(), professor.getDataNascimento(), professor.getEndereco(), professor.getCelular(), professor.getEmail());
		
	}
}
