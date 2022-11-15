package ies.jms.tr18;

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
import java.util.Scanner;

public class WriterXmlCar {


    public static void main(String[] args) {
        Coche coche = createNewCar();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try
        {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            FileOutputStream outputStream = new FileOutputStream("car_out");

            Document document = documentBuilder.newDocument();

            Element carsElement = document.createElement("coches");

            Element carElement = document.createElement("coche");

            Element carMarkElement = document.createElement("marca");
            carMarkElement.setTextContent(coche.getMarca());

            Element carModelElement = document.createElement("modelo");
            carModelElement.setTextContent(coche.getModelo());

            Element carDoorsElement = document.createElement("puertas");
            carDoorsElement.setTextContent(coche.getPuertas());

            Element carKilometersElement = document.createElement("kilometros");
            carKilometersElement.setTextContent(String.valueOf(coche.getKilometros()));

            carElement.appendChild(carMarkElement);
            carElement.appendChild(carModelElement);
            carElement.appendChild(carDoorsElement);
            carElement.appendChild(carKilometersElement);

            Element carEngineElement = document.createElement("motor");

            Element carEngineRevolutionsElement = document.createElement("revoluciones");
            carEngineRevolutionsElement.setTextContent(String.valueOf(coche.getMotor().getRevoluciones()));

            Element carEngineTypeElement = document.createElement("tipo");
            carEngineTypeElement.setTextContent(coche.getMotor().getTipo());

            carEngineElement.appendChild(carEngineRevolutionsElement);
            carEngineElement.appendChild(carEngineTypeElement);

            carElement.appendChild(carEngineElement);

            carElement.appendChild(carElement);

            document.appendChild(carElement);

            writeXml(document,outputStream);


        }
        catch(ParserConfigurationException | TransformerException| IOException xmlException)
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

    private static Coche createNewCar(){
        Scanner scanner = new Scanner(System.in);

        try
        {

        }






        return coche;

    }
}
