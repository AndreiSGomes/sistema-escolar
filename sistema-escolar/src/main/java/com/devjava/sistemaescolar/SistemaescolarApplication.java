package com.devjava.sistemaescolar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devjava.sistemaescolar.controllers.AlunoController;
import com.devjava.sistemaescolar.entities.Aluno;

@SpringBootApplication
public class SistemaescolarApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaescolarApplication.class, args);
		
	}

}
