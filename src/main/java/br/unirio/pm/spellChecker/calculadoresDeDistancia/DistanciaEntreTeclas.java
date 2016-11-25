package br.unirio.pm.spellChecker.calculadoresDeDistancia;

import java.util.ArrayList;

import br.unirio.pm.spellChecker.utilitariosTeclado.Linha;
import br.unirio.pm.spellChecker.utilitariosTeclado.Teclado;

/**
 * Classe que calcula a distância entre teclas levando
 * em consideração o modelo de teclado
 */
public class DistanciaEntreTeclas {
	
	private Teclado teclado;
	
	
	public DistanciaEntreTeclas (Teclado teclado) {

		this.teclado = teclado;
		
	}
	
	/**
	 * Calcula a distância entre duas teclas
	 */
	public double calcularDistancia(char letraInicial, char letraFinal) {
		double distancia = -1;
		
		letraInicial = Character.toUpperCase(letraInicial);
		letraFinal = Character.toUpperCase(letraFinal);		
				
		if (!Character.isLetter(letraInicial) || !Character.isLetter(letraFinal)) {
			return distancia;
		}
		ArrayList<Linha> linhas = teclado.getDesignTeclado();
		
		//armazenam as posicoes (indices) no teclado de cada letra
		//[0] é à qual coluna pertence, [1] é à qual linha
		int[] posicaoNoTecladoDaLetraInicial = new int[2];
		int[] posicaoNoTecladoDaLetraFinal = new int[2];
		
		//variáveis que evitam a verificação de letras a mais
		boolean achouAPrimeiraLetra = false;
		boolean achouASegundaLetra = false;
		
		//percorre as letras do teclado para achar
		//os índices das letras a serem comparadas
		for (int linhaAtual = 0; linhaAtual < linhas.size(); linhaAtual++) {
			char[] letras = linhas.get(linhaAtual).getLetras();
			for (int colunaAtual = 0; colunaAtual < letras.length; colunaAtual++) {
				if (letras[colunaAtual] == letraInicial) {
					posicaoNoTecladoDaLetraInicial[0] = colunaAtual;
					posicaoNoTecladoDaLetraInicial[1] = linhaAtual;
					achouAPrimeiraLetra = true;
					if (achouASegundaLetra)
						break;
				}
				if (letras[colunaAtual] == letraFinal) {
					posicaoNoTecladoDaLetraFinal[0] = colunaAtual;
					posicaoNoTecladoDaLetraFinal[1] = linhaAtual;
					achouASegundaLetra = true;
					if (achouAPrimeiraLetra)
						break;
				}
			}
			if (achouAPrimeiraLetra && achouASegundaLetra)
				break;
		}
		
		//pertencem à mesma linha
		if (posicaoNoTecladoDaLetraInicial[1] == posicaoNoTecladoDaLetraFinal[1]) {
			distancia = (double) Math.abs(posicaoNoTecladoDaLetraInicial[0] - posicaoNoTecladoDaLetraFinal[0]);
			return distancia;
		}
		
		//cateto formado pela distância horizontal
		double distanciaCatetoLinha;
		
		//cateto formado pela distância vertical
		double distanciaCatetoColuna;
		double offsetAcumulado = 0;
		
		// tratando-se de linhas diferentes, define-se qual a de cima e qual a de baixo
		int linhaMaisAbaixo = Math.max(posicaoNoTecladoDaLetraInicial[1], posicaoNoTecladoDaLetraFinal[1]);
		int linhaMaisAcima = Math.min(posicaoNoTecladoDaLetraInicial[1], posicaoNoTecladoDaLetraFinal[1]);

		//calcula o offset de cada linha, de baixo para cima
		for (int i = linhaMaisAbaixo; i > linhaMaisAcima; i--) {
			offsetAcumulado += linhas.get(i).getOffset();
		}
		
		distanciaCatetoColuna = Math.abs(posicaoNoTecladoDaLetraInicial[1] - posicaoNoTecladoDaLetraFinal[1]);
		distanciaCatetoLinha = Math.abs(posicaoNoTecladoDaLetraInicial[0] - posicaoNoTecladoDaLetraFinal[0])+offsetAcumulado;
		
		// se a linha mais abaixo for da primeira letra
		if(posicaoNoTecladoDaLetraInicial[1] == linhaMaisAbaixo){
			// se a coluna da primeira letra estiver à esquerda, o offset é negativo
			if(posicaoNoTecladoDaLetraInicial[0] < posicaoNoTecladoDaLetraFinal[0]){
				distanciaCatetoLinha = Math.abs(posicaoNoTecladoDaLetraInicial[0] - posicaoNoTecladoDaLetraFinal[0])-offsetAcumulado;				
			}
		}
		else{
			if(posicaoNoTecladoDaLetraFinal[0] < posicaoNoTecladoDaLetraInicial[0]){
				distanciaCatetoLinha = Math.abs(posicaoNoTecladoDaLetraInicial[0] - posicaoNoTecladoDaLetraFinal[0])-offsetAcumulado;
			}
		}
		
		//pitágoras
		distancia = Math.sqrt((Math.pow(distanciaCatetoLinha, 2) + Math.pow(distanciaCatetoColuna, 2)));
	
		return distancia;
	}
	
	
}
