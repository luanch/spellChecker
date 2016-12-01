package br.unirio.pm.spellChecker.calculadoresDeDistancia;

import java.util.ArrayList;

import br.unirio.pm.spellChecker.modelos.PalavraComCusto;
import lombok.Getter;

/**
 * Classe que monta e mantem a lista de palavras sugeridas
 * possuindo um determinado tamanho maximo
 */
public class ListaDePalavrasSugeridas {
	private @Getter ArrayList<PalavraComCusto> palavrasSugeridas;
	private static final int TAMANHO_ARRAY_MAX = 10;

	/**
	 * Construtor
	 */
	public ListaDePalavrasSugeridas(){
		palavrasSugeridas = new ArrayList<PalavraComCusto>();
	}

	/**
	 * Adiciona uma nova palavra a lista
	 */
	public void adicionarPalavra (String palavra, int distanciaAtual){
		// Monta um objeto que reune a palavra e a distancia dela
		PalavraComCusto palavraCorrente = new PalavraComCusto(palavra, distanciaAtual);

		// se a lista estiver vazia
		if(palavrasSugeridas.isEmpty()){
			// adiciona palavra na lista
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

	/**
	 * Realiza a insercao de uma PalavraComCusto em um determinado indice,
	 * se preocupando com a reorganizacao no resto da lista
	 */
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