package com.br.alura.challengebackend3.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.alura.challengebackend3.dto.ArquivoForm;
import com.br.alura.challengebackend3.dto.ArquivosDto;
import com.br.alura.challengebackend3.dto.TransacaoDto;
import com.br.alura.challengebackend3.service.ArquivosService;
import com.br.alura.challengebackend3.service.TransacaoService;
import com.br.alura.challengebackend3.service.ValidaFormularioService;
import com.br.alura.challengebackend3.service.arquivo.ArquivoTransacoesService;

@Controller
@RequestMapping("/arquivos")
public class ArquivoController {

	@Autowired
	private ValidaFormularioService formulario;

	@Autowired
	private TransacaoService transacoes;

	@Autowired
	private ArquivosService arquivo;

	@Autowired
	private ArquivoTransacoesService csv;
	
	@GetMapping("importar")
	public String formulario(ArquivoForm form) {

		return "arquivos/formulario";
	}
	
	@PostMapping("importar")
	public ModelAndView carregarArquivo(@ModelAttribute ArquivoForm form, Principal principal) {

		String mensagemErro = formulario.validarCampoArquivo(form.getArquivo());

		if (!mensagemErro.isBlank()) {
			return new ModelAndView("arquivos/formulario").addObject("mensagemErro", mensagemErro);
		}
		
		arquivo.gravarNoDiretorio(form.getArquivo());

		List<TransacaoDto> transacoesDto = csv
				.criarLista(arquivo.carregarDoDiretorio(form.getArquivo().getOriginalFilename()));

		TransacaoDto primeiraTransacaoGravada = transacoes.gravar(transacoesDto);

		arquivo.gravar(primeiraTransacaoGravada, principal);

		return new ModelAndView("redirect:lista");
	}
	
	@GetMapping("lista")
	public ModelAndView lista() {

		return new ModelAndView("arquivos/lista").addObject("arqsCarregados",
				arquivo.listarPorDataTransacaoOrdemDecrescente());
	}
	
	@GetMapping("detalhar/{id}")
	public String detalhar(@PathVariable long id, ModelMap map) {
		ArquivosDto arquivoDto = arquivo.buscarPorId(id);
		map.addAttribute("arquivo", arquivoDto);
		map.addAttribute("transacoes", transacoes.listarPorData(arquivoDto.getDataTransacao()));
		
		return "arquivos/detalhe";
	}
}
