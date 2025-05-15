import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class WeatherAppGUI extends JFrame {
    private JTextField tempField, humidityField, pressureField;     //attribute
    private JTextArea outputArea;       //attrubute
    private WeatherStation station;

    private void showChart() {
        new ChartWindow("Weather Charts", station.getAllRecords());
    }

    public WeatherAppGUI() //method
        {
        station = new WeatherStation();

        setTitle("Weather Monitoring System");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Weather Icon Panel
        try {
            File imageFile = new File("images/cloudy.png");
            BufferedImage originalImage = ImageIO.read(imageFile);
            Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon weatherIcon = new ImageIcon(scaledImage);
            JLabel iconLabel = new JLabel(weatherIcon);
            iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
            add(iconLabel, BorderLayout.NORTH);
        } catch (Exception e) {
            System.out.println("Could not load image: " + e.getMessage());
        }

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Weather Data"));

        inputPanel.add(new JLabel("Temperature (°C):"));
        tempField = new JTextField();
        inputPanel.add(tempField);

        inputPanel.add(new JLabel("Humidity (%):"));
        humidityField = new JTextField();
        inputPanel.add(humidityField);

        inputPanel.add(new JLabel("Pressure (hPa):"));
        pressureField = new JTextField();
        inputPanel.add(pressureField);

        JButton addButton = new JButton("Add Record");
        inputPanel.add(new JLabel());  // empty cell to align button right
        inputPanel.add(addButton);


        add(inputPanel, BorderLayout.CENTER);

        // Output Area
        outputArea = new JTextArea(8, 30);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.EAST);

        // Bottom Panel with Buttons
        JPanel bottomPanel = new JPanel();
        JButton viewAllBtn = new JButton("View All Records");
        JButton viewAvgBtn = new JButton("View Averages");
        JButton viewChartBtn = new JButton("View Chart");

        bottomPanel.add(viewAllBtn);
        bottomPanel.add(viewAvgBtn);
        bottomPanel.add(viewChartBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        // Add Listeners
        addButton.addActionListener(e -> addRecord());
        viewAllBtn.addActionListener(e -> showAllRecords());
        viewAvgBtn.addActionListener(e -> showAverages());
        viewChartBtn.addActionListener(e -> showChart());
    }

    private void addRecord()//method
     {
        try {
            double temp = Double.parseDouble(tempField.getText());
            double humidity = Double.parseDouble(humidityField.getText());
            double pressure = Double.parseDouble(pressureField.getText());

            BasicWeatherData data = new BasicWeatherData(temp, humidity, pressure);
            station.addRecord(data);
            outputArea.setText("Record added successfully!\n");

            tempField.setText("");
            humidityField.setText("");
            pressureField.setText("");
        } catch (NumberFormatException e) {
            outputArea.setText("Invalid input! Please enter numbers only.\n");
        }
    }

    private void showAllRecords() {
        outputArea.setText("Weather Records:\n");
        for (BasicWeatherData data : station.getAllRecords()) {
            outputArea.append("Temp: " + data.getTemperature() + "°C, Humidity: "
                    + data.getHumidity() + "%, Pressure: " + data.getPressure() + " hPa\n");
        }
    }

    private void showAverages() {
        double avgTemp = station.getAverageTemperature();
        double avgHumidity = station.getAverageHumidity();
        double avgPressure = station.getAveragePressure();

        outputArea.setText("Average Stats:\n");
        outputArea.append("Temperature: " + avgTemp + "°C\n");
        outputArea.append("Humidity: " + avgHumidity + "%\n");
        outputArea.append("Pressure: " + avgPressure + " hPa\n");
    }

    public static void main(String[] args) {
        // Show splash screen
        SplashScreen splash = new SplashScreen();
        splash.showSplash(3000); // show for 3 seconds

        // Launch main app
        SwingUtilities.invokeLater(() ->        //makes the code run on swing EDT
         {
            new WeatherAppGUI().setVisible(true); //makes our code visible
        });
    }
}
