package br.unirio.pm.spellChecker.LeitorDePalavras;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class LeitorDeZip {
	
	public ArrayList<InputStream>  lerZip(String caminho){
		
		ArrayList<InputStream> listaDeDicionarios = new ArrayList<InputStream>();
		
		File arquivo = new File(caminho).getAbsoluteFile();
		ZipFile arquivoZip;
		try{
			arquivoZip = new ZipFile(arquivo);	
			
			// Cada item dentro do zip é um objeto da classe ZipEntry
			ZipEntry arquivoCorrente;
			
			Enumeration<? extends ZipEntry> conjuntoDeArquivos = arquivoZip.entries();
			
			while(conjuntoDeArquivos.hasMoreElements()){
				arquivoCorrente = conjuntoDeArquivos.nextElement();
				if (!arquivoCorrente.isDirectory()) {
					if(arquivoCorrente.getName().endsWith(".dic")){
						listaDeDicionarios.add(arquivoZip.getInputStream(arquivoCorrente));
					}
				}
			}
		} catch (ZipException e) {
			System.out.println("Erro ao ler zip "+ e);
		} catch (IOException e) {
			System.out.println("Erro no caminho do dicionário "+ e);
		}
		return listaDeDicionarios;
	}
}
