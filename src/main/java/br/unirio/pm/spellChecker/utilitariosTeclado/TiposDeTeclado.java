package br.unirio.pm.spellChecker.utilitariosTeclado;

import java.util.ArrayList;

public class TiposDeTeclado {
	private ArrayList<Teclado> teclados;
	
	public TiposDeTeclado(){
		LeitorDeTeclado geradorDeTeclados = new LeitorDeTeclado();
		
		teclados = geradorDeTeclados.getTeclados();
	}
	
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