package com.br.alura.challengebackend3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class UsuarioExceptionHandler {

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(UsuarioNaoExisteException.class)
	public ModelAndView usuarioNãoExisteException(UsuarioNaoExisteException e) {
		
		return new ModelAndView("usuarios/erro").addObject("mensagemErro", e.getMessage());
	}
	
	@ResponseStatus(code = HttpStatus.CONFLICT)
	@ExceptionHandler(UsuarioJaCadastradoException.class)
	public ModelAndView usuarioJaCadastrado(UsuarioJaCadastradoException e) {
		
		return new ModelAndView("usuarios/erro").addObject("mensagemErro", e.getMessage());
	}
}
