package com.travel.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Booking {
    private String bookingId;
    private Customer customer;
    private Flight flight; // can be null
    private Hotel hotel; // can be null
    private LocalDate checkInDate; // for hotel (nullable)
    private LocalDate checkOutDate; // for hotel (nullable)
    private int numberOfGuests;
    private LocalDateTime bookingDate;
    private double totalCost;

    public Booking(Customer customer, Flight flight, Hotel hotel,
                   LocalDate checkInDate, LocalDate checkOutDate, int numberOfGuests) {
        this.bookingId = UUID.randomUUID().toString();
        this.customer = customer;
        this.flight = flight;
        this.hotel = hotel;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuests = numberOfGuests;
        this.bookingDate = LocalDateTime.now();
        this.totalCost = calculateTotalCost();
    }

    private double calculateTotalCost() {
        double cost = 0.0;
        if (flight != null) {
            cost += flight.getPrice();
        }
        if (hotel != null && checkInDate != null && checkOutDate != null) {
            int nights = (int) (checkOutDate.toEpochDay() - checkInDate.toEpochDay());
            cost += nights * hotel.getPricePerNight();
        }
        return cost;
    }

    // Getters
    public String getBookingId() { return bookingId; }
    public Customer getCustomer() { return customer; }
    public Flight getFlight() { return flight; }
    public Hotel getHotel() { return hotel; }
    public LocalDate getCheckInDate() { return checkInDate; }
    public LocalDate getCheckOutDate() { return checkOutDate; }
    public int getNumberOfGuests() { return numberOfGuests; }
    public LocalDateTime getBookingDate() { return bookingDate; }
    public double getTotalCost() { return totalCost; }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId +
                ", Customer: " + customer.getName() +
                ", Flight: " + (flight != null ? flight.getFlightNumber() : "None") +
                ", Hotel: " + (hotel != null ? hotel.getName() : "None") +
                ", Check-In: " + (checkInDate != null ? checkInDate : "N/A") +
                ", Check-Out: " + (checkOutDate != null ? checkOutDate : "N/A") +
                ", Guests: " + numberOfGuests +
                ", Booking Date: " + bookingDate +
                ", Total Cost: $" + totalCost;
    }
}
