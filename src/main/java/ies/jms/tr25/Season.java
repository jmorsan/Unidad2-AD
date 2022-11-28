package ies.jms.tr25;

public class Season
{
    private String name;

    public Season()
    {
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
    public String toString()
    {
        return "Season{" +
                "seasonName='" + name + '\'' +
                '}';
    }
}
