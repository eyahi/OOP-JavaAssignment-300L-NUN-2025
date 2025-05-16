package com.booking.model;

public class Booking {
    private final String bookingId;
    private final String username;
    private final String flightId;
    private final String hotelId;
    private final String date;
    private int seats;
    private double totalCost;

    public Booking(String bookingId,
                   String username,
                   String flightId,
                   String hotelId,
                   String date,
                   int seats,
                   double totalCost) {
        this.bookingId   = bookingId;
        this.username    = username;
        this.flightId    = flightId;
        this.hotelId     = hotelId;
        this.date        = date;
        this.seats       = seats;
        this.totalCost   = totalCost; }
    

    public String getBookingId()   { return bookingId; }
    public String getUsername()    { return username; }
    public String getFlightId()    { return flightId; }
    public String getHotelId()     { return hotelId; }
    public String getdate()        { return date; }
    public int    getSeats()       { return seats; }
    public double getTotalCost()   { return totalCost; }

    
    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

}
