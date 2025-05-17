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

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WeatherGraph extends JPanel {
    private final List<Float> temperatureHistory = new ArrayList<>();
    private final List<Float> humidityHistory = new ArrayList<>();
    private final List<Float> pressureHistory = new ArrayList<>();

    public WeatherGraph() {
        setPreferredSize(new Dimension(600, 300));
    }

    public void addData(WeatherData data) {
        if (temperatureHistory.size() >= 30) {
            temperatureHistory.remove(0);
            humidityHistory.remove(0);
            pressureHistory.remove(0);
        }

        temperatureHistory.add(data.getTemperature());
        humidityHistory.add(data.getHumidity());
        pressureHistory.add(data.getPressure());

        repaint(); // trigger re-draw
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGraph((Graphics2D) g);
    }

    private void drawGraph(Graphics2D g) {
        int width = getWidth();
        int height = getHeight();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.BLACK);
        g.drawString("Weather Data Over Time", 10, 20);

        drawLineGraph(g, temperatureHistory, Color.RED, "Temp (°C)", height);
        drawLineGraph(g, humidityHistory, Color.BLUE, "Humidity (%)", height);
        drawLineGraph(g, pressureHistory, Color.GREEN, "Pressure (hPa)", height);
    }

    private void drawLineGraph(Graphics2D g, List<Float> data, Color color, String label, int height) {
        g.setColor(color);
        int size = data.size();
        int maxPoints = 30;
        float scaleY = height / 100.0f;
        int prevX = 0, prevY = height;

        for (int i = 0; i < size; i++) {
            int x = i * getWidth() / maxPoints;
            int y = height - Math.round(data.get(i) * scaleY);
            if (i > 0) {
                g.drawLine(prevX, prevY, x, y);
            }
            prevX = x;
            prevY = y;
        }

        g.drawString(label, 10, height - (int)(data.get(size - 1) * scaleY));
    }
}

