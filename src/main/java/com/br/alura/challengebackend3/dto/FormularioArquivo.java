package com.br.alura.challengebackend3.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormularioArquivo {

	@NotBlank(message = "Não pode ser nulo. Selecione um arquivo")
	@Pattern(regexp = ".*.csv", message = "Arquivo inválido. Deve ser apenas extensão .csv")
	private String nomeDoArquivo;
	
	private String tamanhoDoArquivo;
}
