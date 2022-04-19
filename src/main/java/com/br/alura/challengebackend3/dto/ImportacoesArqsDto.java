package com.br.alura.challengebackend3.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.br.alura.challengebackend3.model.ImportacoesArqs;

import lombok.Getter;

@Getter
public class ImportacoesArqsDto {

	private LocalDate dataTransacao;
	private LocalDateTime dataHoraImportacao;
	
	public ImportacoesArqsDto(ImportacoesArqs impArqs) {
		this.dataTransacao = impArqs.getDataTransacao();
		this.dataHoraImportacao = impArqs.getDataHoraImportacao();
	}
	
	public ImportacoesArqs toImportacoesArquivo() {
		ImportacoesArqs impArq = new ImportacoesArqs();
		impArq.setDataTransacao(dataTransacao);
		impArq.setDataHoraImportacao(dataHoraImportacao);
		
		return impArq;
	}
	
	public static List<ImportacoesArqsDto> obterLista(List<ImportacoesArqs> impArqs) {
		return impArqs.stream().map(ImportacoesArqsDto::new).collect(Collectors.toUnmodifiableList());
	}
}
