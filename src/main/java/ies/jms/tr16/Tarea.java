package ies.jms.tr16;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Tarea {

    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        List<Alumno> listaAlumnos = new ArrayList<Alumno>();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try
        {

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File("alumno.xml"));
            Element rootElement = document.getDocumentElement();
            NodeList nodeListStudent= rootElement.getElementsByTagName("alumno");
            for(int l = 0; l < nodeListStudent.getLength(); l++)
            {
                Element nodeStudent = (Element) nodeListStudent.item(l);

                String nombre = nodeStudent.getAttributes().getNamedItem("nombre").getTextContent();
                int edad= Integer.valueOf(nodeStudent.getAttributes().getNamedItem("edad").getTextContent());
                double calificaciones= Double.valueOf(nodeStudent.getElementsByTagName("calificacion").item(0).getTextContent());
                boolean unidadesPendientes= Boolean.valueOf(nodeStudent.getElementsByTagName("unidadesPendientes").item(0).getTextContent());

                System.out.println("Nombre: "+nombre+" ,Edad: "+edad+ " ,calificaciones: "+ calificaciones+ " , Unidades Pendientes: "+unidadesPendientes);

                Alumno alumno = new Alumno(nombre,edad,calificaciones,unidadesPendientes);

                listaAlumnos.add(alumno);


            }

            alumnosPendiente(listaAlumnos);
            alumnosMaxMinNota(listaAlumnos);
            alumnosEdadMedia(listaAlumnos);
            alumnosNotaMedia(listaAlumnos);


        }
        catch (ParserConfigurationException | SAXException | IOException xmlException)
        {
            xmlException.printStackTrace();

        }
    }

    private static void alumnosPendiente(List<Alumno>listaAlumnos) {
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;

        try {
            fileWriter = new FileWriter("AlumnosPendientes.txt");
            printWriter = new PrintWriter(fileWriter);

            listaAlumnos.sort(Comparator.comparing(Alumno::getNombre));

            for(Alumno alumno : listaAlumnos){
                if(!alumno.isUnidadesPendientes()){
                    printWriter.println(alumno);
                }
            }


            printWriter.flush();

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    private static void alumnosMaxMinNota(List<Alumno>listaAlumnos) {
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;

        try {
            fileWriter = new FileWriter("AlumnoMejorPeorNota.txt");
            printWriter = new PrintWriter(fileWriter);

            listaAlumnos.sort(Comparator.comparing(Alumno::getCalificacion));
            printWriter.println("Peor Alumno:");
            printWriter.println(listaAlumnos.get(0).toString());

            listaAlumnos.sort(Comparator.comparing(Alumno::getCalificacion).reversed());

            printWriter.println("Mejor Alumno:");
            printWriter.println(listaAlumnos.get(0).toString());

            printWriter.flush();

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    private static void alumnosEdadMedia(List<Alumno>listaAlumnos) {
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;

        try {
            fileWriter = new FileWriter("AlumnoMediaEdad.txt");
            printWriter = new PrintWriter(fileWriter);

            int edadTotal = 0;
            for(int i=0; i<listaAlumnos.size();i++)
            {

                edadTotal += listaAlumnos.get(i).getEdad();


            }
            printWriter.println("Media de edad : "+edadTotal/ listaAlumnos.size());

            printWriter.flush();

        }
        catch (FileNotFoundException fileNotFoundException)
        {
            fileNotFoundException.printStackTrace();

        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();

        }
        finally
        {
            if (fileWriter != null)
            {
                try
                {
                    fileWriter.close();
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }

            if (printWriter != null)
            {
                printWriter.close();
            }
        }
    }

    private static void alumnosNotaMedia(List<Alumno>listaAlumnos)
    {
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;

        try
        {
            fileWriter = new FileWriter("AlumnoMediaNota.txt");
            printWriter = new PrintWriter(fileWriter);

            int notaTotal = 0;
            for(int i=0; i<listaAlumnos.size();i++)
            {

                notaTotal += listaAlumnos.get(i).getCalificacion();


            }
            printWriter.println("Media de nota : "+ notaTotal/listaAlumnos.size());

            printWriter.flush();

        }
        catch (FileNotFoundException fileNotFoundException)
        {
            fileNotFoundException.printStackTrace();
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
        finally
        {
            if (fileWriter != null)
            {
                try
                {
                    fileWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

            if (printWriter != null) {
                printWriter.close();
            }
        }
    }



}



