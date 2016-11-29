package br.unirio.pm.spellChecker;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.unirio.pm.spellChecker.LeitorDePalavras.LeitorDePalavras;
import br.unirio.pm.spellChecker.bkTree.BKTree;
import br.unirio.pm.spellChecker.calculadoresDeDistancia.PalavraComCusto;
import br.unirio.pm.spellChecker.calculadoresDeDistancia.DistanciaDeDamerauLevenshtein;
import br.unirio.pm.spellChecker.calculadoresDeDistancia.DistanciaDeLevenshtein;
import br.unirio.pm.spellChecker.calculadoresDeDistancia.MoldeDeCalculadorDeDistanciaEntreStrings;
import br.unirio.pm.spellChecker.utilitariosTeclado.Teclado;
import br.unirio.pm.spellChecker.utilitariosTeclado.TiposDeTeclado;


public class SpellCheckerTest{
	
	private static SpellChecker spellChecker;
	private List<String> listaEsperada;

	
	@Before 
	public void inicializarTestes(){
		listaEsperada = new ArrayList<String>();
	}
	
	@BeforeClass
	public static void inicializadorGeral(){
	}
	
	@Test
	public void testPalavraVaziaLevenshtein()
	{
		spellChecker = new SpellChecker(1, "QWERTY");
		assertEquals(listaEsperada, spellChecker.verificarPalavra("", 4));
		assertEquals(listaEsperada, spellChecker.verificarPalavra("", 1));
		assertEquals(listaEsperada, spellChecker.verificarPalavra("", 0));
		assertEquals(listaEsperada, spellChecker.verificarPalavra(null, 4));
		assertEquals(listaEsperada, spellChecker.verificarPalavra(null, 0));
		assertEquals(listaEsperada, spellChecker.verificarPalavra(null, 1));
	}
	
	@Test
	public void testMarcio() {
		//teclado neutro usando Levenshtein
		spellChecker = new SpellChecker(1);
		
		ArrayList<PalavraComCusto> listaCasa = spellChecker.verificarPalavraComErro("casa", 1);
		ArrayList<String> palavrasCasa = new ArrayList<String>();
		   for (PalavraComCusto custoPalavra: listaCasa){
			   palavrasCasa.add(custoPalavra.getPalavra());
			   System.out.print(custoPalavra.getPalavra() + ":" + custoPalavra.getDistancia() + ";");
		   }
		  // System.out.println();
		   
		assertTrue(palavrasCasa.get(0).equals("casa"));
		assertTrue(palavrasCasa.get(1).equals("asa"));
		assertTrue(palavrasCasa.get(2).equals("cas"));
		assertTrue(palavrasCasa.get(3).equals("casar"));
		assertTrue(palavrasCasa.get(4).equals("casal"));
		assertTrue(palavrasCasa.get(5).equals("causa"));
		assertTrue(palavrasCasa.get(6).equals("casba"));
		assertTrue(palavrasCasa.get(7).equals("casca"));
		assertTrue(palavrasCasa.get(8).equals("casta"));
		assertTrue(palavrasCasa.get(9).equals("caca"));



		/*
		
		BurkhardKellerTreeSearchResult result2 = tree.search("cervega", 2, 10);
		check(result2, 0, "cerveja", 1.0);
		check(result2, 1, "cereja", 2.0);
		check(result2, 2, "corveta", 2.0);
		check(result2, 3, "corveia", 2.0);
		check(result2, 4, "certeza", 2.0);
		
		BurkhardKellerTreeSearchResult result3 = tree.search("aviea", 2, 10);
		check(result3, 0, "aia", 2.0);
		check(result3, 1, "ave", 2.0);
		check(result3, 2, "via", 2.0);
		check(result3, 3, "avioes", 2.0);
		check(result3, 4, "avisar", 2.0);
		check(result3, 5, "avivar", 2.0);
		check(result3, 6, "avidez", 2.0);
		check(result3, 7, "alinea", 2.0);
		check(result3, 8, "ravina", 2.0);
		check(result3, 9, "vies", 2.0);
		*/
	}
	
