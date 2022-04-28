package com.br.alura.challengebackend3.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;

@Entity(name = "perfis")
public class Perfil implements GrantedAuthority {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String autoridade;
	
	@ManyToOne
	@JoinColumn(name = "email", referencedColumnName = "email")
	private Usuario usuario;
	
	public Perfil() {}
	
	public Perfil(Usuario usuario, String autoridade) {
		this.autoridade = autoridade;
		this.usuario = usuario;
	}

	public String getAutoridade() {
		return autoridade;
	}

	public Long getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return autoridade;
	}
}
