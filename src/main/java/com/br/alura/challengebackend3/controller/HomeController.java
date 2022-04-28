package com.br.alura.challengebackend3.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class HomeController {
	
	@GetMapping("home")
	public ModelAndView home(Principal principal) {
		
		return new ModelAndView("home");
	}
}
