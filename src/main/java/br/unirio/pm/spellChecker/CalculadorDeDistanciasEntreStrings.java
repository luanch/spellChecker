package br.unirio.pm.spellChecker;

import java.util.List;

import br.unirio.pm.spellChecker.bkTree.BKTree;

/**
 * Classe que calcula a distancia entre duas strings
 */
public  class CalculadorDeDistanciasEntreStrings {

	private BKTree arvoreDePalavras;
	
	/**
	 * Construtor
	 */
	public CalculadorDeDistanciasEntreStrings(int codigoCalculador){
			arvoreDePalavras = new BKTree(codigoCalculador);
	}

	public List<String> buscar(String palavra, int limiteDeOperacoes){
		return arvoreDePalavras.buscar(palavra, limiteDeOperacoes);
	}
	

}
