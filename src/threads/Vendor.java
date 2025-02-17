package threads;

import core.TicketPool;
import logging.Logger;

import java.util.concurrent.CountDownLatch;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;
    private final CountDownLatch latch;  // CountDownLatch to signal customers

    public Vendor(TicketPool ticketPool, int ticketReleaseRate, CountDownLatch latch) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.latch = latch;
    }

    @Override
    public void run() {
        for (int i = 0; i < ticketReleaseRate; i++) {
            // Generate a unique ticket
            String ticket = "Ticket-" + System.nanoTime();
            ticketPool.addTickets(ticket);
            Logger.log("Vendor added: " + ticket);

            // Signal that a ticket is available for customers to retrieve
            latch.countDown();  

            try {
                Thread.sleep(500);  // Simulate delay between ticket releases
            } catch (InterruptedException e) {
                Logger.log("Vendor interrupted.");
                Thread.currentThread().interrupt();
            }
        }
    }
}
