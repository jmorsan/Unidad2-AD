package ies.jms.tr21;

import org.joda.time.DateTime;

public class Competition {
    private int competitionId;
    private int seasonId;
    private String countryName;
    private String competitionName;
    private String competitionGender;
    private boolean competitionYouth;
    private boolean competitionInternational;
    private String seasonName;
    private DateTime matchUpdated;
    private DateTime matchUpdated360;
    private DateTime matchAvailable;
    private DateTime matchAvailable360;

    public Competition(int competitionId, int seasonId, String countryName, String competitionName, String competitionGender, boolean competitionYouth, boolean competitionInternational, String seasonName, DateTime matchUpdated, DateTime matchUpdated360, DateTime matchAvailable, DateTime matchAvailable360) {
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

    public DateTime getMatchUpdated() {
        return matchUpdated;
    }

    public void setMatchUpdated(DateTime matchUpdated) {
        this.matchUpdated = matchUpdated;
    }

    public DateTime getMatchUpdated360() {
        return matchUpdated360;
    }

    public void setMatchUpdated360(DateTime matchUpdated360) {
        this.matchUpdated360 = matchUpdated360;
    }

    public DateTime getMatchAvailable() {
        return matchAvailable;
    }

    public void setMatchAvailable(DateTime matchAvailable) {
        this.matchAvailable = matchAvailable;
    }

    public DateTime getMatchAvailable360() {
        return matchAvailable360;
    }

    public void setMatchAvailable360(DateTime matchAvailable360) {
        this.matchAvailable360 = matchAvailable360;
    }
}
