package ies.jms.tr25;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import ies.jms.tr22.Json;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MatchJsonParser
{
    private static final Logger LOGGER = LogManager.getLogger();

    private List<Match> listaMatch = new ArrayList<Match>();

    public static void main(String[] args)
    {
        MatchJsonParser matchJsonParser = new MatchJsonParser();

        try
        {
            matchJsonParser.startProcess();
        }
        catch (MatchException competitionException)
        {
            competitionException.printStackTrace();
        }

    }

    private void startProcess()throws MatchException
    {
        String nombreFichero = "src/main/java/ies/jms/tr25/43.json";
        try
        {

            String fileContent = FileUtils.readFileToString(new File(nombreFichero), "UTF-8");

            JsonNode rootJsonNode = Json.mapper().readTree(fileContent);

            if (rootJsonNode.isArray())
            {
                ArrayNode rootArrayJsonNode = (ArrayNode) rootJsonNode;

                final Iterator<JsonNode> iterator = rootArrayJsonNode.elements();

                while (iterator.hasNext())
                {
                    Match match = new Match();

                    final JsonNode matchJsonNode = iterator.next();

                    if (matchJsonNode.has("competition"))
                    {
                        final JsonNode competitionNode = matchJsonNode.get("competition");
                        match.setNombre(nombreNode.asText());
                    }

                    if (matchJsonNode.has("edad"))
                    {
                        final JsonNode edadNode = matchJsonNode.get("edad");
                        alumno.setEdad(Integer.parseInt(edadNode.asText()));
                    }

                    if (matchJsonNode.has("calificacion"))
                    {
                        final JsonNode calificacionNode = matchJsonNode.get("calificacion");
                        alumno.setCalificacion(Double.parseDouble(calificacionNode.asText()));
                    }

                    if (matchJsonNode.has("unidadesPendientes"))
                    {
                        final JsonNode unidadesPendientesNode = matchJsonNode.get("unidadesPendientes");
                        alumno.setUnidadesPendientes(Boolean.parseBoolean(unidadesPendientesNode.asText()));
                    }

                    listaAlumnos.add(alumno);
                }
            }
            alumnoFilter(listaAlumnos);
        }
        catch (IOException ioException)
        {
            LOGGER.error("Error mientras se trataba de leer el fichero " + nombreFichero , ioException);
            throw new AlumnoException("Error mientras se trataba de leer el fichero " + nombreFichero , ioException);

        }
    }

    private static void alumnoFilter(List<Alumno>listaAlumnos) throws AlumnoException
    {
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        String nombreFicheroSalida = "src/main/java/ies/jms/tr24/salida-alumnos.txt";
        try
        {
            fileWriter = new FileWriter(nombreFicheroSalida);
            printWriter = new PrintWriter(fileWriter);

            printWriter.println("ALUMNOS CON UNIDADES PENDIENTES");
            printWriter.println("=======================================");
            listaAlumnos.sort(Comparator.comparing(Alumno::getNombre).reversed());
            for(Alumno alumno : listaAlumnos)
            {
                if(alumno.isUnidadesPendientes())
                {
                    printWriter.println(alumno);
                }
            }

            printWriter.println();
            printWriter.println("NOTA MAS CERCANA A LA MEDIA DE CLASE");
            printWriter.println("=======================================");

            printWriter.println(notaMasAlta(listaAlumnos));

            printWriter.flush();

        }
        catch (IOException fileNotFoundException)
        {
            LOGGER.error("No se pudo encontrar el fichero " + nombreFicheroSalida , fileNotFoundException);
            throw new AlumnoException("No se pudo encontrar el fichero " + nombreFicheroSalida , fileNotFoundException);

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
                    LOGGER.error("No se pudo cerrar FileWriter ", ioException);
                    throw new AlumnoException("No se pudo cerrar FileWriter ", ioException);
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

        Alumno alumnoNotaMasCercana = listaAlumnos.get(0);

        //System.out.println(notaMedia);
        for(Alumno alumno : listaAlumnos)
        {
            double diferenciaAlumnoNotaMedia = Math.abs(alumno.getCalificacion()-notaMedia);
            double diferenciaAlumnoMasCercano = Math.abs(alumnoNotaMasCercana.getCalificacion()-notaMedia);

            //System.out.println("Siguiente --> "+diferenciaAlumnoNotaMedia + " Nombre: "+ alumno.getNombre());
            //System.out.println("Nota guardada --> "+diferenciaAlumnoMasCercano );

            if(diferenciaAlumnoNotaMedia<diferenciaAlumnoMasCercano)
            {
                alumnoNotaMasCercana = alumno;

            }

        }

        return alumnoNotaMasCercana;

    }
}