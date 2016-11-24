package br.unirio.pm.spellChecker.utilitariosTeclado;

import java.util.ArrayList;

import br.unirio.pm.spellChecker.calculadoresDeDistancia.DistanciaEntreTeclas;
import lombok.Getter;
import lombok.Setter;


public class Teclado {
	private @Getter ArrayList<Linha> designTeclado;
	private @Getter @Setter String nome;
	private double[][] distancias;

	public void adicionarLinha(Linha linha){
		designTeclado.add(linha);
	}
	
	public Teclado () {
		designTeclado = new ArrayList<Linha>();
	}
	
	public double getDistancias(char letraInicial, char letraFinal){
		letraInicial = Character.toUpperCase(letraInicial);
		letraFinal = Character.toUpperCase(letraFinal);
		
		return distancias[letraInicial-'A'][letraFinal-'A'];
	}
	
	public void prepararTeclado(){
		distancias = new double[26][26];
		
		DistanciaEntreTeclas distanciaEntreTeclas = new DistanciaEntreTeclas(this);
		
		String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] letrasDoAlfabeto = alfabeto.toCharArray();
		for(int i = 0; i< letrasDoAlfabeto.length; i++){
			for(int j = 0; j< letrasDoAlfabeto.length; j++){
				distancias[letrasDoAlfabeto[i]-'A'][letrasDoAlfabeto[j]-'A'] = distanciaEntreTeclas.calcularDistancia(letrasDoAlfabeto[i], letrasDoAlfabeto[j]);
			}
		}
	}
}
