package br.unirio.pm.spellChecker;

import java.util.Arrays;

/**
 * Calcula a distancia (numero de operacoes necessarias) entre duas sequencias de caracteres
 */
public class CalculadorDeDistanciaDeLevenshtein extends MoldeDeCalculadorDeDistanciaEntreStrings {	

    /**
     * calcula a distancia entre duas palavras 
     */
	@Override
	public int calcular (String primeiraPalavra, String segundaPalavra){
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
            	
            	// adiciona na matriz o menor valor dentre os vizinhos anteriores (lado, cima e diagonal esquerda)
                matrizDeLevenshtein[i][j] = Math.min(Math.min(matrizDeLevenshtein[i - 1][j] + 1,
                		matrizDeLevenshtein[i][j - 1] + 1), matrizDeLevenshtein[i - 1][j - 1] + letrasDiferentes);
            }
        }
        
        //o ultimo elemento da matriz eh a distancia minima
        return matrizDeLevenshtein[tamanhoDaPrimeiraPalavra][tamanhoDaSegundaPalavra];
    }
}