package br.unirio.pm.spellChecker;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.unirio.pm.spellChecker.calculadoresDeDistancia.DistanciaEntreTeclas;
import br.unirio.pm.spellChecker.utilitariosTeclado.Teclado;
import br.unirio.pm.spellChecker.utilitariosTeclado.TiposDeTeclado;

public class DistanciaTeclasTest {

	private static  TiposDeTeclado tiposDeTeclado;
	private DistanciaEntreTeclas calculador;
	Teclado teclado;

	
	@Before 
	public void inicializarTestes(){
		
	}
	
	@BeforeClass
	public static void inicializadorGeral(){
		tiposDeTeclado = new TiposDeTeclado();
		for(Teclado teclado: tiposDeTeclado.getTeclados()){
			teclado.prepararTeclado();
		}
	}
	
	@Test
	public void testTecladoQwerty() {
		calculador = new DistanciaEntreTeclas(tiposDeTeclado.getTecladoByName("QWERTY"));

		
		Teclado teclado = tiposDeTeclado.getTecladoByName("QWERTY");
		teclado.prepararTeclado();
		final double distancia_maxima = teclado.distanciaMaximaEntreTeclas();
		
		assertEquals(dist(0.5, 1)/distancia_maxima, teclado.getDistancia('q', 'a'), 0.001);
		assertEquals(dist(0.5, 1)/distancia_maxima, teclado.getDistancia('a', 'q'), 0.001);

		assertEquals(dist(1.5, 1)/distancia_maxima, teclado.getDistancia('q', 's'), 0.001);
		assertEquals(dist(1.5, 1)/distancia_maxima, teclado.getDistancia('s', 'q'), 0.001);

		assertEquals(dist(8.5, 1)/distancia_maxima, teclado.getDistancia('q', 'l'), 0.001);
		assertEquals(dist(8.5, 1)/distancia_maxima, teclado.getDistancia('l', 'q'), 0.001);

		assertEquals(dist(1.0, 2)/distancia_maxima, teclado.getDistancia('q', 'z'), 0.001);
		assertEquals(dist(1.0, 2)/distancia_maxima, teclado.getDistancia('z', 'q'), 0.001);

		assertEquals(dist(2.0, 2)/distancia_maxima, teclado.getDistancia('q', 'x'), 0.001);
		assertEquals(dist(2.0, 2)/distancia_maxima, teclado.getDistancia('x', 'q'), 0.001);

		assertEquals(dist(7.0, 2)/distancia_maxima, teclado.getDistancia('q', 'm'), 0.001);
		assertEquals(dist(7.0, 2)/distancia_maxima, teclado.getDistancia('m', 'q'), 0.001);

		
		
		
		
		
		assertEquals(dist(0.5, 1)/distancia_maxima, teclado.getDistancia('w', 'a'), 0.001);
		assertEquals(dist(0.5, 1)/distancia_maxima, teclado.getDistancia('a', 'w'), 0.001);

		assertEquals(dist(0.5, 1)/distancia_maxima, teclado.getDistancia('w', 's'), 0.001);
		assertEquals(dist(0.5, 1)/distancia_maxima, teclado.getDistancia('s', 'w'), 0.001);

		assertEquals(dist(0.0, 2)/distancia_maxima, teclado.getDistancia('w', 'z'), 0.001);
		assertEquals(dist(0.0, 2)/distancia_maxima, teclado.getDistancia('z', 'w'), 0.001);

		assertEquals(dist(0.5, 1)/distancia_maxima, teclado.getDistancia('a', 'z'), 0.001);
		assertEquals(dist(0.5, 1)/distancia_maxima, teclado.getDistancia('z', 'a'), 0.001);

		assertEquals(dist(8.0, 2)/distancia_maxima, teclado.getDistancia('p', 'z'), 0.001);
		assertEquals(dist(8.0, 2)/distancia_maxima, teclado.getDistancia('z', 'p'), 0.001);

		assertEquals(dist(0.5, 1)/distancia_maxima, teclado.getDistancia('g', 't'), 0.001);
		assertEquals(dist(1.5, 1)/distancia_maxima, teclado.getDistancia('a', 'e'), 0.001);
		assertEquals(dist(1.0, 0)/distancia_maxima,  teclado.getDistancia('b', 'v'), 0.001);
		
		
		assertEquals(dist(0,0)/distancia_maxima, teclado.getDistancia('a', 'a'), 0.001);
		
		assertEquals(dist(1,0)/distancia_maxima, teclado.getDistancia('a', 's'), 0.001);
		assertEquals(dist(1,0)/distancia_maxima, teclado.getDistancia('s', 'a'), 0.001);
		
		assertEquals(dist(0,0)/distancia_maxima, teclado.getDistancia('x', 'x'), 0.001);
		
		assertEquals(dist(0,4)/distancia_maxima, teclado.getDistancia('c', 'm'), 0.001);
		assertEquals(dist(0,4)/distancia_maxima, teclado.getDistancia('m', 'c'), 0.001);
		
		
		
		assertEquals(0.25, teclado.getCustoInsercaoRemocao(), 0.001);
		assertEquals(dist(9.0, 0), teclado.distanciaMaximaEntreTeclas(), 0.001);
		
		
	}
	
