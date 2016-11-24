package br.unirio.pm.spellChecker;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.unirio.pm.spellChecker.calculadoresDeDistancia.DistanciaEntreTeclas;
import br.unirio.pm.spellChecker.leitorXML.LeitorDeTeclado;
import br.unirio.pm.spellChecker.leitorXML.Teclado;

public class DistanciaTeclasTest {

	private static LeitorDeTeclado geradorDeTeclados;
	private static ArrayList<Teclado> teclados;
	private DistanciaEntreTeclas calculador;

	
	@Before 
	public void inicializarTestes(){
		
	}
	
	@BeforeClass
	public static void inicializadorGeral(){
		geradorDeTeclados = new LeitorDeTeclado();
		teclados = geradorDeTeclados.lerTeclado();

	}
	
	@Test
	public void testTeclado() {
		calculador = new DistanciaEntreTeclas(teclados.get(0));
		assertEquals(2.0, calculador.calcularDistancia('q', 'e'));
		
		assertEquals(1.25, calculador.calcularDistancia('a', 'q'));
		
		
	}
	
}