	@Test
	public void testLetraAMaisLevenshtein(){
		spellChecker = new SpellChecker(1, "QWERTY");

		
		assertTrue(spellChecker.verificarPalavra("casaz", 3).contains("CASA"));
	
		
		
		assertTrue(spellChecker.verificarPalavra("paralelepipewwe", 4).contains("PARALELEPIPEDO"));
		assertTrue(spellChecker.verificarPalavra("casae", 1).contains("casa".toUpperCase()));
		
		//Nao contem
		ArrayList<PalavraComCusto> listaZurrarer = spellChecker.verificarPalavraComErro("kzurrar", 1);
		ArrayList<String> palavrasZurrarer = new ArrayList<String>();
		   for (PalavraComCusto custoPalavra: listaZurrarer){
			   palavrasZurrarer.add(custoPalavra.getPalavra());
			   System.out.print(custoPalavra.getPalavra() + ":" + custoPalavra.getDistancia() + ";");
		   }
		  // System.out.println();
		   
		assertFalse(palavrasZurrarer.contains("ZURRAR"));
		
		
	} 
	

	@Test
	public void testLetraFaltandoLevenshtein(){
		spellChecker = new SpellChecker(1, "QWERTY");
		
		assertTrue(spellChecker.verificarPalavra("feramenta", 1).contains("FERRAMENTA"));
		assertTrue(spellChecker.verificarPalavra("ferrament", 1).contains("FERRAMENTA"));

		assertFalse(spellChecker.verificarPalavra("zura", 1).contains("ZURRAR"));
		
	}
	
	@Test
	public void testSubstituicaoLevenshtein(){
		spellChecker = new SpellChecker(1, "QWERTY");
		
		// FEIRA nao esta presente, pq levenshtein nao descrimina o caso de troca
		// assumindo o mesmo como duas substituicoes
		ArrayList<PalavraComCusto> listaFiera = spellChecker.verificarPalavraComErro("fiera", 2);
		ArrayList<String> palavrasFiera = new ArrayList<String>();
		   for (PalavraComCusto custoPalavra: listaFiera){
			   palavrasFiera.add(custoPalavra.getPalavra());
		//	   System.out.print(custoPalavra.getPalavra() + ":" + custoPalavra.getDistancia() + ";");
		   }
		  // System.out.println();
		assertFalse(palavrasFiera.contains("FEIRA"));
		
		// CASA entra na lista, mas como duas substituições
		ArrayList<PalavraComCusto> listaCsaa = spellChecker.verificarPalavraComErro("csaa-", 2);
		ArrayList<String> palavrasCsaa = new ArrayList<String>();
		  for (PalavraComCusto custoPalavra: listaCsaa){
			   palavrasCsaa.add(custoPalavra.getPalavra());
			   //System.out.print(custoPalavra.getPalavra() + ":" + custoPalavra.getDistancia() + ";");
		   }
		assertTrue(palavrasCsaa.contains("CASA"));

		//System.out.println("Ferramunta");
		ArrayList<PalavraComCusto> listaFerramunta = spellChecker.verificarPalavraComErro("ferramunta", 1);
		ArrayList<String> palavrasFerramunta = new ArrayList<String>();
		   for (PalavraComCusto custoPalavra: listaFerramunta){
			   palavrasFerramunta.add(custoPalavra.getPalavra());
			   //System.out.print(custoPalavra.getPalavra() + ":" + custoPalavra.getDistancia() + ";");
		   }
		//   System.out.println();
		assertTrue(palavrasFerramunta.contains("FERRAMENTA"));
		
//		
//		
//		
//		
//		ArrayList<CustoPalavra> lista2 = spellChecker.verificarPalavraComErro("cafa", 1);
//		
//		ArrayList<String> palavras = new ArrayList<String>();
//		ArrayList<Integer> custos = new ArrayList<Integer>();
//		   for (CustoPalavra custoPalavra: lista2){
//			   palavras.add(custoPalavra.getPalavra());
//			   custos.add(custoPalavra.getDistancia());
//		   }
//		
//		
//		boolean resp2 = palavras.contains("CADA");
//		System.out.println(palavras);		
//		System.out.println(custos);
//
//		assertTrue(resp2);
//		
//		
//		assertTrue(spellChecker.verificarPalavra("ferramenfa", 1).contains("FERRAMENTA"));
//
//		assertFalse(spellChecker.verificarPalavra("zurfa", 1).contains("ZURRAR"));
//		
	}


	@Test
	public void testSubstituicaoDamerauLevenshtein(){
		spellChecker = new SpellChecker(2, "QWERTY");
		
		// Aqui em Damerau-Levenshtein, FEIRA esta presente, pq descrimina o caso de troca
		assertTrue(spellChecker.verificarPalavra("fiera", 2).contains("FEIRA"));
	}

}