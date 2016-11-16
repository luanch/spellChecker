package br.unirio.pm.spellChecker;

import java.util.List;

import br.unirio.pm.spellChecker.bkTree.BKTree;

/**
 * Classe que ve se uma palavra é válida para um dicionário e caso não seja, 
 * sugere palavras que o usuário pode ter desejado digitar
 */


public class SpellChecker {
	private BKTree arvoreDePalavras;

    public SpellChecker(int codigoCalculador) {
        arvoreDePalavras = new BKTree(codigoCalculador);
    }
    
    
   public List<String> verificarPalavra(String palavra, int limiteDeOperacoes){
	   return arvoreDePalavras.buscar(palavra, limiteDeOperacoes);
   }
    

}
