package br.unirio.pm.spellChecker;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.unirio.pm.spellChecker.bkTree.BKTree;
import br.unirio.pm.spellChecker.modelos.PalavraComCusto;
import br.unirio.pm.spellChecker.modelos.Teclado;
import br.unirio.pm.spellChecker.modelos.TiposDeTeclado;
import br.unirio.pm.spellChecker.calculadoresDeDistancia.DistanciaDeDamerauLevenshtein;
import br.unirio.pm.spellChecker.calculadoresDeDistancia.DistanciaDeLevenshtein;
import br.unirio.pm.spellChecker.calculadoresDeDistancia.ModeloDeCalculadorDeDistanciaEntreStrings;
import br.unirio.pm.spellChecker.leitores.LeitorDePalavras;


public class SpellCheckerTest{

	private static SpellChecker spellChecker;
	private List<String> listaEsperada;

	@Before 
	public void inicializarTestes(){
		listaEsperada = new ArrayList<String>();
	}

	@Test
	public void testMarcioLevenshteinTecladoNeutro() {
		//teclado neutro usando Levenshtein
		spellChecker = new SpellChecker(1);
		ArrayList<PalavraComCusto> listaCasa = spellChecker.verificarPalavraComDistancia("casa", 1);
		ArrayList<String> palavrasCasa = new ArrayList<String>();
		for (PalavraComCusto custoPalavra: listaCasa){
			palavrasCasa.add(custoPalavra.getPalavra());
		}
		assertTrue(palavrasCasa.get(0).equals("CASA"));
		assertTrue(palavrasCasa.contains("ASA"));		

		ArrayList<PalavraComCusto> listaCervega = spellChecker.verificarPalavraComDistancia("cervega", 2);
		ArrayList<String> palavrasCervega = new ArrayList<String>();
		for (PalavraComCusto custoPalavra: listaCervega){
			palavrasCervega.add(custoPalavra.getPalavra());
		}
		assertTrue(palavrasCervega.get(0).equals("CERVEJA"));
		assertTrue(palavrasCervega.contains("CEREJA"));
		assertTrue(palavrasCervega.contains("CORVETA"));
		assertTrue(palavrasCervega.contains("CERTEZA"));
		assertTrue(palavrasCervega.contains("CORVEIA"));

		ArrayList<PalavraComCusto> listaAviea = spellChecker.verificarPalavraComDistancia("aviea", 2);
		ArrayList<String> palavrasAviea = new ArrayList<String>();
		for (PalavraComCusto custoPalavra: listaAviea){
			palavrasAviea.add(custoPalavra.getPalavra());
		}
		assertTrue(palavrasAviea.contains("AIA"));
		assertTrue(palavrasAviea.contains("AVE"));
		assertTrue(palavrasAviea.contains("VIA"));
	}

	@Test
	public void testMarcioDamerauLevenshteinTecladoNeutro() {
		//teclado neutro usando Levenshtein
		spellChecker = new SpellChecker(2);
		ArrayList<PalavraComCusto> listaCasa = spellChecker.verificarPalavraComDistancia("casa", 1);
		ArrayList<String> palavrasCasa = new ArrayList<String>();
		for (PalavraComCusto custoPalavra: listaCasa){
			palavrasCasa.add(custoPalavra.getPalavra());
		}
		assertTrue(palavrasCasa.get(0).equals("CASA"));
		assertTrue(palavrasCasa.contains("ASA"));
		assertTrue(palavrasCasa.contains("CAS"));

		ArrayList<PalavraComCusto> listaCervega = spellChecker.verificarPalavraComDistancia("cervega", 2);
		ArrayList<String> palavrasCervega = new ArrayList<String>();
		for (PalavraComCusto custoPalavra: listaCervega){
			palavrasCervega.add(custoPalavra.getPalavra());
		}

		assertTrue(palavrasCervega.contains("CERVEJA"));
		assertTrue(palavrasCervega.contains("CEREJA"));
		assertTrue(palavrasCervega.contains("CERTEZA"));
		assertTrue(palavrasCervega.contains("CORVEIA"));
		assertTrue(palavrasCervega.contains("CORVETA"));

		ArrayList<PalavraComCusto> listaAviea = spellChecker.verificarPalavraComDistancia("aviea", 2);
		ArrayList<String> palavrasAviea = new ArrayList<String>();
		for (PalavraComCusto custoPalavra: listaAviea){
			palavrasAviea.add(custoPalavra.getPalavra());
		}
		assertTrue(palavrasAviea.get(0).equals("AVEIA"));
		assertTrue(palavrasAviea.contains("VIA"));
	}

	@Test
	public void testMarcioLevenshteinQwerty(){		
		spellChecker = new SpellChecker(1, "QWERTY");
		ArrayList<PalavraComCusto> result = spellChecker.verificarPalavraComDistancia("casa", 1);
		check(result, 0, "casa", 0.0);
		check(result, 1, "cada", 0.11);

		spellChecker = new SpellChecker(1, "QWERTY");
		ArrayList<PalavraComCusto> result2 = spellChecker.verificarPalavraComDistancia("cervega", 2);
		check(result2, 0, "cerveja", 0.22);
		check(result2, 1, "cereja", 0.47);
		check(result2, 2, "xereta", 0.48);
		check(result2, 3, "verbete", 0.54);
		check(result2, 4, "carreta", 0.57);
		check(result2, 7, "vereda", 0.58);
		check(result2, 8, "refrega", 0.59);

		spellChecker = new SpellChecker(1, "QWERTY");
		ArrayList<PalavraComCusto> result3 = spellChecker.verificarPalavraComDistancia("aviea", 2);
		check(result3, 0, "acida", 0.23);
		check(result3, 3, "aries", 0.35);
		check(result3, 8, "alea", 0.45);
	}

