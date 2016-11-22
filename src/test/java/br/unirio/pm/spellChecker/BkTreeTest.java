package br.unirio.pm.spellChecker;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.unirio.pm.spellChecker.bkTree.BKTree;


public class BkTreeTest {
	
	private static BKTree bkTree;

	
	@Before 
	public void inicializarTestes(){
	}
	
	@BeforeClass
	public static void inicializadorGeral(){
		System.out.println("Comecei");
		bkTree = new BKTree(1);
		System.out.println("Terminei");
		
	}
	
	@Test
	public void testInsercaoDePalavra(){
		assertFalse (bkTree.contem("casa"));
		bkTree.inserir("casa");
		assertTrue (bkTree.contem("casa"));
		
		assertFalse(bkTree.contem("telhado"));
		bkTree.inserir("telhado");
		
		bkTree.inserir("porta");
		assertTrue (bkTree.contem("porta"));
		assertTrue (bkTree.contem("telhado"));

	}	
}
