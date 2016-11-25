package br.unirio.pm.spellChecker.LeitorDePalavras;

import br.unirio.pm.spellChecker.bkTree.BKTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class LeitorDePalavras {

	public void gerarDicionario(BKTree arvore){
		
		LeitorDeZip leitor = new LeitorDeZip();
		
		String pastaDeDicionarios = "src/main/java/br/unirio/pm/spellChecker/dicionarios/";
	
		ArrayList<InputStream> listaDeDicionarios = leitor.lerZip(pastaDeDicionarios+"dictionary_pt-br.zip");
		
		
		try {	
			for(int i =0; i < listaDeDicionarios.size(); i++){
				InputStream dicionarioStream = listaDeDicionarios.get(i);
				InputStreamReader dicionarioStreamReader = new InputStreamReader(dicionarioStream);
				BufferedReader leitorDeDicionario = new BufferedReader(dicionarioStreamReader);
				String palavra;
				
				// aqui pode dar a excessao
				palavra = leitorDeDicionario.readLine();
				while(palavra != null){ // TÁ DANDO MERDA AQUI DENTRO
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
