package com.br.alura.challengebackend3.service;

import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriaSenhaUsuario {

	private final String senhaGeradaAuto;
	
	public CriaSenhaUsuario(){
		this.senhaGeradaAuto = String.valueOf(new Random().nextInt(999999));
	}
	
	public String gerarSenhaBCrypt() {
		System.out.println(senhaGeradaAuto);
		return new BCryptPasswordEncoder().encode(senhaGeradaAuto);
	}

	public String getSenhaGeradaAuto() {
		return senhaGeradaAuto;
	}
}
