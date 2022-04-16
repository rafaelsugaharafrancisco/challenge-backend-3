package com.br.alura.challengebackend3.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.alura.challengebackend3.dto.FormularioArquivo;
import com.br.alura.challengebackend3.dto.TransacaoDto;
import com.br.alura.challengebackend3.service.ImportacoesArqsService;
import com.br.alura.challengebackend3.service.TransacaoService;

@Controller
@RequestMapping("/transacoes")
public class TransacaoController {

	@Autowired
	TransacaoService transacoes;
	
	@Autowired
	ImportacoesArqsService importacoes;
	
	@GetMapping("home")
	public String home(Model model) {
				
		return "home";
	}
	
	@GetMapping("carregar-arquivo")
	public String formulario(FormularioArquivo form) {
		
		return "transacoes/formulario";
	}
	
	@PostMapping("carregar-arquivo")
	public String carregarArquivo(@Valid FormularioArquivo form, BindingResult result) {
		
		if (result.hasErrors()) {
			return "transacoes/formulario";
		}
		
		TransacaoDto primeiraTransacaoGravada = transacoes.gravar(form.getNomeDoArquivo());
		importacoes.gravar(primeiraTransacaoGravada);
		
		return "redirect:arquivos/lista";
	}
}
