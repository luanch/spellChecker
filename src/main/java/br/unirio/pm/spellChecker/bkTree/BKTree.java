package br.unirio.pm.spellChecker.bkTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.unirio.pm.spellChecker.CalculadorDeDistanciaDeLevenshtein;
import br.unirio.pm.spellChecker.CalculadorDeDistanciasEntreStrings;

public class BKTree {
	
    private static Node raiz;
    
    static CalculadorDeDistanciaDeLevenshtein calculador = new CalculadorDeDistanciaDeLevenshtein(); 
    
    /**
     * Insere uma nova palavra na arvore
     */
    public static void inserir(String palavra){
        palavra = palavra.toLowerCase();
        if (raiz == null)
        {
            raiz = new Node(palavra);
            return;
        }
 
        Node nodeAtual = raiz;
        
        int distancia = calculador.calcular(nodeAtual.palavra, palavra);
        while (nodeAtual.ContainsKey(distancia))
        {
            if (distancia == 0) return;
            
            // itera para o node filho
            nodeAtual = nodeAtual.getNodeFilho(distancia);
            distancia = calculador.calcular(nodeAtual.palavra, palavra);
        }
 
        nodeAtual.adicionarFilho(distancia,palavra);
    }
 
    /**
     * Busca palavras similares a uma palavra respeitando um limite de operacoes
     * @param limiteDeOperacoes: quantidade maxima de operacoes que a palavra buscada pode sofrer
     */
    public static List<String> buscar(String palavra, int limiteDeOperacoes)
    {
        List<String> resultadoDaBusca = new ArrayList<String>();
        palavra = palavra.toLowerCase();
 
        buscar(raiz, resultadoDaBusca, palavra, limiteDeOperacoes);
 
        return resultadoDaBusca;
    }
 
    private static void buscar(Node node, List<String> resultadoDaBusca, String palavra, int limiteDeOperacoes )
    {
        int distanciaAtual = calculador.calcular(node.palavra, palavra);
        
        // seguindo o algoritmo da bk-tree, busca-se apenas palavras entre limite-1 e limite+1
        int limiteMinimo = distanciaAtual - limiteDeOperacoes;
        int limiteMaximo = distanciaAtual + limiteDeOperacoes;
 
        if (distanciaAtual <= limiteDeOperacoes)
            resultadoDaBusca.add(node.palavra);

        Set<Integer> conjuntoDeChaves = node.Keys();
        
        for (Integer chave : conjuntoDeChaves) {
        	if ((chave >= limiteMinimo) && (limiteMinimo <= chave) && (chave <= limiteMaximo)) {
                buscar(node.getNodeFilho(chave), resultadoDaBusca, palavra, limiteDeOperacoes);
        	}
        }
    }
 

}
