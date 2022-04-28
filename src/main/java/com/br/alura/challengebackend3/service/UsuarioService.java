package com.br.alura.challengebackend3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.alura.challengebackend3.dto.UsuarioDto;
import com.br.alura.challengebackend3.dto.UsuarioForm;
import com.br.alura.challengebackend3.exception.UsuarioJaCadastradoException;
import com.br.alura.challengebackend3.exception.UsuarioNaoExisteException;
import com.br.alura.challengebackend3.model.Perfil;
import com.br.alura.challengebackend3.model.Usuario;
import com.br.alura.challengebackend3.repository.PerfisRepository;
import com.br.alura.challengebackend3.repository.UsuariosRepository;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuariosRepository usuarioRepository;
	
	@Autowired
	private PerfisRepository perfilRepository;
	
	@Autowired
	private JavaMailSender mailSender;

	@Transactional
	public UsuarioDto cadastrar(UsuarioForm form) {

		if (usuarioRepository.existsByEmail(form.getEmail()))
			throw new UsuarioJaCadastradoException("Erro! Usuário com e-mail " + form.getEmail() + " já existe");
		
		CriaSenhaUsuario senhaGeradaAuto = new CriaSenhaUsuario();
		
		Usuario usuarioGravado = usuarioRepository.save(form.toUsuario(senhaGeradaAuto.gerarSenhaBCrypt()));
		Perfil perfilUsuarioGravado = new Perfil(usuarioGravado, "COMUM");

		perfilRepository.save(perfilUsuarioGravado);
		
		UsuarioDto usuarioDto = new UsuarioDto(usuarioGravado);
		
		this.enviarEmaildeConfirmacao(usuarioDto, senhaGeradaAuto.getSenhaGeradaAuto());
		
		return usuarioDto;
	}

	public List<UsuarioDto> listar() {

		return UsuarioDto.listaDeUsuariosDto(usuarioRepository.findByPerfisAutoridadeNot("PADRAO"));
	}
	
	public UsuarioDto pesquisarPorId(long id) {
		
		return new UsuarioDto(usuarioRepository.findById(id).get());
	}

	@Transactional
	public void remover(long id) {

		if (!usuarioRepository.existsById(id))
			throw new UsuarioNaoExisteException("Usuário " + id + " não existe!");

		usuarioRepository.deleteById(id);
	}

	@Transactional
	public void editar(long id, UsuarioForm form) {
		
		Usuario usuario = usuarioRepository.findById(id).get();
		usuario.setNome(form.getNome());
		usuario.setEmail(form.getEmail());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Usuario> usuario = usuarioRepository.findByEmail(username);

		if (usuario.isEmpty()) {
			throw new UsernameNotFoundException("Usuário/senha inválido");
		}

		return usuario.get();
	}
	
	private void enviarEmaildeConfirmacao(UsuarioDto usuarioDto, String senhaAleatoria) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("Confirmação de cadastro na Turingbank");
		message.setText("Bem vindo, " + usuarioDto.getNome() + "\nSua senha de castrado é " + senhaAleatoria);
		message.setFrom("turingbank@gmail.com");
		message.setTo(usuarioDto.getEmail());

		try {
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
