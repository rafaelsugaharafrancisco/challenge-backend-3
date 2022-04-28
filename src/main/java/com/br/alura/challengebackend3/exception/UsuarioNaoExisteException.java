package com.br.alura.challengebackend3.exception;

public class UsuarioNaoExisteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioNaoExisteException(String mensagem) {
		super(mensagem);
	}
	
}
