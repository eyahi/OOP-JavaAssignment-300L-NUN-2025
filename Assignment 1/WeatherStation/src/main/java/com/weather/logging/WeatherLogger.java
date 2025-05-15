/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.weather.logging;

/**
 *
 * @author abdul
 */

import com.weather.model.WeatherData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherLogger {

    private static final String FILE_NAME = "WeatherHistoryLog.txt";

    public static void log(WeatherData data) {
        try {
            String userHome = System.getProperty("user.home");  // Get the user home directory
            String desktopPath = userHome + File.separator + "Desktop";  // Path to Desktop (change to "Documents" if needed)
            File desktopDir = new File(desktopPath);  // Desktop folder reference

            // Debug: Print the intended path
            System.out.println("Attempting to write to: " + desktopDir.getAbsolutePath());

            // Ensure Desktop folder exists
            if (!desktopDir.exists()) {
                boolean created = desktopDir.mkdirs();
                if (!created) {
                    System.err.println("Failed to create Desktop directory!");
                    return;
                }
            }

            // Create the log file
            File logFile = new File(desktopDir, FILE_NAME);
            if (!logFile.exists()) {
                boolean fileCreated = logFile.createNewFile();
                if (!fileCreated) {
                    System.err.println("Failed to create log file!");
                    return;
                }
            }

            // Format the data for logging
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timestamp = LocalDateTime.now().format(formatter);
            String logEntry = String.format("[%s] Temperature: %.2f°C, Humidity: %.2f%%, Pressure: %.2f hPa",
                    timestamp, data.getTemperature(), data.getHumidity(), data.getPressure());

            // Write to file
            try (FileWriter writer = new FileWriter(logFile, true)) {
                writer.write(logEntry + System.lineSeparator());
            }

        } catch (IOException e) {
            System.err.println("Error logging weather data:");
            e.printStackTrace();  // More informative than just printing message
        }
    }
}






