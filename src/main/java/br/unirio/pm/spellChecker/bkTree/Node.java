package br.unirio.pm.spellChecker.bkTree;

import java.util.HashMap;
import java.util.Set;

/**
 * Classe que define um nó da BKTree
 */
public class Node {

    public String palavra;
    public HashMap<Integer, Node> filhos;
 
	public Node(String palavra)
    {
        this.palavra = palavra;
        this.filhos = new HashMap<Integer, Node>();
    }
 
	public String getPalavra() {
		return palavra;
	}

    public Node getNodeFilho (int key)
    {
        return (Node)filhos.get(key); 
    }
 
    public Set<Integer> getChaves()
    {
        return filhos.keySet();
    }
 
    /**
     * Verifica se já contem a chave (distância) nos filhos do nó
     */
    public boolean contemChave(int key)
    {
        return filhos.containsKey(key);
    }
 
    /**
     * Adiciona um filho ao nó
     */
    public void adicionarFilho(int key, String word)
    {
        this.filhos.put(key, new Node(word));
    }
}
