package com.br.alura.challengebackend3.exception;

public class UsuarioJaCadastradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioJaCadastradoException(String mensagem) {
		super(mensagem);
	}
}
