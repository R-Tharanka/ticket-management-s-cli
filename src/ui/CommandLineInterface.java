// CommandLineInterface.java

package ui;

import config.Configuration;
import logging.Logger;

import java.io.IOException;
import java.util.Scanner;

public class CommandLineInterface {

    public static Configuration configureSystem() {
        Logger.log("Starting system configuration...");

        try (Scanner scanner = new Scanner(System.in)) {
            // Prompt user inputs
            int totalTickets = getInput(scanner, "Enter Total Tickets: ");
            int ticketReleaseRate = getInput(scanner, "Enter Ticket Release Rate: ");
            int customerRetrievalRate = getInput(scanner, "Enter Customer Retrieval Rate: ");
            int maxTicketCapacity = getInput(scanner, "Enter Max Ticket Capacity: ");

            Logger.log("System configured successfully.");

            // Create a new configuration object
            Configuration config = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);

            // Save the configuration to a JSON file
            try {
                config.saveToFile("resources/config.json");
                Logger.log("Configuration saved successfully.");
            } catch (IOException e) {
                Logger.log("Error saving configuration: " + e.getMessage());
            }

            return config;
        }
    }

    public static Configuration loadConfiguration() {
        try {
            return Configuration.loadFromFile("resources/config.json");
        } catch (IOException e) {
            Logger.log("Error loading configuration: " + e.getMessage());
            return null; // Return null if loading fails
        }
    }

    private static int getInput(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt); // Prompt the user
            try {
                value = Integer.parseInt(scanner.nextLine()); // Parse input
                if (value > 0) {
                    return value; // Valid input
                } else {
                    System.out.println("Value must be positive. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number."); // Handle invalid input
            }
        }
    }
}
