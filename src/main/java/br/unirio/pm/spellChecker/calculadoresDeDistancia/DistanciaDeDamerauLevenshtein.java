package br.unirio.pm.spellChecker.calculadoresDeDistancia;

import java.util.HashMap;

/**
 * Calcula a distancia de 
 * 
 */
public class DistanciaDeDamerauLevenshtein  extends MoldeDeCalculadorDeDistanciaEntreStrings{
	
	/**
     * Compute the distance between strings: the minimum number of operations
     * needed to transform one string into the other (insertion, deletion,
     * substitution of a single character, or a transposition of two adjacent
     * characters).
     * @param primeiraString
     * @param segundaString
     * @return
     */
    public int calcular(String primeiraString, String segundaString) {

        // INFinite distance is the max possible distance
        int maiorDistanciaPossivel = primeiraString.length() + segundaString.length();

        // Create and initialize the character array indices
        HashMap<Character, Integer> indicesDosCaracteres = new HashMap<Character, Integer>();
        int i;
        
        for (i = 0; i < primeiraString.length(); i++) {
            if (!indicesDosCaracteres.containsKey(primeiraString.charAt(i))) {
                indicesDosCaracteres.put(primeiraString.charAt(i), 0);
            }
        }

        for (i = 0; i < segundaString.length(); i++) {
            if (!indicesDosCaracteres.containsKey(segundaString.charAt(i))) {
                indicesDosCaracteres.put(segundaString.charAt(i), 0);
            }
        }

        // Create the distance matrix H[0 .. s1.length+1][0 .. s2.length+1]
        int[][] h = new int[primeiraString.length() + 2][segundaString.length() + 2];

        // initialize the left and top edges of H
        for (i = 0; i <= primeiraString.length(); i++) {
            h[i + 1][0] = maiorDistanciaPossivel;
            h[i + 1][1] = i;
        }

        for (i = 0; i <= segundaString.length(); i++) {
            h[0][i + 1] = maiorDistanciaPossivel;
            h[1][i + 1] = i;

        }

        // fill in the distance matrix H
        // look at each character in s1
        for (i = 1; i <= primeiraString.length(); i++) {
            int db = 0;

            // look at each character in b
            for (int j = 1; j <= segundaString.length(); j++) {
                int i1 = indicesDosCaracteres.get(segundaString.charAt(j - 1));
                int j1 = db;

                int cost = 1;
                if (primeiraString.charAt(i - 1) == segundaString.charAt(j - 1)) {
                    cost = 0;
                    db = j;
                }

                h[i + 1][j + 1] = 
        		Math.min(
            		minimo(
                        h[i][j] + cost, // substitution
                        h[i + 1][j] + 1, // insertion
                        h[i][j + 1] + 1) // deletion
                    ,h[i1][j1] + (i - i1 - 1) + 1 + (j - j1 - 1)
                );
                
                
            }

            indicesDosCaracteres.put(primeiraString.charAt(i - 1), i);
        }

        return h[primeiraString.length() + 1][segundaString.length() + 1];
    }

}
