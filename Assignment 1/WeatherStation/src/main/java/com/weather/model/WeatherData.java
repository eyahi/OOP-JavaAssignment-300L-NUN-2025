/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.weather.model;

/**
 *
 * @author abdul
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherData {
    private float temperature;
    private float humidity;
    private float pressure;
    private LocalDateTime timestamp;

    public WeatherData(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.timestamp = LocalDateTime.now();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("Current Weather: Temperature=%.2f°C, Humidity=%.2f%%, Pressure=%.2f hPa, Time=%s",
                temperature, humidity, pressure,
                timestamp.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
}

