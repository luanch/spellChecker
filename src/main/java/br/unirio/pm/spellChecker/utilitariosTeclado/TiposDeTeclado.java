package br.unirio.pm.spellChecker.utilitariosTeclado;

import java.util.ArrayList;

/**
 * Classe com todos os tipos de teclado em uma lista
 */
public class TiposDeTeclado {
	private ArrayList<Teclado> teclados;
	
	public TiposDeTeclado(){
		LeitorDeTeclado geradorDeTeclados = new LeitorDeTeclado();
		
		teclados = geradorDeTeclados.getTeclados();
	}
	
	/**
	 * Retorna um teclado dado um nome
	 */
	public Teclado getTecladoByName(String nome){
		for(Teclado teclado: teclados){
			if(teclado.getNome().equals(nome)){
				return teclado;
			}
		}
		return null;
	}
	
	public ArrayList<Teclado> getTeclados(){
		return teclados;
	}
}