	@Test
	public void testMarcioDemerauTecladoQwerty(){
		spellChecker = new SpellChecker(2, "QWERTY");
		ArrayList<PalavraComCusto> result = spellChecker.verificarPalavraComDistancia("casa", 1);
		check(result, 0, "casa", 0.0);
		check(result, 1, "cada", 0.11);

		spellChecker = new SpellChecker(2, "QWERTY");
		ArrayList<PalavraComCusto> result2 = spellChecker.verificarPalavraComDistancia("cervega", 2);
		check(result2, 0, "cerveja", 0.22);
		check(result2, 1, "cereja", 0.47);
		check(result2, 2, "xereta", 0.48);
		check(result2, 3, "verbete", 0.54);
		check(result2, 7, "vereda", 0.58);
		check(result2, 8, "refrega", 0.59);

		spellChecker = new SpellChecker(2, "QWERTY");
		ArrayList<PalavraComCusto> result3 = spellChecker.verificarPalavraComDistancia("aviea", 2);
		check(result3, 0, "acida", 0.23);
		check(result3, 1, "aveia", 0.25);
		check(result3, 4, "aries", 0.35);
	}

	@Test
	public void testMarcioDvorakLevenshtein(){
		spellChecker = new SpellChecker(1, "DVORAK");
		ArrayList<PalavraComCusto> result = spellChecker.verificarPalavraComDistancia("casa", 2);
		check(result, 0, "casa", 0.0);

		spellChecker = new SpellChecker(1, "DVORAK");
		ArrayList<PalavraComCusto> result2 = spellChecker.verificarPalavraComDistancia("cervega", 2);	
		check(result2, 0, "corveta", 0.26);
		check(result2, 1, "corveia", 0.35);
		check(result2, 2, "corneta", 0.37);
		check(result2, 3, "corrego", 0.44);
		check(result2, 6, "nervura", 0.48);
		check(result2, 7, "cerveja", 0.49);
		check(result2, 8, "centena", 0.51);
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
	public void testLetraAMaisLevenshtein(){
		spellChecker = new SpellChecker(1, "QWERTY");	
		assertTrue(spellChecker.verificarPalavra("casaz", 3).contains("CASA"));
		assertTrue(spellChecker.verificarPalavra("paralelepipewwe", 4).contains("PARALELEPIPEDO"));
		assertTrue(spellChecker.verificarPalavra("casae", 1).contains("casa".toUpperCase()));

		//Nao contem
		ArrayList<PalavraComCusto> listaZurrarer = spellChecker.verificarPalavraComDistancia("kzurrar", 1);
		ArrayList<String> palavrasZurrarer = new ArrayList<String>();
		for (PalavraComCusto custoPalavra: listaZurrarer){
			palavrasZurrarer.add(custoPalavra.getPalavra());
		}	   
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
		ArrayList<PalavraComCusto> listaFiera = spellChecker.verificarPalavraComDistancia("fiera", 2);
		ArrayList<String> palavrasFiera = new ArrayList<String>();
		for (PalavraComCusto custoPalavra: listaFiera){
			palavrasFiera.add(custoPalavra.getPalavra());
		}
		assertFalse(palavrasFiera.contains("FEIRA"));

		// CASA entra na lista, mas como duas substituições
		ArrayList<PalavraComCusto> listaCsaa = spellChecker.verificarPalavraComDistancia("csaa-", 2);
		ArrayList<String> palavrasCsaa = new ArrayList<String>();
		for (PalavraComCusto custoPalavra: listaCsaa){
			palavrasCsaa.add(custoPalavra.getPalavra());
		}
		assertTrue(palavrasCsaa.contains("CASA"));	
		ArrayList<PalavraComCusto> listaFerramunta = spellChecker.verificarPalavraComDistancia("ferramunta", 1);
		ArrayList<String> palavrasFerramunta = new ArrayList<String>();
		for (PalavraComCusto custoPalavra: listaFerramunta){
			palavrasFerramunta.add(custoPalavra.getPalavra());
		}
		assertTrue(palavrasFerramunta.contains("FERRAMENTA"));
	}

	@Test
	public void testSubstituicaoDamerauLevenshtein(){
		spellChecker = new SpellChecker(2, "QWERTY");

		// Aqui em Damerau-Levenshtein, FEIRA esta presente, pq descrimina o caso de troca
		assertTrue(spellChecker.verificarPalavra("fiera", 2).contains("FEIRA"));
	}

	private void check(ArrayList<PalavraComCusto> resultado, int posicao, String palavra, double distancia){
		assertEquals(palavra.toUpperCase(), resultado.get(posicao).getPalavra());
		assertEquals(distancia, ((double)resultado.get(posicao).getDistancia())/100, 0.001);
	}

}