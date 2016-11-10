package br.unirio.pm.spellChecker;

import java.util.List;

/**
 * Classe que ve se uma palavra é válida para um dicionário e caso não seja, 
 * sugere palavras que o usuário pode ter desejado digitar
 */


public class SpellChecker {
	private CalculadorDeDistanciasEntreStrings arvoreDePalavras;

    public SpellChecker(int codigoCalculador) {
        arvoreDePalavras = new CalculadorDeDistanciasEntreStrings(codigoCalculador);
    }
    
   public List<String> verificarPalavra(String palavra, int limiteDeOperacoes){
	   return arvoreDePalavras.buscar(palavra, limiteDeOperacoes);
   }
    

}
