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

public interface Sensor {
    WeatherData fetchData() throws Exception;
}
