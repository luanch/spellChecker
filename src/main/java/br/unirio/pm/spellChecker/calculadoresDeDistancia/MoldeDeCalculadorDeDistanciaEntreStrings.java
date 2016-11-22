package br.unirio.pm.spellChecker.calculadoresDeDistancia;

/**
 * Classe abstrata que define um calculador de distancias entre strings
 */
public abstract class MoldeDeCalculadorDeDistanciaEntreStrings {
	
    public abstract int calcular(String primeiraString, String segundaString);
    

    protected int minimo(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
