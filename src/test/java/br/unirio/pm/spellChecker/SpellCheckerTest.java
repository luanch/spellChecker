package br.unirio.pm.spellChecker;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.unirio.pm.spellChecker.LeitorDePalavras.LeitorDePalavras;
import br.unirio.pm.spellChecker.bkTree.BKTree;
import br.unirio.pm.spellChecker.calculadoresDeDistancia.CustoPalavra;
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
	
//	@Test
//	public void testPalavraVaziaLevenshtein()
//	{
//		spellChecker = new SpellChecker(1, "QWERTY");
//		assertEquals(listaEsperada, spellChecker.verificarPalavra("", 4));
//		assertEquals(listaEsperada, spellChecker.verificarPalavra("", 1));
//		assertEquals(listaEsperada, spellChecker.verificarPalavra("", 0));
//		assertEquals(listaEsperada, spellChecker.verificarPalavra(null, 4));
//		assertEquals(listaEsperada, spellChecker.verificarPalavra(null, 0));
//		assertEquals(listaEsperada, spellChecker.verificarPalavra(null, 1));
//	}
//	
//	@Test
//	public void testLetraTrocadaLevenshtein(){
//		spellChecker = new SpellChecker(1, "QWERTY");
//
//		ArrayList<String> lista = spellChecker.verificarPalavra("fiera", 1);
//		boolean resp = lista.contains("FEIRA");
//		assertTrue(resp);
//		
//		assertTrue(spellChecker.verificarPalavra("csaa", 2).contains("CASA"));
//		
//		assertTrue(spellChecker.verificarPalavra("csaa-", 2).contains("CASA"));
//		
//		
//	}
//	
//	@Test
//	public void testLetraAMaisLevenshtein(){
//		spellChecker = new SpellChecker(1, "QWERTY");
//
//		
//		assertTrue(spellChecker.verificarPalavra("casaz", 3).contains("CASA"));
//	
//		
//		
//		assertTrue(spellChecker.verificarPalavra("paralelepipewwe", 4).contains("PARALELEPIPEDO"));
//		assertTrue(spellChecker.verificarPalavra("casae", 1).contains("casa".toUpperCase()));
//		
//		
//		assertFalse(spellChecker.verificarPalavra("zurrarer", 1).contains("ZURRAR"));
//	} 
//	
//
//	@Test
//	public void testLetraFaltandoLevenshtein(){
//		spellChecker = new SpellChecker(1, "QWERTY");
//		
//		assertTrue(spellChecker.verificarPalavra("feramenta", 1).contains("FERRAMENTA"));
//		assertTrue(spellChecker.verificarPalavra("ferrament", 1).contains("FERRAMENTA"));
//
//		assertFalse(spellChecker.verificarPalavra("zura", 1).contains("ZURRAR"));
//		
//	}
	// COMENTADO AGORA
	
	@Test
	public void testSubstituicaoLevenshtein(){
		spellChecker = new SpellChecker(1, "QWERTY");
		
//		ArrayList<String> lista = spellChecker.verificarPalavra("fiera", 1);
//		boolean resp = lista.contains("FEIRA");
//		System.out.println(lista);
//		assertTrue(resp);
		
		
		
		ArrayList<String> lista = spellChecker.verificarPalavra("ferramunta", 1);
		
		
//			   arrayCustoPalavra =  dicionarioDePalavras.buscar(palavra, limiteDeOperacoes*100);
//		   for (CustoPalavra custoPalavra: arrayCustoPalavra){
//			   resultadoDaBusca.add(custoPalavra.getPalavra());
//		   }
//		
//		
		
		
		
		boolean resp = lista.contains("FERRAMENTA");
		System.out.println(lista);
		assertTrue(resp);
		
		
		
		
		
//		ArrayList<CustoPalavra> lista2 = spellChecker.verificarPalavraComErro("ferramrnta", 1);
//		
//		
//		ArrayList<String> palavras = new ArrayList<String>();
//		ArrayList<Integer> custos = new ArrayList<Integer>();
//		   for (CustoPalavra custoPalavra: lista2){
//			   palavras.add(custoPalavra.getPalavra());
//			   custos.add(custoPalavra.getDistancia());
//		   }
//		
//		
//		boolean resp2 = lista.contains("FERRAMENTA");
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

}