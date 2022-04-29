package com.br.alura.challengebackend3.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
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

import com.br.alura.challengebackend3.dto.ArquivosDto;
import com.br.alura.challengebackend3.dto.TransacaoDto;
import com.br.alura.challengebackend3.model.Arquivo;
import com.br.alura.challengebackend3.repository.ArquivosRepository;
import com.br.alura.challengebackend3.repository.UsuariosRepository;

@Service
public class ArquivosService {

	@Autowired
	private ArquivosRepository arqsRepository;
	
	@Autowired
	private UsuariosRepository userRepository;

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

	public void gravar(TransacaoDto trnDto, Principal principal) {

		Arquivo arquivo = new Arquivo();
		arquivo.setDataTransacao(LocalDate.from(trnDto.getDataHoraTransacao()));
		arquivo.setDataHoraImportacao(LocalDateTime.now());
		arquivo.setUsuario(userRepository.findByEmail(principal.getName()).get());

		arqsRepository.save(new ArquivosDto(arquivo).toArquivo());
	}

	public List<ArquivosDto> listarPorDataTransacaoOrdemDecrescente() {

		return Collections.unmodifiableList(
				ArquivosDto.obterLista(arqsRepository.findAll(Sort.by(Direction.DESC, "dataTransacao"))));
	}
	
	public ArquivosDto buscarPorId(long id) {
		return new ArquivosDto(arqsRepository.findById(id).get());
	}
}