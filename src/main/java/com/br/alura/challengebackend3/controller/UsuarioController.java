package com.br.alura.challengebackend3.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.alura.challengebackend3.dto.UsuarioDto;
import com.br.alura.challengebackend3.dto.UsuarioForm;
import com.br.alura.challengebackend3.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@GetMapping("cadastro")
	public ModelAndView formulario(UsuarioForm form) {
		return new ModelAndView("usuarios/formulario");
	}
	
	@PostMapping("cadastro")
	public ModelAndView cadastrar(@ModelAttribute @Valid UsuarioForm form, BindingResult result) {
		
		if (result.hasErrors()) {
			return new ModelAndView("usuarios/formulario");
		}
		
		UsuarioDto usuarioCadastrado = service.cadastrar(form);
		
		return new ModelAndView("usuarios/sucesso").addObject("usuarioCadastrado", usuarioCadastrado.getNome());
	}
	
	@GetMapping("lista")
	public ModelAndView listar() {
		return new ModelAndView("usuarios/lista").addObject("usuarios", service.listar());
	}
	
	@GetMapping("editar/{id}")
	public ModelAndView mostrarUm(@PathVariable long id) {
		return new ModelAndView("usuarios/formulario-alteracao").addObject("usuarioForm", service.pesquisarPorId(id));
	}
		
	@PostMapping("editar/{id}")
	public ModelAndView editar(@PathVariable long id, @Valid UsuarioForm form, BindingResult result) {
		
		if (result.hasErrors())
			return new ModelAndView("usuarios/formulario-alteracao");
		
		service.editar(id, form);
		
		return new ModelAndView("redirect:/usuarios/lista");
	}
	
	@GetMapping("remover/{id}")
	public ModelAndView remover(@PathVariable long id) {
			
		service.remover(id);
		
		return new ModelAndView("redirect:/usuarios/lista");
	}
	
	@DeleteMapping("deletar/{id}")
	public ModelAndView deletar(@PathVariable long id) {
		service.remover(id);
		
		return new ModelAndView("redirect:/usuarios/lista");
	}
}
