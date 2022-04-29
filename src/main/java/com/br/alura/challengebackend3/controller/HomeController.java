package com.br.alura.challengebackend3.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.alura.challengebackend3.service.UsuarioService;

@Controller
@RequestMapping
public class HomeController {
	
	@Autowired
	private UsuarioService usuario;
	
	@GetMapping("home")
	public ModelAndView home(Principal principal) {
		
		String nome = usuario.pesquisarPorEmail(principal.getName()).getNome();
		
		return new ModelAndView("home").addObject("nomeDoUsuario", nome);
	}
}
