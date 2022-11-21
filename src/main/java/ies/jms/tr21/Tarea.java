package ies.jms.tr21;


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
import java.util.List;

public class Tarea
{

    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args)
    {
        List<Competition> competitionList = new ArrayList<>();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();


        try
        {

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File("src/main/java/ies/jms/tr21/competitions.xml"));
            Element rootElement = document.getDocumentElement();
            NodeList nodeListCompetition = rootElement.getElementsByTagName("competition");
            for(int l = 0; l < nodeListCompetition.getLength(); l++)
            {
                Element nodeCompetition = (Element) nodeListCompetition.item(l);

                int competitionId = Integer.parseInt(nodeCompetition.getElementsByTagName("competition_id").item(0).getTextContent());
                int seasonId = Integer.parseInt(nodeCompetition.getElementsByTagName("season_id").item(0).getTextContent());
                String countryName = nodeCompetition.getElementsByTagName("country_name").item(0).getTextContent();
                String competitionName = nodeCompetition.getElementsByTagName("competition_name").item(0).getTextContent();
                String competitionGender = nodeCompetition.getElementsByTagName("competition_gender").item(0).getTextContent();
                boolean competitionYouth = Boolean.parseBoolean(nodeCompetition.getElementsByTagName("competition_youth").item(0).getTextContent());
                boolean competitionInternational = Boolean.parseBoolean(nodeCompetition.getElementsByTagName("competition_international").item(0).getTextContent());
                String seasonName = nodeCompetition.getElementsByTagName("season_name").item(0).getTextContent();
                String matchUpdated = nodeCompetition.getElementsByTagName("match_updated").item(0).getTextContent();
                String matchUpdated360 = nodeCompetition.getElementsByTagName("match_updated_360").item(0).getTextContent();
                String matchAvailable = nodeCompetition.getElementsByTagName("match_available").item(0).getTextContent();
                String matchAvailable360;

                if(nodeCompetition.getElementsByTagName("match_available_360").item(0).getTextContent().equals(' '))
                {
                    matchAvailable360 = null;
                }
                else
                {
                    matchAvailable360 = nodeCompetition.getElementsByTagName("match_available_360").item(0).getTextContent();
                }


                Competition competition = new Competition(competitionId, seasonId, countryName, competitionName, competitionGender, competitionYouth, competitionInternational,
                        seasonName, matchUpdated, matchAvailable360, matchUpdated360, matchAvailable);

                competitionList.add(competition);


            }

            CompetitionFilter(competitionList);





        }
        catch (ParserConfigurationException | SAXException | IOException xmlException)
        {
            xmlException.printStackTrace();

        }
    }

    private static void CompetitionFilter(List<Competition>competitionList)
    {
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;

        try {
            fileWriter = new FileWriter("src/main/java/ies/jms/tr21/Competiciones.txt");
            printWriter = new PrintWriter(fileWriter);

            printWriter.println("COMPETICIONES FEMENINAS");
            printWriter.println("=======================================");
            for(Competition competition : competitionList){
                if(competition.getCompetitionGender().equals("female"))
                {
                    printWriter.println(competition);
                }
            }

            printWriter.println();
            printWriter.println("COMPETICIONES EN EUROPA E INTERNACIONALES");
            printWriter.println("=======================================");
            for(Competition competition : competitionList)
            {
                if(competition.getCountryName().equals("Europe") && competition.isCompetitionInternational())
                {
                    printWriter.println(competition);
                }
            }

            printWriter.println();
            printWriter.println("COMPETICIONES CON PARTIDOS DISPONIBLES");
            printWriter.println("=======================================");
            for(Competition competition : competitionList)
            {
                if(competition.getMatchAvailable360()!= "")
                {
                    printWriter.println(competition);
                }
            }


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
}
