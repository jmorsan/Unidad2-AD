package ies.jms.tr25;

public class Manager
{

    private int id;
    private String name;
    private Country country;

    public Manager()
    {
        this.id = -1;
        this.name = "";
        this.country = new Country();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Country getCountry()
    {
        return country;
    }

    public void setCountry(Country country)
    {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameCountry=" + country +
                '}';
    }
}
