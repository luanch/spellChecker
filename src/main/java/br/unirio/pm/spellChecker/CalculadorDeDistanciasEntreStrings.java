package br.unirio.pm.spellChecker;
/**
 * Classe que calcula a distancia entre duas strings
 */
public  class CalculadorDeDistanciasEntreStrings {
	
	private final int CODIGO_LEVENSHTEIN = 1;
	private MoldeDeCalculadorDeDistanciaEntreStrings calculador;
	
	/**
	 * Construtor
	 */
	public CalculadorDeDistanciasEntreStrings(int codigoCalculador){
		switch(codigoCalculador){
		case(1):
			calculador = new CalculadorDeDistanciaDeLevenshtein();
		}
	}
	
	public int getCodigoLevenshtein(){
		return CODIGO_LEVENSHTEIN;
	}
}
