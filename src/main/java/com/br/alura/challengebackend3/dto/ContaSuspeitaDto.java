package com.br.alura.challengebackend3.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaSuspeitaDto {

	private String banco;
	private String agencia;
	private String conta;
	private double valorMovimentado;
	private String tipoMovimentacao;
}
