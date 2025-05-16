package com.travel.model;

import java.time.LocalDateTime;

public class Flight {
    private String flightNumber;
    private String departureCity;
    private String arrivalCity;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private double price;
    private int availableSeats;

    public Flight(String flightNumber, String departureCity, String arrivalCity,
                  LocalDateTime departureDateTime, LocalDateTime arrivalDateTime,
                  double price, int availableSeats) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.price = price;
        this.availableSeats = availableSeats;
    }

    // Getters
    public String getFlightNumber() { return flightNumber; }
    public String getDepartureCity() { return departureCity; }
    public String getArrivalCity() { return arrivalCity; }
    public LocalDateTime getDepartureDateTime() { return departureDateTime; }
    public LocalDateTime getArrivalDateTime() { return arrivalDateTime; }
    public double getPrice() { return price; }
    public int getAvailableSeats() { return availableSeats; }

    // Setter for availableSeats
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "Flight Number: " + flightNumber +
                ", From: " + departureCity +
                ", To: " + arrivalCity +
                ", Departure: " + departureDateTime +
                ", Arrival: " + arrivalDateTime +
                ", Price: $" + price +
                ", Seats Available: " + availableSeats;
    }
}
