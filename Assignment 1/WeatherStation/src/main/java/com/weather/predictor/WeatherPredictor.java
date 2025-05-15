/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.weather.predictor;

/**
 *
 * @author abdul
 */

import com.weather.model.WeatherData;
import java.util.LinkedList;
import java.util.Queue;

public class WeatherPredictor {
    private final Queue<Float> temperatureHistory;
    private final int HISTORY_SIZE;

    public WeatherPredictor(int historySize) {
        this.HISTORY_SIZE = historySize;
        this.temperatureHistory = new LinkedList<>();
    }

    // Add a new temperature reading and update history
    public void addTemperatureReading(WeatherData data) {
        if (temperatureHistory.size() == HISTORY_SIZE) {
            temperatureHistory.poll(); // Remove the oldest temperature
        }
        temperatureHistory.add(data.getTemperature());
    }

    // Predict the next temperature based on the moving average
    public float predictNextTemperature() {
        if (temperatureHistory.size() < HISTORY_SIZE) {
            throw new IllegalStateException("Not enough data to make a prediction.");
        }

        float sum = 0;
        for (float temp : temperatureHistory) {
            sum += temp;
        }

        return sum / HISTORY_SIZE; // Average of last N readings
    }

    public int getHistorySize() {
        return HISTORY_SIZE;
    }
}

