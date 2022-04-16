package com.br.alura.challengebackend3.service.arquivo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.br.alura.challengebackend3.dto.TransacaoDto;
import com.br.alura.challengebackend3.exception.ArquivoNaoCorrespondenteTransacoes;

public abstract class ArquivoTransacoesService {	
	
	protected List<TransacaoDto> filtrarParaDatasIguais(List<TransacaoDto> transacoesDto) {
		
		if (transacoesDto.stream().findFirst().isEmpty())
			throw new ArquivoNaoCorrespondenteTransacoes("Erro no arquivo.");
	
		TransacaoDto primeiraTransacao = transacoesDto.stream().findFirst().get();
		LocalDate data = LocalDate.from(primeiraTransacao.getDataHoraTransacao()); 
		
		return transacoesDto.stream()
				.filter(e -> LocalDate.from(e.getDataHoraTransacao()).isEqual(data)).collect(Collectors.toList());
	}

	public abstract List<TransacaoDto> criarLista(String arquivo);
}
