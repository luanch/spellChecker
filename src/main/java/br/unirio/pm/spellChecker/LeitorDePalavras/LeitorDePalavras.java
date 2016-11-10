package br.unirio.pm.spellChecker.LeitorDePalavras;

import br.unirio.pm.spellChecker.bkTree.BKTree;

public class LeitorDePalavras {

	public static void gerarDicionario(BKTree arvore){
		arvore.inserir("bianca");
		arvore.inserir("oi");
		arvore.inserir("casa");
	}
}
