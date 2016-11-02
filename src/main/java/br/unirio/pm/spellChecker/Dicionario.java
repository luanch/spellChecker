package br.unirio.pm.spellChecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dicionario {
	private int NUMERO_PRIMO_GRANDE = 1319; //prime number
    final private Arvore[] arvore;
    
    // ??????? árvore que cresce para o lado?
    public Dicionario() {
        this.NUMERO_PRIMO_GRANDE = NUMERO_PRIMO_GRANDE;

        arvore = new Arvore[NUMERO_PRIMO_GRANDE];
        for (int i = 0; i < NUMERO_PRIMO_GRANDE; i++) {
            arvore[i] = new Arvore();
        }
    }

    /** 
     * retorna o código hash da palavra
     */
    private int gerarCodigoHash(String palavra) {
        return (palavra.hashCode() & 0x7fffffff) % NUMERO_PRIMO_GRANDE;
    }

    /**
     * Adiciona palavras na biblioteca
     */
    public void adicionarPalavra(String palavra) {
        arvore[gerarCodigoHash(palavra)].adicionarPalavraNaArvore(palavra);
    }

    //call hash() to find what bucket it's in, get it from that bucket. 
    public boolean contains(String palavra) {
        palavra = palavra.toLowerCase();
        return arvore[gerarCodigoHash(palavra)].get(palavra);
    }

    public void construirDicionario(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
            	adicionarPalavra(line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    /**
     * Árvore que contém as palavras do dicionários
     */
    class Arvore {
    	
        private Vertice primeiro;

        /**
         * Verifica se a palavra está presente na árvore
         */
        public boolean get(String palavra) {         //return key true if key exists
            Vertice proximo = primeiro;
            while (proximo != null) {
                if (proximo.valor.equals(palavra)) {
                    return true;
                }
                proximo = proximo.proximo;
            }
            return false;
        }
        
        /**
         * Adiciona a palavra na arvore
         */
        public void adicionarPalavraNaArvore(String palavra) {
            for (Vertice verticeCorrente = primeiro; verticeCorrente != null; verticeCorrente = verticeCorrente.proximo) {
                if (palavra.equals(verticeCorrente.valor)) {
                    return;                     //search hit: return
                }
            }
            primeiro = new Vertice(palavra, primeiro); //search miss: add new node
        }

        /**
         * Um vértice é um nó da árvore
         */
        class Vertice {

            String valor;
            Vertice proximo;

            public Vertice(String palavra, Vertice primeiro) {
                this.valor = palavra;
                this.proximo = primeiro;
            }
        }
    }
}
