package com.br.alura.challengebackend3.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {

	@Email(message = "E-mail inválido! Exemplo de preenchimento: usuario@dominio.com")
	@NotBlank(message = "Não pode estar vazio ou em branco! Preencha com seu e-mail.")
	private String username;
	
	@NotBlank(message = "Não pode estar vazio ou em branco!")
	private String password;
}