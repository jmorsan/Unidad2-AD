package ies.jms.tr14;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main2
{
	public static void main(String[] args)
	{
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		
		try
		{
			
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(new File("car.xml"));
			Element rootElement = document.getDocumentElement();
			NodeList nodeListCar = rootElement.getElementsByTagName("coche");
			
			for(int l = 0; l < nodeListCar.getLength(); l++) {
				Element nodeCar = (Element) nodeListCar.item(l);
				
				String marca = nodeCar.getElementsByTagName("marca").item(0).getTextContent();
				String modelo = nodeCar.getElementsByTagName("modelo").item(0).getTextContent();
				String puertas = nodeCar.getElementsByTagName("puertas").item(0).getTextContent();
				String kilometros = nodeCar.getElementsByTagName("kilometros").item(0).getTextContent();
						
				System.out.println("Marca: "+ marca + ", modelo: "+ modelo + 
						", puertas: " + puertas + ", kilometros: " + kilometros);
				
				NodeList nodeListMotor = nodeCar.getElementsByTagName("motor");
				
				Element nodeMotor = (Element) nodeListMotor.item(0);
				
				String revoluciones = nodeMotor.getElementsByTagName("revoluciones").item(0).getTextContent();
				String tipo = nodeMotor.getElementsByTagName("tipo").item(0).getTextContent();
				
				System.out.println("Motor -> Revoluciones: " + revoluciones + ", tipo: " + tipo) ;
			}
		}
		catch (ParserConfigurationException | SAXException | IOException xmlException)
		{
			xmlException.printStackTrace();
		
		}
	}
}
