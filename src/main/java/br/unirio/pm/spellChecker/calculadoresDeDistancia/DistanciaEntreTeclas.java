package br.unirio.pm.spellChecker.calculadoresDeDistancia;

import java.util.ArrayList;

import br.unirio.pm.spellChecker.leitorXML.Linha;
import br.unirio.pm.spellChecker.leitorXML.Teclado;

public class DistanciaEntreTeclas {
	
	private Teclado teclado;
	
	public DistanciaEntreTeclas (Teclado teclado) {
		
		this.teclado = teclado;
		
	}
	
	public double calcularDistancia(char letraInicial, char letraFinal) {
		double distancia = -1;
		
		letraInicial = Character.toUpperCase(letraInicial);
		letraFinal = Character.toUpperCase(letraFinal);		
				
		if (!Character.isLetter(letraInicial) || !Character.isLetter(letraFinal)) {
			return distancia;
		}
		ArrayList<Linha> linhas = teclado.getDesignTeclado();
		
		int[] posicaoNoTecladoDaLetraInicial = new int[2];
		int[] posicaoNoTecladoDaLetraFinal = new int[2];
		
		for (int i = 0; i < linhas.size(); i++) {
			char[] letras = linhas.get(i).getLetras();
			for (int j = 0; j < letras.length; j++) {
				if (letras[j] == letraInicial) {
					posicaoNoTecladoDaLetraInicial[1] = i;
					posicaoNoTecladoDaLetraInicial[0] = j;
					
				}
				if (letras[j] == letraFinal) {
					posicaoNoTecladoDaLetraFinal[1] = i;
					posicaoNoTecladoDaLetraFinal[0] = j;
				}
			}
		}
		
		if (posicaoNoTecladoDaLetraInicial[1] == posicaoNoTecladoDaLetraFinal[1]) {
			distancia = (double) Math.abs(posicaoNoTecladoDaLetraInicial[0] - posicaoNoTecladoDaLetraFinal[0]);
			return distancia;
		}
		
		double distanciaCatetoLinha;
		double distanciaCatetoColuna;
		double offsetAcumulado = 0;
		
		int linhaMaisAbaixo = Math.max(posicaoNoTecladoDaLetraInicial[1], posicaoNoTecladoDaLetraFinal[1]);
		int linhaMaisAcima = Math.min(posicaoNoTecladoDaLetraInicial[1], posicaoNoTecladoDaLetraFinal[1]);

		for (int i = linhaMaisAbaixo; i > linhaMaisAcima; i--) {
			offsetAcumulado += linhas.get(i).getOffset();
		}
		
		distanciaCatetoColuna = Math.abs(posicaoNoTecladoDaLetraInicial[1] - posicaoNoTecladoDaLetraFinal[1]);
		distanciaCatetoLinha = Math.abs(posicaoNoTecladoDaLetraInicial[0] - posicaoNoTecladoDaLetraFinal[0])+ offsetAcumulado;
		
		distancia = Math.sqrt((Math.pow(distanciaCatetoLinha, 2) + Math.pow(distanciaCatetoColuna, 2)));
				
		return distancia;
	}
	
}
