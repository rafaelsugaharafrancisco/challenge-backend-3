package com.br.alura.challengebackend3.analise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import com.br.alura.challengebackend3.dto.AgenciaSuspeitaDto;
import com.br.alura.challengebackend3.dto.ContaSuspeitaDto;
import com.br.alura.challengebackend3.model.Transacao;

public class AnaliseTransacoes {

	private final List<Transacao> transacoesMesAno;

	public AnaliseTransacoes(List<Transacao> transacoes) {

		this.transacoesMesAno = transacoes;
	}

	public List<Transacao> obterTransacoesSuspeitas() {

		return this.transacoesMesAno.stream().filter(t -> t.getValorTransacao() >= 100000.00)
				.collect(Collectors.toList());
	}

	public List<ContaSuspeitaDto> obterContasSuspeitas() {

		List<ContaSuspeitaDto> contasSuspeitas = new ArrayList<ContaSuspeitaDto>();

		Map<Triplet<String, String, String>, Double> contasOrigemSuspeitasStream = this.transacoesMesAno.stream()
				.collect(Collectors.groupingBy(
						t -> Triplet.with(t.getBancoOrigem(), t.getAgenciaOrigem(), t.getContaOrigem()),
						Collectors.summingDouble(Transacao::getValorTransacao)));

		contasOrigemSuspeitasStream.forEach((tuple, valor) -> {
			if (valor >= 1000000.00) {
				ContaSuspeitaDto contaSuspeitaDto = new ContaSuspeitaDto();
				contaSuspeitaDto.setBanco(tuple.getValue0());
				contaSuspeitaDto.setAgencia(tuple.getValue1());
				contaSuspeitaDto.setConta(tuple.getValue2());
				contaSuspeitaDto.setValorMovimentado(valor);
				contaSuspeitaDto.setTipoMovimentacao("Saída");
				contasSuspeitas.add(contaSuspeitaDto);
			}
		});

		Map<Triplet<String, String, String>, Double> contasDestinoSuspeitasStream = this.transacoesMesAno.stream()
				.collect(Collectors.groupingBy(
						t -> Triplet.with(t.getBancoDestino(), t.getAgenciaDestino(), t.getContaDestino()),
						Collectors.summingDouble(Transacao::getValorTransacao)));

		contasDestinoSuspeitasStream.forEach((tuple, valor) -> {
			if (valor >= 1000000.00) {
				ContaSuspeitaDto contaSuspeitaDto = new ContaSuspeitaDto();
				contaSuspeitaDto.setBanco(tuple.getValue0());
				contaSuspeitaDto.setAgencia(tuple.getValue1());
				contaSuspeitaDto.setConta(tuple.getValue2());
				contaSuspeitaDto.setValorMovimentado(valor);
				contaSuspeitaDto.setTipoMovimentacao("Entrada");
				contasSuspeitas.add(contaSuspeitaDto);
			}
		});

		return contasSuspeitas.stream().sorted(Comparator.comparing(ContaSuspeitaDto::getConta))
				.collect(Collectors.toUnmodifiableList());
	}
	
	public List<AgenciaSuspeitaDto> obterAgenciasSuspeitas() {

		List<AgenciaSuspeitaDto> agenciasSuspeitas = new ArrayList<AgenciaSuspeitaDto>();

		Map<Pair<String, String>, Double> agenciasOrigemSuspeitasStream = this.transacoesMesAno.stream()
				.collect(Collectors.groupingBy(
						t -> Pair.with(t.getBancoOrigem(), t.getAgenciaOrigem()),
						Collectors.summingDouble(Transacao::getValorTransacao)));

		agenciasOrigemSuspeitasStream.forEach((tuple, valor) -> {
			if (valor >= 1000000000.00) {
				AgenciaSuspeitaDto agenciaSuspeitaDto = new AgenciaSuspeitaDto();
				agenciaSuspeitaDto.setBanco(tuple.getValue0());
				agenciaSuspeitaDto.setAgencia(tuple.getValue1());
				agenciaSuspeitaDto.setValorMovimentado(valor);
				agenciaSuspeitaDto.setTipoMovimentacao("Saída");
				agenciasSuspeitas.add(agenciaSuspeitaDto);
			}
		});

		Map<Pair<String, String>, Double> agenciasDestinoSuspeitasStream = this.transacoesMesAno.stream()
				.collect(Collectors.groupingBy(
						t -> Pair.with(t.getBancoDestino(), t.getAgenciaDestino()),
						Collectors.summingDouble(Transacao::getValorTransacao)));

		agenciasDestinoSuspeitasStream.forEach((tuple, valor) -> {
			if (valor >= 1000000000.00) {
				AgenciaSuspeitaDto agenciaSuspeitaDto = new AgenciaSuspeitaDto();
				agenciaSuspeitaDto.setBanco(tuple.getValue0());
				agenciaSuspeitaDto.setAgencia(tuple.getValue1());
				agenciaSuspeitaDto.setValorMovimentado(valor);
				agenciaSuspeitaDto.setTipoMovimentacao("Entrada");
				agenciasSuspeitas.add(agenciaSuspeitaDto);
			}
		});

		return agenciasSuspeitas.stream().sorted(Comparator.comparing(AgenciaSuspeitaDto::getAgencia))
				.collect(Collectors.toUnmodifiableList());
	}
}
