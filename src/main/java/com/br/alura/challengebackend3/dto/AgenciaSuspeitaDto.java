package com.br.alura.challengebackend3.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgenciaSuspeitaDto {
	
	private String banco;
	private String agencia;
	private double valorMovimentado;
	private String tipoMovimentacao;
}
