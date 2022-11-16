package ies.jms.tr19;

import org.w3c.dom.Attr;
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

public class WriteXml
{
    public static void main(String[] args)
    {
        WriteXml writeXml = new WriteXml();

        writeXml.crearXml();

    }

    private void crearXml()
    {
        List<Casa>listaCasas = new ArrayList<>();

        listaCasas.add(new Casa("Jaen","adosado",300,2,7,false));
        listaCasas.add(new Casa("Andujar","unifamiliar",200,1,5,false));
        listaCasas.add(new Casa("Linares","piso",90,1,4,true));

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        FileOutputStream outputStream = null;

        try
        {
            outputStream = new FileOutputStream("casas_out.xml");

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document doc = documentBuilder.newDocument();
            Element casas = doc.createElement("casas");

            for(Casa choza : listaCasas)
            {
                Element casa = doc.createElement("casa");
                casas.appendChild(casa);

                Attr ciudad = doc.createAttribute("ciudad");
                ciudad.setValue(choza.getCiudad());
                casa.setAttributeNode(ciudad);

                Attr tipo = doc.createAttribute("tipo");
                tipo.setValue(choza.getTipo());
                casa.setAttributeNode(tipo);

                Attr metrosCuadrados = doc.createAttribute("metrosCuadrados");
                metrosCuadrados.setValue(String.valueOf(choza.getMetrosCuadrados()));
                casa.setAttributeNode(metrosCuadrados);

                Attr plantas = doc.createAttribute("plantas");
                plantas.setValue(String.valueOf(choza.getPlantas()));
                casa.setAttributeNode(plantas);

                Attr habitaciones = doc.createAttribute("habitaciones");
                habitaciones.setValue(String.valueOf(choza.getHabitaciones()));
                casa.setAttributeNode(habitaciones);

                Attr zonasComunes = doc.createAttribute("zonasComunes");
                zonasComunes.setValue(String.valueOf(choza.isZonasComunes()));
                casa.setAttributeNode(zonasComunes);

                casas.appendChild(casa);

            }

            doc.appendChild(casas);

            writeXml(doc,outputStream);


        }
        catch(ParserConfigurationException | TransformerException | IOException xmlException)
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
