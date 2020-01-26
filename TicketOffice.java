/**
 * A class which contains the method for buying tickets and cancelling tickets.
 */
public class TicketOffice
{
    public SortedArrayList<Event> eventList;
    public SortedArrayList<Client> clientList;
    public static String outputInformation = "";

    public TicketOffice(SortedArrayList<Event> eventList, SortedArrayList<Client> clientList)
    {
        this.eventList = eventList;
        this.clientList = clientList;
    }

    public SortedArrayList<Event> getEventList()
    {
        return eventList;
    }

    public SortedArrayList<Client> getClientList()
    {
        return clientList;
    }

    /**
     * The method for buying tickets
     * @param client the client who will buy the ticket entered by the console
     * @param event the event to buy entered by the console
     * @param numberOfTicketsBuy the ticket numbers of the event bought by the client entered in the console
     * @return type String, which shows if the buy process is success or not
     */
    public String buyTicket(Client client, Event event, int numberOfTicketsBuy)
    {
        Event eventBuy = null;
        for (Event event1 : eventList)
        {
            if (event1.equals(event))
            {
                eventBuy = event1;
                break;
            }
        }
        if (eventBuy != null)
        {
            Client clientBuy = null;
            for (Client client1 : clientList)
            {
                if (client1.equals(client))
                {
                    clientBuy = client1;
                    break;
                }
            }
            if (clientBuy != null)
            {
                if (clientBuy.getEventTicket().size() >= Client.Max_Event_Buy)
                {
                    outputInformation = outputInformation + ("\nSorry, the maximum events one client can buy is 3 events! So the event " + eventBuy.getEventName() + " is not possible to buy!\n"
                            + "The data is shown as: "+(clientBuy.toString()));
                    return outputInformation;
                }

                if (eventBuy.getNumberOfTicketLeft() < numberOfTicketsBuy)
                {
                    outputInformation = outputInformation + ("\nSorry, client: " + clientBuy.getFirstName() + ", " + clientBuy.getLastName() +
                            " fail to buy " + eventBuy.getEventName() + " ticket because there is not enough tickets!\n");
                    return outputInformation;
                }

                for (Client client2 : clientList)
                {
                    for (Event event2 : eventList)
                    {
                        if (client2.equals(clientBuy) && event2.equals(eventBuy))
                        {
                            if (client2.getEventTicket().containsKey(event2.getEventName()))
                            {
                                Integer value = client2.getEventTicket().get(event2.getEventName());
                                client2.getEventTicket().put(event2.getEventName(), numberOfTicketsBuy + value);
                            }
                            else
                            {
                                client2.getEventTicket().put(event2.getEventName(), numberOfTicketsBuy);
                            }
                            event2.setNumberOfTicketLeft(event2.getNumberOfTicketLeft() - numberOfTicketsBuy);
                            return "Transaction Success!";
                        }
                    }
                }
            }
            else
            {
                return "Sorry, the client is not a valid client!";
            }
        }
        else
        {
            return "Sorry, the event is not a valid event!";
        }
        return null;
    }

    /**
     * the method for cancelling tickets
     * @param client the client who will cancel the ticket entered by the console
     * @param event the event to cancel entered by the console
     * @param numberOfTicketsCancel the ticket numbers of the event cancelled by the client entered in the console
     * @return type String, which shows if the cancel process is success or not
     */
    public String cancelTicket(Client client, Event event, int numberOfTicketsCancel)
    {
        Event eventCancel = null;
        for (Event event1 : eventList)
        {
            if (event1.equals(event))
            {
                eventCancel = event1;
                break;
            }
        }
        if (eventCancel != null)
        {
            Client clientCancel = null;
            for (Client client1 : clientList)
            {
                if (client1.equals(client))
                {
                    clientCancel = client1;
                    break;
                }
            }
            if (clientCancel != null)
            {
                Integer value = clientCancel.getEventTicket().get(event.getEventName());
                if (value < numberOfTicketsCancel)
                {
                    return "Error, the clients don't have so many tickets!";
                }
                else
                    {
                        for (Client client2 : clientList)
                        {
                            for (Event event2 : eventList)
                            {
                                if (client2.equals(clientCancel) && event2.equals(eventCancel) && client2.getEventTicket().containsKey(event2.getEventName()))
                                {
                                    Integer value1 = client2.getEventTicket().get(event2.getEventName());
                                    client2.getEventTicket().put(event2.getEventName(), value1 - numberOfTicketsCancel);
                                    event2.setNumberOfTicketLeft(event2.getNumberOfTicketLeft() + numberOfTicketsCancel);
                                    if (value1 == numberOfTicketsCancel)
                                    {
                                        client2.getEventTicket().remove(event2.getEventName());
                                    }
                                    return "Cancel Success!";
                                }
                            }
                        }
                    }
            }
            else
                {
                    return "Sorry, the client is not a valid client!";
                }
        }
        else
            {
                return "Sorry, the event is not a valid event!";
            }
        return null;
    }
}
