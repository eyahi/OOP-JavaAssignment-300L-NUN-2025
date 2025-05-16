//Encapsulation code(i kept internal data private and ranted access only via methods)

import java.util.ArrayList;
import java.util.List;

public class WeatherStation {
    private List<BasicWeatherData> records;//the records list is private

    public WeatherStation()     //my contructor to initialize an empty list 
    {
        records = new ArrayList<>();
    }

    public void addRecord(BasicWeatherData data)        //method for adding new record 
    {
        records.add(data);
    }

    public List<BasicWeatherData> getAllRecords() {
        return records;
    }

    public double getAverageTemperature() {
        if (records.isEmpty()) return 0;
        double total = 0;
        for (BasicWeatherData data : records) {
            total += data.getTemperature();
        }
        return total / records.size();
    }

    public double getAverageHumidity() {
        if (records.isEmpty()) return 0;
        double total = 0;
        for (BasicWeatherData data : records) {
            total += data.getHumidity();
        }
        return total / records.size();
    }

    public double getAveragePressure() {
        if (records.isEmpty()) return 0;
        double total = 0;
        for (BasicWeatherData data : records) {
            total += data.getPressure();
        }
        return total / records.size();
    }
}
