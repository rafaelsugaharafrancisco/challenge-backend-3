package com.br.alura.challengebackend3.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "arquivosImportados")
public class Arquivo {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate dataTransacao;
	
	private LocalDateTime dataHoraImportacao;
	
	@ManyToOne
	private Usuario usuario;

	public LocalDate getDataTransacao() {
		return dataTransacao;
	}

	public Long getId() {
		return id;
	}
	
	public void setDataTransacao(LocalDate dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	public LocalDateTime getDataHoraImportacao() {
		return dataHoraImportacao;
	}

	public void setDataHoraImportacao(LocalDateTime dataHoraImportacao) {
		this.dataHoraImportacao = dataHoraImportacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}