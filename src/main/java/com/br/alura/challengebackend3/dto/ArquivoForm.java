package com.br.alura.challengebackend3.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArquivoForm {

	private MultipartFile arquivo;
	
	private String tamanhoDoArquivo;
}
