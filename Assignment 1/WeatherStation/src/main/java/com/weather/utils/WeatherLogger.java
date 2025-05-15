/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.weather.utils;

/**
 *
 * @author abdul
 */

import com.weather.model.WeatherData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherLogger {
    private static final String FILE_NAME = "weather_history.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Logs the current weather data with a timestamp into a file.
     * 
     * @param data The WeatherData to log
     */
    public static void log(WeatherData data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            String timestamp = FORMATTER.format(LocalDateTime.now());
            String logEntry = String.format("[%s] Temp: %.2f°C, Humidity: %.2f%%, Pressure: %.2f hPa",
                    timestamp,
                    data.getTemperature(),
                    data.getHumidity(),
                    data.getPressure()
            );

            writer.write(logEntry);
            writer.newLine(); // adds a new line
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}


