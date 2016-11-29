package br.unirio.pm.spellChecker.calculadoresDeDistancia;

import lombok.Getter;

public class PalavraComCusto {
	private @Getter String palavra;
	private @Getter int distancia;
	
	public PalavraComCusto(String palavra, int custo){
		this.palavra = palavra;
		this.distancia = custo;
	}
}
