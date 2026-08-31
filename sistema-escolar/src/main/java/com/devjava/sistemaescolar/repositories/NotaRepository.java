package com.devjava.sistemaescolar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devjava.sistemaescolar.entities.Nota;

public interface NotaRepository extends JpaRepository<Nota, Integer>{

}
