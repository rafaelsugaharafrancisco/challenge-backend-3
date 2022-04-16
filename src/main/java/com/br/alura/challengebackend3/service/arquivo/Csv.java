package com.br.alura.challengebackend3.service.arquivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.br.alura.challengebackend3.dto.TransacaoDto;
import com.br.alura.challengebackend3.exception.ArquivoNaoEncontradoException;
import com.br.alura.challengebackend3.exception.ArquivoVazioException;

@Component
public class Csv extends ArquivoTransacoesService {
	
	@Value("${file.directory}")
	private String diretorio;

	@Override
	public List<TransacaoDto> criarLista(String arquivo) {
		List<TransacaoDto> lista = new ArrayList<>();
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(new File(arquivo));
			
			if (!scanner.hasNextLine())
				throw new ArquivoVazioException("Arquivo " + arquivo + " não pode estar vazio");
			
			while (scanner.hasNextLine()) {
				String linha = scanner.nextLine();
				Scanner scannerLinha = new Scanner(linha);
				scannerLinha.useDelimiter(",");
				
				boolean haErro = false;
				while(scannerLinha.hasNext() && !haErro) {
					if (scannerLinha.next().isBlank()) {
						haErro = true;
						System.out.println(linha);
					}
				}
				scannerLinha.close();
				
				if (!haErro) {
					scannerLinha = new Scanner(linha);
					scannerLinha.useDelimiter(",");
					TransacaoDto t = new TransacaoDto();
					t.setBancoOrigem(scannerLinha.next());
					t.setAgenciaOrigem(scannerLinha.next());
					t.setContaOrigem(scannerLinha.next());
					t.setBancoDestino(scannerLinha.next());
					t.setAgenciaDestino(scannerLinha.next());
					t.setContaDestino(scannerLinha.next());
					t.setValorTransacao(Double.parseDouble(scannerLinha.next()));
					t.setDataHoraTransacao(LocalDateTime.parse(scannerLinha.next()));
					scannerLinha.close();
					lista.add(t);
				}
				
			}
			
		} catch (FileNotFoundException e) {
			throw new ArquivoNaoEncontradoException("Arquivo " + arquivo + " não encontrado no diretório " + diretorio);
			
		} finally {
			scanner.close();
		}
		
		return super.filtrarParaDatasIguais(lista);
	}

}
