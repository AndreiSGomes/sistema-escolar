package com.estudos.desafios.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.estudos.desafios.exception.ErroPadrao;
import com.estudos.desafios.exception.NotNumberException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class NotNumberExceptionHandler {

	@ExceptionHandler(NotNumberException.class)
	public ResponseEntity<ErroPadrao> notNumber(NotNumberException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		ErroPadrao erro = new ErroPadrao();
		erro.setTimestamp(LocalDateTime.now());
		erro.setStatus(status.value());
		erro.setError(e.getMessage());
		erro.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(erro);
		
	}
}
