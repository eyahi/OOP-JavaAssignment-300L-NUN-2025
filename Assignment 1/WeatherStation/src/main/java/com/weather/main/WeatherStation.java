/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.weather.main;

/**
 *
 * @author abdul
 */

import com.weather.model.*;
import com.weather.sensor.*;
import com.weather.display.*;
import com.weather.utils.*;

public class WeatherStation {
    public static void main(String[] args) {
        Sensor sensor = new RandomWeatherSensor();
        WeatherPredictor predictor = new WeatherPredictor();

        DisplayElement consoleDisplay = new CurrentConditionsDisplay();
        GUIDisplay guiDisplay = new GUIDisplay(predictor);

        WeatherData currentData = null;

        for (int i = 0; i < 5; i++) {
            try {
                WeatherData newData = sensor.fetchData();
                currentData = newData;
                WeatherLogger.log(newData);
                consoleDisplay.display(newData);
                guiDisplay.display(newData);
            } catch (Exception e) {
                System.out.println("Error fetching data: " + e.getMessage());
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted.");
            }
        }

        System.out.println("Weather monitoring system stopped.");
    }
}

