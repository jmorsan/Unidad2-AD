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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MatchJsonParser
{
    private static final Logger LOGGER = LogManager.getLogger();

    private List<Match> matchList = new ArrayList<Match>();

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
                    Stage stage = new Stage();
                    Season season = new Season();
                    Team homeTeam = new Team();
                    Team awayTeam = new Team();
                    Manager homeManager = new Manager();
                    Manager awayManager = new Manager();
                    Country countryHomeManager = new Country();
                    Country countryAwayManager = new Country();
                    Country countryHomeTeam = new Country();
                    Country countryAwayTeam = new Country();



                    final JsonNode matchJsonNode = iterator.next();

                    if (matchJsonNode.has("match_date"))
                    {
                        final JsonNode matchDateNode = matchJsonNode.get("match_date");
                        match.setDate(matchDateNode.asText());
                    }


                    if (matchJsonNode.has("season"))
                    {
                        final JsonNode seasonNode = matchJsonNode.get("season");

                        if(seasonNode.has("season_name"))
                        {

                            final JsonNode seasonNameNode = seasonNode.get("season_name");

                            season.setSeasonName(seasonNameNode.asText());

                            match.setSeason(season);
                        }

                    }


                    if (matchJsonNode.has("home_team"))
                    {
                        final JsonNode homeTeamNode = matchJsonNode.get("home_team");

                        if(homeTeamNode.has("home_team_id"))
                        {

                            final JsonNode homeTeamNameNode = homeTeamNode.get("home_team_id");

                            homeTeam.setTeamName(homeTeamNameNode.asText());

                            match.setHomeTeam(homeTeam);

                        }

                        if(homeTeamNode.has("home_team_name"))
                        {

                            final JsonNode homeTeamNameNode = homeTeamNode.get("home_team_name");

                            homeTeam.setTeamName(homeTeamNameNode.asText());

                            match.setHomeTeam(homeTeam);

                        }

                        if(homeTeamNode.has("country"))
                        {
                            final JsonNode homeTeamCountryNode = homeTeamNode.get("country");

                            if(homeTeamCountryNode.has("name"))
                            {
                                final JsonNode homeTeamCountryNameNode = homeTeamCountryNode.get("name");

                                countryHomeTeam.setName(homeTeamCountryNameNode.asText());
                                homeTeam.setTeamCountry(countryHomeTeam);
                                match.setHomeTeam(homeTeam);
                            }
                        }

                        if(homeTeamNode.has("managers"))
                        {
                            final JsonNode managersNode = homeTeamNode.get("managers");

                            ArrayNode managerArrayJsonNode = (ArrayNode) managersNode;

                            final Iterator<JsonNode> managerIterator = managerArrayJsonNode.elements();

                            if(managersNode.isArray())
                            {
                                while (managerIterator.hasNext())
                                {
                                    final JsonNode managerNode = managerIterator.next();

                                    if(managerNode.has("name"))
                                    {
                                        final JsonNode managerNameNode = managerNode.get("name");

                                        homeManager.setName(managerNameNode.asText());

                                        homeTeam.getManager().add(homeManager);

                                        match.setHomeTeam(homeTeam);
                                    }

                                    if(managerNode.has("country"))
                                    {
                                        final JsonNode managerCountryNode = managerNode.get("country");

                                        if(managerCountryNode.has("name"))
                                        {
                                            final JsonNode managerCountryNameNode = managerCountryNode.get("name");

                                            countryHomeManager.setName(managerCountryNameNode.asText());

                                            homeManager.setNameCountry(countryHomeManager);

                                            homeTeam.getManager().add(homeManager);

                                            match.setHomeTeam(homeTeam);
                                        }

                                    }
                                }
                            }
                        }
                    }

                    if (matchJsonNode.has("away_team"))
                    {
                        final JsonNode awayTeamNode = matchJsonNode.get("away_team");

                        if(awayTeamNode.has("away_team_id"))
                        {

                            final JsonNode awayTeamNameNode = awayTeamNode.get("away_team_id");

                            awayTeam.setTeamName(awayTeamNameNode.asText());

                            match.setAwayTeam(awayTeam);
                        }

                        if(awayTeamNode.has("away_team_name"))
                        {

                            final JsonNode awayTeamNameNode = awayTeamNode.get("away_team_name");

                            awayTeam.setTeamName(awayTeamNameNode.asText());

                            match.setAwayTeam(awayTeam);
                        }

                        if(awayTeamNode.has("country"))
                        {
                            final JsonNode awayTeamCountryNode = awayTeamNode.get("country");

                            if(awayTeamCountryNode.has("name"))
                            {
                                final JsonNode awayTeamCountryNameNode = awayTeamCountryNode.get("name");

                                countryAwayTeam.setName(awayTeamCountryNameNode.asText());
                                awayTeam.setTeamCountry(countryAwayTeam);
                                match.setAwayTeam(awayTeam);
                            }
                        }


                        if(awayTeamNode.has("managers"))
                        {
                            final JsonNode managersNode = awayTeamNode.get("managers");

                            ArrayNode managerArrayJsonNode = (ArrayNode) managersNode;

                            final Iterator<JsonNode> managerIterator = managerArrayJsonNode.elements();

                            if(managersNode.isArray())
                            {
                                while (managerIterator.hasNext())
                                {
                                    final JsonNode managerNode = managerIterator.next();

                                    if(managerNode.has("id"))
                                    {
                                        final JsonNode managerIdNode = managerNode.get("id");

                                        awayManager.setId(Integer.parseInt(managerIdNode.asText()));

                                        awayTeam.getManager().add(awayManager);

                                        match.setAwayTeam(awayTeam);
                                    }

                                    if(managerNode.has("name"))
                                    {
                                        final JsonNode managerNameNode = managerNode.get("name");

                                        awayManager.setName(managerNameNode.asText());

                                        awayTeam.getManager().add(awayManager);

                                        match.setAwayTeam(awayTeam);
                                    }

                                    if(managerNode.has("country"))
                                    {
                                        final JsonNode managerCountryNode = managerNode.get("country");

                                        if(managerCountryNode.has("name"))
                                        {
                                            final JsonNode managerCountryNameNode = managerCountryNode.get("name");

                                            countryAwayManager.setName(managerCountryNameNode.asText());

                                            awayManager.setNameCountry(countryAwayManager);

                                            awayTeam.getManager().add(awayManager);

                                            match.setAwayTeam(awayTeam);

                                        }

                                    }
                                }
                            }
                        }
                    }

                    if (matchJsonNode.has("competition_stage"))
                    {
                        final JsonNode stageNode = matchJsonNode.get("competition_stage");

                        if(stageNode.has("name"))
                        {

                            final JsonNode stageNameNode = stageNode.get("name");

                            stage.setStageName(stageNameNode.asText());

                            match.setStage(stage);
                        }
                    }

                    matchList.add(match);
                }
            }
            matchFilter(matchList);
        }
        catch (IOException ioException)
        {
            LOGGER.error("Error mientras se trataba de leer el fichero " + nombreFichero , ioException);
            throw new MatchException("Error mientras se trataba de leer el fichero " + nombreFichero , ioException);

        }
    }

    private static void matchFilter(List<Match>matchList) throws MatchException
    {
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        String nombreFicheroSalida = "src/main/java/ies/jms/tr25/partidos.txt";
        try
        {
            fileWriter = new FileWriter(nombreFicheroSalida);
            printWriter = new PrintWriter(fileWriter);

            printWriter.println("EQUIPOS QUE JUGARON LA FINAL EUROCOPA 2020");
            printWriter.println("=======================================");

            for(Match match : matchList)
            {
                if(match.getStage().getStageName().equals("Final") && match.getSeason().getSeasonName().equals("2020") )
                {
                    printWriter.println(match.getHomeTeam().getTeamName()+" VS "+ match.getAwayTeam().getTeamName());

                }
            }

            printWriter.println();
            printWriter.println(" NACIONALIDAD ENTRENADOR NO IGUAL A LA NACIONALIDAD DEL EQUIPO ");
            printWriter.println("=====================================================================");

            Map<Integer,Team> teamList = new HashMap();

            for(Match match : matchList)
            {
                for(Manager manager : match.getHomeTeam().getManager())
                {
                    if(!match.getHomeTeam().getTeamCountry().getName().equals(manager.getNameCountry().getName()))
                    {
                        if(!teamList.containsKey(manager.getId()))
                        {
                            teamList.put(manager.getId(),match.getHomeTeam());

                        }
                    }
                }

                for(Manager manager : match.getAwayTeam().getManager())
                {
                    if(!match.getAwayTeam().getTeamCountry().getName().equals(manager.getNameCountry().getName()))
                    {
                        if(!teamList.containsKey(manager.getId()))
                        {
                            teamList.put(manager.getId(),match.getAwayTeam());

                        }
                    }
                }
            }

            for(Map.Entry<Integer,Team> team : teamList.entrySet()){

                printWriter.println(" Equipo: " + team.getValue().getTeamName() + " Nacionalidad Equipos: " + team.getValue().getTeamCountry().getName() + " Entrenador: "
                        + team.getValue().getManager().get(0).getName() + " Nacionalidad Emtrenador: " +team.getValue().getManager().get(0).getNameCountry().getName());
            }

            printWriter.println();
            printWriter.println("PARTIDOS DISPUTADOS DESPUES DEL 01-07-2021");
            printWriter.println("==========================================");

            for(Match match : matchList)
            {
                Date matchDate = null;
                Date dateFilter = null;

                try
                {
                    matchDate = new SimpleDateFormat("yyyy-MM-dd").parse(match.getDate());
                    dateFilter = new SimpleDateFormat("yyyy-MM-dd").parse("2021-07-01");


                }
                catch (ParseException parseException)
                {
                    LOGGER.error("No se pudo parsear la fecha " ,  parseException);
                    throw new MatchException("No se pudo parsear la fecha  " , parseException);
                }
                if(matchDate!=null && dateFilter!=null)
                {

                    if(matchDate.after(dateFilter) )
                    {
                        printWriter.println("Partido: "+ match.getHomeTeam().getTeamName() + " VS " + match.getAwayTeam().getTeamName() + " Del " + match.getDate() );

                    }
                }
                else
                {
                    LOGGER.error("No se pudo parsear la fecha " );
                    throw new MatchException("No se pudo parsear la fecha ");

                }
            }
            printWriter.flush();

        }
        catch (IOException fileNotFoundException)
        {
            LOGGER.error("No se pudo encontrar el fichero " + nombreFicheroSalida , fileNotFoundException);
            throw new MatchException("No se pudo encontrar el fichero " + nombreFicheroSalida , fileNotFoundException);

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
                    throw new MatchException("No se pudo cerrar FileWriter ", ioException);
                }
            }

            if (printWriter != null)
            {
                printWriter.close();
            }
        }
    }
}
