package com.br.alura.challengebackend3.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoDestinoDto {

	private String bancoDestino;
	private String agenciaDestino;
	private String contaDestino;
}
