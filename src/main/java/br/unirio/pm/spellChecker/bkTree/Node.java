package br.unirio.pm.spellChecker.bkTree;

import java.util.HashMap;
import java.util.Set;

/**
 * Classe que define um nó da BKTree
 */
public class Node {

	public String palavra;
	public HashMap<Integer, Node> filhos;

	/**
	 * Node referencia, que pode possuir outros Nodes como filhos
	 */
	public Node(String palavra){
		this.palavra = palavra;
		this.filhos = new HashMap<Integer, Node>();
	}

	/**
	 * Retorna a palavra do Node
	 */
	public String getPalavra(){
		return palavra;
	}

	/**
	 * retorna o filho do Node
	 */
	public Node getNodeFilho (int key){
		return (Node)filhos.get(key); 
	}

	/**
	 * Retorna um Set de chaves do filho do node
	 */
	public Set<Integer> getChaves(){
		return filhos.keySet();
	}

	/**
	 * Verifica se já contem a chave (distância) nos nodes filhos do node
	 */
	public boolean contemChave(int key){
		return filhos.containsKey(key);
	}

	/**
	 * Adiciona um filho ao node
	 */
	public void adicionarFilho(int key, String word){
		this.filhos.put(key, new Node(word));
	}
}
