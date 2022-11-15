package ies.jms.tr18;

import ies.jms.tr16.Alumno;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriterXmlCar {

    private List<Coche> listaCoches;

    public WriterXmlCar() {
        this.listaCoches = new ArrayList<Coche>();
    }


    public static void main(String[] args) {
        WriterXmlCar writeXml = new WriterXmlCar();

        writeXml.menu();

    }



    private void menu(){
        Scanner scanner = new Scanner(System.in);

        String marca;
        String modelo;
        String puertas;
        int kilometros;
        int revoluciones;
        String tipoMotor;
        int opcion;
        boolean ok =true;
        do {
            ok = true;

            try {

                do {
                    System.out.println("Crear un coche nuevo?:");
                    System.out.println("1- SI");
                    System.out.println("0- Salir");

                    opcion = Integer.parseInt(scanner.nextLine());


                    switch (opcion) {

                        case 1:
                            System.out.println("Introduzca marca ");
                            marca = scanner.nextLine();
                            System.out.println("Introduzca modelo ");
                            modelo = scanner.nextLine();
                            System.out.println("Introduzca puertas ");
                            puertas=scanner.nextLine();
                            System.out.println("Introduzca kilometros");
                            kilometros=Integer.parseInt(scanner.nextLine());
                            System.out.println("Introduzca revoluciones motor ");
                            revoluciones = Integer.parseInt(scanner.nextLine());
                            System.out.println("Introduzca tipo motor");
                            tipoMotor = scanner.nextLine();
                            createNewCar(marca,modelo,puertas,kilometros,revoluciones,tipoMotor);

                            break;

                        case 0:
                            System.out.println("Que tenga un buen dia");
                            writeXml(listaCoches);
                            break;


                        default:
                            System.out.println("Elija una de la opciones.");
                            menu();
                            break;

                    }
                }
                while (opcion != 0);
            }
            catch (NumberFormatException numberFormatException)
            {
                numberFormatException.printStackTrace();
                ok=false;
                scanner.nextLine();
            }
            finally
            {
                if(scanner!=null)
                {
                    scanner.close();
                }


            }
        }while(!ok);
    }

    private  void createNewCar(String marca,String modelo,String puertas,int kilometros,int revoluciones,String tipoMotor){
        Coche coche = new Coche(marca, modelo, puertas, kilometros,new Motor(revoluciones,tipoMotor));

        listaCoches.add(coche);

    }

    private void writeXml(List<Coche>listaCoches){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        FileOutputStream outputStream = null;

        try
        {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            outputStream = new FileOutputStream("car_out.xml");

            Document document = documentBuilder.newDocument();

            Element carsElement = document.createElement("coches");

            document.appendChild(carsElement);


            for(Coche coche:listaCoches){
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

                carsElement.appendChild(carElement);

            }

            writeXml(document,outputStream);


        }
        catch(ParserConfigurationException | TransformerException| IOException xmlException)
        {
            xmlException.printStackTrace();
        }
        finally
        {
            if (outputStream != null)
            {
                try
                {
                    outputStream.close();
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }

        }
    }

    private void writeXml(Document document, OutputStream outputStream) throws TransformerException
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
