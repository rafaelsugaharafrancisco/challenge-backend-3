package com.br.alura.challengebackend3.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.br.alura.challengebackend3.model.Arquivo;
import com.br.alura.challengebackend3.model.Usuario;

import lombok.Getter;

@Getter
public class ArquivosDto {

	private Long id;
	private LocalDate dataTransacao;
	private LocalDateTime dataHoraImportacao;
	private Usuario usuario;
	
	public ArquivosDto(Arquivo arq) {
		this.id = arq.getId();
		this.dataTransacao = arq.getDataTransacao();
		this.dataHoraImportacao = arq.getDataHoraImportacao();
		this.usuario = arq.getUsuario();
	}
	
	public Arquivo toArquivo() {
		Arquivo arq = new Arquivo();
		arq.setDataTransacao(dataTransacao);
		arq.setDataHoraImportacao(dataHoraImportacao);
		arq.setUsuario(usuario);
		
		return arq;
	}
	
	public static List<ArquivosDto> obterLista(List<Arquivo> arqs) {
		return arqs.stream().map(ArquivosDto::new).collect(Collectors.toUnmodifiableList());
	}
}
