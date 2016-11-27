package br.unirio.pm.spellChecker.bkTree;

import java.util.ArrayList;
import java.util.Set;

import br.unirio.pm.spellChecker.calculadoresDeDistancia.CustoPalavra;
import br.unirio.pm.spellChecker.calculadoresDeDistancia.InsertionSort;
import br.unirio.pm.spellChecker.calculadoresDeDistancia.MoldeDeCalculadorDeDistanciaEntreStrings;

/**
 * Classe que monta a BK tree levando em consideração qual calculador será usado
 */
public class BKTree {
    private Node raiz;
	private InsertionSort palavrasSugeridas;
    private final int CODIGO_LEVENSHTEIN = 1;
	private final int CODIGO_DAMERAU_LEVENSHTEIN = 2;

    
    // Aceita qualquer calculador de distancias entre string
    private MoldeDeCalculadorDeDistanciaEntreStrings calculador;
    
    /**
     * Construtor da BKTree, que recebe como parametro qual
     * calculador de distancia será utilizado
     */
    public BKTree(MoldeDeCalculadorDeDistanciaEntreStrings calculador) {
    	this.palavrasSugeridas = new InsertionSort();
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
        
        while (nodeAtual.contemChave(distancia))
        {
            
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
    public ArrayList<CustoPalavra> buscar(String palavra, int limiteDeOperacoes){
        	
        if((palavra != null) && (!palavra.equals("")) ){

        	if(buscar(raiz, palavra, limiteDeOperacoes)){
        		return null;
        	}
        }
        return palavrasSugeridas.getPalavrasSugeridas();
    }
 
    /**
     * Método auxiliar ao anterior, busca uma palavra na árvore e retorna se achou ou não
     */
    private boolean buscar(Node node, String palavra, int limiteDeOperacoes ){
        
    	String palavraModificada = normalizarPalavra(palavra);
    	int distanciaAtual = calculador.calcular(node.palavra, palavraModificada);
        
        // seguindo o algoritmo da bk-tree, busca-se apenas palavras entre limite-1 e limite+1
        int limiteMinimo = distanciaAtual - limiteDeOperacoes;
        int limiteMaximo = distanciaAtual + limiteDeOperacoes;
 
        if ((distanciaAtual <= limiteDeOperacoes) && (distanciaAtual != 0)){
        	ArrayList<CustoPalavra> lista = palavrasSugeridas.getPalavrasSugeridas();
			
        	palavrasSugeridas.adicionarPalavra(node.palavra, distanciaAtual);
        }
        else if (distanciaAtual == 0) {
        	return true;
        }
        
        Set<Integer> conjuntoDeChaves = node.getChaves();
        
        for (Integer chave : conjuntoDeChaves) {
        	if ((chave >= limiteMinimo) && (chave <= limiteMaximo)) {
                if( buscar(node.getNodeFilho(chave), palavraModificada, limiteDeOperacoes))
                	return true;
        	}
        }
        return false;
    }
    
    /**
     * Retorna true caso a palavra exista na árvore e false caso contrário
     */
    public boolean contem(String palavra) {
    	if (raiz == null) 
    		return false;
        ArrayList<String> resultadoDaBusca = new ArrayList<String>();
    	return buscar(raiz, palavra, 0);
    }
    
    /**
     * Calcula a distância entre duas palavras
     */
	public int calcular (String primeiraPalavra, String segundaPalavra){
		return calculador.calcular(primeiraPalavra, segundaPalavra);
	}
    
	public int getCodigoLevenshtein(){
		return CODIGO_LEVENSHTEIN;
	}
	
	public int getCodigoDamerauLevenshtein(){
		return CODIGO_DAMERAU_LEVENSHTEIN;
	}

	
	/**
	 * Põe a palavra nos moldes definidos
	 * Letra maiúscula, sem espaços e sem hifens
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
