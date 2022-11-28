package ies.jms.tr25;

public class Stage
{
    private String name;

    public Stage()
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
        return "Stage{" +
                "name='" + name + '\'' +
                '}';
    }
}
