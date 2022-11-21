package ies.jms.tr21;



public class Competition {
    private int competitionId;
    private int seasonId;
    private String countryName;
    private String competitionName;
    private String competitionGender;
    private boolean competitionYouth;
    private boolean competitionInternational;
    private String seasonName;
    private String matchUpdated;
    private String matchUpdated360;
    private String matchAvailable;
    private String matchAvailable360;

    public Competition(int competitionId, int seasonId, String countryName, String competitionName, String competitionGender, boolean competitionYouth, boolean competitionInternational,
                       String seasonName, String matchUpdated, String matchAvailable360,String matchUpdated360, String matchAvailable ) {
        this.competitionId = competitionId;
        this.seasonId = seasonId;
        this.countryName = countryName;
        this.competitionName = competitionName;
        this.competitionGender = competitionGender;
        this.competitionYouth = competitionYouth;
        this.competitionInternational = competitionInternational;
        this.seasonName = seasonName;
        this.matchUpdated = matchUpdated;
        this.matchUpdated360 = matchUpdated360;
        this.matchAvailable = matchAvailable;
        this.matchAvailable360 = matchAvailable360;
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getCompetitionGender() {
        return competitionGender;
    }

    public void setCompetitionGender(String competitionGender) {
        this.competitionGender = competitionGender;
    }

    public boolean isCompetitionYouth() {
        return competitionYouth;
    }

    public void setCompetitionYouth(boolean competitionYouth) {
        this.competitionYouth = competitionYouth;
    }

    public boolean isCompetitionInternational() {
        return competitionInternational;
    }

    public void setCompetitionInternational(boolean competitionInternational) {
        this.competitionInternational = competitionInternational;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public String getMatchUpdated() {
        return matchUpdated;
    }

    public void setMatchUpdated(String matchUpdated) {
        this.matchUpdated = matchUpdated;
    }

    public String getMatchUpdated360() {
        return matchUpdated360;
    }

    public void setMatchUpdated360(String matchUpdated360) {
        this.matchUpdated360 = matchUpdated360;
    }

    public String getMatchAvailable() {
        return matchAvailable;
    }

    public void setMatchAvailable(String matchAvailable) {
        this.matchAvailable = matchAvailable;
    }

    public String getMatchAvailable360() {
        return matchAvailable360;
    }

    public void setMatchAvailable360(String matchAvailable360) {
        this.matchAvailable360 = matchAvailable360;
    }

    @Override
    public String toString() {
        return
                "ID: " + competitionId +
                ", Season ID: " + seasonId +
                ", Country Name: " + countryName +
                ", Competition Name: " + competitionName +
                ", Competition Gender: " + competitionGender +
                ", Competition Youth: " + competitionYouth +
                ", Competition International: " + competitionInternational +
                ", Season Name: " + seasonName +
                ", Match Updated: " + matchUpdated +
                ", Match Updated 360: " + matchUpdated360 +
                ", Match Available: " + matchAvailable +
                ", Match Available 360: " + matchAvailable360
                ;
    }
}
