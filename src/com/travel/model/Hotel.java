package com.travel.model;

public class Hotel {
    private String hotelId;
    private String name;
    private String city;
    private String address;
    private double pricePerNight;
    private int availableRooms;

    public Hotel(String hotelId, String name, String city, String address, double pricePerNight, int availableRooms) {
        this.hotelId = hotelId;
        this.name = name;
        this.city = city;
        this.address = address;
        this.pricePerNight = pricePerNight;
        this.availableRooms = availableRooms;
    }

    // Getters
    public String getHotelId() { return hotelId; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public String getAddress() { return address; }
    public double getPricePerNight() { return pricePerNight; }
    public int getAvailableRooms() { return availableRooms; }

    // Setter for availableRooms
    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    @Override
    public String toString() {
        return "Hotel Name: " + name +
                ", City: " + city +
                ", Address: " + address +
                ", Price per Night: $" + pricePerNight +
                ", Available Rooms: " + availableRooms;
    }
}
