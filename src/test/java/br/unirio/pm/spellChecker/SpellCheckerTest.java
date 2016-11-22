package br.unirio.pm.spellChecker;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class SpellCheckerTest{
	
	private static SpellChecker spellChecker;
	private List<String> listaEsperada;

	
	@Before 
	public void inicializarTestes(){
		listaEsperada = new ArrayList<String>();
	}
	
	@BeforeClass
	public static void inicializadorGeral(){
		System.out.println("Comecei");
		spellChecker = new SpellChecker(1);
		System.out.println("Terminei");
	}
	
	@Test
	public void testPalavraVazia()
	{
		assertEquals(listaEsperada, spellChecker.verificarPalavra("", 4));
		assertEquals(listaEsperada, spellChecker.verificarPalavra("", 1));
		assertEquals(listaEsperada, spellChecker.verificarPalavra("", 0));
		assertEquals(listaEsperada, spellChecker.verificarPalavra(null, 4));
		assertEquals(listaEsperada, spellChecker.verificarPalavra(null, 0));
		assertEquals(listaEsperada, spellChecker.verificarPalavra(null, 1));
	}
	
	@Test
	public void testLetraTrocada(){
		List<String> lista = spellChecker.verificarPalavra("fiera", 2);
		boolean resp = lista.contains("FEIRA");
		assertTrue(resp);
		
		assertTrue(spellChecker.verificarPalavra("csaa", 2).contains("CASA"));
		
		assertTrue(spellChecker.verificarPalavra("csaa-", 2).contains("CASA"));
		
		
	}
	
	@Test
	public void testLetraAMais(){
		
		assertTrue(spellChecker.verificarPalavra("casaz", 1).contains("CASA"));
		assertTrue(spellChecker.verificarPalavra("paralelepipewwe", 4).contains("PARALELEPIPEDO"));
		
		assertFalse(spellChecker.verificarPalavra("zurrarer", 1).contains("ZURRAR"));
	} 
	

	@Test
	public void testLetraFaltando(){
		assertTrue(spellChecker.verificarPalavra("feramenta", 1).contains("FERRAMENTA"));
		
		assertFalse(spellChecker.verificarPalavra("zura", 1).contains("ZURRAR"));
		
	}
		/*
//		assertEquals(new String[]{"casa"},spellChecker.checarOrtografia("casal"));
//		assertEquals(new String[]{"casa"},spellChecker.checarOrtografia("cas"));
//		assertEquals(new String[]{"casa"},spellChecker.checarOrtografia("caas"));
//		assertEquals(new String[]{"casa"},spellChecker.checarOrtografia("csa"));
//		assertEquals(null,spellChecker.checarOrtografia(""));
//		assertEquals(new String[]{},spellChecker.checarOrtografia("Ola"));
//		assertEquals(new String[]{"oi"},spellChecker.checarOrtografia("oie"));
//	}
//
//	public void testLevenshteinDistance(){
//		limitedCompare(null, *, *)             = IllegalArgumentException
//			     * limitedCompare(*, null, *)             = IllegalArgumentException
//			     * limitedCompare(*, *, -1)               = IllegalArgumentException
//			     * limitedCompare("","", 0)               = 0
//			     * limitedCompare("aaapppp", "", 8)       = 7
//			     * limitedCompare("aaapppp", "", 7)       = 7
//			     * limitedCompare("aaapppp", "", 6))      = -1
//			     * limitedCompare("elephant", "hippo", 7) = 7
//			     * limitedCompare("elephant", "hippo", 6) = -1
//			     * limitedCompare("hippo", "elephant", 7) = 7
//			     * limitedCompare("hippo", "elephant", 6) = -1
//	}
//	
//	*/
}