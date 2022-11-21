package ies.jms.tr21;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
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

                int competitionId = Integer.parseInt(nodeCompetition.getAttributes().getNamedItem("competition_id").getTextContent());
                int seasonId = Integer.parseInt(nodeCompetition.getAttributes().getNamedItem("season_name").getTextContent());
                String countryName = nodeCompetition.getAttributes().getNamedItem("country_name").getTextContent();
                String competitionName = nodeCompetition.getAttributes().getNamedItem("competition_name").getTextContent();
                String competitionGender = nodeCompetition.getAttributes().getNamedItem("competition_gender").getTextContent();
                boolean competitionYouth = Boolean.parseBoolean(nodeCompetition.getAttributes().getNamedItem("competition_youth").getTextContent());
                boolean competitionInternational = Boolean.parseBoolean(nodeCompetition.getAttributes().getNamedItem("competition_international").getTextContent());
                String seasonName = nodeCompetition.getAttributes().getNamedItem("season_name").getTextContent();
                DateTime matchUpdated = DateTime.parse(nodeCompetition.getAttributes().getNamedItem("match_updated").getTextContent());
                DateTime matchUpdated360 = DateTime.parse(nodeCompetition.getAttributes().getNamedItem("match_updated_360").getTextContent());
                DateTime matchAvailable360 = DateTime.parse(nodeCompetition.getAttributes().getNamedItem("match_available_360").getTextContent());
                DateTime matchAvailable = DateTime.parse(nodeCompetition.getAttributes().getNamedItem("match_available").getTextContent());

                System.out.println(competitionId);
                Competition competition = new Competition(competitionId,seasonId,competitionName,countryName,competitionGender,competitionYouth,
                        competitionInternational,seasonName,matchUpdated,matchUpdated360,matchAvailable360,matchAvailable);

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
            for(Competition competition : competitionList){
                if(competition.getCompetitionGender().equals("female"))
                {
                    printWriter.println(competition);
                }
            }

            printWriter.println("COMPETICIONES EN EUROPA E INTERNACIONALES");
            for(Competition competition : competitionList)
            {
                if(competition.getCountryName().equals("Europe")& competition.isCompetitionInternational())
                {
                    printWriter.println(competition);
                }
            }

            printWriter.println("COMPETICIONES CON PARTIDOS DISPONIBLES");
            for(Competition competition : competitionList)
            {
                if(competition.getMatchAvailable360()==null)
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
