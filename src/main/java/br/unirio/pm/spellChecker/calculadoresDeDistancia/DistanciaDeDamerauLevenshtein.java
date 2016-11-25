package br.unirio.pm.spellChecker.calculadoresDeDistancia;

import java.util.HashMap;

import br.unirio.pm.spellChecker.utilitariosTeclado.Teclado;

/**
 * Calcula a distancia de Damerou Levenshtein entre duas palavras 
 * 
 */
public class DistanciaDeDamerauLevenshtein extends MoldeDeCalculadorDeDistanciaEntreStrings{

	public DistanciaDeDamerauLevenshtein(Teclado teclado) {
		this.teclado = teclado;
	}
	
	/**
     * Calcula a distância entre duas palavras levando em consideração 
     * o número mínimo de operações para transformar uma palavra na outra
     * (inserção, remoção, substituição de um caracter por outro ou transposição
     * de dois caracteres adjacentes)
     * Retorna -1 caso não haja palavras a serem comparadas
     */
	@Override
    public int calcular(String primeiraPalavra, String segundaPalavra) {
    	if (primeiraPalavra.length() == 0 && segundaPalavra.length() == 0) {
			return -1;
		}
        if (primeiraPalavra.length() == 0) 
        	return segundaPalavra.length();
        if (segundaPalavra.length() == 0) 
        	return primeiraPalavra.length();

        // maior distância possível
        int maiorDistanciaPossivel = primeiraPalavra.length() + segundaPalavra.length();

        // cria e inicializa uma tabela dos índices dos caracteres
        HashMap<Character, Integer> indicesDosCaracteres = new HashMap<Character, Integer>();
        
        int i;
        
        for (i = 0; i < primeiraPalavra.length(); i++) {
            if (!indicesDosCaracteres.containsKey(primeiraPalavra.charAt(i))) {
                indicesDosCaracteres.put(primeiraPalavra.charAt(i), 0);
            }
        }

        for (i = 0; i < segundaPalavra.length(); i++) {
            if (!indicesDosCaracteres.containsKey(segundaPalavra.charAt(i))) {
                indicesDosCaracteres.put(segundaPalavra.charAt(i), 0);
            }
        }

        // cria a matriz de distância 
        int[][] matrizDeDistancias = new int[primeiraPalavra.length() + 2][segundaPalavra.length() + 2];

        // preenche as bordas da matriz 
        for (i = 0; i <= primeiraPalavra.length(); i++) {
            matrizDeDistancias[i + 1][0] = maiorDistanciaPossivel;
            matrizDeDistancias[i + 1][1] = i;
        }

        for (i = 0; i <= segundaPalavra.length(); i++) {
            matrizDeDistancias[0][i + 1] = maiorDistanciaPossivel;
            matrizDeDistancias[1][i + 1] = i;

        }

        // preenche a matriz de distância
        // passa por cada char da primeiraPalavra
        for (i = 1; i <= primeiraPalavra.length(); i++) {
            int db = 0;

            // passa por cada char da segundaPalavra
            for (int j = 1; j <= segundaPalavra.length(); j++) {
                int i1 = indicesDosCaracteres.get(segundaPalavra.charAt(j - 1));
                int j1 = db;

                int custo = 1;
                if (primeiraPalavra.charAt(i - 1) == segundaPalavra.charAt(j - 1)) {
                    custo = 0;
                    db = j;
                }

                matrizDeDistancias[i + 1][j + 1] = 
        		Math.min(
            		minimo(
                        matrizDeDistancias[i][j] + custo, // substituição
                        matrizDeDistancias[i + 1][j] + 1, // inserção
                        matrizDeDistancias[i][j + 1] + 1) // remoção
                    ,matrizDeDistancias[i1][j1] + (i - i1 - 1) + 1 + (j - j1 - 1)
                );
                
                
            }

            indicesDosCaracteres.put(primeiraPalavra.charAt(i - 1), i);
        }

        return matrizDeDistancias[primeiraPalavra.length() + 1][segundaPalavra.length() + 1];
    }

}
