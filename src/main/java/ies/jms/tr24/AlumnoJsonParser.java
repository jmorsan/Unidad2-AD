package ies.jms.tr24;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import ies.jms.tr16.Alumno;
import ies.jms.tr21.Competition;
import ies.jms.tr22.Json;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class AlumnoJsonParser
{


    public static void main(String[] args) throws IOException
    {
        String nombre = " ";
        int edad = 0;
        double calificacion = 0.0;
        boolean unidadesPendientes = false;


        List<Alumno> listaAlumnos = new ArrayList<Alumno>();

        String fileContent = FileUtils.readFileToString(new File("src/main/java/ies/jms/tr24/alumno.json"), "UTF-8");

        JsonNode rootJsonNode = Json.mapper().readTree(fileContent);

        if(rootJsonNode.isArray())
        {
            ArrayNode rootArrayJsonNode = (ArrayNode) rootJsonNode;

            final Iterator<JsonNode> iterator = rootArrayJsonNode.elements();
            while (iterator.hasNext())

            {
                final JsonNode alumnoJsonNode = iterator.next();

                if (alumnoJsonNode.has("nombre"))
                {
                    final JsonNode nombreNode = alumnoJsonNode.get("nombre");
                    nombre = nombreNode.asText();
                }

                if (alumnoJsonNode.has("edad"))
                {
                    final JsonNode edadNode = alumnoJsonNode.get("edad");
                    edad = Integer.parseInt(edadNode.asText());
                }

                if (alumnoJsonNode.has("calificacion"))
                {
                    final JsonNode calificacionNode = alumnoJsonNode.get("calificacion");
                    calificacion = Double.parseDouble(calificacionNode.asText());
                }

                if (alumnoJsonNode.has("unidadesPendientes"))
                {
                    final JsonNode unidadesPendientesNode = alumnoJsonNode.get("unidadesPendientes");
                    unidadesPendientes = Boolean.parseBoolean(unidadesPendientesNode.asText());
                }

                Alumno alumno = new Alumno(nombre,edad,calificacion,unidadesPendientes);
                listaAlumnos.add(alumno);
            }
        }
        alumnoFilter(listaAlumnos);
    }

    private static void alumnoFilter(List<Alumno>listaAlumnos)
    {
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;

        try {
            fileWriter = new FileWriter("src/main/java/ies/jms/tr24/salida-alumnos.txt");
            printWriter = new PrintWriter(fileWriter);

            printWriter.println("ALUMNOS CON UNIDADES PENDIENTES");
            printWriter.println("=======================================");
            listaAlumnos.sort(Comparator.comparing(Alumno::getNombre).reversed());
            for(Alumno alumno : listaAlumnos){
                if(alumno.isUnidadesPendientes())
                {
                    printWriter.println(alumno);
                }
            }

            printWriter.println();
            printWriter.println("COMPETICIONES EN EUROPA E INTERNACIONALES");
            printWriter.println("=======================================");

            printWriter.println(notaMasAlta(listaAlumnos));

            printWriter.flush();

        }
        catch (IOException fileNotFoundException)
        {
            fileNotFoundException.printStackTrace();
        } finally
        {
            if (fileWriter != null)
            {
                try
                {
                    fileWriter.close();
                } catch (IOException ioException)
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

    private static double notaMedia(List<Alumno>listaAlumnos)
    {
        double totalNotas = 0.0;

        for(Alumno alumno : listaAlumnos)
        {
            totalNotas += alumno.getCalificacion();

        }

        return totalNotas/listaAlumnos.size();

    }

    private static Alumno notaMasAlta(List<Alumno>listaAlumnos)
    {

        double notaMedia = notaMedia(listaAlumnos);

        Alumno alumnoNotaMasCercana = new Alumno();


        System.out.println(notaMedia);
        for(Alumno alumno : listaAlumnos)
        {
            double nota1 = Math.abs(alumno.getCalificacion()-notaMedia);
            double nota2 = Math.abs(alumnoNotaMasCercana.getCalificacion()-notaMedia);

            System.out.println("Siguiente --> "+nota1 + " Nombre: "+ alumno.getNombre());
            System.out.println("Nota guardada --> "+nota2 );

            if(nota1<nota2)
            {
                alumnoNotaMasCercana = alumno;

            }

        }

        return alumnoNotaMasCercana;

    }
}
