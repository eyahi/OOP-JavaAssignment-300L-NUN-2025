

package com.weather.sensor;

import com.weather.model.WeatherData;
import java.util.Random;

public class RandomWeatherSensor implements Sensor {
    private Random random = new Random();

    @Override
    public WeatherData fetchData() {
        float temperature = 15 + random.nextFloat() * 25; 
        float humidity = 40 + random.nextFloat() * 50;   
        float pressure = 980 + random.nextFloat() * 50;   

        return new WeatherData(temperature, humidity, pressure);
    }
}