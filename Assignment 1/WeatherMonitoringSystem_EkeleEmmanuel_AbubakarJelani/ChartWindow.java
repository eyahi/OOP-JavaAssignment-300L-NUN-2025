import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChartWindow extends JFrame {

    public ChartWindow(String title, List<BasicWeatherData> records) {
        super(title);

        // Prepare dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();//field
        int index = 1;//field
        for (BasicWeatherData data : records) {
            dataset.addValue(data.getTemperature(), "Temperature", String.valueOf(index));
            dataset.addValue(data.getHumidity(), "Humidity", String.valueOf(index));
            dataset.addValue(data.getPressure(), "Pressure", String.valueOf(index));
            index++;
        }

        // Create chart
        JFreeChart chart = ChartFactory.createLineChart(
                "Weather Data Over Time",
                "Record Index",
                "Value",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Create chart panel with padding
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 400));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(chartPanel);

        // Final settings
        pack();
        setLocationRelativeTo(null); // center on screen
        setVisible(true);
    }
}
