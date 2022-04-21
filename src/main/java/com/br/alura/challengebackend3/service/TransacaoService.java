package com.br.alura.challengebackend3.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.alura.challengebackend3.dto.TransacaoDto;
import com.br.alura.challengebackend3.exception.TransacaoJaExisteException;
import com.br.alura.challengebackend3.model.Transacao;
import com.br.alura.challengebackend3.repository.TransacoesRepository;

@Service
public class TransacaoService {

	@Autowired
	private TransacoesRepository repository;

	@Transactional
	public TransacaoDto gravar(List<TransacaoDto> transacoesDto) {

		TransacaoDto transacaoDto = transacoesDto.stream().findFirst().get();
		
		LocalDate dataTransacao = LocalDate.from(transacaoDto.getDataHoraTransacao());

		boolean existeTransacao = repository.findAll().stream()
				.anyMatch(e -> LocalDate.from(e.getDataHoraTransacao()).isEqual(dataTransacao));

		if (existeTransacao)
			throw new TransacaoJaExisteException(
					"Já existem transações com a data " + dataTransacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

		List<Transacao> transacoes = new ArrayList<Transacao>();
		transacoesDto.forEach(trnDto -> transacoes.add(trnDto.toTransacao()));

		repository.saveAll(transacoes);

		return transacaoDto;
	}
}
