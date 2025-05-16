

package com.weather.display;

import com.weather.model.WeatherData;

import javax.swing.*;
import java.awt.*;

public class GUIDisplay implements DisplayElement {

    private JFrame frame;
    private JLabel tempLabel;
    private JLabel humidityLabel;
    private JLabel pressureLabel;

    public GUIDisplay() {
        frame = new JFrame("Weather Monitoring GUI");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1));

        tempLabel = new JLabel("Temperature: ");
        humidityLabel = new JLabel("Humidity: ");
        pressureLabel = new JLabel("Pressure: ");

        frame.add(tempLabel);
        frame.add(humidityLabel);
        frame.add(pressureLabel);

        frame.setVisible(true);
    }

    @Override
    public void display(WeatherData data) {
        tempLabel.setText("Temperature: " + data.getTemperature() + "°C");
        humidityLabel.setText("Humidity: " + data.getHumidity() + "%");
        pressureLabel.setText("Pressure: " + data.getPressure() + " hPa");
    }
}