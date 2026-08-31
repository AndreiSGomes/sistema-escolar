package com.devjava.sistemaescolar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devjava.sistemaescolar.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

}
