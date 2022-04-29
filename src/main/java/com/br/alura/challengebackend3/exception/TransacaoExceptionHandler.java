package com.br.alura.challengebackend3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class TransacaoExceptionHandler {

	@ResponseStatus(code = HttpStatus.CONFLICT)
	@ExceptionHandler(TransacaoJaExisteException.class)
	public ModelAndView transacoesJaExistem(TransacaoJaExisteException e) {
		
		return new ModelAndView("transacoes/erro").addObject("mensagemErro", e.getMessage());
	}
}
