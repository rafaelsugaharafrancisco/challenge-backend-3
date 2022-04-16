package com.br.alura.challengebackend3.exception;

public class TransacaoJaExisteException extends RuntimeException {

	public TransacaoJaExisteException(String mensagem) {
		super(mensagem);
	}
}
