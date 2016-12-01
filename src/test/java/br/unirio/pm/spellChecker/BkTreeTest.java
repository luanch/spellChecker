package br.unirio.pm.spellChecker;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.unirio.pm.spellChecker.bkTree.BKTree;
import br.unirio.pm.spellChecker.calculadoresDeDistancia.DistanciaDeLevenshtein;
import br.unirio.pm.spellChecker.leitores.LeitorDePalavras;
import br.unirio.pm.spellChecker.modelos.Teclado;
import br.unirio.pm.spellChecker.modelos.TiposDeTeclado;


public class BkTreeTest {
	
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
		LeitorDePalavras leitorDePalavras = new LeitorDePalavras();
        leitorDePalavras.gerarDicionario(bkTree);

		
		
		assertFalse (bkTree.contem("casardyz"));
		assertTrue (bkTree.contem("casa"));
		
		assertFalse(bkTree.contem("telhaawrado"));
		assertTrue(bkTree.contem("telhado"));

	}	
}
