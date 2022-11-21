package ies.jms.tr22;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class HelloWorldJsonParser
{
    public static void main(String[] args) throws IOException {
        String fileContent = FileUtils.readFileToString(new File("hello_world.json"),"UTF-8");

        JsonNode rootJsonNode = Json.mapper().readTree(fileContent);

        if(rootJsonNode.has("hello"))
        {
            final JsonNode attributeNode = rootJsonNode.get("hello");
            System.out.println(attributeNode.asText());
        }
    }
}
