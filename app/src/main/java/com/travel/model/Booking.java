package com.travel.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Booking {
    private String bookingId;
    private Customer customer;
    private Flight flight;
    private Hotel hotel;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
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

        // Calculate total cost
        double cost = 0.0;
        if (flight != null) {
            cost += flight.getPrice();
        }
        if (hotel != null && checkInDate != null && checkOutDate != null) {
            long nights = java.time.temporal.ChronoUnit.DAYS.between(checkInDate, checkOutDate);
            cost += nights * hotel.getPricePerNight();
        }
        this.totalCost = cost;
    }

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
        return "Booking ID: " + bookingId + "\nCustomer: " + customer +
                (flight != null ? "\nFlight: " + flight : "") +
                (hotel != null ? "\nHotel: " + hotel + "\nCheck-in: " + checkInDate + ", Check-out: " + checkOutDate : "") +
                "\nGuests: " + numberOfGuests +
                "\nDate: " + bookingDate +
                "\nTotal Cost: $" + totalCost;
    }
}
