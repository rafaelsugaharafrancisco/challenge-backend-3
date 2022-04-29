package com.br.alura.challengebackend3.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.alura.challengebackend3.model.Transacao;


@Repository
public interface TransacoesRepository extends JpaRepository<Transacao, Long>  {
	
	@Query("SELECT t FROM transacoes t WHERE DATE(t.dataHoraTransacao) = :date")
	List<Transacao> findAll(Date date);

}
