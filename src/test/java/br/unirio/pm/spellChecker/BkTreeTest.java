package br.unirio.pm.spellChecker;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.unirio.pm.spellChecker.LeitorDePalavras.LeitorDePalavras;
import br.unirio.pm.spellChecker.bkTree.BKTree;
import br.unirio.pm.spellChecker.calculadoresDeDistancia.DistanciaDeLevenshtein;
import br.unirio.pm.spellChecker.utilitariosTeclado.Teclado;
import br.unirio.pm.spellChecker.utilitariosTeclado.TiposDeTeclado;


public class BkTreeTest {
	
	private static BKTree bkTree;

	
	@Before 
	public void inicializarTestes(){
	}
	
	@BeforeClass
	public static void inicializadorGeral(){
		

		
	}
	
	@Test
	public void testInsercaoDePalavra(){
		


    	
    	TiposDeTeclado tiposDeTeclado = new TiposDeTeclado();
    	Teclado teclado = tiposDeTeclado.getTecladoByName("QWERTY");
    	
		
		DistanciaDeLevenshtein calculador = new DistanciaDeLevenshtein(teclado);
		
		BKTree bkTree = new BKTree(calculador);
		
		
		
		assertFalse (bkTree.contem("casa"));
		bkTree.inserir("casa");
		assertTrue (bkTree.contem("casa"));
		
		assertFalse(bkTree.contem("telhado"));
		bkTree.inserir("telhado");
		
		bkTree.inserir("porta");
		assertTrue (bkTree.contem("porta"));
		assertTrue (bkTree.contem("telhado"));

	}	
}
