package ies.jms.tr13;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Main
{
	public static void main(String[] args)
	{
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		
		try
		{
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(new File("hello_world.xml"));
			Element rootElement = document.getDocumentElement();
			
			System.out.println("Nombre del elemento ra�z ..." + rootElement.getNodeName());
			System.out.println("Valor del elemento ra�z ..." + rootElement.getTextContent());
		}
		catch (ParserConfigurationException | SAXException | IOException xmlException)
		{

			xmlException.printStackTrace();
		
		}
	}
}
