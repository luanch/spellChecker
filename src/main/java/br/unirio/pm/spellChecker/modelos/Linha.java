package br.unirio.pm.spellChecker.modelos;

import lombok.Data;

/**
 * Modelo de linha(que contem as letras e compoe o teclado)
 */
@Data
public class Linha{
	private char[] letras;
	private double offset;
	private static final int OFFSET_DEFAULT = 0;

	/**
	 * Construtor padrão
	 */
	public Linha(String letras){
		this.letras = letras.toCharArray();
		this.offset = OFFSET_DEFAULT;
	}

	/**
	 * Construtor com Offset especifico
	 */
	public Linha(String letras, double offset){
		this.letras = letras.toCharArray();
		this.offset = offset;
	}
}