	@Test
	public void testTecladoQwertz() {
		calculador = new DistanciaEntreTeclas(tiposDeTeclado.getTecladoByName("QWERTZ"));

		
		Teclado teclado = tiposDeTeclado.getTecladoByName("QWERTZ");
		teclado.prepararTeclado();
		final double distancia_maxima = teclado.distanciaMaximaEntreTeclas();
		
		assertEquals(dist(0.5, 1)/distancia_maxima, teclado.getDistancia('q', 'a'), 0.001);
		assertEquals(dist(0.5, 1)/distancia_maxima, teclado.getDistancia('a', 'q'), 0.001);

		assertEquals(dist(1.5, 1)/distancia_maxima, teclado.getDistancia('q', 's'), 0.001);
		assertEquals(dist(1.5, 1)/distancia_maxima, teclado.getDistancia('s', 'q'), 0.001);

		assertEquals(dist(8.5, 1)/distancia_maxima, teclado.getDistancia('q', 'l'), 0.001);
		assertEquals(dist(8.5, 1)/distancia_maxima, teclado.getDistancia('l', 'q'), 0.001);

		assertEquals(dist(5.0, 0)/distancia_maxima, teclado.getDistancia('q', 'z'), 0.001);
		assertEquals(dist(5.0, 0)/distancia_maxima, teclado.getDistancia('z', 'q'), 0.001);

		assertEquals(dist(2.0, 2)/distancia_maxima, teclado.getDistancia('q', 'x'), 0.001);
		assertEquals(dist(2.0, 2)/distancia_maxima, teclado.getDistancia('x', 'q'), 0.001);

		assertEquals(dist(7.0, 2)/distancia_maxima, teclado.getDistancia('q', 'm'), 0.001);
		assertEquals(dist(7.0, 2)/distancia_maxima, teclado.getDistancia('m', 'q'), 0.001);		
		
		assertEquals(dist(0.5, 1)/distancia_maxima, teclado.getDistancia('w', 'a'), 0.001);
		assertEquals(dist(0.5, 1)/distancia_maxima, teclado.getDistancia('a', 'w'), 0.001);

		assertEquals(dist(0.5, 1)/distancia_maxima, teclado.getDistancia('w', 's'), 0.001);
		assertEquals(dist(0.5, 1)/distancia_maxima, teclado.getDistancia('s', 'w'), 0.001);

		assertEquals(dist(4.0, 0)/distancia_maxima, teclado.getDistancia('w', 'z'), 0.001);
		assertEquals(dist(4.0, 0)/distancia_maxima, teclado.getDistancia('z', 'w'), 0.001);

		assertEquals(dist(4.5, 1)/distancia_maxima, teclado.getDistancia('a', 'z'), 0.001);
		assertEquals(dist(4.5, 1)/distancia_maxima, teclado.getDistancia('z', 'a'), 0.001);

		assertEquals(dist(4.0, 0)/distancia_maxima, teclado.getDistancia('p', 'z'), 0.001);
		assertEquals(dist(4.0, 0)/distancia_maxima, teclado.getDistancia('z', 'p'), 0.001);

		assertEquals(dist(0.5, 1)/distancia_maxima, teclado.getDistancia('g', 't'), 0.001);
		assertEquals(dist(1.5, 1)/distancia_maxima, teclado.getDistancia('a', 'e'), 0.001);
		assertEquals(dist(1.0, 0)/distancia_maxima,  teclado.getDistancia('b', 'v'), 0.001);
		
		
		assertEquals(dist(0,0)/distancia_maxima, teclado.getDistancia('a', 'a'), 0.001);
		
		assertEquals(dist(1,0)/distancia_maxima, teclado.getDistancia('a', 's'), 0.001);
		assertEquals(dist(1,0)/distancia_maxima, teclado.getDistancia('s', 'a'), 0.001);
		
		assertEquals(dist(0,0)/distancia_maxima, teclado.getDistancia('x', 'x'), 0.001);
		
		assertEquals(dist(0,4)/distancia_maxima, teclado.getDistancia('c', 'm'), 0.001);
		assertEquals(dist(0,4)/distancia_maxima, teclado.getDistancia('m', 'c'), 0.001);
		
		assertEquals(0.25, teclado.getCustoInsercaoRemocao(), 0.001);
		assertEquals(dist(9.0, 0), teclado.distanciaMaximaEntreTeclas(), 0.001);
	}
	
