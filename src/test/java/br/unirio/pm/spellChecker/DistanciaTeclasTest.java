package br.unirio.pm.spellChecker;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.unirio.pm.spellChecker.calculadoresDeDistancia.DistanciaEntreTeclas;
import br.unirio.pm.spellChecker.utilitariosTeclado.LeitorDeTeclado;
import br.unirio.pm.spellChecker.utilitariosTeclado.Teclado;
import br.unirio.pm.spellChecker.utilitariosTeclado.TiposDeTeclado;

public class DistanciaTeclasTest {

	private static  TiposDeTeclado tiposDeTeclado;
	private DistanciaEntreTeclas calculador;

	
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
	public void testTeclado() {
		calculador = new DistanciaEntreTeclas(tiposDeTeclado.getTecladoByName("QWERTY"));

		
		Teclado teclado = tiposDeTeclado.getTecladoByName("QWERTY");
		teclado.prepararTeclado();
		
		
		assertEquals(dist(0.5, 1), teclado.getDistancia('q', 'a'), 0.001);
		assertEquals(dist(0.5, 1), teclado.getDistancia('a', 'q'), 0.001);

		assertEquals(dist(1.5, 1), teclado.getDistancia('q', 's'), 0.001);
		assertEquals(dist(1.5, 1), teclado.getDistancia('s', 'q'), 0.001);

		assertEquals(dist(8.5, 1), teclado.getDistancia('q', 'l'), 0.001);
		assertEquals(dist(8.5, 1), teclado.getDistancia('l', 'q'), 0.001);

		assertEquals(dist(1.0, 2), teclado.getDistancia('q', 'z'), 0.001);
		assertEquals(dist(1.0, 2), teclado.getDistancia('z', 'q'), 0.001);

		assertEquals(dist(2.0, 2), teclado.getDistancia('q', 'x'), 0.001);
		assertEquals(dist(2.0, 2), teclado.getDistancia('x', 'q'), 0.001);

		assertEquals(dist(7.0, 2), teclado.getDistancia('q', 'm'), 0.001);
		assertEquals(dist(7.0, 2), teclado.getDistancia('m', 'q'), 0.001);

		
		
		
		
		
		assertEquals(dist(0.5, 1), teclado.getDistancia('w', 'a'), 0.001);
		assertEquals(dist(0.5, 1), teclado.getDistancia('a', 'w'), 0.001);

		assertEquals(dist(0.5, 1), teclado.getDistancia('w', 's'), 0.001);
		assertEquals(dist(0.5, 1), teclado.getDistancia('s', 'w'), 0.001);

		assertEquals(dist(0.0, 2), teclado.getDistancia('w', 'z'), 0.001);
		assertEquals(dist(0.0, 2), teclado.getDistancia('z', 'w'), 0.001);

		assertEquals(dist(0.5, 1), teclado.getDistancia('a', 'z'), 0.001);
		assertEquals(dist(0.5, 1), teclado.getDistancia('z', 'a'), 0.001);

		assertEquals(dist(8.0, 2), teclado.getDistancia('p', 'z'), 0.001);
		assertEquals(dist(8.0, 2), teclado.getDistancia('z', 'p'), 0.001);

		assertEquals(dist(0.5, 1), teclado.getDistancia('g', 't'), 0.001);
		assertEquals(dist(1.5, 1), teclado.getDistancia('a', 'e'), 0.001);
		assertEquals(dist(1.0, 0),  teclado.getDistancia('b', 'v'), 0.001);
		
		
		assertEquals(dist(0,0), teclado.getDistancia('a', 'a'), 0.001);
		
		assertEquals(dist(1,0), teclado.getDistancia('a', 's'), 0.001);
		assertEquals(dist(1,0), teclado.getDistancia('s', 'a'), 0.001);
		
		assertEquals(dist(0,0), teclado.getDistancia('x', 'x'), 0.001);
		
		assertEquals(dist(0,4), teclado.getDistancia('c', 'm'), 0.001);
		assertEquals(dist(0,4), teclado.getDistancia('m', 'c'), 0.001);
		
		
		
//		assertEquals(0.25, palavra.getCustoDeInserirEDeletar(), 0.001);
//		assertEquals(dist(9.0, 0), layout.getDistanciaMaxima(), 0.001);
		
		
	}
	
	private double dist(double width, double height)
	{
		return Math.sqrt(width * width + height * height);
	}
	
}
