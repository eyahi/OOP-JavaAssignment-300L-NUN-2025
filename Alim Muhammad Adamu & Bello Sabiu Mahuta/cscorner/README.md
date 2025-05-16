•	Name: ALIM ADAMU 20220858

•	Name: BELLO SABIU MAHUTA 20221197 

Weather Monitoring System

Description
The Weather Monitoring System simulates weather data monitoring. It gathers and displays weather readings like temperature, humidity, and pressure at regular intervals. 
The system also simulates sensor failures and attempts to recover from them. The solution uses key Object-Oriented Programming (OOP) principles to design the system.


Instructions

Compiling and Running the Application
	
	1.	Compile the Code
Open the terminal and navigate to your project directory. To compile all Java files, run the following command:

javac -d . com/weather/model/WeatherData.java com/weather/sensor/Sensor.java com/weather/sensor/RandomWeatherSensor.java com/weather/display/DisplayElement.java com/weather/display/CurrentConditionsDisplay.java com/weather/display/GUIDisplay.java com/weather/main/WeatherStation.java

2.	Run the Application
After compiling, you can run the main program with the following command:
java com.weather.main.WeatherStation

3.	Observe Output
The program will continuously display simulated weather data in the console and handle sensor failures.


OOP Principles Demonstrated

1. Encapsulation

This is demonstrated through the WeatherData class, which holds weather-related information (temperature, humidity, pressure) as private fields. The class provides public getter and setter methods to access and modify these values. This ensures that the internal state of the object is protected and can only be accessed in a controlled manner.

2. Abstraction

This is achieved through the Sensor interface, which defines a contract for fetching weather data. The RandomWeatherSensor class implements this interface, hiding the complexities of how data is fetched and only exposing the necessary method (fetchData()) to the user.

3. Polymorphism

This is demonstrated by the use of interfaces like Sensor and DisplayElement. Both RandomWeatherSensor and CurrentConditionsDisplay classes implement these interfaces, and the application interacts with these objects through their common interface methods. This allows for the flexibility to swap implementations without affecting the rest of the system.


