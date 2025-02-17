//<Configuration.java code here>

package config;

import com.google.gson.Gson;
import java.io.*;

public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    // Default file path constant
    private static final String DEFAULT_FILE_PATH = "resources/config.json";

    // Constructor with validation
    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        if (totalTickets <= 0 || ticketReleaseRate <= 0 || customerRetrievalRate <= 0 || maxTicketCapacity <= 0) {
            throw new IllegalArgumentException("All values must be positive integers.");
        }
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    // Getters
    public int getTotalTickets() { return totalTickets; }
    public int getTicketReleaseRate() { return ticketReleaseRate; }
    public int getCustomerRetrievalRate() { return customerRetrievalRate; }
    public int getMaxTicketCapacity() { return maxTicketCapacity; }

    // Load configuration from file
    public static Configuration loadFromFile(String filePath) throws IOException {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, Configuration.class);
        } catch (FileNotFoundException e) {
            System.out.println("Configuration file not found. Returning null.");
            return null;
        }
    }

    // Save configuration to file
    public void saveToFile(String filePath) throws IOException {
        Gson gson = new Gson();
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(this, writer);
        }
    }

    // Convenience method to load from default file path
    public static Configuration loadDefault() throws IOException {
        return loadFromFile(DEFAULT_FILE_PATH);
    }

    // Convenience method to save to default file path
    public void saveDefault() throws IOException {
        saveToFile(DEFAULT_FILE_PATH);
    }
}
