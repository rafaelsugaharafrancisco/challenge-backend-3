package com.br.alura.challengebackend3.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
	
	public Usuario toUsuario(String senhaGerada) {
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setSenha(new BCryptPasswordEncoder().encode(senhaGerada));
		usuario.setHabilitado(true);
		
		return usuario;
	}
}
