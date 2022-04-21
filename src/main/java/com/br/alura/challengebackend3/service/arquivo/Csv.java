package com.br.alura.challengebackend3.service.arquivo;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.br.alura.challengebackend3.dto.TransacaoDto;
import com.br.alura.challengebackend3.exception.ArquivoNaoCorrespondenteTransacoes;

@Service
public class Csv extends ArquivoTransacoesService {

	private Scanner scanner;

	@Override
	protected void abrirArquivo(Resource arquivo) {
		
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(new File(arquivo.getURI()));
		} catch (IOException e) {
			System.out.println("Erro ao abrir o arquivo " + arquivo.getFilename());
		}
		
		this.scanner = scanner;
	}
	
	@Override
	protected List<TransacaoDto> lerLinhasEliminarColunasInvalidas() {
		
		List<TransacaoDto> transacoesDto = new ArrayList<TransacaoDto>();
		
		while (this.scanner.hasNextLine()) {
			String linha = this.scanner.nextLine();
			
			if (!linha.contains(",")) {
				throw new ArquivoNaoCorrespondenteTransacoes("Conteúdo do arquivo não corresponde a extensão csv");
			}
			
			Scanner scannerLinha = new Scanner(linha);
			scannerLinha.useDelimiter(",");
			
			boolean haCampoInvalido = false;
			while(scannerLinha.hasNext() && !haCampoInvalido) {
				if (scannerLinha.next().isBlank()) {
					haCampoInvalido = true;
					System.out.println(linha);
				}
			}
			scannerLinha.close();
			
			if (!haCampoInvalido) {
				scannerLinha = new Scanner(linha);
				scannerLinha.useDelimiter(",");
				
				while (scannerLinha.hasNext()) {
					TransacaoDto t = new TransacaoDto();
					t.setBancoOrigem(scannerLinha.next());
					t.setAgenciaOrigem(scannerLinha.next());
					t.setContaOrigem(scannerLinha.next());
					t.setBancoDestino(scannerLinha.next());
					t.setAgenciaDestino(scannerLinha.next());
					t.setContaDestino(scannerLinha.next());
					t.setValorTransacao(Double.parseDouble(scannerLinha.next()));
					t.setDataHoraTransacao(LocalDateTime.parse(scannerLinha.next()));
					transacoesDto.add(t);
				}
				scannerLinha.close();
			}
		}
		if (this.scanner != null)
			this.scanner.close();
			
		return transacoesDto;
	}
}
