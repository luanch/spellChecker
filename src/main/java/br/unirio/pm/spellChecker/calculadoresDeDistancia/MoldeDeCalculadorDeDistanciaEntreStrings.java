package br.unirio.pm.spellChecker.calculadoresDeDistancia;

import br.unirio.pm.spellChecker.utilitariosTeclado.Teclado;

/**
 * Classe abstrata que define um calculador de distancias entre strings
 */
public abstract class MoldeDeCalculadorDeDistanciaEntreStrings {
	
	protected Teclado teclado;
	
    public abstract int calcular(String primeiraPalavra, String segundaPalavra);
    

    protected double minimo(double a, double b, double c) {
        return Math.min(a, Math.min(b, c));
    }
}
