package br.unirio.pm.spellChecker.calculadoresDeDistancia;

import lombok.Getter;

public class CustoPalavra {
	private @Getter String palavra;
	private @Getter int distancia;
	
	public CustoPalavra(String palavra, int custo){
		this.palavra = palavra;
		this.distancia = custo;
	}
}
