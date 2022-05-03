package com.br.alura.challengebackend3.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.br.alura.challengebackend3.exception.ErroDeFormularioArqException;

@Service
public class ValidaFormularioArqService {

	public void validarCampoArquivo(MultipartFile arquivo) {

		if (arquivo.isEmpty()) {
			throw new ErroDeFormularioArqException("Operação inválida! O campo ou o arquivo " + arquivo.getOriginalFilename()
					+ " não pode estar vazio.");
		} else if (!arquivo.getContentType().endsWith("csv")) {
			throw new ErroDeFormularioArqException("Arquivo inválido! Deve ter extensão .csv");
		}
	}
}
