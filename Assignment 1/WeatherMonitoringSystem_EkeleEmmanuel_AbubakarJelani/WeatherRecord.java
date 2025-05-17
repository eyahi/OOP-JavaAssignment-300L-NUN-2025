//In this code Abstraction was performed using an abstract class WeatherRecord


public abstract class WeatherRecord {
  private double temperature; //attributes or field
  private double humidity;      //attributes or field

  public WeatherRecord(double temperature, double humidity) //constructor
   {
      this.temperature = temperature;
      this.humidity = humidity;
  }

  //getter methods
  public double getTemperature() {
      return temperature;
  }

  public void setTemperature(double temperature) {
      this.temperature = temperature;
  }

  public double getHumidity() {
      return humidity;
  }

  public void setHumidity(double humidity) {
      this.humidity = humidity;
  }

  public abstract void display();// any sub class must implement this method
}
