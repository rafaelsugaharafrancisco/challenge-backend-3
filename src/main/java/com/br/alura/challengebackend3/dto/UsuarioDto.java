package com.br.alura.challengebackend3.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.br.alura.challengebackend3.model.Usuario;

import lombok.Getter;

@Getter
public class UsuarioDto {

	private Long id;
	private String nome;
	private String email;
	
	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getUsername();
		this.email = usuario.getEmail();
	}
	
	public static List<UsuarioDto> listaDeUsuariosDto(List<Usuario> usuarios) {
		return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toUnmodifiableList());
	}
}
