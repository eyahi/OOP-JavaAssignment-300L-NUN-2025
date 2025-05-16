import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WeatherStation station = new WeatherStation();
        int choice;


        //i used the do while loop to show menu until exit

        do {
            System.out.println("\n--- Weather Monitoring System ---");
            System.out.println("1. Add Weather Data");
            System.out.println("2. View All Records");
            System.out.println("3. View Average Stats");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter temperature (°C): ");
                    double temp = scanner.nextDouble();
                    System.out.print("Enter humidity (%): ");
                    double humidity = scanner.nextDouble();
                    System.out.print("Enter pressure (hPa): ");
                    double pressure = scanner.nextDouble();

                    BasicWeatherData data = new BasicWeatherData(temp, humidity, pressure);
                    station.addRecord(data);//methods, guides the interraction with WeatherStation
                    break;

                // case 2:
                //     station.showAllRecords();
                //     break;

                // case 3:
                //     station.showAverage();
                //     break;

                case 4:
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }

        } while (choice != 4);

        scanner.close();
    }
}
