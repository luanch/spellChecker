package br.unirio.pm.spellChecker.calculadoresDeDistancia;

import java.util.HashMap;
import java.util.Map;

import br.unirio.pm.spellChecker.utilitariosTeclado.Teclado;

/**
 * The Damerau-Levenshtein Algorithm is an extension to the Levenshtein
 * Algorithm which solves the edit distance problem between a source string and
 * a target string with the following operations:
 * 
 * <ul>
 * <li>Character Insertion</li>
 * <li>Character Deletion</li>
 * <li>Character Replacement</li>
 * <li>Adjacent Character Swap</li>
 * </ul>
 * 
 * Note that the adjacent character swap operation is an edit that may be
 * applied when two adjacent characters in the source string match two adjacent
 * characters in the target string, but in reverse order, rather than a general
 * allowance for adjacent character swaps.
 * <p>
 * 
 * This implementation allows the client to specify the costs of the various
 * edit operations with the restriction that the cost of two swap operations
 * must not be less than the cost of a delete operation followed by an insert
 * operation. This restriction is required to preclude two swaps involving the
 * same character being required for optimality which, in turn, enables a fast
 * dynamic programming solution.
 * <p>
 * 
 * The running time of the Damerau-Levenshtein algorithm is O(n*m) where n is
 * the length of the source string and m is the length of the target string.
 * This implementation consumes O(n*m) space.
 * 
 * @author Kevin L. Stern
 */
public class DistanciaDeDamerauLevenshtein extends MoldeDeCalculadorDeDistanciaEntreStrings {
	private Teclado teclado;

	/**
	 * Compute the Damerau-Levenshtein distance between the specified source
	 * string and the specified target string.
	 */
	public DistanciaDeDamerauLevenshtein(Teclado teclado) {
		this.teclado = teclado;
	}


	// calcula a distancia da primeira para a segunda palavra
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
