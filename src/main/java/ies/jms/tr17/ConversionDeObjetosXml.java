package ies.jms.tr17;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ConversionDeObjetosXml
{
    public static void main(String[] args) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try
        {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            FileOutputStream outputStream = new FileOutputStream("hello_world_out.xml");

            Document document = documentBuilder.newDocument();

            Element rootElement = document.createElement("mi_etiqueta_raiz");

            rootElement.setTextContent("Hola mundo");

            document.appendChild(rootElement);

            writeXml(document,outputStream);



        }
        catch (ParserConfigurationException | TransformerException| IOException xmlException)
        {
            xmlException.printStackTrace();
        }

    }

    private static void writeXml(Document document, OutputStream outputStream) throws TransformerException
    {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        Transformer transformer = transformerFactory.newTransformer();

        DOMSource xmlAsObject = new DOMSource(document);

        StreamResult xmlAsFile = new StreamResult(outputStream);

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        transformer.transform(xmlAsObject,xmlAsFile);

    }
}
