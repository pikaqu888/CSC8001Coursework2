import java.io.*;
import java.util.*;

/**
 * The main class, with the main method that controls the whole project.
 */
public class Menu
{
    static SortedArrayList<Event> listOfEvent = new SortedArrayList<>();
    static SortedArrayList<Client> listOfClient = new SortedArrayList<>();
    static TicketOffice ticketOffice = new TicketOffice(listOfEvent, listOfClient);

    public static void main(String[] args) throws IOException
    {
        readInformation();
        while(true)
        {
            System.out.println("f - to finish running the program.");
            System.out.println("e - to display on the screen the information about all the events.");
            System.out.println("c - to display on the screen the information about all the clients.");
            System.out.println("b - to update the stored data when tickets are bought by one of the registered clients.");
            System.out.println("r - to update the store data when a registered client cancels/returns tickets.");
            System.out.println("----------------------------------------------------------------------------------------");

            Scanner in = new Scanner(System.in);
            String choice = in.nextLine();

            switch (choice)
            {
                case "f":
                    save();
                    System.out.println("Exit to the menu and save the data to the output.");
                    System.exit(0);
                    break;
                case "e":
                    e();
                    break;
                case "c":
                    c();
                    break;
                case "b":
                    b();
                    break;
                case "r":
                    r();
                    break;
                default:
                    System.out.println("Invalid input, please try it again.");

            }
        }
    }

    /**
     * The method to read the file information
     * @throws IOException
     */
    public static void readInformation() throws IOException
    {
        FileReader in = new FileReader("input.txt");
        BufferedReader reader = new BufferedReader(in);
        String line = reader.readLine();
        int eventNumbers = Integer.parseInt(line);
        for (int i = 0; i < eventNumbers; i++)
        {
                String eventName = reader.readLine();
                int numOfTicketLeft = Integer.parseInt(reader.readLine());
                Event event = new Event(eventName, numOfTicketLeft);
                ticketOffice.eventList.insertSorted(event);
        }

        int clientNumbers = Integer.parseInt(reader.readLine());
        for (int i = 0; i < clientNumbers; i++)
        {
            String clientName = reader.readLine();
            String[] split = clientName.split("\\s+");
            Client client = new Client(split[0], split[1]);
            ticketOffice.clientList.insertSorted(client);
        }

        reader.close();
    }

    /**
     * The method to write the information in a file
     * @throws IOException
     */
    public static void save() throws IOException
    {
        FileWriter out = new FileWriter("output.txt");
        BufferedWriter output = new BufferedWriter(out);
        output.write(ticketOffice.outputInformation);
        output.newLine();
        for (Client client : ticketOffice.clientList)
        {
            output.write(client.toString());
        }
        output.newLine();
        for (Event event : ticketOffice.eventList)
        {
            out.write(event.toString());
        }

        output.close();
    }

    public static void e() //display all the event information
    {
        for (Event event : ticketOffice.getEventList())
        {
            System.out.println(event.toString());
        }
    }

    public static void c() //display all the client information
    {
        for (Client client : ticketOffice.getClientList())
        {
            System.out.println(client.toString());
        }
    }

    public static void b() //update the store data when buy tickets
    {
        Scanner in = new Scanner(System.in);
        try
        {
            System.out.println("Please enter the event you want to buy(capitalize the first letter):");
            String eventName = in.next();

            System.out.println("Please enter the first name of the client:");
            String firstName = in.next();

            System.out.println("Please enter the last name of the client:");
            String lastName = in.next();

            System.out.println("Please enter the number of tickets to buy:");
            int numberOfTicketsBuy = in.nextInt();
            if (numberOfTicketsBuy <= 0)
            {
                System.out.println("Wrong input, try it again!");
            }
            else
            {
                Client client = new Client(firstName, lastName);
                Event event = new Event(eventName);

                String ticketInformation = ticketOffice.buyTicket(client, event, numberOfTicketsBuy);
                System.out.println(ticketInformation);
            }
        }
        catch (Exception e)
        {
            System.out.println("The input data is wrong, please try again!");
        }
    }

    public static void r() //update the store data cancel/return tickets
    {
        Scanner in = new Scanner(System.in);
        try
        {
            System.out.println("Please enter the event you want to cancel/return(capitalize the first letter):");
            String eventName = in.next();

            System.out.println("Please enter the first name of the client:");
            String firstName = in.next();

            System.out.println("Please enter the last name of the client:");
            String lastName = in.next();

            System.out.println("Please enter the number of tickets to cancel/return:");
            Integer numberOfTicketsCancel = in.nextInt();
            if (numberOfTicketsCancel <= 0)
            {
                System.out.println("Wrong input, try it again!");
            }
            else
            {
                Client client = new Client(firstName, lastName);
                Event event = new Event(eventName);
                String ticketInformation = ticketOffice.cancelTicket(client, event, numberOfTicketsCancel);
                System.out.println(ticketInformation);
            }
        }
        catch (Exception e)
        {
            System.out.println("The input data is wrong, please try again!");
        }
    }
}
