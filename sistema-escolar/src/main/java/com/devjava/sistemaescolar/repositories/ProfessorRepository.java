package com.devjava.sistemaescolar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devjava.sistemaescolar.entities.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

}
