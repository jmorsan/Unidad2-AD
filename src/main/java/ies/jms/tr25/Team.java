package ies.jms.tr25;

import java.util.ArrayList;
import java.util.List;

public class Team
{
    private int id;
    private String name;

    private List<Manager> manager;

    private Country country;

    public Team()
    {
        this.id = -1;
        this.name = "";
        this.manager = new ArrayList<Manager>();
        this.country = new Country();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Country getCountry()
    {
        return country;
    }

    public void setCountry(Country country)
    {
        this.country = country;
    }

    public List<Manager> getManager() {
        return manager;
    }

    public void setManager(List<Manager> manager) {
        this.manager = manager;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamID=" + id +
                ", teamName='" + name + '\'' +
                ", manager=" + manager +
                ", teamCountry=" + country +
                '}';
    }
}
