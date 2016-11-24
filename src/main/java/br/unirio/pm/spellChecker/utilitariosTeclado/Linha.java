package br.unirio.pm.spellChecker.utilitariosTeclado;

import lombok.Data;

@Data
public class Linha{
	private char[] letras;
	private double offset;
	
	private static final int OFFSET_DEFAULT = 0;
	
	public Linha(String letras){
		this.letras = letras.toCharArray();
		this.offset = OFFSET_DEFAULT;
	}
	
	public Linha(String letras, double offset){
		this.letras = letras.toCharArray();
		this.offset = offset;
	}
}