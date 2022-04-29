package com.br.alura.challengebackend3.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.br.alura.challengebackend3.model.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioForm {

	private Long id;
	
	@NotBlank(message = "Não pode estar vazio ou em branco! Preencha com seu nome.")
	private String nome;
	
	@Email(message = "E-mail inválido! Exemplo de preenchimento: usuario@dominio.com")
	@NotBlank(message = "Não pode estar vazio ou em branco! Preencha com seu e-mail.")
	private String email;
	
	public Usuario toUsuario(String senhaAleatoria) {
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setSenha(senhaAleatoria);
		usuario.setHabilitado(true);
		
		return usuario;
	}
}
