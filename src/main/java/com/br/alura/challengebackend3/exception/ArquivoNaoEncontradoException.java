package com.br.alura.challengebackend3.exception;

public class ArquivoNaoEncontradoException extends RuntimeException {

	public ArquivoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
}
