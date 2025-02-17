package main;

import config.Configuration;
import core.TicketPool;
import logging.Logger;
import threads.Customer;
import threads.Vendor;
import ui.CommandLineInterface;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        // Load system configuration from CLI
        Configuration config = CommandLineInterface.configureSystem();
        
        // Create a TicketPool with the max ticket capacity from the configuration
        TicketPool ticketPool = new TicketPool(config.getMaxTicketCapacity());
        
        // Create a CountDownLatch for each customer to wait until the vendor adds at least one ticket
        CountDownLatch latch = new CountDownLatch(1);  // Ensure customers wait until vendor adds tickets
        
        // Create and start vendor threads based on the ticket release rate
        Thread[] vendorThreads = new Thread[config.getTicketReleaseRate()];
        for (int i = 0; i < config.getTicketReleaseRate(); i++) {
            Vendor vendor = new Vendor(ticketPool, config.getTicketReleaseRate(), latch);
            vendorThreads[i] = new Thread(vendor);
            vendorThreads[i].start();  // Start vendor thread
        }
        
        // Create and start customer threads based on the customer retrieval rate
        Thread[] customerThreads = new Thread[config.getCustomerRetrievalRate()];
        for (int i = 0; i < config.getCustomerRetrievalRate(); i++) {
            Customer customer = new Customer(ticketPool, latch);  // Pass latch to customer
            customerThreads[i] = new Thread(customer);
            customerThreads[i].start();  // Start customer thread
        }

        // Wait for all vendor threads to finish
        try {
            for (Thread vendorThread : vendorThreads) {
                vendorThread.join();  // Main thread waits for each vendor thread to finish
            }

            // Wait for all customer threads to finish
            for (Thread customerThread : customerThreads) {
                customerThread.join();  // Main thread waits for each customer thread to finish
            }
        } catch (InterruptedException e) {
            Logger.log("Main thread interrupted.");
        }

        Logger.log("System terminated.");
    }
}
