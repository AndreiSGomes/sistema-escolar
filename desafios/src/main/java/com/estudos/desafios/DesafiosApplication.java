package com.estudos.desafios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.estudos.desafios.exception.NotNumberException;

@SpringBootApplication
public class DesafiosApplication {
	
	@ExceptionHandler(NotNumberException.class)
	public static void main(String[] args) {
		SpringApplication.run(DesafiosApplication.class, args);
	}

}
