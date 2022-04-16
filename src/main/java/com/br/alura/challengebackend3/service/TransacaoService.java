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
import com.br.alura.challengebackend3.service.arquivo.ArquivoTransacoesService;


@Service
public class TransacaoService {
	
	@Autowired
	private TransacoesRepository repository;
	
	@Autowired
	private ArquivoTransacoesService csv;
	
	@Transactional
	public TransacaoDto gravar(String arquivo) {
		
		List<TransacaoDto> transacoesDto = csv.criarLista(arquivo);
		
		LocalDate data = LocalDate.from(transacoesDto.stream().findFirst().get().getDataHoraTransacao());
		
		boolean existeTransacao = repository.findAll().stream()
				.anyMatch(e -> LocalDate.from(e.getDataHoraTransacao()).isEqual(data));
		
		if (existeTransacao)
			throw new TransacaoJaExisteException("Transações com a data " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) 
			+ " já existem");
		
		List<Transacao> transacoes = new ArrayList<Transacao>();
		transacoesDto.forEach(trnDto -> transacoes.add(trnDto.toTransacao()));
		
		repository.saveAll(transacoes);
		
		return transacoesDto.stream().findFirst().get();
	}
}
