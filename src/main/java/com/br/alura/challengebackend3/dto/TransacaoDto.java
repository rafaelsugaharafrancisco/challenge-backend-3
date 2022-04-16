package com.br.alura.challengebackend3.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.br.alura.challengebackend3.model.Transacao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoDto {
	
	private String bancoOrigem;
	private String agenciaOrigem;
	private String contaOrigem;
	private String bancoDestino;
	private String agenciaDestino;
	private String contaDestino;
	private double valorTransacao;
	private LocalDateTime dataHoraTransacao;
	
	public TransacaoDto() {}
	
	public TransacaoDto(Transacao trn) {
		this.bancoOrigem = trn.getBancoOrigem();
		this.agenciaOrigem = trn.getAgenciaOrigem();
		this.contaOrigem = trn.getContaOrigem();
		this.bancoDestino = trn.getBancoDestino();
		this.agenciaDestino = trn.getAgenciaDestino();
		this.contaDestino = trn.getContaDestino();
		this.valorTransacao = trn.getValorTransacao();
		this.dataHoraTransacao = trn.getDataHoraTransacao();
	}
	
	public Transacao toTransacao() {
		Transacao transacao = new Transacao();
		transacao.setBancoOrigem(bancoOrigem);
		transacao.setAgenciaOrigem(agenciaOrigem);
		transacao.setContaOrigem(contaOrigem);
		transacao.setBancoDestino(bancoDestino);
		transacao.setAgenciaDestino(agenciaDestino);
		transacao.setContaDestino(contaDestino);
		transacao.setValorTransacao(valorTransacao);
		transacao.setDataHoraTransacao(dataHoraTransacao);
		
		return transacao;
	}
	
	public static List<TransacaoDto> obterLista(List<Transacao> transacoes) {
		return transacoes.stream().map(TransacaoDto::new).collect(Collectors.toList());
	}
}
