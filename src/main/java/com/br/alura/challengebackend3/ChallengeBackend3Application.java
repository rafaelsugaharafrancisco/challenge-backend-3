package com.br.alura.challengebackend3;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.br.alura.challengebackend3.model.Perfil;
import com.br.alura.challengebackend3.model.Usuario;
import com.br.alura.challengebackend3.repository.PerfisRepository;
import com.br.alura.challengebackend3.repository.UsuariosRepository;
import com.br.alura.challengebackend3.service.ArquivosService;

@SpringBootApplication
public class ChallengeBackend3Application implements CommandLineRunner {

	@Resource
	ArquivosService ias;
	
	@Resource
	UsuariosRepository rep;
	
	@Resource
	PerfisRepository repAut;
	
	public static void main(String[] args) {
		SpringApplication.run(ChallengeBackend3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ias.deletarDiretorio();
		ias.criarDiretorio();
		
//		Usuario user = new Usuario();
//		user.setNome("admin");
//		user.setEmail("admin@email.com.br");
//		user.setSenha(new BCryptPasswordEncoder().encode("123999"));
//		user.setHabilitado(true);
//		Perfil aut = new Perfil(user, "PADRAO");
//		rep.save(user);
//		repAut.save(aut);
	}
}
