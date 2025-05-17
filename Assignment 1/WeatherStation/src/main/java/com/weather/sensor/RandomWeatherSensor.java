/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.weather.sensor;

/**
 *
 * @author abdul
 */

import com.weather.model.WeatherData;
import java.util.Random;

public class RandomWeatherSensor implements Sensor {
    private Random random = new Random();
    private boolean isWorking = true;
    private int readCount = 0;

    @Override
    public WeatherData fetchData() throws Exception {
        readCount++;

        if (readCount == 3) { // Simulate sensor failure on 3rd read
            isWorking = false;
            throw new Exception("Sensor failure occurred.");
        }

        if (!isWorking && readCount == 4) { // Recover on 4th read
            isWorking = true;
        }

        if (!isWorking) {
            throw new Exception("Sensor is not operational.");
        }

        float temperature = 15 + random.nextFloat() * 25;
        float humidity = 40 + random.nextFloat() * 50;
        float pressure = 980 + random.nextFloat() * 50;

        return new WeatherData(temperature, humidity, pressure);
    }
}

