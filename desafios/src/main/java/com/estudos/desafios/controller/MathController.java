package com.estudos.desafios.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.desafios.exception.NotNumberException;

@RestController
@RequestMapping("/api/math")
public class MathController {
		
	@GetMapping("/times/{numeroUm}/{numeroDois}")
	public Integer multiplicacao(@PathVariable String numeroUm, @PathVariable String numeroDois) {
		if(isNumeric(numeroUm) && isNumeric(numeroDois)) {
			return Integer.parseInt(numeroUm) * Integer.parseInt(numeroDois);
		}
		throw new NotNumberException("O(s) valor(res) passado(s) não é(são) numérico(s).");
	}
	
	private boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
}

