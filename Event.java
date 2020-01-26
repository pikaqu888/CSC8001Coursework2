/**
 * A class which contains the events of the sports.
 */
public class Event implements Comparable<Event>
{
    private String eventName;
    private int numberOfTicketLeft;

    public Event(String eventName, int numberOfTicketLeft)
    {
        this.eventName = eventName;
        this.numberOfTicketLeft = numberOfTicketLeft;
    }

    public Event(String eventName)
    {
        this.eventName = eventName;
    }

    public String getEventName()
    {
        return eventName;
    }

    public void setEventName(String eventName)
    {
        this.eventName = eventName;
    }

    public int getNumberOfTicketLeft()
    {
        return numberOfTicketLeft;
    }

    public void setNumberOfTicketLeft(int numberOfTicketLeft)
    {
        this.numberOfTicketLeft = numberOfTicketLeft;
    }

    /**
     * compare the name of the events
     * @param e
     * @return the int type after the comparision, which is <0, =0 and >0
     */
    public int compareTo(Event e)
    {
        int names = eventName.compareTo(e.eventName);
        return names;
    }

    /**
     * Override the toString method
     * @return the event name and the tickets left
     */
    public String toString()
    {
        return ("The event name is: "  + eventName + "," + " with " + numberOfTicketLeft + " tickets left!\n");
    }

    /**
     * Override the equals method
     * @param obj
     * @return type boolean, if the event name is the same, return true, vise versa
     */
    public boolean equals(Object obj)
    {
        if (this.eventName.equals(((Event)obj).getEventName()))
        {
            return true;
        }
        return false;
    }

}