package br.unirio.pm.spellChecker;

import static org.junit.Assert.*;

import org.junit.Test;


public class AppTest
{
	@Test
	public void testLetraAMais()
	{
		SpellChecker spellChecker = new SpellChecker();
		
		assertEquals(new String[]{"casa"},spellChecker.checarOrtografia("casal"));
		assertEquals(new String[]{"casa"},spellChecker.checarOrtografia("cas"));
		assertEquals(new String[]{"casa"},spellChecker.checarOrtografia("caas"));
		assertEquals(new String[]{"casa"},spellChecker.checarOrtografia("csa"));
		assertEquals(null,spellChecker.checarOrtografia(""));
		assertEquals(new String[]{},spellChecker.checarOrtografia("Ola"));
		assertEquals(new String[]{"oi"},spellChecker.checarOrtografia("oie"));
	}
	/*
	public void testLevenshteinDistance(){
		limitedCompare(null, *, *)             = IllegalArgumentException
			     * limitedCompare(*, null, *)             = IllegalArgumentException
			     * limitedCompare(*, *, -1)               = IllegalArgumentException
			     * limitedCompare("","", 0)               = 0
			     * limitedCompare("aaapppp", "", 8)       = 7
			     * limitedCompare("aaapppp", "", 7)       = 7
			     * limitedCompare("aaapppp", "", 6))      = -1
			     * limitedCompare("elephant", "hippo", 7) = 7
			     * limitedCompare("elephant", "hippo", 6) = -1
			     * limitedCompare("hippo", "elephant", 7) = 7
			     * limitedCompare("hippo", "elephant", 6) = -1
	}
	
	*/
}