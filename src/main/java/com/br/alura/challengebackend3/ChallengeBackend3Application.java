package com.br.alura.challengebackend3;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.alura.challengebackend3.service.ImportacoesArqsService;

@SpringBootApplication
public class ChallengeBackend3Application implements CommandLineRunner {

	@Resource
	ImportacoesArqsService ias; 
	public static void main(String[] args) {
		SpringApplication.run(ChallengeBackend3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ias.deletarDiretorio();
		ias.criarDiretorio();
	}
}
