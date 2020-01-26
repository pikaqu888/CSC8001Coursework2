import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A class which contains information for the clients.
 */
public class Client implements Comparable<Client>
{
    private String firstName;
    private String lastName;
    private Map<String, Integer> eventTicket;
    public static final int Max_Event_Buy = 3;


    public Client(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eventTicket = new HashMap<>();
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Map<String, Integer> getEventTicket()
    {
        return eventTicket;
    }

    public void setEventTicket(HashMap<String, Integer> eventTicket)
    {
        this.eventTicket = eventTicket;
    }

    /**
     * compares the name of the clients lexicographically, first by last name,
     * if the last name is the same, then with the first name
     * @param c
     * @return the int type after the comparision, which is <0, =0 and >0
     */
    public int compareTo(Client c)
    {
        int lnCmp = lastName.compareTo(c.lastName);
        if (lnCmp != 0)
        {
            return lnCmp;
        }
        int fnCmp = firstName.compareTo(c.firstName);
        if (fnCmp != 0)
        {
            return fnCmp;
        }
        return 0;
    }

    /**
     * Override the toString method
     * @return the information about the clients
     */
    public String toString()
    {
        if (eventTicket.isEmpty())
        {
            return ("The client first name is " + firstName + "," +  "last name is " + lastName + "; this client have not bought ticket!\n");
        }
        String information = "The client first name is " + firstName + "," + "last name is " + lastName + ";" + " the chosen event is/are: \n";
        Set<Map.Entry<String, Integer>> entryList = eventTicket.entrySet();
        for (Map.Entry<String, Integer> entry: entryList)
        {
            information = information + (entry.getKey() + "," + " totally buy " + entry.getValue() + " tickets.\n");
        }
        return information;
    }

    /**
     * Override the equals method
     * @param obj
     * @return type boolean, if the first name is equal and the last name is equal, return true, vice versa
     */
    public boolean equals(Object obj)
    {
        if (this.firstName.equals(((Client)obj).getFirstName()))
        {
            if (this.lastName.equals(((Client)obj).getLastName()))
                return true;
        }
        return false;
    }

}
