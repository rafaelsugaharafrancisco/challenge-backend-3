package com.br.alura.challengebackend3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ArquivoExceptionHandler {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ArquivoVazioException.class)
	public ModelAndView arquivoVazio(ArquivoVazioException e) {
		
		return new ModelAndView("transacoes/erro").addObject("mensagemErro", e.getMessage());
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ArquivoNaoEncontradoException.class)
	public ModelAndView arquivoNaoEncontrado(ArquivoNaoEncontradoException e) {
		
		return new ModelAndView("transacoes/erro").addObject("mensagemErro", e.getMessage());
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ArquivoNaoCorrespondenteTransacoes.class)
	public ModelAndView arquivoNaoCorrespondente(ArquivoNaoCorrespondenteTransacoes e) {
		
		return new ModelAndView("transacoes/erro").addObject("mensagemErro", e.getMessage());
	}
}
