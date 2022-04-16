package com.br.alura.challengebackend3.exception;

public class ArquivoNaoCorrespondenteTransacoes extends RuntimeException {

	public ArquivoNaoCorrespondenteTransacoes(String mensagem) {
		super(mensagem);
	}
}
