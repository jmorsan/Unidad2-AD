package ies.jms.tr26;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ies.jms.tr18.Coche;
import ies.jms.tr22.Json;

import java.nio.file.Paths;
import java.util.List;

public class ParserJsonCarsOption2
{
    public static void main(String[] args)
    {
        ObjectMapper mapper = Json.mapper();

        try
        {
            //Convert a JSON string to a Coche object
            Coche coche = mapper.readValue(Paths.get("car.json").toFile(), Coche.class);

            //Convert a JSON string to a Coche Array
            Coche [] cochesArray = mapper.readValue(Paths.get("cars.json").toFile(), Coche[].class);

            //Convert a JSON string to a Coche List (Option 1)
            List<Coche> cocheList1 = mapper.readValue(Paths.get("cars.json").toFile(), new TypeReference<List<Coche>>() {});

            //Convert a JSON string to a Coche List (Option 2)
            List<Coche> cochesList2 = mapper.readValue(Paths.get("cars.json").toFile(), mapper.getTypeFactory().constructCollectionType(List.class, Coche.class));
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
