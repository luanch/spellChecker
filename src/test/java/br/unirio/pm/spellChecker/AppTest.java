package br.unirio.pm.spellChecker;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;


public class AppTest{
	
	SpellChecker spellChecker = new SpellChecker(1);
	List<String> listaEsperada = new ArrayList<String>();
	
	@Test
	public void testPalavraVazia()
	{
		assertEquals(listaEsperada ,spellChecker.verificarPalavra("", 4));
		assertEquals(listaEsperada ,spellChecker.verificarPalavra("", 1));
		assertEquals(listaEsperada ,spellChecker.verificarPalavra("", 0));
		assertEquals(listaEsperada ,spellChecker.verificarPalavra(null, 4));
		assertEquals(listaEsperada ,spellChecker.verificarPalavra(null, 0));
		assertEquals(listaEsperada ,spellChecker.verificarPalavra(null, 1));
	}
	
	@Test
	public void testLetraAMais(){
		listaEsperada.add("casa");
		assertEquals(listaEsperada ,spellChecker.verificarPalavra("casal", 1));
		
	}
	
	@Test
	public void testLetraTrocada(){
		listaEsperada.add("oi");
		assertEquals(listaEsperada ,spellChecker.verificarPalavra("oiae", 2));
		

	}

	@Test
	public void testLetraFaltando(){
		
	}
//		
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