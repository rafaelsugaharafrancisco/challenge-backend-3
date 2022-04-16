package com.br.alura.challengebackend3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.alura.challengebackend3.model.ImportacoesArqs;

@Repository
public interface ImportacoesArqsRepository extends JpaRepository<ImportacoesArqs, Long> {

}