	@Test
	public void testTecladoAzerty() {
		calculador = new DistanciaEntreTeclas(tiposDeTeclado.getTecladoByName("AZERTY"));

		
		Teclado teclado = tiposDeTeclado.getTecladoByName("AZERTY");
		teclado.prepararTeclado();
		final double distancia_maxima = teclado.distanciaMaximaEntreTeclas();
		
		assertEquals(dist(0, 1)/distancia_maxima, teclado.getDistancia('q', 'a'), 0.001);
		assertEquals(dist(0, 1)/distancia_maxima, teclado.getDistancia('a', 'q'), 0.001);

		assertEquals(dist(1, 0)/distancia_maxima, teclado.getDistancia('q', 's'), 0.001);
		assertEquals(dist(1, 0)/distancia_maxima, teclado.getDistancia('s', 'q'), 0.001);

		assertEquals(dist(8, 0)/distancia_maxima, teclado.getDistancia('q', 'l'), 0.001);
		assertEquals(dist(8, 0)/distancia_maxima, teclado.getDistancia('l', 'q'), 0.001);

		assertEquals(dist(1, 1)/distancia_maxima, teclado.getDistancia('q', 'z'), 0.001);
		assertEquals(dist(1, 1)/distancia_maxima, teclado.getDistancia('z', 'q'), 0.001);

		assertEquals(dist(1, 1)/distancia_maxima, teclado.getDistancia('q', 'x'), 0.001);
		assertEquals(dist(1, 1)/distancia_maxima, teclado.getDistancia('x', 'q'), 0.001);

		assertEquals(dist(9, 0)/distancia_maxima, teclado.getDistancia('q', 'm'), 0.001);
		assertEquals(dist(9, 0)/distancia_maxima, teclado.getDistancia('m', 'q'), 0.001);		
		
		assertEquals(dist(2, 0)/distancia_maxima, teclado.getDistancia('w', 'a'), 0.001);
		assertEquals(dist(2, 0)/distancia_maxima, teclado.getDistancia('a', 'w'), 0.001);

		assertEquals(dist(1, 1)/distancia_maxima, teclado.getDistancia('w', 's'), 0.001);
		assertEquals(dist(1, 1)/distancia_maxima, teclado.getDistancia('s', 'w'), 0.001);

		assertEquals(dist(2, 1)/distancia_maxima, teclado.getDistancia('w', 'z'), 0.001);
		assertEquals(dist(2, 1)/distancia_maxima, teclado.getDistancia('z', 'w'), 0.001);

		assertEquals(dist(0, 1)/distancia_maxima, teclado.getDistancia('a', 'z'), 0.001);
		assertEquals(dist(0, 1)/distancia_maxima, teclado.getDistancia('z', 'a'), 0.001);

		assertEquals(dist(8, 0)/distancia_maxima, teclado.getDistancia('p', 'z'), 0.001);
		assertEquals(dist(8, 0)/distancia_maxima, teclado.getDistancia('z', 'p'), 0.001);

		assertEquals(dist(0, 1)/distancia_maxima, teclado.getDistancia('g', 't'), 0.001);
		assertEquals(dist(2, 0)/distancia_maxima, teclado.getDistancia('a', 'e'), 0.001);
		assertEquals(dist(0, 1)/distancia_maxima,  teclado.getDistancia('b', 'v'), 0.001);
		
		
		assertEquals(dist(0,0)/distancia_maxima, teclado.getDistancia('a', 'a'), 0.001);
		
		assertEquals(dist(1,1)/distancia_maxima, teclado.getDistancia('a', 's'), 0.001);
		assertEquals(dist(1,1)/distancia_maxima, teclado.getDistancia('s', 'a'), 0.001);
		
		assertEquals(dist(0,0)/distancia_maxima, teclado.getDistancia('x', 'x'), 0.001);
		
		assertEquals(dist(1,7)/distancia_maxima, teclado.getDistancia('c', 'm'), 0.001);
		assertEquals(dist(1,7)/distancia_maxima, teclado.getDistancia('m', 'c'), 0.001);
		assertEquals(dist(1,9)/distancia_maxima, teclado.getDistancia('m', 'a'), 0.001);

		assertEquals(0.25, teclado.getCustoInsercaoRemocao(), 0.001);
		assertEquals(dist(9, 2), teclado.distanciaMaximaEntreTeclas(), 0.001);
	}
	
