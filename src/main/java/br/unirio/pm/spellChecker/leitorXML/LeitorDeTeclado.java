package br.unirio.pm.spellChecker.leitorXML;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LeitorDeTeclado {

	public void lerTeclado() {
	    
		try {
			File arquivoXml = new File("KeyboardLayouts.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document documento = dBuilder.parse(arquivoXml);
		
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			documento.getDocumentElement().normalize();
		
			System.out.println("Root element :" + documento.getDocumentElement().getNodeName());
		
			NodeList nList = documento.getElementsByTagName("layout");
	
			
			System.out.println("----------------------------");
	
			for (int temp = 0; temp < nList.getLength(); temp++) {
		
				Node nNode = nList.item(temp);
		
				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		
					Element eElement = (Element) nNode;
		
					System.out.println("Staff id : " + eElement.getAttribute("model"));
					Teclado teclado = new Teclado();
					teclado.setNome(eElement.getAttribute("model"));
					
					for (int i = 0; i < eElement.getElementsByTagName("line").getLength(); i++){
						NodeList linhas = eElement.getElementsByTagName("line");
						Node linhaNode = linhas.item(i);
						NamedNodeMap atributosDeLinha = linhas.item(i).getAttributes();
						Linha linha;
						// verifica se linha tem atributos
						if(atributosDeLinha.getLength()!= 0){
							
							// o valor de offset vem com algo do tipo offset="0.5"
							String offSetStringComLixo = atributosDeLinha.getNamedItem("offset").toString();
							
							// remove-se as aspas e o offset=
							String offSetString = offSetStringComLixo.replace("offset=\"", "").replace("\"", "");
							
							// Agora sim temos um valor double de offset, 
							double offset = Double.parseDouble(offSetString) ;
							
							// que pode ser passado no construtor de Linha
							linha = new Linha(linhaNode.getTextContent(), offset);
						}
						else{
							//se n tem offset, usamos o construtor sem offset
							linha = new Linha(linhaNode.getTextContent());
						}
						
						teclado.adicionarLinha(linha);	
					}
				}
			}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
}