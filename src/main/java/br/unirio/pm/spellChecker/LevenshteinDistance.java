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
    public static LevenshteinDistance getInstance(int limite){
    	if(instancia == null)
    		instancia = new LevenshteinDistance(limite);
    	
    	return instancia;
    }
    
    /**
     * retorna limite
     */
    public int getLimite() {
        return limite;
    }
 
    
    
    
    
    public int calcular(String primeiraString, String segundaString){
        if (primeiraString.length() == 0) 
        	return segundaString.length();
        if (segundaString.length() == 0) 
        	return primeiraString.length();
 
        int primeiraStringLength = primeiraString.length();
        int segundaStringLength = segundaString.length();
 
        int[][] distancias = new int[primeiraStringLength + 1][segundaStringLength + 1];
        
        // preenche a primeira linha e coluna com o indice corrente
        for (int i = 0; i <= primeiraStringLength; i++)
            distancias[i][0] = i;
        for (int i = 0; i <= segundaStringLength; i++)
            distancias[0][i] = i;
 
        // a partir da posicao [2,2] comeca o loop
        for (int i = 1; i <= primeiraStringLength; i++)
        {
            for (int j = 1; j <= segundaStringLength; j++)
            {
            	// variável que incrementa 1 ao custo caso os caracteres sejam diferentes
            	int letraIgual;
            	if (primeiraString.charAt(i - 1) == segundaString.charAt(j - 1)) {
            		letraIgual = 0;
            	}
            	else {
            		letraIgual = 1;
            	}
            	
            	// insere o menor 
                distancias[i][j] = Math.min(Math.min(distancias[i - 1][j] + 1, distancias[i][j - 1] + 1), distancias[i - 1][j - 1] + letraIgual);
            }
        }
        
        //o ultimo elemento da matriz eh a distancia minima
        return distancias[primeiraStringLength][segundaStringLength];
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Encontra a distancia de Levenshtein entre duas strings
     * 
     * @return distancia entre as duas Strings, ou -1 caso alguma string seja menor que o limite
     */
    
/*
    public int calcular(String primeiraString, String segundaString) {
        if (limite > 0) {
            return comparacaoLimitada(primeiraString, segundaString, limite);
        } else {
            return comparacaoIlimitada(primeiraString, segundaString);
        }
    }

*/

    /**
     * Calcula distancia de Levenshtein se um limite foi estabelecido (se é maior que 0)
     */
/*    
    private static int comparacaoLimitada(String primeiraString, String segundaString, int limite) {
        if (primeiraString == null || segundaString == null) {
            throw new IllegalArgumentException("Palavras não podem ser nulas");
        }
        
        int tamanhoDaPrimeiraString = primeiraString.length(); 
        int tamanhoDaSegundaString = segundaString.length(); 
        
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

        int[] custosAntigo = new int[tamanhoDaPrimeiraString + 1]; 
        int[] custos = new int[tamanhoDaPrimeiraString + 1];
        int[] tempArray; // para trocas entre os dois arrays
      
        
        final int limiteMinimo = Math.min(tamanhoDaPrimeiraString, limite) + 1;
        
        // preenche o primeiro array com o valor crescente
        for (int i = 0; i < limiteMinimo; i++) {
            custosAntigo[i] = i;
        }
        
        // preenche as posições maiores que o limite minimo com infinito, para serem ignoradas
        // no loop
        //         ( array      , inicio      , fim                , valor preenchido)
        Arrays.fill(custosAntigo, limiteMinimo, custosAntigo.length, Integer.MAX_VALUE);
        Arrays.fill(custos, Integer.MAX_VALUE);

        for (int posicaoNaSegundaString = 1; posicaoNaSegundaString <= tamanhoDaSegundaString;
        		posicaoNaSegundaString++) {
            final char jDaSegundaString = segundaString.charAt(posicaoNaSegundaString - 1); 
            custos[0] = posicaoNaSegundaString;
            
            // marca os indices limites, sendo no máximo o tamanho do array
            final int min = Math.max(1, posicaoNaSegundaString - limite);
            final int max = posicaoNaSegundaString > Integer.MAX_VALUE - limite ?
            		tamanhoDaPrimeiraString : 
            			Math.min(tamanhoDaPrimeiraString, posicaoNaSegundaString + limite);

            if (min > max) {
                return -1;
            }

            
            if (min > 1) {
                custos[min - 1] = Integer.MAX_VALUE;
            }

            // iterates through [min, max] in s
            for (int i = min; i <= max; i++) {
                if (primeiraString.charAt(i - 1) == jDaSegundaString) {
                    // diagonally left and up
                    custos[i] = custosAntigo[i - 1];
                } else {
                    // 1 + minimum of cell to the left, to the top, diagonally
                    // left and up
                    custos[i] = 1 + Math.min(Math.min(custos[i - 1], custosAntigo[i]), custosAntigo[i - 1]);
                }
            }

            // copy current distance counts to 'previous row' distance counts
            tempArray = custosAntigo;
            custosAntigo = custos;
            custos = tempArray;
        }

        // if p[n] is greater than the threshold, there's no guarantee on it
        // being the correct
        // distance
        if (custosAntigo[tamanhoDaPrimeiraString] <= limite) {
            return custosAntigo[tamanhoDaPrimeiraString];
        }
        return -1;
    }
*/
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
/*
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
*/
}