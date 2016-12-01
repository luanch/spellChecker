package br.unirio.pm.spellChecker.leitores;

import br.unirio.pm.spellChecker.bkTree.BKTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Classe responsavel por ler palavras e armazená-las numa BKTree
 */
public class LeitorDePalavras {

	/**
	 * Funcao responsavel por ler as palavras e coloca-las numa BKTree
	 */
	public void gerarDicionario(BKTree arvore){	
		LeitorDeZip leitor = new LeitorDeZip();
		String pastaDeDicionarios = "arquivos/";
		ArrayList<InputStream> listaDeDicionarios = leitor.lerZip(pastaDeDicionarios+"dictionary_pt-br.zip");

		try {	
			for(int i =0; i < listaDeDicionarios.size(); i++){
				InputStream dicionarioStream = listaDeDicionarios.get(i);
				InputStreamReader dicionarioStreamReader = new InputStreamReader(dicionarioStream);
				BufferedReader leitorDeDicionario = new BufferedReader(dicionarioStreamReader);
				String palavra;

				// aqui pode dar a excecao
				palavra = leitorDeDicionario.readLine();
				while(palavra != null){ 
					arvore.inserir(palavra);
					palavra = leitorDeDicionario.readLine(); 
				}
				leitorDeDicionario.close();
			}
		} catch (IOException e) {
			System.out.println("Erro ao ler palavra do dicionario - " + e);
		} 
	}
}