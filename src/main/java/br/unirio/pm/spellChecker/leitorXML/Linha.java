package br.unirio.pm.spellChecker.leitorXML;

import lombok.Data;

@Data
public class Linha{
	private String letras;
	private double offset;
	
	public Linha(String letras){
		new Linha(letras, 1);
	}
	
	public Linha(String letras, double offset){
		this.letras = letras;
		this.offset = offset;
	}
}
