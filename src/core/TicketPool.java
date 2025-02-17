//<TicketPool.java code here>

package core;

import logging.Logger;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TicketPool {
    private final List<String> tickets = Collections.synchronizedList(new LinkedList<>());
    private final int maxTicketCapacity;
    private int totalTicketsAdded = 0;
    private int totalTicketsRemoved = 0;

    public TicketPool(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    // Method to add tickets (called by vendors)
    public synchronized void addTickets(String ticket) {
        if (tickets.size() < maxTicketCapacity) {
            tickets.add(ticket);
            totalTicketsAdded++;  // Increment the count of added tickets
        } else {
            // Log if the pool is full
            Logger.log("Ticket pool is full. Cannot add more tickets.");
        }
    }

    // Method to remove tickets (called by customers)
    public synchronized String removeTicket() {
        if (tickets.isEmpty()) {
            return null;  // No tickets available
        }
        totalTicketsRemoved++;  // Increment the count of removed tickets
        return tickets.remove(0);
    }

    // Method to get the current ticket count
    public int getTicketCount() {
        return tickets.size();
    }

    // Method to get the total number of tickets added
    public int getTotalTicketsAdded() {
        return totalTicketsAdded;
    }

    // Method to get the total number of tickets removed
    public int getTotalTicketsRemoved() {
        return totalTicketsRemoved;
    }

    // Method to get the maximum ticket capacity
    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }
}
