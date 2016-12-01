package br.unirio.pm.spellChecker.bkTree;

import java.util.ArrayList;
import java.util.Set;

import br.unirio.pm.spellChecker.calculadoresDeDistancia.ListaDePalavrasSugeridas;
import br.unirio.pm.spellChecker.calculadoresDeDistancia.ModeloDeCalculadorDeDistanciaEntreStrings;
import br.unirio.pm.spellChecker.modelos.PalavraComCusto;

/**
 * Classe que monta a BK tree levando em consideração qual calculador será usado
 */
public class BKTree {
	private Node raiz;
	private ListaDePalavrasSugeridas palavrasSugeridas;

	// Aceita qualquer calculador de distancias entre string
	private ModeloDeCalculadorDeDistanciaEntreStrings calculador;

	/**
	 * Construtor da BKTree, que recebe como parametro qual
	 * calculador de distancia será utilizado
	 */
	public BKTree(ModeloDeCalculadorDeDistanciaEntreStrings calculador) {
		this.palavrasSugeridas = new ListaDePalavrasSugeridas();
		this.calculador = calculador;
	}

	/**
	 * Insere uma nova palavra na arvore
	 */
	public void inserir(String palavra){
		String palavraModificada = normalizarPalavra(palavra);
		if (raiz == null){
			raiz = new Node(palavraModificada);
			return;
		} 
		Node nodeAtual = raiz;
		int distancia = calculador.calcular(nodeAtual.palavra, palavraModificada);
		if (distancia == 0) return;
		while (nodeAtual.contemChave(distancia)){     
			// itera para o node filho
			nodeAtual = nodeAtual.getNodeFilho(distancia);
			distancia = calculador.calcular(nodeAtual.palavra, palavraModificada);
			if (distancia == 0) return;
		}
		nodeAtual.adicionarFilho(distancia,palavraModificada);
	}

	/**
	 * Busca palavras similares a uma palavra respeitando um limite de operacoes
	 * @param limiteDeOperacoes: quantidade maxima de operacoes que a palavra buscada pode sofrer
	 */
	public ArrayList<PalavraComCusto> buscar(String palavra, int limiteDeOperacoes){
		palavrasSugeridas = new ListaDePalavrasSugeridas();	
		if((palavra != null) && (!palavra.equals("")) ){
			buscar(raiz, palavra, limiteDeOperacoes);
		}
		return palavrasSugeridas.getPalavrasSugeridas();
	}

	/**
	 * Método auxiliar ao anterior, busca uma palavra na árvore e retorna se achou ou não
	 */
	private void buscar(Node node, String palavra, int limiteDeOperacoes ){
		String palavraModificada = normalizarPalavra(palavra);
		int distanciaAtual = calculador.calcular(node.palavra, palavraModificada);

		// seguindo o algoritmo da bk-tree, busca-se apenas palavras entre limite-1 e limite+1
		int limiteMinimo = distanciaAtual - limiteDeOperacoes;
		int limiteMaximo = distanciaAtual + limiteDeOperacoes;

		if ((distanciaAtual <= limiteDeOperacoes)){
			palavrasSugeridas.adicionarPalavra(node.palavra, distanciaAtual);
		}
		Set<Integer> conjuntoDeChaves = node.getChaves();
		for (Integer chave : conjuntoDeChaves) {
			if ((chave >= limiteMinimo) && (chave <= limiteMaximo)) {
				buscar(node.getNodeFilho(chave), palavraModificada, limiteDeOperacoes);
			}
		}
	}

	/**
	 * Retorna true caso a palavra exista na árvore e false caso contrário
	 */
	public boolean contem (String palavraBuscada) {
		String palavraModificada = normalizarPalavra(palavraBuscada);
		return contemNode(raiz, palavraModificada);
	}

	/**
	 * busca a palavraBuscada no node passado, recursivamente ate encontrar
	 * palavra (retorna true) ou terminar de percorrer a lista (retorna false)
	 */
	public boolean contemNode (Node node, String palavraBuscada) {
		int distanciaAtual = calculador.calcular(node.palavra, palavraBuscada);
		if (distanciaAtual == 0) {
			return true;
		}
		Set<Integer> conjuntoDeChaves = node.getChaves();
		for (Integer chave : conjuntoDeChaves) {
			if (chave == distanciaAtual) {
				if(contemNode(node.getNodeFilho(chave), palavraBuscada)){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Calcula a distância entre duas palavras
	 */
	public int calcular (String primeiraPalavra, String segundaPalavra){
		return calculador.calcular(primeiraPalavra, segundaPalavra);
	}

	/**
	 * Poe a palavra nos moldes definidos
	 * Letra maiuscula, sem espaços e sem hifens
	 */
	private String normalizarPalavra(String palavra){
		String palavraModificada = palavra.toUpperCase();
		palavraModificada = palavraModificada.trim();
		palavraModificada = palavraModificada.replace("-", "");
		palavraModificada = palavraModificada.replace("'", "");
		palavraModificada = palavraModificada.replace(".", "");
		return palavraModificada;
	}
}
