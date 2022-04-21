package com.br.alura.challengebackend3.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ValidaFormularioService {

	public String validarCampoArquivo(MultipartFile arquivo) {

		String mensagem = "";

		if (arquivo.isEmpty()) {
			mensagem = "Operação inválida! O campo ou o arquivo " + arquivo.getOriginalFilename()
					+ " não podem estar vazio.";
		} else if (!arquivo.getContentType().endsWith("csv")) {
			mensagem = "Arquivo inválido! Deve ter extensão .csv";
		}

		return mensagem;
	}
}
