package br.unirio.pm.spellChecker.calculadoresDeDistancia;

/**
 * Calcula a distancia (numero de operacoes necessarias) entre duas sequencias de caracteres
 */
public class DistanciaDeLevenshtein extends MoldeDeCalculadorDeDistanciaEntreStrings{	
  
	/**
     * calcula a distancia entre duas palavras, retornando -1 caso não haja palavras a serem comparadas
     */
	@Override
	public int calcular (String primeiraPalavra, String segundaPalavra){
		if (primeiraPalavra.length() == 0 && segundaPalavra.length() == 0) {
			return -1;
		}
        if (primeiraPalavra.length() == 0) 
        	return segundaPalavra.length();
        if (segundaPalavra.length() == 0) 
        	return primeiraPalavra.length();
 
        int tamanhoDaPrimeiraPalavra = primeiraPalavra.length();
        int tamanhoDaSegundaPalavra = segundaPalavra.length();
 
        int[][] matrizDeLevenshtein = new int[tamanhoDaPrimeiraPalavra + 1][tamanhoDaSegundaPalavra + 1];
        
        // preenche a primeira linha e coluna com o valor corrente
        for (int i = 0; i <= tamanhoDaPrimeiraPalavra; i++)
            matrizDeLevenshtein[i][0] = i;
 
        for (int i = 0; i <= tamanhoDaSegundaPalavra; i++)
            matrizDeLevenshtein[0][i] = i;
 
        
        //monta a matriz que gera a distancia minima para que uma string se torne outra 
        for (int i = 1; i <= tamanhoDaPrimeiraPalavra; i++)
        {
            for (int j = 1; j <= tamanhoDaSegundaPalavra; j++)
            {
            	// caso as letras da linha e coluna sejam diferentes, adiciona-se 1 ao valor da posicao da matriz
            	int letrasDiferentes;
            	
            	if (primeiraPalavra.charAt(i - 1) == segundaPalavra.charAt(j - 1)) {
            		letrasDiferentes = 0;
            	}
            	else {
            		letrasDiferentes = 1;
            	}
//            	  h[i + 1][j + 1] = minimo(
//                          h[i][j] + cost, // substitution
//                          h[i + 1][j] + 1, // insertion
//                          h[i][j + 1] + 1, // deletion
//                          h[i1][j1] + (i - i1 - 1) + 1 + (j - j1 - 1));

            	// adiciona na matriz o menor valor dentre os vizinhos anteriores (lado, cima e diagonal esquerda)
                matrizDeLevenshtein[i][j] = 
                		minimo(matrizDeLevenshtein[i - 1][j] + 1,
                				matrizDeLevenshtein[i][j - 1] + 1,
                				matrizDeLevenshtein[i - 1][j - 1]
						) + letrasDiferentes;
            }
        }
        
        //o ultimo elemento da matriz eh a distancia minima
        return matrizDeLevenshtein[tamanhoDaPrimeiraPalavra][tamanhoDaSegundaPalavra];
    }
}