package com.devjava.sistemaescolar.exceptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class NaoEncontradoExceptionHandler {

	@ExceptionHandler(NaoEncontradoException.class)
	public ResponseEntity<ErroPadrao> NaoEncontrado(NaoEncontradoException e, HttpServletRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		ErroPadrao erro = new ErroPadrao();
		erro.setTimestamp(LocalDateTime.now());
		erro.setStatus(status.value());
		erro.setError(e.getMessage());
		erro.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(erro);
	}
}
