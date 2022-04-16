package com.br.alura.challengebackend3.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "transacoes")
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String bancoOrigem;
	
	private String agenciaOrigem;
	
	private String contaOrigem;
	
	private String bancoDestino;
	
	private String agenciaDestino;
	
	private String contaDestino;
	
	private double valorTransacao;
	
	@Column(unique = true)
	private LocalDateTime dataHoraTransacao;
}
