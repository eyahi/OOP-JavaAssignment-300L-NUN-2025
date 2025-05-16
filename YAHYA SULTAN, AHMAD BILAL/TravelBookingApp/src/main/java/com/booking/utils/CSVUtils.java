package com.booking.utils;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import com.booking.model.Booking;
import com.booking.model.Flight;
import com.booking.model.Hotel;
import com.booking.model.User;


public class CSVUtils {
    private static final String SEP = ",";
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public static List<Flight> readFlights(String path) throws IOException {
        List<Flight> list = new ArrayList<>();
        for (String line : Files.readAllLines(Paths.get(path))) {
            if (line.startsWith("flightId")) continue; // skip header
            String[] s = line.split(SEP);
            list.add(new Flight(
              s[0],
              s[1],
              s[2],
              LocalDate.parse(s[3], FMT),
              Double.parseDouble(s[4]),
              Integer.parseInt(s[5])
            ));
        }
        return list;
    }

    public static List<Hotel> readHotels(String path) throws IOException {
        List<Hotel> list = new ArrayList<>();
        for (String line : Files.readAllLines(Paths.get(path))) {
            if (line.startsWith("hotelId")) continue;
            String[] s = line.split(SEP);
            list.add(new Hotel(
              s[0],
              s[1],
              s[2],
              Double.parseDouble(s[3]),
              Integer.parseInt(s[4])
            ));
        }
        return list;
    }

     private static final String[] HEADERS = {
    "bookingId", "username","flightId", "hotelId", "date", "seats", "totalCost"
    };

    public static List<Booking> readBookings(String path) throws IOException {
        try (Reader in = new FileReader(path)) {
            CSVParser parser = CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withSkipHeaderRecord()
                .parse(in);

            List<Booking> out = new ArrayList<>();
            for (CSVRecord r : parser) {
                Booking b = new Booking(
                    r.get("bookingId"),
                    r.get("username"),
                    r.get("flightId"),
                    r.get("hotelId"),
                    r.get("date"),                                      
                    Integer.parseInt(r.get("seats").trim()),
                    Double.parseDouble(r.get("totalCost").trim())
                );
                out.add(b);
            }
            return out;
        }
    }



  public static void writeBookings(String path, List<Booking> bookings) throws IOException {
    // open the file for writing, include a header if you like
    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path));
         CSVPrinter printer = new CSVPrinter(writer,
           CSVFormat.DEFAULT.withHeader(
             "bookingId", "username", "flightId", "hotelId", "bookingDate","seats","totalCost"
           ))
    ) {
      for (Booking b : bookings) {
        printer.printRecord(
          b.getBookingId(),
          b.getUsername(),
          b.getFlightId(),
          b.getHotelId(),
          b.getdate(),
          b.getSeats(),
          b.getTotalCost()
        );
      }
    }
  }
  


    public static List<User> readUsers(String path) throws IOException {
        List<User> out = new ArrayList<>();
        if (!Files.exists(Paths.get(path))) return out;
        try (Reader r = Files.newBufferedReader(Paths.get(path));
            CSVParser p = CSVFormat.DEFAULT
                .withHeader("username","passwordHash")
                .withFirstRecordAsHeader()
                .parse(r)) {
        for (CSVRecord rec : p) {
            out.add(new User(rec.get("username"), rec.get("passwordHash")));
        }
        }
        return out;
    }
    public static void appendUser(String path, User u) throws IOException {
        boolean exists = Files.exists(Paths.get(path));
        try (java.io.BufferedWriter w = Files.newBufferedWriter(Paths.get(path), StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
            CSVPrinter p = new CSVPrinter(w,
                exists ? CSVFormat.DEFAULT : CSVFormat.DEFAULT.withHeader("username","passwordHash")
            )) {
        p.printRecord(u.getUsername(), u.getPasswordHash());
        }
    }
    }