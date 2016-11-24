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
					posicaoNoTecladoDaLetraInicial[0] = i;
					posicaoNoTecladoDaLetraInicial[1] = j;
					
				}
				if (letras[j] == letraFinal) {
					posicaoNoTecladoDaLetraFinal[0] = i;
					posicaoNoTecladoDaLetraFinal[1] = j;
				}
			}
		}
		
		if (posicaoNoTecladoDaLetraInicial[0] == posicaoNoTecladoDaLetraFinal[0]) {
			distancia = Math.abs(posicaoNoTecladoDaLetraInicial[1] - posicaoNoTecladoDaLetraInicial[1]);
			return distancia;
		}
		
		double distanciaCatetoLinha;
		double distanciaCatetoColuna;
		double offsetAcumulado = 0;
		
		int linhaMaisAbaixo = Math.max(posicaoNoTecladoDaLetraInicial[0], posicaoNoTecladoDaLetraInicial[0]);
		int linhaMaisAcima = Math.min(posicaoNoTecladoDaLetraInicial[0], posicaoNoTecladoDaLetraInicial[0]);

		for (int i = linhaMaisAbaixo; i > linhaMaisAcima; i--) {
			offsetAcumulado += linhas.get(i).getOffset();
		}
		
		distanciaCatetoLinha = Math.abs(posicaoNoTecladoDaLetraInicial[0] - posicaoNoTecladoDaLetraInicial[0]) + offsetAcumulado;
		distanciaCatetoColuna = Math.abs(posicaoNoTecladoDaLetraInicial[1] - posicaoNoTecladoDaLetraInicial[1]);
		
		distancia = Math.sqrt((Math.pow(distanciaCatetoLinha, 2) + Math.pow(distanciaCatetoColuna, 2)));
				
		return distancia;
	}
	
}
