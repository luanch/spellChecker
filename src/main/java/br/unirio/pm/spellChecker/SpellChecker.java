package br.unirio.pm.spellChecker;

import java.util.ArrayList;
import java.util.List;

import br.unirio.pm.spellChecker.LeitorDePalavras.LeitorDePalavras;
import br.unirio.pm.spellChecker.bkTree.BKTree;

/**
 * Classe que ve se uma palavra é válida para um dicionário e caso não seja, 
 * sugere palavras que o usuário pode ter desejado digitar
 */
public class SpellChecker {
	private BKTree dicionarioDePalavras;

    public SpellChecker(int codigoCalculador) {
    	
        dicionarioDePalavras = new BKTree(codigoCalculador);
        LeitorDePalavras leitorDePalavras = new LeitorDePalavras();
    	
		
		leitorDePalavras.gerarDicionario(dicionarioDePalavras);
    }
    
	/**
	 * Busca palavras similares a uma palavra respeitando um limite de operacoes
	 * @param limiteDeOperacoes: quantidade maxima de operacoes que a palavra buscada pode sofrer
	 */
   public List<String> verificarPalavra(String palavra, int limiteDeOperacoes){
       List<String> resultadoDaBusca = new ArrayList<String>();

	   resultadoDaBusca =  dicionarioDePalavras.buscar(palavra, limiteDeOperacoes);
	   
	   return resultadoDaBusca;
   }
    

}
