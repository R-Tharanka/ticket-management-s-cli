package threads;

import core.TicketPool;
import logging.Logger;

import java.util.concurrent.CountDownLatch;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final CountDownLatch latch;  // Synchronize each customer after vendor adds a ticket

    public Customer(TicketPool ticketPool, CountDownLatch latch) {
        this.ticketPool = ticketPool;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();  // Wait for the vendor to add at least one ticket

            // Try to remove a ticket
            String ticket = ticketPool.removeTicket();
            if (ticket != null) {
                Logger.log("Customer retrieved: " + ticket);
            } else {
                Logger.log("Customer found no tickets available.");
            }
        } catch (InterruptedException e) {
            Logger.log("Customer interrupted.");
            Thread.currentThread().interrupt();
        }
    }
}
