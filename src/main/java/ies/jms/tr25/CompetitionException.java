package ies.jms.tr25;

public class CompetitionException extends Exception
{
    public CompetitionException()
    {
        super();
    }

    public CompetitionException(String message)
    {
        super(message);
    }

    public CompetitionException(String message, Throwable exception)
    {
        super(message,exception);
    }
}
