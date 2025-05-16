package com.booking.model;

public class Hotel {
    private String hotelId;
    private String city;
    private String name;
    private double pricePerNight;
    private int availableRooms;

    public Hotel(String hotelId, String city, String name,
                 double pricePerNight, int availableRooms) {
        this.hotelId = hotelId;
        this.city = city;
        this.name = name;
        this.pricePerNight = pricePerNight;
        this.availableRooms = availableRooms;
    }

    public String getHotelId()       { return hotelId; }
    public String getCity()          { return city; }
    public String getName()          { return name; }
    public double getPricePerNight() { return pricePerNight; }
    public int getAvailableRooms()   { return availableRooms; }

    @Override
    public String toString() {
        // For the ChoiceDialog display:
        return name + " ($" + pricePerNight + "/night)";
    }
}
