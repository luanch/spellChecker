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
	private @Getter double custoInsercaoRemocao;

	public void adicionarLinha(Linha linha){
		linhasDoTeclado.add(linha);
	}
	
	public Teclado () {
		linhasDoTeclado = new ArrayList<Linha>();
		this.custoInsercaoRemocao = 1;
	}
	
	public Teclado(boolean neutro){
		distancias = new double[26][26];
		for(int i=0; i< 26; i++){
			for(int j=0; j< 26; j++){
				distancias[i][j] = 1;
			}
		}
		this.custoInsercaoRemocao = 1;
	}
	
	public double getDistancia(char letraInicial, char letraFinal){
		if(distancias == null){
			prepararTeclado();
		}
		
		letraInicial = Character.toUpperCase(letraInicial);
		letraFinal = Character.toUpperCase(letraFinal);
		
		if ((letraInicial-'A' < 0) || (letraFinal-'A' < 0)) {
			System.out.println("op");
		}
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
