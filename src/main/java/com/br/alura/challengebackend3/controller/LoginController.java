package com.br.alura.challengebackend3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.alura.challengebackend3.dto.LoginForm;
import com.br.alura.challengebackend3.service.UsuarioService;

@Controller
@RequestMapping
public class LoginController {

	@Autowired
	UsuarioService service;
	
	@GetMapping("login")
	public String login() {
		return "login";
	}
//	
//	@PostMapping("login") 
//	public void loginU(LoginForm form) {
//		service.loadUserByUsername(form.getUsername());
//	}
	
}
