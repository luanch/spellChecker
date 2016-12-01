package br.unirio.pm.spellChecker;

import java.util.ArrayList;

import br.unirio.pm.spellChecker.bkTree.BKTree;
import br.unirio.pm.spellChecker.modelos.PalavraComCusto;
import br.unirio.pm.spellChecker.modelos.Teclado;
import br.unirio.pm.spellChecker.modelos.TiposDeTeclado;
import br.unirio.pm.spellChecker.calculadoresDeDistancia.DistanciaDeDamerauLevenshtein;
import br.unirio.pm.spellChecker.calculadoresDeDistancia.DistanciaDeLevenshtein;
import br.unirio.pm.spellChecker.calculadoresDeDistancia.ModeloDeCalculadorDeDistanciaEntreStrings;
import br.unirio.pm.spellChecker.leitores.LeitorDePalavras;

/**
 * Classe que ve se uma palavra é válida para um dicionário e caso não seja, 
 * sugere palavras que o usuário pode ter desejado digitar
 */
public class SpellChecker {
	private BKTree arvoreDePalavras;
	private final static int CODIGO_LEVENSHTEIN = 1;
	private final static int CODIGO_DAMERAU_LEVENSHTEIN = 2;

	/**
	 * Construtor padrao
	 */
	public SpellChecker(int codigoCalculador, String nomeTeclado) {

		TiposDeTeclado tiposDeTeclado = new TiposDeTeclado();
		Teclado teclado = tiposDeTeclado.getTecladoByName(nomeTeclado);

		ModeloDeCalculadorDeDistanciaEntreStrings calculador;
		switch(codigoCalculador){	
		case CODIGO_DAMERAU_LEVENSHTEIN:
			calculador = new DistanciaDeDamerauLevenshtein(teclado);
			break;
		default:
			calculador = new DistanciaDeLevenshtein(teclado);
		}
		arvoreDePalavras = new BKTree(calculador);

		LeitorDePalavras leitorDePalavras = new LeitorDePalavras();
		leitorDePalavras.gerarDicionario(arvoreDePalavras);
	}

	/**
	 * Construtor da classe com reclado neutro
	 */
	public SpellChecker (int codigoCalculador) {
		Teclado teclado = new Teclado(true);
		ModeloDeCalculadorDeDistanciaEntreStrings calculador;
		switch(codigoCalculador){	
		case CODIGO_DAMERAU_LEVENSHTEIN:
			calculador = new DistanciaDeDamerauLevenshtein(teclado);
			break;
		default:
			calculador = new DistanciaDeLevenshtein(teclado);
		}
		arvoreDePalavras = new BKTree(calculador);

		LeitorDePalavras leitorDePalavras = new LeitorDePalavras();
		leitorDePalavras.gerarDicionario(arvoreDePalavras);
	}

	/**
	 * Busca palavras similares a uma palavra respeitando um limite de operacoes
	 * @param limiteDeOperacoes: quantidade maxima de operacoes que a palavra buscada pode sofrer
	 */
	public ArrayList<String> verificarPalavra(String palavra, int limiteDeOperacoes){
		ArrayList<PalavraComCusto> arrayCustoPalavra = new ArrayList<PalavraComCusto>();
		ArrayList<String> resultadoDaBusca = new ArrayList<String>();

		arrayCustoPalavra =  arvoreDePalavras.buscar(palavra, limiteDeOperacoes*100);
		for (PalavraComCusto custoPalavra: arrayCustoPalavra){
			resultadoDaBusca.add(custoPalavra.getPalavra());
		}
		return resultadoDaBusca;
	}

	/**
	 * Retorna a lista de Palavras sugeridas e suas respectivas distancias da palavra original
	 */
	public ArrayList<PalavraComCusto> verificarPalavraComDistancia(String palavra, int limiteDeOperacoes){
		ArrayList<PalavraComCusto> arrayCustoPalavra = new ArrayList<PalavraComCusto>();
		arrayCustoPalavra =  arvoreDePalavras.buscar(palavra, limiteDeOperacoes*100);
		return arrayCustoPalavra;
	}

	/**
	 * Getter estático para o código da distancia de Levenshtein 
	 */
	public static int getCodigoLevenshtein(){
		return CODIGO_LEVENSHTEIN;
	}

	/**
	 * Getter estático para o código da distancia de Damerau-Levenshtein 
	 */
	public static int getCodigoDamerauLevenshtein(){
		return CODIGO_DAMERAU_LEVENSHTEIN;
	}
}
