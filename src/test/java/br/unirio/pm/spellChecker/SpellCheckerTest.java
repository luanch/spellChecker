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
	public void testMarcioLevenshteinTecladoNeutro() {
		//teclado neutro usando Levenshtein
		spellChecker = new SpellChecker(1);
		
		ArrayList<PalavraComCusto> listaCasa = spellChecker.verificarPalavraComErro("casa", 1);
		ArrayList<String> palavrasCasa = new ArrayList<String>();
		   for (PalavraComCusto custoPalavra: listaCasa){
			   palavrasCasa.add(custoPalavra.getPalavra());
			 //  System.out.print(custoPalavra.getPalavra() + ":" + custoPalavra.getDistancia() + ";");
		   }
		  // System.out.println();
  
		assertTrue(palavrasCasa.get(0).equals("CASA"));
		assertTrue(palavrasCasa.get(1).equals("ASA"));
		assertTrue(palavrasCasa.get(2).equals("CAS"));
		assertTrue(palavrasCasa.get(3).equals("CACA"));
		assertTrue(palavrasCasa.get(4).equals("CADA"));
		assertTrue(palavrasCasa.get(5).equals("CAMA"));
		assertTrue(palavrasCasa.get(6).equals("CANA"));
		assertTrue(palavrasCasa.get(7).equals("CAPA"));
		assertTrue(palavrasCasa.get(8).equals("NASA"));
		assertTrue(palavrasCasa.get(9).equals("CASO"));

		

		ArrayList<PalavraComCusto> listaCervega = spellChecker.verificarPalavraComErro("cervega", 2);
		ArrayList<String> palavrasCervega = new ArrayList<String>();
		   for (PalavraComCusto custoPalavra: listaCervega){
			   palavrasCervega.add(custoPalavra.getPalavra());
			  // System.out.print(custoPalavra.getPalavra() + ":" + custoPalavra.getDistancia() + ";");
		   }
		   
			assertTrue(palavrasCervega.get(0).equals("CERVEJA"));
			assertTrue(palavrasCervega.get(1).equals("CEREJA"));
			assertTrue(palavrasCervega.get(2).equals("CORVETA"));
			assertTrue(palavrasCervega.get(3).equals("CERTEZA"));
			assertTrue(palavrasCervega.get(4).equals("CORVEIA"));
			
			
			
		ArrayList<PalavraComCusto> listaAviea = spellChecker.verificarPalavraComErro("aviea", 2);
		ArrayList<String> palavrasAviea = new ArrayList<String>();
		   for (PalavraComCusto custoPalavra: listaAviea){
			   palavrasAviea.add(custoPalavra.getPalavra());
		//	   System.out.print(custoPalavra.getPalavra() + ":" + custoPalavra.getDistancia() + ";");
		   }
		   
			assertTrue(palavrasAviea.get(0).equals("AIA"));
			assertTrue(palavrasAviea.get(1).equals("AVE"));
			assertTrue(palavrasAviea.get(2).equals("VIA"));
			assertTrue(palavrasAviea.get(3).equals("ALEA"));
			assertTrue(palavrasAviea.get(4).equals("AREA"));
			assertTrue(palavrasAviea.get(5).equals("ARIA"));
			assertTrue(palavrasAviea.get(6).equals("AZIA"));
			assertTrue(palavrasAviea.get(7).equals("VILA"));
			assertTrue(palavrasAviea.get(8).equals("VIRA"));
			assertTrue(palavrasAviea.get(9).equals("VIGA"));
	}
	
	@Test
	public void testMarcioDamerauLevenshteinTecladoNeutro() {
		//teclado neutro usando Levenshtein
		spellChecker = new SpellChecker(2);
		
		ArrayList<PalavraComCusto> listaCasa = spellChecker.verificarPalavraComErro("casa", 1);
		ArrayList<String> palavrasCasa = new ArrayList<String>();
		   for (PalavraComCusto custoPalavra: listaCasa){
			   palavrasCasa.add(custoPalavra.getPalavra());
//			   System.out.print(custoPalavra.getPalavra() + ":" + custoPalavra.getDistancia() + ";");
		   }
//		   System.out.println();
  
		assertTrue(palavrasCasa.get(0).equals("CASA"));
		assertTrue(palavrasCasa.get(1).equals("ASA"));
		assertTrue(palavrasCasa.get(2).equals("CAS"));
		assertTrue(palavrasCasa.get(3).equals("CACA"));
		assertTrue(palavrasCasa.get(4).equals("CADA"));
		assertTrue(palavrasCasa.get(5).equals("CAMA"));
		assertTrue(palavrasCasa.get(6).equals("CANA"));
		assertTrue(palavrasCasa.get(7).equals("CAPA"));
		assertTrue(palavrasCasa.get(8).equals("CARA"));
		assertTrue(palavrasCasa.get(9).equals("NASA"));

		

		ArrayList<PalavraComCusto> listaCervega = spellChecker.verificarPalavraComErro("cervega", 2);
		ArrayList<String> palavrasCervega = new ArrayList<String>();
		   for (PalavraComCusto custoPalavra: listaCervega){
			   palavrasCervega.add(custoPalavra.getPalavra());
//			   System.out.print(custoPalavra.getPalavra() + ":" + custoPalavra.getDistancia() + ";");
		   }
		   
			assertTrue(palavrasCervega.get(0).equals("CERVEJA"));
			assertTrue(palavrasCervega.get(1).equals("CEREJA"));
			assertTrue(palavrasCervega.get(2).equals("CERTEZA"));
			assertTrue(palavrasCervega.get(3).equals("CORVEIA"));
			assertTrue(palavrasCervega.get(4).equals("CORVETA"));
			
			
			
		ArrayList<PalavraComCusto> listaAviea = spellChecker.verificarPalavraComErro("aviea", 2);
		ArrayList<String> palavrasAviea = new ArrayList<String>();
		   for (PalavraComCusto custoPalavra: listaAviea){
			   palavrasAviea.add(custoPalavra.getPalavra());
//			   System.out.print(custoPalavra.getPalavra() + ":" + custoPalavra.getDistancia() + ";");
		   }
		   
			assertTrue(palavrasAviea.get(0).equals("AVEIA"));
			assertTrue(palavrasAviea.get(1).equals("VIA"));
			assertTrue(palavrasAviea.get(2).equals("ALEA"));
			assertTrue(palavrasAviea.get(3).equals("AREA"));
			assertTrue(palavrasAviea.get(4).equals("ARIA"));
			assertTrue(palavrasAviea.get(5).equals("AZIA"));
			assertTrue(palavrasAviea.get(6).equals("VEIA"));
			assertTrue(palavrasAviea.get(7).equals("VIRA"));
			assertTrue(palavrasAviea.get(8).equals("VIDA"));
			assertTrue(palavrasAviea.get(9).equals("VILA"));
	}
	
	@Test
	public void testMarcioLevenshteinQwerty(){
		
		spellChecker = new SpellChecker(1, "QWERTY");
		ArrayList<PalavraComCusto> result = spellChecker.verificarPalavraComErro("casa", 1);
		
		   for (PalavraComCusto custoPalavra: result){
			//   System.out.print(custoPalavra.getPalavra() + ":" + custoPalavra.getDistancia() + ";");
		   }
		
		check(result, 0, "casa", 0.0);
		check(result, 1, "cada", 0.11);
		check(result, 2, "cara", 0.20);
		check(result, 3, "caca", 0.20);
		check(result, 4, "vaza", 0.23);
		check(result, 5, "fada", 0.23);
		check(result, 6, "casta", 0.25);
		check(result, 7, "casca", 0.25);
		check(result, 8, "casba", 0.25);
		check(result, 9, "cas", 0.25);
		
		
		
		spellChecker = new SpellChecker(1, "QWERTY");
		ArrayList<PalavraComCusto> result2 = spellChecker.verificarPalavraComErro("cervega", 2);
		
		   for (PalavraComCusto custoPalavra: result2){
//			   System.out.print(custoPalavra.getPalavra() + ":" + custoPalavra.getDistancia() + ";");
		   }
		
		check(result2, 0, "cerveja", 0.22);
		check(result2, 1, "cereja", 0.47);
		check(result2, 2, "xereta", 0.48);
		check(result2, 3, "verbete", 0.54);
		check(result2, 4, "carreta", 0.57);
		check(result2, 5, "ferver", 0.57);
		check(result2, 6, "careta", 0.57);
		check(result2, 7, "vereda", 0.58);
		check(result2, 8, "refrega", 0.59);
		check(result2, 9, "verve", 0.61);
		
		
		
		spellChecker = new SpellChecker(1, "QWERTY");
		ArrayList<PalavraComCusto> result3 = spellChecker.verificarPalavraComErro("aviea", 2);
		
		   for (PalavraComCusto custoPalavra: result3){
//			   System.out.print(custoPalavra.getPalavra() + ":" + custoPalavra.getDistancia() + ";");
		   }
		
		check(result3, 0, "acida", 0.23);
		check(result3, 1, "afora", 0.34);
		check(result3, 2, "agora", 0.34);
		check(result3, 3, "aries", 0.35);
		check(result3, 4, "avioes", 0.36);
		check(result3, 5, "aves", 0.36);
		check(result3, 6, "avisar", 0.37);
		check(result3, 7, "avidez", 0.37);
		check(result3, 8, "alea", 0.45);
		check(result3, 9, "alvura", 0.47);
	}
	
	@Test
	public void testMarcioDemerauTecladoQwerty(){
		spellChecker = new SpellChecker(2, "QWERTY");
		ArrayList<PalavraComCusto> result = spellChecker.verificarPalavraComErro("casa", 1);
		
		check(result, 0, "casa", 0.0);
		check(result, 1, "cada", 0.11);
		check(result, 2, "caca", 0.20);
		check(result, 3, "cara", 0.20);
		check(result, 4, "vaza", 0.23);
		check(result, 5, "fada", 0.23);
		check(result, 6, "casta", 0.25);
		check(result, 7, "casca", 0.25);
		check(result, 8, "cas", 0.25);
		check(result, 9, "asa", 0.25);
		
		
		spellChecker = new SpellChecker(2, "QWERTY");
		ArrayList<PalavraComCusto> result2 = spellChecker.verificarPalavraComErro("cervega", 2);

		check(result2, 0, "cerveja", 0.22);
		check(result2, 1, "cereja", 0.47);
		check(result2, 2, "xereta", 0.48);
		check(result2, 3, "verbete", 0.54);
		check(result2, 4, "ferver", 0.57);
		check(result2, 5, "carreta", 0.57);
		check(result2, 6, "careta", 0.57);
		check(result2, 7, "vereda", 0.58);
		check(result2, 8, "refrega", 0.59);
		check(result2, 9, "verve", 0.61);
		
		
		spellChecker = new SpellChecker(2, "QWERTY");
		ArrayList<PalavraComCusto> result3 = spellChecker.verificarPalavraComErro("aviea", 2);
		
		check(result3, 0, "acida", 0.23);
		check(result3, 1, "aveia", 0.25);
		check(result3, 2, "afora", 0.34);
		check(result3, 3, "agora", 0.34);
		check(result3, 4, "aries", 0.35);
		check(result3, 5, "avioes", 0.36);
		check(result3, 6, "vies", 0.36);
		check(result3, 7, "aves", 0.36);
		check(result3, 8, "vira", 0.36);
		check(result3, 9, "vida", 0.37);
	}
	
	@Test
	public void testMarcioDvorakLevenshtein(){
		
		spellChecker = new SpellChecker(1, "DVORAK");
		ArrayList<PalavraComCusto> result = spellChecker.verificarPalavraComErro("casa", 2);
		
		check(result, 0, "casa", 0.0);
		check(result, 1, "caso", 0.11);
		check(result, 2, "cana", 0.11);
		check(result, 3, "nasa", 0.15);
		check(result, 4, "cara", 0.15);
		check(result, 5, "calo", 0.22);
		check(result, 6, "cano", 0.22);
		check(result, 7, "tala", 0.22);
		check(result, 8, "gala", 0.22);
		check(result, 9, "gana", 0.22);

		
		spellChecker = new SpellChecker(1, "DVORAK");
		ArrayList<PalavraComCusto> result2 = spellChecker.verificarPalavraComErro("cervega", 2);
		
		check(result2, 0, "corveta", 0.26);
		check(result2, 1, "corveia", 0.35);
		check(result2, 2, "corneta", 0.37);
		check(result2, 3, "corrego", 0.44);
		check(result2, 4, "cornea", 0.47);
		check(result2, 5, "colega", 0.47);
		check(result2, 6, "nervura", 0.48);
		check(result2, 7, "cerveja", 0.49);
		check(result2, 8, "centena", 0.51);
		check(result2, 9, "certeza", 0.55);
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
		ArrayList<PalavraComCusto> listaZurrarer = spellChecker.verificarPalavraComErro("kzurrar", 1);
		ArrayList<String> palavrasZurrarer = new ArrayList<String>();
		   for (PalavraComCusto custoPalavra: listaZurrarer){
			   palavrasZurrarer.add(custoPalavra.getPalavra());
			//   System.out.print(custoPalavra.getPalavra() + ":" + custoPalavra.getDistancia() + ";");
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
	
	private void check(ArrayList<PalavraComCusto> resultado, int posicao, String palavra, double distancia)
	{
		assertEquals(palavra.toUpperCase(), resultado.get(posicao).getPalavra());
		assertEquals(distancia, ((double)resultado.get(posicao).getDistancia())/100, 0.001);
	}

}