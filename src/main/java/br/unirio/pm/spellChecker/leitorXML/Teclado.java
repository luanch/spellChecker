package br.unirio.pm.spellChecker.leitorXML;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;


public class Teclado {
	private @Getter ArrayList<Linha> designTeclado;
	private @Getter @Setter String nome;

	public void adicionarLinha(Linha linha){
		designTeclado.add(linha);
	}
	
	public Teclado () {
		designTeclado = new ArrayList<Linha>();
	}

}
