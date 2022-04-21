package com.br.alura.challengebackend3.service.arquivo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;

import com.br.alura.challengebackend3.dto.TransacaoDto;
import com.br.alura.challengebackend3.exception.ArquivoVazioException;

public abstract class ArquivoTransacoesService {	
	
	protected void verificarSeArquivoVazio(Resource arquivo) {
			
		try {
			if (arquivo.contentLength() == 0)
				throw new ArquivoVazioException("Erro! Arquivo " + arquivo.getFilename() + " vazio");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected abstract void abrirArquivo(Resource arquivo);
	
	protected abstract List<TransacaoDto> lerLinhasEliminarColunasInvalidas();
	
	protected List<TransacaoDto> eliminarDatasDistintas(List<TransacaoDto> transacoesDto) {
		
		TransacaoDto primeiraTransacao = transacoesDto.stream().findFirst().get();
		LocalDate data = LocalDate.from(primeiraTransacao.getDataHoraTransacao()); 
		
		return transacoesDto.stream()
				.filter(e -> LocalDate.from(e.getDataHoraTransacao()).isEqual(data)).collect(Collectors.toList());
	}

	public List<TransacaoDto> criarLista(Resource arquivo) {
		
		this.verificarSeArquivoVazio(arquivo);
		this.abrirArquivo(arquivo);
		List<TransacaoDto> transacoesColunasValidadas = this.lerLinhasEliminarColunasInvalidas();
		List<TransacaoDto> transacoes = this.eliminarDatasDistintas(transacoesColunasValidadas);
		
		return transacoes;
	}
}
