package ies.jms.tr20;

import ies.jms.tr16.Alumno;
import ies.jms.tr16.Tarea;
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

    public void crearXml(List<Alumno>listaAlumnos)
    {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        FileOutputStream outputStream = null;

        try
        {
            outputStream = new FileOutputStream("src/main/java/ies/jms/tr20/alumno_out.xml");

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document doc = documentBuilder.newDocument();
            Element alumnos = doc.createElement("alumnos");

            for(Alumno alumn : listaAlumnos)
            {
                Element alumno = doc.createElement("alumno");
                alumnos.appendChild(alumno);

                Attr nombre = doc.createAttribute("nombre");
                nombre.setValue(alumn.getNombre());
                alumno.setAttributeNode(nombre);

                Attr edad = doc.createAttribute("edad");
                edad.setValue(String.valueOf(alumn.getEdad()));
                alumno.setAttributeNode(edad);

                Element calificacion = doc.createElement("calificacion");
                calificacion.setTextContent(String.valueOf(alumn.getCalificacion()));
                alumno.appendChild(calificacion);

                Element unidadesPendientes = doc.createElement("unidadesPendientes");
                unidadesPendientes.setTextContent(String.valueOf(alumn.isUnidadesPendientes()));
                alumno.appendChild(unidadesPendientes);

                alumnos.appendChild(alumno);

            }

            doc.appendChild(alumnos);

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
