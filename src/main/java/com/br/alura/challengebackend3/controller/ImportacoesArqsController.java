package com.br.alura.challengebackend3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.alura.challengebackend3.service.ImportacoesArqsService;

@Controller
@RequestMapping("/transacoes/arquivos")
public class ImportacoesArqsController {

	@Autowired
	private ImportacoesArqsService service;

	@GetMapping("lista")
	public ModelAndView lista() {
		
		return new ModelAndView("arquivos/lista").addObject("arqsCarregados",
				service.listarPorDataTransacaoOrdemDecrescente());
	}
}