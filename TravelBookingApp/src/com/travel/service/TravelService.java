package com.travel.service;

import com.travel.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TravelService {
    private List<Flight> flights;
    private List<Hotel> hotels;
    private List<Booking> bookings;

    public TravelService() {
        flights = new ArrayList<>();
        hotels = new ArrayList<>();
        bookings = new ArrayList<>();
        initializeSampleData();
    }

    private void initializeSampleData() {
        // Sample Flights
        flights.add(new Flight("FL001", "New York", "Los Angeles",
                LocalDateTime.of(2025, 5, 10, 8, 0),
                LocalDateTime.of(2025, 5, 10, 11, 0),
                299.99, 50));

        flights.add(new Flight("FL002", "Chicago", "Miami",
                LocalDateTime.of(2025, 5, 12, 9, 30),
                LocalDateTime.of(2025, 5, 12, 13, 0),
                199.99, 40));

        // Sample Hotels
        hotels.add(new Hotel("HT001", "Grand Plaza", "Los Angeles", "123 Sunset Blvd", 150.0, 20));
        hotels.add(new Hotel("HT002", "Ocean View", "Miami", "456 Beach Ave", 200.0, 15));
    }

    public List<Flight> searchFlights(String departureCity, String arrivalCity, LocalDate departureDate) {
        List<Flight> result = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getDepartureCity().equalsIgnoreCase(departureCity)
                    && flight.getArrivalCity().equalsIgnoreCase(arrivalCity)
                    && flight.getDepartureDateTime().toLocalDate().equals(departureDate)) {
                result.add(flight);
            }
        }
        return result;
    }

    public List<Hotel> searchHotels(String city, LocalDate checkInDate, LocalDate checkOutDate, int numberOfGuests) {
        List<Hotel> result = new ArrayList<>();
        for (Hotel hotel : hotels) {
            if (hotel.getCity().equalsIgnoreCase(city)) {
                result.add(hotel);
            }
        }
        return result;
    }

    public Booking bookFlight(Customer customer, Flight flight, int numberOfPassengers) {
        if (flight.getAvailableSeats() >= numberOfPassengers) {
            flight.setAvailableSeats(flight.getAvailableSeats() - numberOfPassengers);
            Booking booking = new Booking(customer, flight, null, null, null, numberOfPassengers);
            bookings.add(booking);
            return booking;
        } else {
            return null; // or throw new RuntimeException("Not enough seats available.");
        }
    }

    public Booking bookHotel(Customer customer, Hotel hotel, LocalDate checkInDate, LocalDate checkOutDate, int numberOfGuests) {
        if (hotel.getAvailableRooms() > 0) { // Simplified: 1 room for all guests
            hotel.setAvailableRooms(hotel.getAvailableRooms() - 1);
            Booking booking = new Booking(customer, null, hotel, checkInDate, checkOutDate, numberOfGuests);
            bookings.add(booking);
            return booking;
        } else {
            return null; // or throw new RuntimeException("No rooms available.");
        }
    }

    public List<Booking> getAllBookingsForCustomer(Customer customer) {
        List<Booking> customerBookings = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getCustomer().getCustomerId().equals(customer.getCustomerId())) {
                customerBookings.add(booking);
            }
        }
        return customerBookings;
    }

    public boolean cancelBooking(String bookingId) {
        Booking bookingToCancel = null;
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                bookingToCancel = booking;
                break;
            }
        }

        if (bookingToCancel != null) {
            // Refund the seat/room
            if (bookingToCancel.getFlight() != null) {
                bookingToCancel.getFlight().setAvailableSeats(
                        bookingToCancel.getFlight().getAvailableSeats() + bookingToCancel.getNumberOfGuests()
                );
            }
            if (bookingToCancel.getHotel() != null) {
                bookingToCancel.getHotel().setAvailableRooms(
                        bookingToCancel.getHotel().getAvailableRooms() + 1
                );
            }
            bookings.remove(bookingToCancel);
            return true;
        }
        return false;
    }
}
