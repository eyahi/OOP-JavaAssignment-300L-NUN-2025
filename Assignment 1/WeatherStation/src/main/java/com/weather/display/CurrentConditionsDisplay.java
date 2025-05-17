/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.weather.display;

/**
 *
 * @author abdul
 */

import com.weather.model.WeatherData;

public class CurrentConditionsDisplay implements DisplayElement {
    @Override
    public void display(WeatherData data) {
        System.out.println("Console Display:");
        System.out.println(data.toString());
        System.out.println("----------------------------------");
    }
}

