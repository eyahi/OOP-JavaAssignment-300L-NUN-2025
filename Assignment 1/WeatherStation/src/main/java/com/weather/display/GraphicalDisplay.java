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
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class GraphicalDisplay extends JFrame implements DisplayElement {

    private final TimeSeries tempSeries;
    private final TimeSeries humiditySeries;
    private final TimeSeries pressureSeries;

    private final JLabel tempLabel;
    private final JLabel humidityLabel;
    private final JLabel pressureLabel;

    public GraphicalDisplay() {
        setTitle("Weather Monitoring Chart");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create time series for each data type
        tempSeries = new TimeSeries("Temperature (°C)");
        humiditySeries = new TimeSeries("Humidity (%)");
        pressureSeries = new TimeSeries("Pressure (hPa)");

        // Combine them into a dataset
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(tempSeries);
        dataset.addSeries(humiditySeries);
        dataset.addSeries(pressureSeries);

        // Create chart
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Real-Time Weather Data",
                "Time",
                "Values",
                dataset,
                true,
                true,
                false
        );

        // Customize chart appearance
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
        plot.setRenderer(renderer);

        // Setup time axis formatting
        DateAxis domainAxis = (DateAxis) plot.getDomainAxis();
        domainAxis.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss"));
        domainAxis.setTickUnit(new DateTickUnit(DateTickUnitType.MINUTE, 1));

        // Add chart to panel
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);

        // Info Panel for live updates
        JPanel infoPanel = new JPanel(new GridLayout(1, 3));
        tempLabel = new JLabel("Temperature: -- °C");
        humidityLabel = new JLabel("Humidity: -- %");
        pressureLabel = new JLabel("Pressure: -- hPa");

        infoPanel.add(tempLabel);
        infoPanel.add(humidityLabel);
        infoPanel.add(pressureLabel);

        add(infoPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public void display(WeatherData data) {
        // Add new data points to the time series
        Millisecond now = new Millisecond();
        tempSeries.addOrUpdate(now, data.getTemperature());
        humiditySeries.addOrUpdate(now, data.getHumidity());
        pressureSeries.addOrUpdate(now, data.getPressure());

        // Update labels
        tempLabel.setText(String.format("Temperature: %.2f °C", data.getTemperature()));
        humidityLabel.setText(String.format("Humidity: %.2f %%", data.getHumidity()));
        pressureLabel.setText(String.format("Pressure: %.2f hPa", data.getPressure()));
    }
}