	@Test 
	public void testDvorak() {
		calculador = new DistanciaEntreTeclas(tiposDeTeclado.getTecladoByName("DVORAK"));
		Teclado teclado = tiposDeTeclado.getTecladoByName("DVORAK");
		teclado.prepararTeclado();
		
		final double distancia_maxima = teclado.distanciaMaximaEntreTeclas();
		
		assertEquals(dist(-3, 1)/distancia_maxima, teclado.getDistancia('a', 'p'), 0.001);
		
		assertEquals(dist(1, 1)/distancia_maxima, teclado.getDistancia('q', 'a'), 0.001);
		assertEquals(dist(1, 1)/distancia_maxima, teclado.getDistancia('a', 'q'), 0.001);

		assertEquals(dist(8, 1)/distancia_maxima, teclado.getDistancia('q', 's'), 0.001);
		assertEquals(dist(8, 1)/distancia_maxima, teclado.getDistancia('s', 'q'), 0.001);

		assertEquals(dist(2, 8)/distancia_maxima, teclado.getDistancia('q', 'l'), 0.001);
		assertEquals(dist(2, 8)/distancia_maxima, teclado.getDistancia('l', 'q'), 0.001);

		assertEquals(dist(8, 0)/distancia_maxima, teclado.getDistancia('q', 'z'), 0.001);
		assertEquals(dist(8, 0)/distancia_maxima, teclado.getDistancia('z', 'q'), 0.001);

		assertEquals(dist(3, 0)/distancia_maxima, teclado.getDistancia('q', 'x'), 0.001);
		assertEquals(dist(3, 0)/distancia_maxima, teclado.getDistancia('x', 'q'), 0.001);

		assertEquals(dist(5, 0)/distancia_maxima, teclado.getDistancia('q', 'm'), 0.001);
		assertEquals(dist(5, 0)/distancia_maxima, teclado.getDistancia('m', 'q'), 0.001);		
		
		assertEquals(dist(7, 1)/distancia_maxima, teclado.getDistancia('w', 'a'), 0.001);
		assertEquals(dist(7, 1)/distancia_maxima, teclado.getDistancia('a', 'w'), 0.001);

		assertEquals(dist(2, 1)/distancia_maxima, teclado.getDistancia('w', 's'), 0.001);
		assertEquals(dist(2, 1)/distancia_maxima, teclado.getDistancia('s', 'w'), 0.001);

		assertEquals(dist(2, 0)/distancia_maxima, teclado.getDistancia('w', 'z'), 0.001);
		assertEquals(dist(2, 0)/distancia_maxima, teclado.getDistancia('z', 'w'), 0.001);

		assertEquals(dist(9, 1)/distancia_maxima, teclado.getDistancia('a', 'z'), 0.001);
		assertEquals(dist(9, 1)/distancia_maxima, teclado.getDistancia('z', 'a'), 0.001);

		assertEquals(dist(6, 2)/distancia_maxima, teclado.getDistancia('p', 'z'), 0.001);
		assertEquals(dist(6, 2)/distancia_maxima, teclado.getDistancia('z', 'p'), 0.001);

		assertEquals(dist(1, 1)/distancia_maxima, teclado.getDistancia('g', 't'), 0.001);
		assertEquals(dist(2, 0)/distancia_maxima, teclado.getDistancia('a', 'e'), 0.001);
		assertEquals(dist(3, 0)/distancia_maxima,  teclado.getDistancia('b', 'v'), 0.001);
		
		
		assertEquals(dist(0,0)/distancia_maxima, teclado.getDistancia('a', 'a'), 0.001);
		
		assertEquals(dist(9,0)/distancia_maxima, teclado.getDistancia('a', 's'), 0.001);
		assertEquals(dist(9,0)/distancia_maxima, teclado.getDistancia('s', 'a'), 0.001);
		
		assertEquals(dist(0,0)/distancia_maxima, teclado.getDistancia('x', 'x'), 0.001);
		
		assertEquals(dist(2,1)/distancia_maxima, teclado.getDistancia('c', 'm'), 0.001);
		assertEquals(dist(2,1)/distancia_maxima, teclado.getDistancia('m', 'c'), 0.001);
		assertEquals(dist(1,6)/distancia_maxima, teclado.getDistancia('m', 'a'), 0.001);
	}
	
	private double dist(double width, double height)
	{
		
		double r = Math.sqrt(width * width + height * height);
		return r;
	}
}
