package ies.jms.tr27;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectWriter;
import ies.jms.tr18.Coche;
import ies.jms.tr18.Motor;
import ies.jms.tr22.Json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteJsonCar
{
    public static void main(String[] args) throws IOException {
        List<Coche> listaCoches = new ArrayList<Coche>();

        listaCoches.add(createNewCar());
        listaCoches.add(createNewCar());
        listaCoches.add(createNewCar());

        ObjectWriter writer = Json.mapper().writer(new DefaultPrettyPrinter());
        writer.writeValue(new File("cars_out.json"),listaCoches);
    }

    private static Coche createNewCar()
    {
        return new Coche("Wolsvagen","Polo","5",20000, new Motor(1000, "150cv"));
    }
}
