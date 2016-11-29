package br.unirio.pm.spellChecker.calculadoresDeDistancia;

import java.util.ArrayList;

import lombok.Getter;

public class InsertionSort {
	private @Getter ArrayList<PalavraComCusto> palavrasSugeridas;
	private static final int TAMANHO_ARRAY_MAX = 10;
	
	public InsertionSort(){
		palavrasSugeridas = new ArrayList<PalavraComCusto>();
	}
	public void adicionarPalavra (String palavra, int distanciaAtual){
		PalavraComCusto palavraCorrente = new PalavraComCusto(palavra, distanciaAtual);
		
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
				int i;
				for(i = ultimoElemento - 1; i >= 0; i--){
					if(distanciaAtual > palavrasSugeridas.get(i).getDistancia()){
						inserirPalavraNoIndice(i+1, palavraCorrente);
						break;
					}
				}
				if(i<0){
					inserirPalavraNoIndice(0, palavraCorrente);
				}
			}
		}
	}
	public void inserirPalavraNoIndice(int indice, PalavraComCusto palavraCorrente){
		PalavraComCusto auxiliar;
		int j;
		for(j = indice; j< palavrasSugeridas.size() && j < TAMANHO_ARRAY_MAX ;j++){
			auxiliar = palavrasSugeridas.get(j);
			palavrasSugeridas.set(j, palavraCorrente);
			palavraCorrente = auxiliar;
		}
		if(palavrasSugeridas.size() < TAMANHO_ARRAY_MAX){
			palavrasSugeridas.add(j, palavraCorrente);
		}
	}
}