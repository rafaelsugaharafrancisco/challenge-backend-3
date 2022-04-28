package com.br.alura.challengebackend3.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.br.alura.challengebackend3.dto.ImportacoesArqsDto;
import com.br.alura.challengebackend3.dto.TransacaoDto;
import com.br.alura.challengebackend3.model.ImportacoesArqs;
import com.br.alura.challengebackend3.repository.ArquivosRepository;

@Service
public class ArquivosService {

	@Autowired
	private ArquivosRepository repository;

	private final Path root = Paths.get("uploads");

	public void criarDiretorio() {
		try {
			Files.createDirectory(this.root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Resource carregarDoDiretorio(String filename) {
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	public void gravarNoDiretorio(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deletarDiretorio() {
		try {
			Files.deleteIfExists(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void gravar(TransacaoDto trnDto) {

		ImportacoesArqs impArqs = new ImportacoesArqs();
		impArqs.setDataTransacao(LocalDate.from(trnDto.getDataHoraTransacao()));
		impArqs.setDataHoraImportacao(LocalDateTime.now());

		repository.save(new ImportacoesArqsDto(impArqs).toImportacoesArquivo());
	}

	public List<ImportacoesArqsDto> listarPorDataTransacaoOrdemDecrescente() {

		return Collections.unmodifiableList(
				ImportacoesArqsDto.obterLista(repository.findAll(Sort.by(Direction.DESC, "dataTransacao"))));
	}
}