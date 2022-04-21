package com.br.alura.challengebackend3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.alura.challengebackend3.dto.FormularioArquivo;
import com.br.alura.challengebackend3.dto.TransacaoDto;
import com.br.alura.challengebackend3.service.ImportacoesArqsService;
import com.br.alura.challengebackend3.service.TransacaoService;
import com.br.alura.challengebackend3.service.ValidaFormularioService;
import com.br.alura.challengebackend3.service.arquivo.ArquivoTransacoesService;

@Controller
@RequestMapping("/transacoes")
public class TransacaoController {

	@Autowired
	private ValidaFormularioService formulario;

	@Autowired
	private TransacaoService transacoes;

	@Autowired
	private ImportacoesArqsService arquivo;

	@Autowired
	private ArquivoTransacoesService csv;

	@GetMapping("home")
	public String home(Model model) {

		return "home";
	}

	@GetMapping("carregar-arquivo")
	public String formulario(FormularioArquivo form) {

		return "transacoes/formulario";
	}

	@PostMapping("carregar-arquivo")
	public ModelAndView carregarArquivo(@ModelAttribute FormularioArquivo form) {

		String mensagemErro = formulario.validarCampoArquivo(form.getArquivo());

		if (!mensagemErro.isBlank()) {
			return new ModelAndView("transacoes/formulario").addObject("mensagemErro", mensagemErro);
		}

		System.out.println(form.getArquivo().getContentType());
		
		arquivo.gravarNoDiretorio(form.getArquivo());

		List<TransacaoDto> transacoesDto = csv
				.criarLista(arquivo.carregarDoDiretorio(form.getArquivo().getOriginalFilename()));

		TransacaoDto primeiraTransacaoGravada = transacoes.gravar(transacoesDto);

		arquivo.gravar(primeiraTransacaoGravada);

		return new ModelAndView("redirect:arquivos/lista");
	}
}
