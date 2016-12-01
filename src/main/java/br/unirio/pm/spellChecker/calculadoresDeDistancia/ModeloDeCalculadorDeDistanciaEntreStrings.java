package br.unirio.pm.spellChecker.calculadoresDeDistancia;

/**
 * Classe abstrata que define um calculador de distancias entre strings
 */
public abstract class ModeloDeCalculadorDeDistanciaEntreStrings {
	
	/**
	 * Calcula a quantidade de operacoes necessarias para chegar de uma palavra
	 * na outra, de acordo com o calculador que a implementa
	 */
    public abstract int calcular(String primeiraPalavra, String segundaPalavra);

    /**
     * retorna o valor minimo dentre as 3 variaveis passadas
     */
    protected double minimo(double a, double b, double c) {
        return Math.min(a, Math.min(b, c));
    }
}