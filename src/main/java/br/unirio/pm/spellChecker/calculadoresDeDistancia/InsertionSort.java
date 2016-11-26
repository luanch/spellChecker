package br.unirio.pm.spellChecker.calculadoresDeDistancia;

import java.util.ArrayList;

import lombok.Getter;

public class InsertionSort {
	private @Getter ArrayList<CustoPalavra> palavrasSugeridas;
	private static final int TAMANHO_ARRAY_MAX = 10;
	
	public InsertionSort(){
		palavrasSugeridas = new ArrayList<CustoPalavra>();
	}
	public void adicionarPalavra (String palavra, int distanciaAtual){
		CustoPalavra palavraCorrente = new CustoPalavra(palavra, distanciaAtual);
		
		if(palavrasSugeridas.isEmpty()){
			palavrasSugeridas.add(palavraCorrente);
		}
		else{
			int ultimoElemento = palavrasSugeridas.size()-1;
			if(distanciaAtual >= palavrasSugeridas.get(ultimoElemento).getDistancia()){
				if(palavrasSugeridas.size() < TAMANHO_ARRAY_MAX){
					palavrasSugeridas.add(palavraCorrente);
				}
			}
			else{
				for(int i = ultimoElemento - 1; i >= 0; i--){
					if(distanciaAtual < palavrasSugeridas.get(i).getDistancia()){
						inserirPalavraNoIndice(i, palavraCorrente);
						break;
					}
				}
			}
		}
	}
	public void inserirPalavraNoIndice(int indice, CustoPalavra palavraCorrente){
		palavrasSugeridas.set(indice, palavraCorrente);
		CustoPalavra auxiliar;
		for(int j = indice; j< palavrasSugeridas.size() && j < TAMANHO_ARRAY_MAX ;j++){
			auxiliar = palavrasSugeridas.get(j);
			palavrasSugeridas.set(j, palavraCorrente);
			palavraCorrente = auxiliar;
		}
	}

}
