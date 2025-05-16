

package com.weather.main;

import com.weather.sensor.*;
import com.weather.model.WeatherData;
import com.weather.display.*;  
import com.weather.display.GUIDisplay;  

public class WeatherStation {
    public static void main(String[] args) {
        
        Sensor sensor = new RandomWeatherSensor();
        DisplayElement display = new CurrentConditionsDisplay();
        GUIDisplay guiDisplay = new GUIDisplay();  

        int count = 0;
        while (count < 5) {
            try {
               
                if (count == 2) {
                    throw new Exception("Sensor failure detected!");
                }

               
                WeatherData data = sensor.fetchData();

                
                display.display(data);         
                guiDisplay.display(data);      

            } catch (Exception e) {
                System.out.println("[ERROR] " + e.getMessage());
                System.out.println("Attempting to recover...");
            }

            count++;
            try {
                Thread.sleep(3000); // Wait 3 seconds before next reading
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Weather monitoring stopped.");
    }
}