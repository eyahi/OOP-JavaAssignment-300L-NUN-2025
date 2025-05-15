public class BasicWeatherData extends WeatherRecord {
  private double pressure; // new attribute

  public BasicWeatherData(double temperature, double humidity, double pressure) {
      super(temperature, humidity);
      this.pressure = pressure;
  }

  public double getPressure() {
      return pressure;
  }

  public void setPressure(double pressure) {
      this.pressure = pressure;
  }

  // Overriding abstract method (Polymorphism)
  @Override
  public void display()     //new version of method
   {
      System.out.println("Temp: " + getTemperature() + "°C, Humidity: " + getHumidity() + "%, Pressure: " + pressure + " hPa");
  }
}
