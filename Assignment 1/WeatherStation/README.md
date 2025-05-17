# Weather Monitoring System

## Group Members:
- **ABDULAZEEZ ALIYU KAWU** - 20221936
- **SAMAILA AMINU DAHUWA** - 20220948

## Project Overview:
The Weather Monitoring System is a Java-based application designed to simulate the collection of weather data, including temperature, humidity, and pressure. The system displays the weather data in real-time through both a console display and a graphical chart. It also logs the weather data to a file for historical analysis.

The system utilizes Object-Oriented Programming (OOP) principles such as encapsulation, abstraction, inheritance, and polymorphism to create a flexible and modular design.

## Features:
1. **Weather Data Class**: 
   - Holds temperature, humidity, and pressure values.
   - Includes timestamps for each reading.
   - Provides getter and setter methods.
   
2. **Sensor Interface & RandomWeatherSensor Class**: 
   - Defines a contract for all sensor types.
   - Simulates the generation of weather data with random values for temperature, humidity, and pressure.
   - Includes data logging to save weather history.
   
3. **Graphical Display**:
   - A time-series chart that updates every 3 seconds to display temperature, humidity, and pressure.
   - Interactive features such as panning and zooming.
   - Real-time stats are shown in a panel below the chart.
   
4. **WeatherLogger**:
   - Saves the weather data to a file on the desktop.
   
5. **Weather Predictor**:
   - Uses a basic predictive algorithm to forecast future temperature based on past data.
   
6. **Main System**:
   - Manages the sensor, display, and logging operations.
   - Simulates sensor failures and recovery, and logs system data.
   - Demonstrates all system functionalities through continuous monitoring.

## Running the Application:
1. **Install JFreeChart**:
   - If you haven't already, download the JFreeChart library from [JFreeChart](http://www.jfree.org/jfreechart/) and add it to your project dependencies.

2. **Steps to Run**:
   1. Compile and run the `WeatherStation` class in your IDE (e.g., Apache NetBeans).
   2. The system will start collecting and displaying weather data, including generating a time-series graph.
   3. Every 3 seconds, the data will be updated on the chart and console display.
   4. The system simulates sensor failures and repairs as part of the demonstration.
   
3. **Dependencies**:
   - JDK 11+ (or higher).
   - JFreeChart library (for graphical display).

## How the System Demonstrates OOP Principles:

1. **Encapsulation**: 
   - The `WeatherData` class encapsulates temperature, humidity, and pressure data along with methods to access and modify these attributes. Access to this data is controlled using getters and setters.

2. **Abstraction**: 
   - The `Sensor` interface abstracts the method to fetch weather data, and concrete sensor classes (like `RandomWeatherSensor`) implement this interface. The user doesn’t need to know how the data is generated or fetched.

3. **Inheritance and Polymorphism**: 
   - The `DisplayElement` interface is implemented by both `CurrentConditionsDisplay` and `GraphicalDisplay`. This allows for polymorphic behavior where the same interface is used to display data in different ways (console and graphical).

4. **Real-Time Data Handling**:
   - The system processes and updates weather data in real-time. The `GraphicalDisplay` class uses a time-series chart to plot data points continuously. New data is added every 3 seconds, and updates are displayed to the user in an interactive format.

## Conclusion:
The Weather Monitoring System is a robust, real-time application that demonstrates key OOP principles while providing useful functionalities for monitoring and predicting weather patterns. With its visual displays and logging features, this system can be extended further for real sensor integration or more advanced weather prediction algorithms.

---


