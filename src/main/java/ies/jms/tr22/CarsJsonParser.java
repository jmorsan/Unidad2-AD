package ies.jms.tr22;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class CarsJsonParser
{
    public static void main(String[] args) throws IOException
    {
        String fileContent = FileUtils.readFileToString(new File("cars.json"), "UTF-8");

        JsonNode rootJsonNode = Json.mapper().readTree(fileContent);

        if(rootJsonNode.isArray())
        {
            ArrayNode rootArrayJsonNode = (ArrayNode) rootJsonNode;

            final Iterator<JsonNode> iterator = rootArrayJsonNode.elements();
            while (iterator.hasNext())
            {
                final JsonNode carJsonNode = iterator.next();

                if (carJsonNode.has("marca"))
                {
                    final JsonNode markNode = carJsonNode.get("marca");
                    System.out.println(markNode.asText());
                }

                if (carJsonNode.has("modelo"))
                {
                    final JsonNode modelNode = carJsonNode.get("modelo");
                    System.out.println(modelNode.asText());
                }

                if (carJsonNode.has("motor"))
                {
                    final JsonNode engineNode = carJsonNode.get("motor");

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

                if (carJsonNode.has("puertas"))
                {
                    final JsonNode doorsNode = carJsonNode.get("puertas");
                    System.out.println(doorsNode.asText());
                }

                if (carJsonNode.has("kilometros"))
                {
                    final JsonNode kilometersNode = carJsonNode.get("kilometros");
                    System.out.println(kilometersNode.asText());
                }

            }
        }


    }
}
