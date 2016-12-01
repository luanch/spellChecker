package br.unirio.pm.spellChecker;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.unirio.pm.spellChecker.bkTree.BKTree;
import br.unirio.pm.spellChecker.calculadoresDeDistancia.DistanciaDeDamerauLevenshtein;
import br.unirio.pm.spellChecker.calculadoresDeDistancia.DistanciaDeLevenshtein;
import br.unirio.pm.spellChecker.calculadoresDeDistancia.ModeloDeCalculadorDeDistanciaEntreStrings;
import br.unirio.pm.spellChecker.leitores.LeitorDePalavras;
import br.unirio.pm.spellChecker.modelos.PalavraComCusto;
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
	public void testAdicionandoPalavras(){
		TiposDeTeclado tiposDeTeclado = new TiposDeTeclado();
		Teclado teclado = tiposDeTeclado.getTecladoByName("QWERTY");
		ModeloDeCalculadorDeDistanciaEntreStrings calculador;
		calculador = new DistanciaDeDamerauLevenshtein(teclado);
		BKTree bkTree = new BKTree(calculador);
		assertEquals(new ArrayList<PalavraComCusto>(),bkTree.buscar("casa", 1));
		bkTree.inserir("casa");
		bkTree.contem("CASA");
		
		assertEquals(new ArrayList<PalavraComCusto>(),bkTree.buscar("arroz", 1));
		bkTree.inserir("arroz");
		bkTree.contem("ARROZ");
		
		assertEquals(new ArrayList<PalavraComCusto>(),bkTree.buscar("asdf", 1));
		bkTree.inserir("asdf");
		bkTree.contem("ASDF");
	}
	
	@Test
	public void testPalavraPresentes(){
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
