package com.travel.main;

import com.travel.model.*;
import com.travel.service.TravelService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class TravelApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TravelService travelService = new TravelService();

        System.out.println("Welcome to the Travel Booking System!");

        // Create a default Customer
        System.out.print("Enter your Customer ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter your Name: ");
        String customerName = scanner.nextLine();
        Customer customer = new Customer(customerId, customerName);

        boolean running = true;
        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Search Flights");
            System.out.println("2. Search Hotels");
            System.out.println("3. View My Bookings");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    searchFlights(scanner, travelService, customer);
                    break;
                case "2":
                    searchHotels(scanner, travelService, customer);
                    break;
                case "3":
                    viewBookings(travelService, customer);
                    break;
                case "4":
                    running = false;
                    System.out.println("Thank you for using the Travel Booking System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        scanner.close();
    }

    private static void searchFlights(Scanner scanner, TravelService travelService, Customer customer) {
        try {
            System.out.print("Enter Departure City: ");
            String departureCity = scanner.nextLine();
            System.out.print("Enter Arrival City: ");
            String arrivalCity = scanner.nextLine();
            System.out.print("Enter Departure Date (yyyy-MM-dd): ");
            LocalDate departureDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);

            List<Flight> flights = travelService.searchFlights(departureCity, arrivalCity, departureDate);

            if (flights.isEmpty()) {
                System.out.println("No flights found for the given criteria.");
            } else {
                System.out.println("\nAvailable Flights:");
                for (int i = 0; i < flights.size(); i++) {
                    System.out.println((i + 1) + ". " + flights.get(i));
                }

                System.out.print("Select a flight to book (number) or 0 to cancel: ");
                int flightChoice = Integer.parseInt(scanner.nextLine());

                if (flightChoice > 0 && flightChoice <= flights.size()) {
                    Flight selectedFlight = flights.get(flightChoice - 1);
                    System.out.print("Enter number of passengers: ");
                    int numberOfPassengers = Integer.parseInt(scanner.nextLine());

                    Booking booking = travelService.bookFlight(customer, selectedFlight, numberOfPassengers);
                    if (booking != null) {
                        System.out.println("Flight booked successfully! Booking Details:");
                        System.out.println(booking);
                    } else {
                        System.out.println("Booking failed. Not enough available seats.");
                    }
                }
            }
        } catch (DateTimeParseException | NumberFormatException e) {
            System.out.println("Invalid input. Please check your date or number formats.");
        }
    }

    private static void searchHotels(Scanner scanner, TravelService travelService, Customer customer) {
        try {
            System.out.print("Enter City: ");
            String city = scanner.nextLine();
            System.out.print("Enter Check-In Date (yyyy-MM-dd): ");
            LocalDate checkInDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);
            System.out.print("Enter Check-Out Date (yyyy-MM-dd): ");
            LocalDate checkOutDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);
            System.out.print("Enter Number of Guests: ");
            int numberOfGuests = Integer.parseInt(scanner.nextLine());

            List<Hotel> hotels = travelService.searchHotels(city, checkInDate, checkOutDate, numberOfGuests);

            if (hotels.isEmpty()) {
                System.out.println("No hotels found for the given criteria.");
            } else {
                System.out.println("\nAvailable Hotels:");
                for (int i = 0; i < hotels.size(); i++) {
                    System.out.println((i + 1) + ". " + hotels.get(i));
                }

                System.out.print("Select a hotel to book (number) or 0 to cancel: ");
                int hotelChoice = Integer.parseInt(scanner.nextLine());

                if (hotelChoice > 0 && hotelChoice <= hotels.size()) {
                    Hotel selectedHotel = hotels.get(hotelChoice - 1);

                    Booking booking = travelService.bookHotel(customer, selectedHotel, checkInDate, checkOutDate, numberOfGuests);
                    if (booking != null) {
                        System.out.println("Hotel booked successfully! Booking Details:");
                        System.out.println(booking);
                    } else {
                        System.out.println("Booking failed. No available rooms.");
                    }
                }
            }
        } catch (DateTimeParseException | NumberFormatException e) {
            System.out.println("Invalid input. Please check your date or number formats.");
        }
    }

    private static void viewBookings(TravelService travelService, Customer customer) {
        List<Booking> bookings = travelService.getAllBookingsForCustomer(customer);

        if (bookings.isEmpty()) {
            System.out.println("You have no bookings yet.");
        } else {
            System.out.println("\nYour Bookings:");
            for (Booking booking : bookings) {
                System.out.println(booking);
            }
        }
    }
}
