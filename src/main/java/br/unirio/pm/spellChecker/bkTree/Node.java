package br.unirio.pm.spellChecker.bkTree;

import java.util.HashMap;
import java.util.Set;

public class Node {

    public String palavra;
    public HashMap<Integer, Node> Children;
 
	public Node(String word)
    {
        this.palavra = word.toLowerCase();
        this.Children = new HashMap<Integer, Node>();
    }
 
//    public Hashtable getChildren() {
//		return Children;
//	}

//	public void setChildren(Hashtable children) {
//		Children = children;
//	}

	public String getWord() {
		return palavra;
	}


//	public void setWord(String word) {
//		this.word = word;
//	}


    public Node getNodeFilho (int key)
    {
        return (Node)Children.get(key); 
    }
 
    public Set<Integer> Keys()
    {
        return Children.keySet();
    }
 
    public boolean ContainsKey(int key)
    {
        return Children.containsKey(key);
    }
 
    public void adicionarFilho(int key, String word)
    {
        this.Children.put(key, new Node(word));
    }
}
