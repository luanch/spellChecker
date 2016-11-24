package br.unirio.pm.spellChecker.leitorXML;

import lombok.Data;

@Data
public class Linha{
	private char[] letras;
	private double offset;
	
	private static final int OFFSET_DEFAULT = 1;
	
	public Linha(String letras){
		new Linha(letras, OFFSET_DEFAULT);
	}
	
	public Linha(String letras, double offset){
		this.letras = letras.toCharArray();
		this.offset = offset;
	}
}