package ies.jms.tr24;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import ies.jms.tr22.Json;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class AlumnoJsonParser
{
    public static void main(String[] args) throws IOException
    {
        String fileContent = FileUtils.readFileToString(new File("alumno.json"), "UTF-8");

        JsonNode rootJsonNode = Json.mapper().readTree(fileContent);

        if(rootJsonNode.isArray())
        {
            ArrayNode rootArrayJsonNode = (ArrayNode) rootJsonNode;

            final Iterator<JsonNode> iterator = rootArrayJsonNode.elements();
            while (iterator.hasNext())

            {
                final JsonNode alumnoJsonNode = iterator.next();

                if (alumnoJsonNode.has("marca"))
                {
                    final JsonNode markNode = alumnoJsonNode.get("marca");
                    System.out.println(markNode.asText());
                }

                if (alumnoJsonNode.has("modelo"))
                {
                    final JsonNode modelNode = alumnoJsonNode.get("modelo");
                    System.out.println(modelNode.asText());
                }

                if (alumnoJsonNode.has("motor"))
                {
                    final JsonNode engineNode = alumnoJsonNode.get("motor");

                    if(engineNode.has("revoluciones"))
                    {
                        final JsonNode engineRevolutionsNode = engineNode.get("revoluciones");
                        System.out.println(engineRevolutionsNode.asText());

                    }

                    if(engineNode.has("tipo"))
                    {
                        final JsonNode engineTypeNode = engineNode.get("tipo");
                        System.out.println(engineTypeNode.asText());

                    }
                }

                if (alumnoJsonNode.has("puertas"))
                {
                    final JsonNode doorsNode = alumnoJsonNode.get("puertas");
                    System.out.println(doorsNode.asText());
                }

                if (alumnoJsonNode.has("kilometros"))
                {
                    final JsonNode kilometersNode = alumnoJsonNode.get("kilometros");
                    System.out.println(kilometersNode.asText());
                }

            }
        }


    }
}
