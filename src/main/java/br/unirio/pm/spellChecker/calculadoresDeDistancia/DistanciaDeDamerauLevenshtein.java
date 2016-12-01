package br.unirio.pm.spellChecker.calculadoresDeDistancia;

import java.util.HashMap;
import java.util.Map;

import br.unirio.pm.spellChecker.modelos.Teclado;

/**
 * Classe que calcula a distancia entre duas palavras usando o algoritmo de Damerau-Levenshtein,
 * levando em considerecao 4 operacoes (insercao de letra, remocao de letra, substituicao de letra
 * e troca de letras vizinhas. O algoritmo tambem leva em consideracao o custo diferenciado de 
 * cada uma das operacoes
 * 
 * @author Kevin L. Stern
 */
public class DistanciaDeDamerauLevenshtein extends ModeloDeCalculadorDeDistanciaEntreStrings {
	private Teclado teclado;

	/**
	 * Construtor da classe
	 */
	public DistanciaDeDamerauLevenshtein(Teclado teclado) {
		this.teclado = teclado;
	}

	/**
	 *  calcula a distancia da primeira para a segunda palavra
	 */
	public int calcular (String primeiraString, String segundaString) {
		double custoRemocao, custoInsercao, custoSubstituicao, custoTroca;
		custoRemocao = teclado.getCustoInsercaoRemocao();
		custoInsercao = custoRemocao;
		custoTroca = teclado.getCustoTroca();

		//considera que todos os caracteres foram inseridos  
		if (primeiraString.length() == 0) {
			return (int) Math.abs(segundaString.length() * custoInsercao * 100);
		}
		//considera que todos os caracteres foram removidos
		if (segundaString.length() == 0) {
			return (int) Math.abs(primeiraString.length() * custoRemocao * 100);
		}

		double[][] matrizDeDistancias = new double[primeiraString.length()][segundaString.length()];
		Map<Character, Integer> indicesDaPrimeiraStringPorCaracter = new HashMap<Character, Integer>();
		if (primeiraString.charAt(0) != segundaString.charAt(0)) {
			matrizDeDistancias[0][0] = Math.min(teclado.getDistancia(segundaString.charAt(0), primeiraString.charAt(0)), custoRemocao + custoInsercao);
		}
		indicesDaPrimeiraStringPorCaracter.put(primeiraString.charAt(0), 0);
		double distanciaRemocao;
		double distanciaInsercao;
		double distanciaSubstituicao;

		for (int i = 1; i < primeiraString.length(); i++) {
			custoSubstituicao = teclado.getDistancia(primeiraString.charAt(i), segundaString.charAt(0));
			distanciaRemocao = matrizDeDistancias[i - 1][0] + custoRemocao;
			distanciaInsercao = (i + 1) * custoRemocao + custoInsercao;
			distanciaSubstituicao = i * custoRemocao
					+ (primeiraString.charAt(i) == segundaString.charAt(0) ? 0 : custoSubstituicao);
			matrizDeDistancias[i][0] = Math.min(Math.min(distanciaRemocao, distanciaInsercao),
					distanciaSubstituicao);
		}
		for (int j = 1; j < segundaString.length(); j++) {
			custoSubstituicao = teclado.getDistancia(primeiraString.charAt(0), segundaString.charAt(j));
			distanciaRemocao = (j + 1) * custoInsercao + custoRemocao;
			distanciaInsercao = matrizDeDistancias[0][j - 1] + custoInsercao;
			distanciaSubstituicao = j * custoInsercao
					+ (primeiraString.charAt(0) == segundaString.charAt(j) ? 0 : custoSubstituicao);
			matrizDeDistancias[0][j] = Math.min(Math.min(distanciaRemocao, distanciaRemocao),
					distanciaSubstituicao);
		}
		for (int i = 1; i < primeiraString.length(); i++) {
			int maxSourceLetterMatchIndex = primeiraString.charAt(i) == segundaString.charAt(0) ? 0
					: -1;
			for (int j = 1; j < segundaString.length(); j++) {
				custoSubstituicao = teclado.getDistancia(primeiraString.charAt(i), segundaString.charAt(j));

				Integer candidateSwapIndex = indicesDaPrimeiraStringPorCaracter.get(segundaString
						.charAt(j));
				int jSwap = maxSourceLetterMatchIndex;
				distanciaRemocao = matrizDeDistancias[i - 1][j] + custoRemocao;
				distanciaInsercao = matrizDeDistancias[i][j - 1] + custoInsercao;
				distanciaSubstituicao = matrizDeDistancias[i - 1][j - 1];
				if (primeiraString.charAt(i) != segundaString.charAt(j)) {
					distanciaSubstituicao += custoSubstituicao;
				} else {
					maxSourceLetterMatchIndex = j;
				}
				double  swapDistance;
				if (candidateSwapIndex != null && jSwap != -1) {
					int iSwap = candidateSwapIndex;
					double preSwapCost;
					if (iSwap == 0 && jSwap == 0) {
						preSwapCost = 0;
					} else {
						preSwapCost = matrizDeDistancias[Math.max(0, iSwap - 1)][Math.max(0, jSwap - 1)];
					}
					swapDistance = preSwapCost + (i - iSwap - 1) * custoRemocao
							+ (j - jSwap - 1) * custoInsercao + custoTroca;
				} else {
					swapDistance = Integer.MAX_VALUE;
				}
				matrizDeDistancias[i][j] = Math.min(Math.min(Math
						.min(distanciaRemocao, distanciaInsercao), distanciaSubstituicao), swapDistance);
			}
			indicesDaPrimeiraStringPorCaracter.put(primeiraString.charAt(i), i);
		}
		return (int) Math.abs(matrizDeDistancias[primeiraString.length() - 1][segundaString.length() - 1]*100);
	}
}
