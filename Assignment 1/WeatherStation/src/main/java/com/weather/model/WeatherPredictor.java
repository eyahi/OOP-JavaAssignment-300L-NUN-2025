/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.weather.model;

/**
 *
 * @author abdul
 */

import java.util.LinkedList;
import java.util.Queue;

public class WeatherPredictor {
    private Queue<Float> temperatureHistory = new LinkedList<>();
    private static final int MAX_SIZE = 5;

    public void addReading(float temperature) {
        if (temperatureHistory.size() >= MAX_SIZE) {
            temperatureHistory.poll();
        }
        temperatureHistory.offer(temperature);
    }

    public float predictNextTemperature() {
        if (temperatureHistory.isEmpty()) return 0;
        float sum = 0;
        for (float temp : temperatureHistory) sum += temp;
        return sum / temperatureHistory.size();
    }
}

