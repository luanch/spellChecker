package br.unirio.pm.spellChecker;

public abstract class CalculadorDeDistanciasEntreStrings {
    
	 protected static LevenshteinDistance instancia = null;

	/**
     *  limite é uma variável que, uma vez definida no construtor, não pode ser alterada
     */
    protected int limite;
    
    

    
    public abstract int calcular();
}
