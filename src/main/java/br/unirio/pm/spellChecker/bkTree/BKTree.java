package br.unirio.pm.spellChecker.bkTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.unirio.pm.spellChecker.DistanciaDeLevenshtein;
import br.unirio.pm.spellChecker.MoldeDeCalculadorDeDistanciaEntreStrings;
import br.unirio.pm.spellChecker.LeitorDePalavras.LeitorDePalavras;

public class BKTree {
	
	private final int CODIGO_LEVENSHTEIN = 1;
    private Node raiz;
    private MoldeDeCalculadorDeDistanciaEntreStrings calculador;
    
    public BKTree(int codigoCalculador) {
    	
    	LeitorDePalavras leitorDePalavras = new LeitorDePalavras();
    	
		switch(codigoCalculador){
		case 1:
			calculador = new DistanciaDeLevenshtein();
		}
		leitorDePalavras.gerarDicionario(this);
	}
    
    /**
     * Insere uma nova palavra na arvore
     */
    public void inserir(String palavra){
        palavra = palavra.toLowerCase();
        if (raiz == null)
        {
            raiz = new Node(palavra);
            return;
        }
 
        Node nodeAtual = raiz;


        int distancia = calculador.calcular(nodeAtual.palavra, palavra);
        if (distancia == 0) return;
        
        while (nodeAtual.contemChave(distancia))
        {
            
            // itera para o node filho
            nodeAtual = nodeAtual.getNodeFilho(distancia);
           
            distancia = calculador.calcular(nodeAtual.palavra, palavra);
            if (distancia == 0) return;
        }
 
        nodeAtual.adicionarFilho(distancia,palavra);
    }
 
    /**
     * Busca palavras similares a uma palavra respeitando um limite de operacoes
     * @param limiteDeOperacoes: quantidade maxima de operacoes que a palavra buscada pode sofrer
     */
    public List<String> buscar(String palavra, int limiteDeOperacoes){
        List<String> resultadoDaBusca = new ArrayList<String>();

        if((palavra != null) && (palavra != "")){
            palavra = palavra.toLowerCase();
        	buscar(raiz, resultadoDaBusca, palavra, limiteDeOperacoes);
        }
        return resultadoDaBusca;
    }
 
    private void buscar(Node node, List<String> resultadoDaBusca, String palavra, int limiteDeOperacoes ){
        int distanciaAtual = calculador.calcular(node.palavra, palavra);
        
        // seguindo o algoritmo da bk-tree, busca-se apenas palavras entre limite-1 e limite+1
        int limiteMinimo = distanciaAtual - limiteDeOperacoes;
        int limiteMaximo = distanciaAtual + limiteDeOperacoes;
 
        if (distanciaAtual <= limiteDeOperacoes)
            resultadoDaBusca.add(node.palavra);

        Set<Integer> conjuntoDeChaves = node.getChaves();
        
        for (Integer chave : conjuntoDeChaves) {
        	if ((chave >= limiteMinimo) && (limiteMinimo <= chave) && (chave <= limiteMaximo)) {
                buscar(node.getNodeFilho(chave), resultadoDaBusca, palavra, limiteDeOperacoes);
        	}
        }
    }
    
	public int calcular (String primeiraPalavra, String segundaPalavra){
		return calculador.calcular(primeiraPalavra, segundaPalavra);
	}
    
	public int getCodigoLevenshtein(){
		return CODIGO_LEVENSHTEIN;
	}
}
