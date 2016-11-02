package br.unirio.pm.spellChecker;

import java.util.Arrays;

/**
 * Calcula a diferenca (numero de operacoes necessarias) entre duas sequencias de caracteres
 */
public class LevenshteinDistance extends CalculadorDeDistanciasEntreStrings {	
	
	/**
     *  limite é uma variável que define o máximo de operações que podem ser feitas
     */
	private int limite;

    /**
     * Construtor de calculador de distancia de Levenshtein
     */
    public LevenshteinDistance(int limite) {
       this.limite = limite;
    }
    
    /**
     * Retorna uma instancia unica de LevenshteinDistance
     */  
    private static LevenshteinDistance getInstance(int limite){
    	if(instancia == null)
    		instancia = new LevenshteinDistance(limite);
    	
    	return instancia;
    }

    /**
     * Encontra a distancia de Levenshtein entre duas strings
     * 
     * @return distancia entre as duas Strings, ou -1 caso alguma string seja menor que o limite
     */
    public int calcular(String primeiraString, String segundaString) {
        if (limite > 0) {
            return comparacaoLimitada(primeiraString, segundaString, limite);
        } else {
            return comparacaoIlimitada(primeiraString, segundaString);
        }
    }

    /**
     * retorna limite
     */
    public int getLimite() {
        return limite;
    }

    /**
     * Calcula distancia de Levenshtein se um limite foi estabelecido (se é maior que 0)
     */
    private static int comparacaoLimitada(String primeiraString, String segundaString, int limite) {
        if (primeiraString == null || segundaString == null) {
            throw new IllegalArgumentException("Palavras não podem ser nulas");
        }
        
        int tamanhoDaPrimeiraString = primeiraString.length(); // length of left
        int tamanhoDaSegundaString = segundaString.length(); // length of right

        // se o tamanho das strings for menor ou igual ao limite, retorna -1
        if((tamanhoDaPrimeiraString <= limite) && (tamanhoDaSegundaString <= limite)){
        	return -1;
        }
        // Se uma string está vazia, a distancia é o tamanho da outra
        if (tamanhoDaPrimeiraString == 0)
            return tamanhoDaSegundaString;
        else if (tamanhoDaSegundaString == 0) 
            return tamanhoDaPrimeiraString;

        // Padronização de que, se o tamanho das strings é diferente,
        // a segunda string é a maior
        if (tamanhoDaPrimeiraString > tamanhoDaSegundaString) {
        	String tmp = primeiraString;
            primeiraString = segundaString;
            segundaString = tmp;
            tamanhoDaPrimeiraString = tamanhoDaSegundaString;
            tamanhoDaSegundaString = segundaString.length();
        }

        int[] custoDoArrayAnterior = new int[tamanhoDaPrimeiraString + 1]; 
        int[] custoDoArray = new int[tamanhoDaPrimeiraString + 1];
        int[] tempArray; // para trocas entre os dois arrays
      

        final int limiteMinimo = Math.min(tamanhoDaPrimeiraString, limite) + 1;
        
        // preenche o primeiro array com o valor crescente
        for (int i = 0; i < limiteMinimo; i++) {
            custoDoArrayAnterior[i] = i;
        }
        
        // these fills ensure that the value above the rightmost entry of our
        // stripe will be ignored in following loop iterations
        // preenche as posições maiores que o limiteMinimo com infinito, para serem ignoradas
        // no loop
        Arrays.fill(custoDoArrayAnterior, limiteMinimo, custoDoArrayAnterior.length, Integer.MAX_VALUE);
        Arrays.fill(custoDoArray, Integer.MAX_VALUE);

        // iterates through t
        for (int j = 1; j <= tamanhoDaSegundaString; j++) {
            final char jDaSegundaString = segundaString.charAt(j - 1); 
            custoDoArray[0] = j;

            // compute stripe indices, constrain to array size
            final int min = Math.max(1, j - limite);
            final int max = j > Integer.MAX_VALUE - limite ? tamanhoDaPrimeiraString : Math.min(
                    tamanhoDaPrimeiraString, j + limite);

            // the stripe may lead off of the table if s and t are of different
            // sizes
            if (min > max) {
                return -1;
            }

            // ignore entry left of leftmost
            if (min > 1) {
                custoDoArray[min - 1] = Integer.MAX_VALUE;
            }

            // iterates through [min, max] in s
            for (int i = min; i <= max; i++) {
                if (primeiraString.charAt(i - 1) == jDaSegundaString) {
                    // diagonally left and up
                    custoDoArray[i] = custoDoArrayAnterior[i - 1];
                } else {
                    // 1 + minimum of cell to the left, to the top, diagonally
                    // left and up
                    custoDoArray[i] = 1 + Math.min(Math.min(custoDoArray[i - 1], custoDoArrayAnterior[i]), custoDoArrayAnterior[i - 1]);
                }
            }

            // copy current distance counts to 'previous row' distance counts
            tempArray = custoDoArrayAnterior;
            custoDoArrayAnterior = custoDoArray;
            custoDoArray = tempArray;
        }

        // if p[n] is greater than the threshold, there's no guarantee on it
        // being the correct
        // distance
        if (custoDoArrayAnterior[tamanhoDaPrimeiraString] <= limite) {
            return custoDoArrayAnterior[tamanhoDaPrimeiraString];
        }
        return -1;
    }

