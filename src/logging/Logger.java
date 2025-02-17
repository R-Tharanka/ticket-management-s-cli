//<Logger.java code here>

package logging;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {
    private static final String LOG_FILE = "resources/logs.txt";

    public static void log(String message) {
        String timeStampedMessage = LocalDateTime.now() + ": " + message;
        System.out.println(timeStampedMessage);  // Print to console

        // Log to a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(timeStampedMessage);  // Write message to file
            writer.newLine();  // Add a new line after the log entry
        } catch (IOException e) {
            e.printStackTrace();  // Handle file writing errors
        }
    }
}
