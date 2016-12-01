package br.unirio.pm.spellChecker.modelos;

import java.util.ArrayList;

import br.unirio.pm.spellChecker.leitores.LeitorDeTeclado;

/**
 * Classe com todos os tipos de teclado em uma lista
 */
public class TiposDeTeclado {
	private ArrayList<Teclado> teclados;
	
	/**
	 * Construtor
	 */
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
	
	/**
	 * Retorna a lista de todos os teclados
	 */
	public ArrayList<Teclado> getTeclados(){
		return teclados;
	}
	
	/**
	 * Retorna um teclado neutro
	 */
	public Teclado getTecladoNeutro(){
		boolean neutro = true;
		Teclado teclado = new Teclado(neutro);
		return teclado;
	}
}