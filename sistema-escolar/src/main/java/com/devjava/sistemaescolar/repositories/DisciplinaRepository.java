
package com.devjava.sistemaescolar.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.devjava.sistemaescolar.entities.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {

}