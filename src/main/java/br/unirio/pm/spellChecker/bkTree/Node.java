package br.unirio.pm.spellChecker.bkTree;

import java.util.HashMap;
import java.util.Set;

public class Node {

    public String palavra;
    public HashMap<Integer, Node> filhos;
 
	public Node(String palavra)
    {
        this.palavra = palavra;
        this.filhos = new HashMap<Integer, Node>();
    }
 
//    public Hashtable getChildren() {
//		return Children;
//	}

//	public void setChildren(Hashtable children) {
//		Children = children;
//	}

	public String getPalavra() {
		return palavra;
	}


//	public void setWord(String word) {
//		this.word = word;
//	}


    public Node getNodeFilho (int key)
    {
        return (Node)filhos.get(key); 
    }
 
    public Set<Integer> getChaves()
    {
        return filhos.keySet();
    }
 
    public boolean contemChave(int key)
    {
        return filhos.containsKey(key);
    }
 
    public void adicionarFilho(int key, String word)
    {
        this.filhos.put(key, new Node(word));
    }
}
