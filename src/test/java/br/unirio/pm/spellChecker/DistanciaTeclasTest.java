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
		
		
		assertEquals(dist(0.5, 1), teclado.getDistancias('q', 'a'), 0.001);
		assertEquals(dist(0.5, 1), teclado.getDistancias('a', 'q'), 0.001);

		assertEquals(dist(1.5, 1), teclado.getDistancias('q', 's'), 0.001);
		assertEquals(dist(1.5, 1), teclado.getDistancias('s', 'q'), 0.001);

		assertEquals(dist(8.5, 1), teclado.getDistancias('q', 'l'), 0.001);
		assertEquals(dist(8.5, 1), teclado.getDistancias('l', 'q'), 0.001);

		assertEquals(dist(1.0, 2), teclado.getDistancias('q', 'z'), 0.001);
		assertEquals(dist(1.0, 2), teclado.getDistancias('z', 'q'), 0.001);

		assertEquals(dist(2.0, 2), teclado.getDistancias('q', 'x'), 0.001);
		assertEquals(dist(2.0, 2), teclado.getDistancias('x', 'q'), 0.001);

		assertEquals(dist(7.0, 2), teclado.getDistancias('q', 'm'), 0.001);
		assertEquals(dist(7.0, 2), teclado.getDistancias('m', 'q'), 0.001);

		
		
		
		
		
		assertEquals(dist(0.5, 1), teclado.getDistancias('w', 'a'), 0.001);
		assertEquals(dist(0.5, 1), teclado.getDistancias('a', 'w'), 0.001);

		assertEquals(dist(0.5, 1), teclado.getDistancias('w', 's'), 0.001);
		assertEquals(dist(0.5, 1), teclado.getDistancias('s', 'w'), 0.001);

		assertEquals(dist(0.0, 2), teclado.getDistancias('w', 'z'), 0.001);
		assertEquals(dist(0.0, 2), teclado.getDistancias('z', 'w'), 0.001);

		assertEquals(dist(0.5, 1), teclado.getDistancias('a', 'z'), 0.001);
		assertEquals(dist(0.5, 1), teclado.getDistancias('z', 'a'), 0.001);

		assertEquals(dist(8.0, 2), teclado.getDistancias('p', 'z'), 0.001);
		assertEquals(dist(8.0, 2), teclado.getDistancias('z', 'p'), 0.001);

		assertEquals(dist(0.5, 1), teclado.getDistancias('g', 't'), 0.001);
		assertEquals(dist(1.5, 1), teclado.getDistancias('a', 'e'), 0.001);
		assertEquals(dist(1.0, 0),  teclado.getDistancias('b', 'v'), 0.001);
		
		
		assertEquals(dist(0,0), teclado.getDistancias('a', 'a'), 0.001);
		
		assertEquals(dist(1,0), teclado.getDistancias('a', 's'), 0.001);
		assertEquals(dist(1,0), teclado.getDistancias('s', 'a'), 0.001);
		
		assertEquals(dist(0,0), teclado.getDistancias('x', 'x'), 0.001);
		
		assertEquals(dist(0,4), teclado.getDistancias('c', 'm'), 0.001);
		assertEquals(dist(0,4), teclado.getDistancias('m', 'c'), 0.001);
		
		
		
//		assertEquals(0.25, palavra.getCustoDeInserirEDeletar(), 0.001);
//		assertEquals(dist(9.0, 0), layout.getDistanciaMaxima(), 0.001);
		
		
	}
	
	private double dist(double width, double height)
	{
		return Math.sqrt(width * width + height * height);
	}
	
}
