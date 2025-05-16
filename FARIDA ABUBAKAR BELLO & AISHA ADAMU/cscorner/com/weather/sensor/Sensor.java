

package com.weather.sensor;

import com.weather.model.WeatherData;

public interface Sensor {
    WeatherData fetchData();
}