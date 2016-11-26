package br.unirio.pm.spellChecker;

import java.util.ArrayList;
import java.util.List;

import br.unirio.pm.spellChecker.LeitorDePalavras.LeitorDePalavras;
import br.unirio.pm.spellChecker.bkTree.BKTree;
import br.unirio.pm.spellChecker.calculadoresDeDistancia.CustoPalavra;
import br.unirio.pm.spellChecker.calculadoresDeDistancia.DistanciaDeDamerauLevenshtein;
import br.unirio.pm.spellChecker.calculadoresDeDistancia.DistanciaDeLevenshtein;
import br.unirio.pm.spellChecker.calculadoresDeDistancia.MoldeDeCalculadorDeDistanciaEntreStrings;
import br.unirio.pm.spellChecker.utilitariosTeclado.LeitorDeTeclado;
import br.unirio.pm.spellChecker.utilitariosTeclado.Teclado;
import br.unirio.pm.spellChecker.utilitariosTeclado.TiposDeTeclado;

/**
 * Classe que ve se uma palavra é válida para um dicionário e caso não seja, 
 * sugere palavras que o usuário pode ter desejado digitar
 */
public class SpellChecker {
	private BKTree dicionarioDePalavras;

	private final int CODIGO_LEVENSHTEIN = 1;
	private final int CODIGO_DAMERAU_LEVENSHTEIN = 2;
	
	/**
	 * Construtor da classe
	 */
    public SpellChecker(int codigoCalculador, String nomeTeclado) {
    	
    	TiposDeTeclado tiposDeTeclado = new TiposDeTeclado();
    	Teclado teclado = tiposDeTeclado.getTecladoByName(nomeTeclado);
    			
    	MoldeDeCalculadorDeDistanciaEntreStrings calculador;
		switch(codigoCalculador){	
			case CODIGO_DAMERAU_LEVENSHTEIN:
				calculador = new DistanciaDeDamerauLevenshtein(teclado);
				break;
			default:
				calculador = new DistanciaDeLevenshtein(teclado);
		}
    	dicionarioDePalavras = new BKTree(calculador);
    	
    	LeitorDePalavras leitorDePalavras = new LeitorDePalavras();
        leitorDePalavras.gerarDicionario(dicionarioDePalavras);
    }
    
    
	/**
	 * Busca palavras similares a uma palavra respeitando um limite de operacoes
	 * @param limiteDeOperacoes: quantidade maxima de operacoes que a palavra buscada pode sofrer
	 */
   public ArrayList<String> verificarPalavra(String palavra, int limiteDeOperacoes){
       
	   ArrayList<CustoPalavra> arrayCustoPalavra = new ArrayList<CustoPalavra>();
	   ArrayList<String> resultadoDaBusca = new ArrayList<String>();
	   
	   arrayCustoPalavra =  dicionarioDePalavras.buscar(palavra, limiteDeOperacoes*100);
	   for (CustoPalavra custoPalavra: arrayCustoPalavra){
		   resultadoDaBusca.add(custoPalavra.getPalavra());
	   }
	   
	   return resultadoDaBusca;
   }
   
   public ArrayList<CustoPalavra> verificarPalavraComErro(String palavra, int limiteDeOperacoes){
	   ArrayList<CustoPalavra> arrayCustoPalavra = new ArrayList<CustoPalavra>();
	   
	   arrayCustoPalavra =  dicionarioDePalavras.buscar(palavra, limiteDeOperacoes*100);

	   
	   return arrayCustoPalavra;
   }
    

}
