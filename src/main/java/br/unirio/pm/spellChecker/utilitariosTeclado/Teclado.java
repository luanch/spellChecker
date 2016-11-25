package br.unirio.pm.spellChecker.utilitariosTeclado;

import java.util.ArrayList;

import br.unirio.pm.spellChecker.calculadoresDeDistancia.DistanciaEntreTeclas;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe que representa um teclado
 * Um teclado possui n linhas, um nome e uma matriz padrão de distâncias entre todas as letras
 */
public class Teclado {
	private @Getter ArrayList<Linha> linhasDoTeclado;
	private @Getter @Setter String nome;
	private double[][] distancias;

	public void adicionarLinha(Linha linha){
		linhasDoTeclado.add(linha);
	}
	
	public Teclado () {
		linhasDoTeclado = new ArrayList<Linha>();
	}
	
	public double getDistancia(char letraInicial, char letraFinal){
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
