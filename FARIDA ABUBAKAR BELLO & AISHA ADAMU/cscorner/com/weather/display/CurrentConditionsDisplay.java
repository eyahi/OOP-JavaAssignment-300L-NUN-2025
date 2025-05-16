package com.weather.display;



import com.weather.model.WeatherData;

public class CurrentConditionsDisplay implements DisplayElement {

    @Override
    public void display(WeatherData data) {
        System.out.println("Current Conditions: " +
                String.format("%.2f°C, %.2f%%, %.2f hPa",
                        data.getTemperature(),
                        data.getHumidity(),
                        data.getPressure()));
    }
}