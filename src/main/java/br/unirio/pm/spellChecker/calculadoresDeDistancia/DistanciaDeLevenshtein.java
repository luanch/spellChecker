package br.unirio.pm.spellChecker.calculadoresDeDistancia;

import br.unirio.pm.spellChecker.utilitariosTeclado.Teclado;

/**
 * Calcula a distancia (numero de operacoes necessarias) entre duas sequencias de caracteres
 */
public class DistanciaDeLevenshtein extends MoldeDeCalculadorDeDistanciaEntreStrings{	
	private Teclado teclado;

	
	public DistanciaDeLevenshtein(Teclado teclado) {
		this.teclado = teclado;	
	}
	
	/**
     * calcula a distancia entre duas palavras, retornando -1 caso não haja palavras a serem comparadas
     */
	
	public int calcular (String primeiraPalavra, String segundaPalavra){
		if (primeiraPalavra.length() == 0 && segundaPalavra.length() == 0) {
			return -1;
		}
        if (primeiraPalavra.length() == 0) 
        	return (int) Math.abs(segundaPalavra.length() * teclado.getCustoInsercaoRemocao() * 100);
        if (segundaPalavra.length() == 0) 
        	return (int) Math.abs(primeiraPalavra.length() * teclado.getCustoInsercaoRemocao() * 100);

        int tamanhoDaPrimeiraPalavra = primeiraPalavra.length();
        int tamanhoDaSegundaPalavra = segundaPalavra.length();
 
        double[][] matrizDeLevenshtein = new double[tamanhoDaPrimeiraPalavra + 1][tamanhoDaSegundaPalavra + 1];
        
        int i;
        // preenche a primeira linha e coluna com o valor corrente
        for ( i= 0; i <= tamanhoDaPrimeiraPalavra; i++)
            matrizDeLevenshtein[i][0] = i;
 
        for (i = 0; i <= tamanhoDaSegundaPalavra; i++)
            matrizDeLevenshtein[0][i] = i;
 
        
        //monta a matriz que gera a distancia minima para que uma string se torne outra 
        for (i = 1; i <= tamanhoDaPrimeiraPalavra; i++)
        {
            for (int j = 1; j <= tamanhoDaSegundaPalavra; j++)
            {
            	// caso as letras da linha e coluna sejam diferentes, adiciona-se 1 ao valor da posicao da matriz
            	char letraPrimeiraPalavra = primeiraPalavra.charAt(i - 1);
            	char letraSegundaPalavra = segundaPalavra.charAt(j - 1);
            			
            	double distanciaEntreLetras = teclado.getDistancia(letraPrimeiraPalavra, letraSegundaPalavra);
            	double custoInsercaoRemocao = teclado.getCustoInsercaoRemocao();
            	// adiciona na matriz o menor valor dentre os vizinhos anteriores (lado, cima e diagonal esquerda)
            	matrizDeLevenshtein[i][j] = 
                		minimo(matrizDeLevenshtein[i - 1][j] + custoInsercaoRemocao, // insercao
                				matrizDeLevenshtein[i][j - 1] + custoInsercaoRemocao, // remocao
                				matrizDeLevenshtein[i - 1][j - 1] + distanciaEntreLetras // substituicao
						);
            }
        }
        
        //o ultimo elemento da matriz eh a distancia minima
        return (int) Math.abs(matrizDeLevenshtein[tamanhoDaPrimeiraPalavra][tamanhoDaSegundaPalavra]*100);
	}
}