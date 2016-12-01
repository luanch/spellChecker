package br.unirio.pm.spellChecker.modelos;

import lombok.Getter;

/**
 * Classe representa a palavra e o custo como um unico objeto,
 * utilizada na lista de palavras sugeridas
 */
public class PalavraComCusto {
	private @Getter String palavra;
	private @Getter int distancia;

	/**
	 * Construtor
	 */
	public PalavraComCusto(String palavra, int custo){
		this.palavra = palavra;
		this.distancia = custo;
	}
}