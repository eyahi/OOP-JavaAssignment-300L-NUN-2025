package com.booking.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.booking.model.Booking;
import com.booking.model.Flight;
import com.booking.model.Hotel;
import com.booking.model.User;
import com.booking.utils.CSVUtils;

public class TravelService {
    private final Path dataDir;
    private static TravelService instance;
    private final List<Flight> flights;
    private final List<Hotel>  hotels;
    private User currentUser;
    

    private static final DateTimeFormatter DTF =
        DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void addUser(User u) throws IOException {
        CSVUtils.appendUser(dataDir.resolve("users.csv").toString(), u);
        }

    public Optional<User> authenticate(String username, String hash) throws IOException {
        List<User> all = CSVUtils.readUsers(dataDir.resolve("users.csv").toString());
        return all.stream()
                    .filter(u -> u.getUsername().equals(username)
                            && u.getPasswordHash().equals(hash))
                    .findFirst();
        }
      
    public static TravelService getInstance() {
      if (instance == null) {
        throw new IllegalStateException("TravelService not yet initialized");
      }
      return instance;
    }

    public void setCurrentUser(User u) { this.currentUser = u; }
    public User getCurrentUser()      { return currentUser; }

    

    public TravelService(String dataDir) throws IOException {
        this.dataDir = Paths.get(dataDir);
    // 1) load flights.csv
    Path flightsPath = this.dataDir.resolve("flights.csv");
    if (!Files.isRegularFile(flightsPath))
      throw new IOException("Missing flights.csv at " + flightsPath);
    this.flights = CSVUtils.readFlights(flightsPath.toString());

    // 2) load hotels.csv
    Path hotelsPath = this.dataDir.resolve("hotels.csv");
    if (!Files.isRegularFile(hotelsPath))
      throw new IOException("Missing hotels.csv at " + hotelsPath);
    this.hotels = CSVUtils.readHotels(hotelsPath.toString());
    instance = this;
  }
  


  /** Returns up to all matching flights, sorted by the comparator (or by ID if null). */
  public List<Flight> searchFlights(
      String origin,
      String destination,
      String date,
      Comparator<Flight> comparator
  ) {
    return flights.stream()
      .filter(f ->
           (origin      == null || origin.trim().isEmpty()      || f.getOrigin().equalsIgnoreCase(origin.trim()))
        && (destination == null || destination.trim().isEmpty() || f.getDestination().equalsIgnoreCase(destination.trim()))
        && (date        == null || date.trim().isEmpty()        || f.getDepartureDate().equals(LocalDate.parse(date.trim(), DTF)))
      )
      .sorted(comparator == null
        ? Comparator.comparing(Flight::getFlightId)
        : comparator
      )
      .collect(Collectors.toList());
  }

  
  public Flight findFlightById(String id) {
    return flights.stream()
                  .filter(f -> f.getFlightId().equals(id))
                  .findFirst().orElse(null);
  }

  
  public List<Hotel> getHotelsByCity(String city) {
    return hotels.stream()
                 .filter(h -> h.getCity().equalsIgnoreCase(city.trim()))
                 .collect(Collectors.toList());
  }

 
  public Hotel findHotelById(String id) {
    return hotels.stream()
                 .filter(h -> h.getHotelId().equals(id))
                 .findFirst().orElse(null);
  }


  public List<Booking> getAllBookings() throws IOException {
    return CSVUtils.readBookings(dataDir.resolve("bookings.csv").toString());
  }
  public void addBooking(Booking b) throws IOException {
    // 1) load current list
    Path csv = dataDir.resolve("bookings.csv");
    List<Booking> all = CSVUtils.readBookings(csv.toString());

    // 2) append the new one
    all.add(b);

    // 3) overwrite the file with the full list
    CSVUtils.writeBookings(csv.toString(), all);
}
  public void deleteBooking(Booking b) throws IOException {
    Path csv = dataDir.resolve("bookings.csv");
    List<Booking> all = CSVUtils.readBookings(csv.toString());
    all.removeIf(x -> x.getBookingId().equals(b.getBookingId()));
    CSVUtils.writeBookings(csv.toString(), all);
  }
  public void updateBooking(Booking updated) throws IOException {
      // 1) locate your CSV
      String csvPath = dataDir.resolve("bookings.csv").toString();

      // 2) load all bookings
      List<Booking> all = CSVUtils.readBookings(csvPath);

      // 3) replace the one with the same ID
      for (int i = 0; i < all.size(); i++) {
        if (all.get(i).getBookingId().equals(updated.getBookingId())) {
          all.set(i, updated);
          break;
        }
      }

      // 4) write *the list* back to disk
      CSVUtils.writeBookings(csvPath, all);
  }

}
