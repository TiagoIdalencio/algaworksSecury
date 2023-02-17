package com.algafood.domain.exception;

public class EmptyResultDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmptyResultDataException (String mensagem) {
		super(mensagem);
	}

}
