package com.devjava.sistemaescolar.exceptions;

public class NaoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	
	public NaoEncontradoException(Integer id) {
		super(String.format("O id(%d) não foi encontrado.", id));
	}

}
