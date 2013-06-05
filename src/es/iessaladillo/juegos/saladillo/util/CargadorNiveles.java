package es.iessaladillo.juegos.saladillo.util;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class CargadorNiveles {
	
	public static ArrayList<Entidad> cargarNivel(String fichero){
	
		ArrayList<Entidad> entidades = new ArrayList<Entidad>();;


		 
		    try {
		 
			File fXmlFile = new File(fichero);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
		
		 
			doc.getDocumentElement().normalize();
		 
		
		 
			NodeList nList = doc.getElementsByTagName("entity");
		 
			
		 
			for (int temp = 0; temp < nList.getLength(); temp++) {
		 
				Node nNode = nList.item(temp);
		 
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					String type= eElement.getAttribute("type");
					String x = eElement.getAttribute("x");
					String y =  eElement.getAttribute("y");
					entidades.add(new Entidad(type,Integer.parseInt(x)/32,Integer.parseInt(y)/32));
				}
			}
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		  
		 
	return entidades;
		
	
	

	}
	
/*	static public void main(String args[]){
		
		ArrayList<Entidad> entidades =cargarNivel("src/1.lvl");
		System.out.println(entidades);
	}
	*/
}
