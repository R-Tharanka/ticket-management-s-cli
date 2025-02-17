package threads;

import core.TicketPool;
import logging.Logger;

public class TicketStatisticsReporter implements Runnable {
    private final TicketPool ticketPool;

    public TicketStatisticsReporter(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Wait for 5 seconds before printing the report
                Thread.sleep(5000);

                // Periodic statistics reporting
                Logger.log("Ticket Pool Statistics:");
                Logger.log("Total tickets added: " + ticketPool.getTotalTicketsAdded());
                Logger.log("Total tickets removed: " + ticketPool.getTotalTicketsRemoved());
                Logger.log("Current tickets in pool: " + ticketPool.getTicketCount());

            } catch (InterruptedException e) {
                Logger.log("Statistics reporting interrupted.");
                Thread.currentThread().interrupt();
                break;  // Stop the thread if interrupted
            }
        }
    }
}
