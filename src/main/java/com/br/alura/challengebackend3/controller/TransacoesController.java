package com.br.alura.challengebackend3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.alura.challengebackend3.dto.AnaliseTransacoesForm;
import com.br.alura.challengebackend3.service.TransacaoService;

@Controller
@RequestMapping("/transacoes")
public class TransacoesController {

	@Autowired
	private TransacaoService transacoes;

	@GetMapping("analise")
	public ModelAndView formulario(AnaliseTransacoesForm form) {

		return new ModelAndView("transacoes/formulario");
	}

	@PostMapping("analise")
	public ModelAndView analisar(AnaliseTransacoesForm form, ModelMap m) {
		m.addAttribute("naoExisteTransacao", transacoes.naoExisteTransacaoMesAno(form.getMesAno()));
		m.addAttribute("transacoesSuspeitas", transacoes.obterTransacoesSuspeitas(form.getMesAno()));
		m.addAttribute("contasSuspeitas", transacoes.obterContasSuspeitas(form.getMesAno()));
		m.addAttribute("agenciasSuspeitas", transacoes.obterAgenciasSuspeitas(form.getMesAno()));
		
		return new ModelAndView("transacoes/formulario");
	}
}
