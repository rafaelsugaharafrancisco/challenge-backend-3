package com.br.alura.challengebackend3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.alura.challengebackend3.model.Usuario;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Long> {

	boolean existsByEmail(String email);
	
	Optional<Usuario> findByEmail(String email);
	
	List<Usuario> findByPerfisAutoridadeNot(String autoridade);
}
