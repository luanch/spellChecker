package br.unirio.pm.spellChecker.modelos;

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
	private @Getter double custoTroca;
	private static final int QUANTIDADE_LETRAS_NO_ALFABETO = 26;

	/**
	 * Construtor de algum teclado definido no XML
	 */
	public Teclado () {
		linhasDoTeclado = new ArrayList<Linha>();
		this.custoInsercaoRemocao = 0.25;
		this.custoTroca = 0.25;
	}

	/**
	 * Construtor de um teclado neutro
	 */
	public Teclado(boolean neutro){
		this.nome = "neutro";
		distancias = new double[QUANTIDADE_LETRAS_NO_ALFABETO][QUANTIDADE_LETRAS_NO_ALFABETO];
		for(int i=0; i< QUANTIDADE_LETRAS_NO_ALFABETO; i++){
			for(int j=0; j< QUANTIDADE_LETRAS_NO_ALFABETO; j++){
				if(i==j){
					distancias[i][j]= 0;
				}
				else{
					distancias[i][j] = 1;
				}
			}
		}
		this.custoInsercaoRemocao = 1;
		this.custoTroca = 1;
	}

	/**
	 * Funcao responsavel por adicionar uma linha ao teclado
	 */
	public void adicionarLinha(Linha linha){
		linhasDoTeclado.add(linha);
	}

	/** 
	 * Retorna a distancia entre duas letras
	 */
	public double getDistancia(char letraInicial, char letraFinal){
		if(distancias == null){
			prepararTeclado();
		}
		letraInicial = Character.toUpperCase(letraInicial);
		letraFinal = Character.toUpperCase(letraFinal);
		if ((letraInicial-'A' < 0) || (letraFinal-'A' < 0)) {
			System.out.println("op");
		}
		return distancias[letraInicial-'A'][letraFinal-'A']/distanciaMaximaEntreTeclas();
	}

	/**
	 * Calcula a distancia entre todas as letras do teclado e as salva numa matriz
	 */
	public void prepararTeclado(){
		distancias = new double[QUANTIDADE_LETRAS_NO_ALFABETO][QUANTIDADE_LETRAS_NO_ALFABETO];
		DistanciaEntreTeclas distanciaEntreTeclas = new DistanciaEntreTeclas(this);
		String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] letrasDoAlfabeto = alfabeto.toCharArray();
		for(int i = 0; i< QUANTIDADE_LETRAS_NO_ALFABETO; i++){
			for(int j = 0; j< QUANTIDADE_LETRAS_NO_ALFABETO; j++){
				distancias[letrasDoAlfabeto[i]-'A'][letrasDoAlfabeto[j]-'A'] = 
						(distanciaEntreTeclas.calcularDistancia(letrasDoAlfabeto[i],
								letrasDoAlfabeto[j]));
			}
		}
		// Normaliza tudo, colocando a maior distancia do teclado com valor 1
		for(int i = 0; i< letrasDoAlfabeto.length; i++){
			for(int j = 0; j< letrasDoAlfabeto.length; j++){
				distancias[i][j] = distancias[i][j];
			}
		}
	}

	/**
	 * Retorna a distancia maxima entre teclas deste teclado
	 */
	public double distanciaMaximaEntreTeclas(){
		double distanciaMaxima = Integer.MIN_VALUE;
		for(int i = 0; i< QUANTIDADE_LETRAS_NO_ALFABETO; i++){
			for(int j = 0; j< QUANTIDADE_LETRAS_NO_ALFABETO; j++){
				if(distancias[i][j] > distanciaMaxima){
					distanciaMaxima = distancias[i][j];
				}
			}
		}
		return distanciaMaxima;
	}
}