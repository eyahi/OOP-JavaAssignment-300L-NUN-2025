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
import com.weather.model.WeatherPredictor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class GUIDisplay extends JFrame implements DisplayElement {
    private JLabel currentLabel;
    private JLabel predictedLabel;
    private JButton historyButton;

    private DefaultCategoryDataset dataset;
    private WeatherPredictor predictor;
    private int readingIndex = 1;

    public GUIDisplay(WeatherPredictor predictor) {
        this.predictor = predictor;

        setTitle("Weather GUI");
        setSize(800, 600);
        setLayout(new BorderLayout());

        currentLabel = new JLabel("Current: ");
        predictedLabel = new JLabel("Predicted Temp: ");

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.add(currentLabel);
        infoPanel.add(predictedLabel);

        // History Button
        historyButton = new JButton("View History");
        historyButton.addActionListener(e -> showHistory());
        infoPanel.add(historyButton);

        add(infoPanel, BorderLayout.NORTH);

        // Dataset & Chart setup
        dataset = new DefaultCategoryDataset();
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Weather Trends", "Reading", "Value",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(lineChart);
        add(chartPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void display(WeatherData data) {
        predictor.addReading(data.getTemperature());
        currentLabel.setText("Current: " + data.toString());
        predictedLabel.setText(String.format("Predicted Temp: %.2f°C", predictor.predictNextTemperature()));

        // Add data to the chart
        dataset.addValue(data.getTemperature(), "Temperature", String.valueOf(readingIndex));
        dataset.addValue(data.getHumidity(), "Humidity", String.valueOf(readingIndex));
        dataset.addValue(data.getPressure(), "Pressure", String.valueOf(readingIndex));
        readingIndex++;
    }

    private void showHistory() {
        JFrame historyFrame = new JFrame("Weather History");
        historyFrame.setSize(600, 400);
        JTextArea historyArea = new JTextArea();
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        historyFrame.add(scrollPane);

        // Read from the log file
        File logFile = new File("weather_history.txt");
        try (Scanner scanner = new Scanner(logFile)) {
            while (scanner.hasNextLine()) {
                historyArea.append(scanner.nextLine() + "\n");
            }
        } catch (IOException e) {
            historyArea.setText("Error reading weather history.");
        }

        historyFrame.setVisible(true);
    }
}