    /**
     * <p>Find the Levenshtein distance between two Strings.</p>
     *
     * <p>A higher score indicates a greater distance.</p>
     *
     * <p>The previous implementation of the Levenshtein distance algorithm
     * was from <a href="http://www.merriampark.com/ld.htm">http://www.merriampark.com/ld.htm</a></p>
     *
     * <p>Chas Emerick has written an implementation in Java, which avoids an OutOfMemoryError
     * which can occur when my Java implementation is used with very large strings.<br>
     * This implementation of the Levenshtein distance algorithm
     * is from <a href="http://www.merriampark.com/ldjava.htm">http://www.merriampark.com/ldjava.htm</a></p>
     *
     * <pre>
     * unlimitedCompare(null, *)             = IllegalArgumentException
     * unlimitedCompare(*, null)             = IllegalArgumentException
     * unlimitedCompare("","")               = 0
     * unlimitedCompare("","a")              = 1
     * unlimitedCompare("aaapppp", "")       = 7
     * unlimitedCompare("frog", "fog")       = 1
     * unlimitedCompare("fly", "ant")        = 3
     * unlimitedCompare("elephant", "hippo") = 7
     * unlimitedCompare("hippo", "elephant") = 7
     * unlimitedCompare("hippo", "zzzzzzzz") = 8
     * unlimitedCompare("hello", "hallo")    = 1
     * </pre>
     *
     * @param left the first String, must not be null
     * @param right the second String, must not be null
     * @return result distance, or -1
     * @throws IllegalArgumentException if either String input {@code null}
     */
    private static int comparacaoIlimitada(CharSequence left, CharSequence right) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }



        int n = left.length(); // length of left
        int m = right.length(); // length of right

        if (n == 0) {
            return m;
        } else if (m == 0) {
            return n;
        }

        if (n > m) {
            // swap the input strings to consume less memory
            final CharSequence tmp = left;
            left = right;
            right = tmp;
            n = m;
            m = right.length();
        }

        int[] p = new int[n + 1]; //'previous' cost array, horizontally
        int[] d = new int[n + 1]; // cost array, horizontally
        int[] tempD; //placeholder to assist in swapping p and d

        // indexes into strings left and right
        int i; // iterates through left
        int j; // iterates through right

        char rightJ; // jth character of right

        int cost; // cost

        for (i = 0; i <= n; i++) {
            p[i] = i;
        }

        for (j = 1; j <= m; j++) {
            rightJ = right.charAt(j - 1);
            d[0] = j;

            for (i = 1; i <= n; i++) {
                cost = left.charAt(i - 1) == rightJ ? 0 : 1;
                // minimum of cell to the left+1, to the top+1, diagonally left and up +cost
                d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
            }

            // copy current distance counts to 'previous row' distance counts
            tempD = p;
            p = d;
            d = tempD;
        }

        // our last action in the above loop was to switch d and p, so p now
        // actually has the most recent cost counts
        return p[n];
    }

}