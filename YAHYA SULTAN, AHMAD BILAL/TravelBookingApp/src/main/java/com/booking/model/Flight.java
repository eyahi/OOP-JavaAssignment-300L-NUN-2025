package com.booking.model;

import java.time.LocalDate;

public class Flight {
    private String flightId;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private double price;
    private int availableSeats;

    public Flight(String flightId,
                  String origin,
                  String destination,
                  LocalDate departureDate,
                  double price,
                  int availableSeats) {
        this.flightId      = flightId;
        this.origin        = origin;
        this.destination   = destination;
        this.departureDate = departureDate;
        this.price         = price;
        this.availableSeats= availableSeats;
    }

    // Getters
    public String    getFlightId()      { return flightId; }
    public String    getOrigin()        { return origin; }
    public String    getDestination()   { return destination; }
    public LocalDate getDepartureDate() { return departureDate; }
    public double    getPrice()         { return price; }
    public int       getAvailableSeats(){ return availableSeats; }

    // Allow updating seat counts
    public void setAvailableSeats(int seats) {
        this.availableSeats = seats;
    }

    @Override
    public String toString() {
        return flightId + " | " + origin + "→" + destination +
               " @ " + departureDate + " | $" + price +
               " | Seats: " + availableSeats;
    }
}
