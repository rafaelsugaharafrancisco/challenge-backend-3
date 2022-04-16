package com.br.alura.challengebackend3.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.br.alura.challengebackend3.dto.ImportacoesArqsDto;
import com.br.alura.challengebackend3.dto.TransacaoDto;
import com.br.alura.challengebackend3.model.ImportacoesArqs;
import com.br.alura.challengebackend3.repository.ImportacoesArqsRepository;

@Service
public class ImportacoesArqsService {

	@Autowired
	private ImportacoesArqsRepository repository;

	public void gravar(TransacaoDto trnDto) {

		ImportacoesArqs impArqs = new ImportacoesArqs();
		impArqs.setDataTransacao(LocalDate.from(trnDto.getDataHoraTransacao()));
		impArqs.setDataHoraImportacao(LocalDateTime.now());

		repository.save(new ImportacoesArqsDto(impArqs).toImportacoesArquivo());
	}

	public List<ImportacoesArqsDto> listaOrdenada() {

//		List<ImportacoesArqs> listaOrdenada = repository.findAll().stream()
//				.sorted(Comparator.comparing(ImportacoesArqs::getDataTransacao).reversed())
//				.collect(Collectors.toList());
//
//		return ImportacoesArqsDto.obterLista(listaOrdenada);
		
		return ImportacoesArqsDto.obterLista(repository.findAll(Sort.by(Direction.DESC, "dataTransacao")));
	}
}