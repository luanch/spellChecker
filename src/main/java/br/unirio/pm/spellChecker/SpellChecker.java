package br.unirio.pm.spellChecker;

import java.util.ArrayList;
/**
 * Classe que ve se uma palavra é válida para um dicionário e caso não seja, 
 * sugere palavras que o usuário pode ter desejado digitar
 */
public class SpellChecker {
	private Dicionario dicionario;
    final static String PATH_DICIONARIO = "src/main/java/br/unirio/pm/spellChecker/data/dicionario.txt";
    final static char[] ALFABETO = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public SpellChecker() {
        dicionario = new Dicionario();
        dicionario.construirDicionario(PATH_DICIONARIO);
    }
    
    /**
     * Checa se palavra é válida para uma biblioteca e sugere palavras semelhantes,
     * caso esta esteja errada
     */
    public String[] checarOrtografia(String palavra) {
       
//        while (true) {
//            System.out.print("\n-------Enter a word: ");
            if (palavra.equals("")) {
//                break;
            	return null;
            }
            if (dicionario.contains(palavra)) {
            	return null;
            } else {
            	return listarPalavrasSimilares(palavra);
            }
//        }
    }
     
	 /**
	  *  Retorna lista de palavras similares
	  */
    private String[] listarPalavrasSimilares(String input) {
        ArrayList<String> palavrasSimilares = new ArrayList<String>();
        palavrasSimilares.addAll(palavrasComCaracterAMais(input));
        palavrasSimilares.addAll(palavrasComCaracterAMenos(input));
        palavrasSimilares.addAll(palavrasComCaracteresTrocados(input));
        String palavras[] = palavrasSimilares.toArray(new String[palavrasSimilares.size()]);
        return palavras;
    }

    /**
     *  Verifica se existem palavras existentes ao adicionar letras
     */
    private ArrayList<String> palavrasComCaracterAMais(String palavra) { 
        ArrayList<String> palavrasEncontradas = new ArrayList<String>();
        
        for (char c : ALFABETO) {
        	for (int i = 0; i <= palavra.length(); i++) {
        		StringBuilder palavraFornecida = new StringBuilder(palavra);
        		String palavraAVerificar = palavraFornecida.insert(i, c).toString();
        		if (dicionario.contains(palavraAVerificar)) {
                    palavrasEncontradas.add(palavraAVerificar);
                }
        	}
        }
        return palavrasEncontradas;
    }

    /**
     *  Verifica se existem palavras se retirar algum carater da palavra
     */
    private ArrayList<String> palavrasComCaracterAMenos(String palavra) {   
        ArrayList<String> palavrasEncontradas = new ArrayList<String>();
   	 	
        for (int i = 0; i < palavra.length(); i++) {
        	StringBuilder palavraFornecida = new StringBuilder(palavra);

        	String palavraAVerificar = palavraFornecida.deleteCharAt(i).toString();
            if(dicionario.contains(palavraAVerificar)){
            	palavrasEncontradas.add(palavraAVerificar);
            }
        }
        return palavrasEncontradas;
    }

    /**
     * Verifica se existem palavras ao trocar caracteres da palavra
     */
    private ArrayList<String> palavrasComCaracteresTrocados(String palavra) {   
        ArrayList<String> palavrasEncontradas = new ArrayList<String>();

        for (int i = 0; i < palavra.length() - 1; i++) {
            for(int j = i+1 ; j < palavra.length(); j++){
            	StringBuilder palavraFornecida = new StringBuilder(palavra);
            	char letraAuxiliar = palavraFornecida.charAt(i);
            	palavraFornecida.setCharAt(i, palavraFornecida.charAt(j));
            	palavraFornecida.setCharAt(j, letraAuxiliar);
            	String palavraAVerificar = palavraFornecida.toString();
            	if (dicionario.contains(palavraAVerificar)) {
            		palavrasEncontradas.add(palavraAVerificar);
            	}
            }
        }
        return palavrasEncontradas;
    }
}
