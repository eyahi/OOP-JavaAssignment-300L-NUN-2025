package com.travel.service;

import com.travel.model.*;
import com.travel.model.Flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class TravelService {

    private static TravelService instance;

    public static TravelService getInstance() {
        if (instance == null) {
            instance = new TravelService();
        }
        return instance;
    }

    private List<Flight> flights;
    private List<Hotel> hotels;
    private List<Booking> bookings;

    private TravelService() {
        flights = new ArrayList<>();
        hotels = new ArrayList<>();
        bookings = new ArrayList<>();
        loadSampleData();
    }

    private void loadSampleData() {
        // Sample Flights
        flights.add(new Flight("FL001", "Calabar", "Kano",
                LocalDateTime.of(2025, 7, 11, 10, 0),
                LocalDateTime.of(2025, 7, 11, 12, 0),
                79660, 17));
        flights.add(new Flight("FL002", "Lagos", "Port Harcourt",
                LocalDateTime.of(2025, 6, 21, 11, 0),
                LocalDateTime.of(2025, 6, 21, 12, 0),
                55761, 21));
        flights.add(new Flight("FL003", "Abuja", "Ibadan",
                LocalDateTime.of(2025, 7, 29, 10, 0),
                LocalDateTime.of(2025, 7, 29, 12, 0),
                45626, 38));
        flights.add(new Flight("FL004", "Port Harcourt", "Enugu",
                LocalDateTime.of(2025, 6, 15, 10, 0),
                LocalDateTime.of(2025, 6, 15, 12, 0),
                32253, 47));
        flights.add(new Flight("FL005", "Calabar", "Kano",
                LocalDateTime.of(2025, 7, 2, 10, 0),
                LocalDateTime.of(2025, 7, 2, 11, 0),
                64246, 15));
        flights.add(new Flight("FL006", "Jos", "Kano",
                LocalDateTime.of(2025, 8, 9, 12, 0),
                LocalDateTime.of(2025, 8, 9, 15, 0),
                53623, 32));
        flights.add(new Flight("FL007", "Port Harcourt", "Ibadan",
                LocalDateTime.of(2025, 8, 2, 10, 0),
                LocalDateTime.of(2025, 8, 2, 13, 0),
                60391, 16));
        flights.add(new Flight("FL008", "Ibadan", "Enugu",
                LocalDateTime.of(2025, 6, 14, 12, 0),
                LocalDateTime.of(2025, 6, 14, 14, 0),
                62684, 47));
        flights.add(new Flight("FL009", "Ibadan", "Enugu",
                LocalDateTime.of(2025, 7, 10, 7, 0),
                LocalDateTime.of(2025, 7, 10, 9, 0),
                63595, 39));
        flights.add(new Flight("FL010", "Enugu", "Kano",
                LocalDateTime.of(2025, 6, 25, 12, 0),
                LocalDateTime.of(2025, 6, 25, 15, 0),
                56381, 11));
        flights.add(new Flight("FL011", "Lagos", "Abuja",
                LocalDateTime.of(2025, 7, 17, 9, 0),
                LocalDateTime.of(2025, 7, 17, 12, 0),
                59916, 12));
        flights.add(new Flight("FL012", "Abuja", "Calabar",
                LocalDateTime.of(2025, 7, 15, 12, 0),
                LocalDateTime.of(2025, 7, 15, 15, 0),
                56891, 11));
        flights.add(new Flight("FL013", "Enugu", "Calabar",
                LocalDateTime.of(2025, 7, 6, 12, 0),
                LocalDateTime.of(2025, 7, 6, 15, 0),
                49920, 32));
        flights.add(new Flight("ABJ-LAG-01", "Abuja", "Lagos",
                LocalDateTime.of(2025, 5, 11, 9, 0),
                LocalDateTime.of(2025, 5, 11, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-01", "Lagos", "Abuja",
                LocalDateTime.of(2025, 5, 11, 15, 0),
                LocalDateTime.of(2025, 5, 11, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-02", "Abuja", "Lagos",
                LocalDateTime.of(2025, 5, 12, 9, 0),
                LocalDateTime.of(2025, 5, 12, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-02", "Lagos", "Abuja",
                LocalDateTime.of(2025, 5, 12, 15, 0),
                LocalDateTime.of(2025, 5, 12, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-03", "Abuja", "Lagos",
                LocalDateTime.of(2025, 5, 13, 9, 0),
                LocalDateTime.of(2025, 5, 13, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-03", "Lagos", "Abuja",
                LocalDateTime.of(2025, 5, 13, 15, 0),
                LocalDateTime.of(2025, 5, 13, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-04", "Abuja", "Lagos",
                LocalDateTime.of(2025, 5, 14, 9, 0),
                LocalDateTime.of(2025, 5, 14, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-04", "Lagos", "Abuja",
                LocalDateTime.of(2025, 5, 14, 15, 0),
                LocalDateTime.of(2025, 5, 14, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-05", "Abuja", "Lagos",
                LocalDateTime.of(2025, 5, 15, 9, 0),
                LocalDateTime.of(2025, 5, 15, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-05", "Lagos", "Abuja",
                LocalDateTime.of(2025, 5, 15, 15, 0),
                LocalDateTime.of(2025, 5, 15, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-06", "Abuja", "Lagos",
                LocalDateTime.of(2025, 5, 16, 9, 0),
                LocalDateTime.of(2025, 5, 16, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-06", "Lagos", "Abuja",
                LocalDateTime.of(2025, 5, 16, 15, 0),
                LocalDateTime.of(2025, 5, 16, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-07", "Abuja", "Lagos",
                LocalDateTime.of(2025, 5, 17, 9, 0),
                LocalDateTime.of(2025, 5, 17, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-07", "Lagos", "Abuja",
                LocalDateTime.of(2025, 5, 17, 15, 0),
                LocalDateTime.of(2025, 5, 17, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-08", "Abuja", "Lagos",
                LocalDateTime.of(2025, 5, 18, 9, 0),
                LocalDateTime.of(2025, 5, 18, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-08", "Lagos", "Abuja",
                LocalDateTime.of(2025, 5, 18, 15, 0),
                LocalDateTime.of(2025, 5, 18, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-09", "Abuja", "Lagos",
                LocalDateTime.of(2025, 5, 19, 9, 0),
                LocalDateTime.of(2025, 5, 19, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-09", "Lagos", "Abuja",
                LocalDateTime.of(2025, 5, 19, 15, 0),
                LocalDateTime.of(2025, 5, 19, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-10", "Abuja", "Lagos",
                LocalDateTime.of(2025, 5, 20, 9, 0),
                LocalDateTime.of(2025, 5, 20, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-10", "Lagos", "Abuja",
                LocalDateTime.of(2025, 5, 20, 15, 0),
                LocalDateTime.of(2025, 5, 20, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-11", "Abuja", "Lagos",
                LocalDateTime.of(2025, 5, 21, 9, 0),
                LocalDateTime.of(2025, 5, 21, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-11", "Lagos", "Abuja",
                LocalDateTime.of(2025, 5, 21, 15, 0),
                LocalDateTime.of(2025, 5, 21, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-12", "Abuja", "Lagos",
                LocalDateTime.of(2025, 5, 22, 9, 0),
                LocalDateTime.of(2025, 5, 22, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-12", "Lagos", "Abuja",
                LocalDateTime.of(2025, 5, 22, 15, 0),
                LocalDateTime.of(2025, 5, 22, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-13", "Abuja", "Lagos",
                LocalDateTime.of(2025, 5, 23, 9, 0),
                LocalDateTime.of(2025, 5, 23, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-13", "Lagos", "Abuja",
                LocalDateTime.of(2025, 5, 23, 15, 0),
                LocalDateTime.of(2025, 5, 23, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-14", "Abuja", "Lagos",
                LocalDateTime.of(2025, 5, 24, 9, 0),
                LocalDateTime.of(2025, 5, 24, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-14", "Lagos", "Abuja",
                LocalDateTime.of(2025, 5, 24, 15, 0),
                LocalDateTime.of(2025, 5, 24, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-15", "Abuja", "Lagos",
                LocalDateTime.of(2025, 5, 25, 9, 0),
                LocalDateTime.of(2025, 5, 25, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-15", "Lagos", "Abuja",
                LocalDateTime.of(2025, 5, 25, 15, 0),
                LocalDateTime.of(2025, 5, 25, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-16", "Abuja", "Lagos",
                LocalDateTime.of(2025, 5, 26, 9, 0),
                LocalDateTime.of(2025, 5, 26, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-16", "Lagos", "Abuja",
                LocalDateTime.of(2025, 5, 26, 15, 0),
                LocalDateTime.of(2025, 5, 26, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-17", "Abuja", "Lagos",
                LocalDateTime.of(2025, 5, 27, 9, 0),
                LocalDateTime.of(2025, 5, 27, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-17", "Lagos", "Abuja",
                LocalDateTime.of(2025, 5, 27, 15, 0),
                LocalDateTime.of(2025, 5, 27, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-18", "Abuja", "Lagos",
                LocalDateTime.of(2025, 5, 28, 9, 0),
                LocalDateTime.of(2025, 5, 28, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-18", "Lagos", "Abuja",
                LocalDateTime.of(2025, 5, 28, 15, 0),
                LocalDateTime.of(2025, 5, 28, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-19", "Abuja", "Lagos",
                LocalDateTime.of(2025, 5, 29, 9, 0),
                LocalDateTime.of(2025, 5, 29, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-19", "Lagos", "Abuja",
                LocalDateTime.of(2025, 5, 29, 15, 0),
                LocalDateTime.of(2025, 5, 29, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-20", "Abuja", "Lagos",
                LocalDateTime.of(2025, 5, 30, 9, 0),
                LocalDateTime.of(2025, 5, 30, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-20", "Lagos", "Abuja",
                LocalDateTime.of(2025, 5, 30, 15, 0),
                LocalDateTime.of(2025, 5, 30, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-21", "Abuja", "Lagos",
                LocalDateTime.of(2025, 5, 31, 9, 0),
                LocalDateTime.of(2025, 5, 31, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-21", "Lagos", "Abuja",
                LocalDateTime.of(2025, 5, 31, 15, 0),
                LocalDateTime.of(2025, 5, 31, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-22", "Abuja", "Lagos",
                LocalDateTime.of(2025, 6, 1, 9, 0),
                LocalDateTime.of(2025, 6, 1, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-22", "Lagos", "Abuja",
                LocalDateTime.of(2025, 6, 1, 15, 0),
                LocalDateTime.of(2025, 6, 1, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-23", "Abuja", "Lagos",
                LocalDateTime.of(2025, 6, 2, 9, 0),
                LocalDateTime.of(2025, 6, 2, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-23", "Lagos", "Abuja",
                LocalDateTime.of(2025, 6, 2, 15, 0),
                LocalDateTime.of(2025, 6, 2, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-24", "Abuja", "Lagos",
                LocalDateTime.of(2025, 6, 3, 9, 0),
                LocalDateTime.of(2025, 6, 3, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-24", "Lagos", "Abuja",
                LocalDateTime.of(2025, 6, 3, 15, 0),
                LocalDateTime.of(2025, 6, 3, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-25", "Abuja", "Lagos",
                LocalDateTime.of(2025, 6, 4, 9, 0),
                LocalDateTime.of(2025, 6, 4, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-25", "Lagos", "Abuja",
                LocalDateTime.of(2025, 6, 4, 15, 0),
                LocalDateTime.of(2025, 6, 4, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-26", "Abuja", "Lagos",
                LocalDateTime.of(2025, 6, 5, 9, 0),
                LocalDateTime.of(2025, 6, 5, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-26", "Lagos", "Abuja",
                LocalDateTime.of(2025, 6, 5, 15, 0),
                LocalDateTime.of(2025, 6, 5, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-27", "Abuja", "Lagos",
                LocalDateTime.of(2025, 6, 6, 9, 0),
                LocalDateTime.of(2025, 6, 6, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-27", "Lagos", "Abuja",
                LocalDateTime.of(2025, 6, 6, 15, 0),
                LocalDateTime.of(2025, 6, 6, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-28", "Abuja", "Lagos",
                LocalDateTime.of(2025, 6, 7, 9, 0),
                LocalDateTime.of(2025, 6, 7, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-28", "Lagos", "Abuja",
                LocalDateTime.of(2025, 6, 7, 15, 0),
                LocalDateTime.of(2025, 6, 7, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-29", "Abuja", "Lagos",
                LocalDateTime.of(2025, 6, 8, 9, 0),
                LocalDateTime.of(2025, 6, 8, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-29", "Lagos", "Abuja",
                LocalDateTime.of(2025, 6, 8, 15, 0),
                LocalDateTime.of(2025, 6, 8, 16, 0),
                56000, 30));
        flights.add(new Flight("ABJ-LAG-30", "Abuja", "Lagos",
                LocalDateTime.of(2025, 6, 9, 9, 0),
                LocalDateTime.of(2025, 6, 9, 10, 0),
                55000, 30));
        flights.add(new Flight("LAG-ABJ-30", "Lagos", "Abuja",
                LocalDateTime.of(2025, 6, 9, 15, 0),
                LocalDateTime.of(2025, 6, 9, 16, 0),
                56000, 30));
        flights.add(new Flight("FL014", "Kano", "Ibadan",
                LocalDateTime.of(2025, 6, 27, 10, 0),
                LocalDateTime.of(2025, 6, 27, 12, 0),
                74594, 13));
        flights.add(new Flight("FL015", "Calabar", "Port Harcourt",
                LocalDateTime.of(2025, 7, 2, 12, 0),
                LocalDateTime.of(2025, 7, 2, 13, 0),
                54633, 39));
        flights.add(new Flight("FL016", "Jos", "Lagos",
                LocalDateTime.of(2025, 6, 27, 9, 0),
                LocalDateTime.of(2025, 6, 27, 10, 0),
                38196, 19));
        flights.add(new Flight("FL017", "Ibadan", "Port Harcourt",
                LocalDateTime.of(2025, 7, 29, 9, 0),
                LocalDateTime.of(2025, 7, 29, 11, 0),
                36377, 36));
        flights.add(new Flight("FL018", "Enugu", "Abuja",
                LocalDateTime.of(2025, 7, 10, 12, 0),
                LocalDateTime.of(2025, 7, 10, 15, 0),
                64126, 43));
        flights.add(new Flight("FL019", "Port Harcourt", "Jos",
                LocalDateTime.of(2025, 7, 7, 10, 0),
                LocalDateTime.of(2025, 7, 7, 12, 0),
                54671, 17));
        flights.add(new Flight("FL020", "Calabar", "Jos",
                LocalDateTime.of(2025, 8, 9, 8, 0),
                LocalDateTime.of(2025, 8, 9, 10, 0),
                58311, 24));
        flights.add(new Flight("FL021", "Jos", "Lagos",
                LocalDateTime.of(2025, 8, 7, 9, 0),
                LocalDateTime.of(2025, 8, 7, 12, 0),
                69597, 17));
        flights.add(new Flight("FL022", "Abuja", "Calabar",
                LocalDateTime.of(2025, 6, 27, 6, 0),
                LocalDateTime.of(2025, 6, 27, 8, 0),
                35255, 42));
        flights.add(new Flight("FL023", "Abuja", "Calabar",
                LocalDateTime.of(2025, 7, 27, 12, 0),
                LocalDateTime.of(2025, 7, 27, 13, 0),
                55705, 18));
        flights.add(new Flight("FL024", "Ibadan", "Abuja",
                LocalDateTime.of(2025, 7, 23, 7, 0),
                LocalDateTime.of(2025, 7, 23, 8, 0),
                70793, 18));
        flights.add(new Flight("FL025", "Kano", "Jos",
                LocalDateTime.of(2025, 7, 1, 11, 0),
                LocalDateTime.of(2025, 7, 1, 14, 0),
                67581, 32));
        flights.add(new Flight("FL026", "Calabar", "Jos",
                LocalDateTime.of(2025, 8, 4, 9, 0),
                LocalDateTime.of(2025, 8, 4, 11, 0),
                41552, 24));
        flights.add(new Flight("FL027", "Enugu", "Abuja",
                LocalDateTime.of(2025, 7, 1, 8, 0),
                LocalDateTime.of(2025, 7, 1, 11, 0),
                68965, 38));
        flights.add(new Flight("FL028", "Kano", "Ibadan",
                LocalDateTime.of(2025, 7, 28, 11, 0),
                LocalDateTime.of(2025, 7, 28, 13, 0),
                50917, 44));
        flights.add(new Flight("FL029", "Port Harcourt", "Calabar",
                LocalDateTime.of(2025, 7, 5, 7, 0),
                LocalDateTime.of(2025, 7, 5, 8, 0),
                60799, 44));
        flights.add(new Flight("FL030", "Enugu", "Kano",
                LocalDateTime.of(2025, 7, 30, 9, 0),
                LocalDateTime.of(2025, 7, 30, 10, 0),
                73922, 47));
        flights.add(new Flight("FL031", "Lagos", "Jos",
                LocalDateTime.of(2025, 7, 25, 6, 0),
                LocalDateTime.of(2025, 7, 25, 8, 0),
                40278, 50));
        flights.add(new Flight("FL032", "Kano", "Port Harcourt",
                LocalDateTime.of(2025, 7, 22, 6, 0),
                LocalDateTime.of(2025, 7, 22, 8, 0),
                66688, 39));
        flights.add(new Flight("FL033", "Lagos", "Kano",
                LocalDateTime.of(2025, 6, 14, 10, 0),
                LocalDateTime.of(2025, 6, 14, 13, 0),
                71823, 38));
        flights.add(new Flight("FL034", "Abuja", "Ibadan",
                LocalDateTime.of(2025, 6, 20, 8, 0),
                LocalDateTime.of(2025, 6, 20, 10, 0),
                51324, 39));
        flights.add(new Flight("FL035", "Ibadan", "Lagos",
                LocalDateTime.of(2025, 7, 18, 11, 0),
                LocalDateTime.of(2025, 7, 18, 14, 0),
                73928, 46));
        flights.add(new Flight("FL036", "Port Harcourt", "Enugu",
                LocalDateTime.of(2025, 7, 29, 11, 0),
                LocalDateTime.of(2025, 7, 29, 12, 0),
                55413, 31));
        flights.add(new Flight("FL037", "Lagos", "Jos",
                LocalDateTime.of(2025, 7, 22, 7, 0),
                LocalDateTime.of(2025, 7, 22, 9, 0),
                50491, 26));
        flights.add(new Flight("FL038", "Kano", "Jos",
                LocalDateTime.of(2025, 7, 11, 7, 0),
                LocalDateTime.of(2025, 7, 11, 8, 0),
                69654, 13));
        flights.add(new Flight("FL039", "Port Harcourt", "Jos",
                LocalDateTime.of(2025, 7, 10, 7, 0),
                LocalDateTime.of(2025, 7, 10, 8, 0),
                63058, 32));
        flights.add(new Flight("FL040", "Ibadan", "Calabar",
                LocalDateTime.of(2025, 6, 30, 6, 0),
                LocalDateTime.of(2025, 6, 30, 7, 0),
                67169, 12));
        flights.add(new Flight("FL041", "Abuja", "Jos",
                LocalDateTime.of(2025, 7, 18, 8, 0),
                LocalDateTime.of(2025, 7, 18, 10, 0),
                43874, 35));
        flights.add(new Flight("FL042", "Calabar", "Abuja",
                LocalDateTime.of(2025, 7, 2, 8, 0),
                LocalDateTime.of(2025, 7, 2, 11, 0),
                56719, 29));
        flights.add(new Flight("FL043", "Ibadan", "Port Harcourt",
                LocalDateTime.of(2025, 7, 6, 9, 0),
                LocalDateTime.of(2025, 7, 6, 10, 0),
                40131, 40));
        flights.add(new Flight("FL044", "Kano", "Port Harcourt",
                LocalDateTime.of(2025, 6, 13, 9, 0),
                LocalDateTime.of(2025, 6, 13, 12, 0),
                47798, 36));
        flights.add(new Flight("FL045", "Lagos", "Enugu",
                LocalDateTime.of(2025, 7, 13, 8, 0),
                LocalDateTime.of(2025, 7, 13, 11, 0),
                33790, 30));
        flights.add(new Flight("FL046", "Lagos", "Enugu",
                LocalDateTime.of(2025, 8, 8, 10, 0),
                LocalDateTime.of(2025, 8, 8, 12, 0),
                77833, 10));
        flights.add(new Flight("FL047", "Port Harcourt", "Abuja",
                LocalDateTime.of(2025, 8, 6, 6, 0),
                LocalDateTime.of(2025, 8, 6, 7, 0),
                66687, 36));
        flights.add(new Flight("FL048", "Kano", "Enugu",
                LocalDateTime.of(2025, 7, 13, 9, 0),
                LocalDateTime.of(2025, 7, 13, 12, 0),
                63546, 45));
        flights.add(new Flight("FL049", "Ibadan", "Lagos",
                LocalDateTime.of(2025, 7, 7, 7, 0),
                LocalDateTime.of(2025, 7, 7, 10, 0),
                50925, 32));
        flights.add(new Flight("FL050", "Jos", "Port Harcourt",
                LocalDateTime.of(2025, 7, 8, 6, 0),
                LocalDateTime.of(2025, 7, 8, 9, 0),
                66229, 45));
        hotels.add(new Hotel("H001", "Royal Suites 1", "Lagos", "170 Independence Ave", 29721, 7));
        hotels.add(new Hotel("H002", "Royal Suites 2", "Enugu", "616 Unity Rd", 32126, 20));
        hotels.add(new Hotel("H003", "Golden Palace 3", "Abuja", "207 Broadway", 20906, 16));
        hotels.add(new Hotel("H004", "Prestige Stay 4", "Ibadan", "964 Unity Rd", 15030, 17));
        hotels.add(new Hotel("H005", "Golden Palace 5", "Kano", "988 Main St", 17767, 5));
        hotels.add(new Hotel("H006", "Sunset Inn 6", "Calabar", "376 Unity Rd", 29950, 19));
        hotels.add(new Hotel("H007", "Peace Hotel 7", "Kano", "153 Independence Ave", 15946, 17));
        hotels.add(new Hotel("H008", "Peace Hotel 8", "Ibadan", "719 Broadway", 24968, 12));
        hotels.add(new Hotel("H009", "Palm Grove 9", "Lagos", "656 Independence Ave", 19197, 17));
        hotels.add(new Hotel("H010", "Prestige Stay 10", "Ibadan", "340 Main St", 27140, 10));
        hotels.add(new Hotel("H011", "Golden Palace 11", "Enugu", "292 Main St", 32949, 6));
        hotels.add(new Hotel("H012", "Peace Hotel 12", "Port Harcourt", "617 Broadway", 22839, 17));
        hotels.add(new Hotel("H013", "Golden Palace 13", "Enugu", "789 Unity Rd", 30445, 12));
        hotels.add(new Hotel("H014", "Prestige Stay 14", "Kano", "548 Main St", 30970, 6));
        hotels.add(new Hotel("H015", "Ocean Breeze 15", "Calabar", "210 Broadway", 28868, 17));
        hotels.add(new Hotel("H016", "Royal Suites 16", "Kano", "85 Unity Rd", 13040, 9));
        hotels.add(new Hotel("H017", "Royal Suites 17", "Kano", "107 Broadway", 26694, 8));
        hotels.add(new Hotel("H018", "Golden Palace 18", "Enugu", "131 Broadway", 26088, 5));
        hotels.add(new Hotel("H019", "Royal Suites 19", "Lagos", "204 Unity Rd", 33887, 20));
        hotels.add(new Hotel("H020", "Peace Hotel 20", "Ibadan", "106 Independence Ave", 28634, 13));
        hotels.add(new Hotel("H021", "Sunset Inn 21", "Ibadan", "392 Main St", 16409, 17));
        hotels.add(new Hotel("H022", "Sunset Inn 22", "Calabar", "170 Broadway", 19470, 12));
        hotels.add(new Hotel("H023", "Palm Grove 23", "Jos", "809 Broadway", 12396, 12));
        hotels.add(new Hotel("H024", "Palm Grove 24", "Ibadan", "973 Unity Rd", 29033, 16));
        hotels.add(new Hotel("H025", "Prestige Stay 25", "Enugu", "205 Broadway", 26696, 17));
        hotels.add(new Hotel("H026", "Golden Palace 26", "Lagos", "428 Independence Ave", 14278, 8));
        hotels.add(new Hotel("H027", "Ocean Breeze 27", "Kano", "620 Main St", 29844, 6));
        hotels.add(new Hotel("H028", "Sunset Inn 28", "Lagos", "244 Independence Ave", 30254, 11));
        hotels.add(new Hotel("H029", "Peace Hotel 29", "Lagos", "873 Unity Rd", 24040, 8));
        hotels.add(new Hotel("H030", "Peace Hotel 30", "Calabar", "107 Broadway", 12737, 11));
        hotels.add(new Hotel("H031", "Royal Suites 31", "Abuja", "648 Unity Rd", 17768, 9));
        hotels.add(new Hotel("H032", "Royal Suites 32", "Kano", "424 Unity Rd", 19190, 16));
        hotels.add(new Hotel("H033", "Royal Suites 33", "Enugu", "813 Unity Rd", 15627, 13));
        hotels.add(new Hotel("H034", "Peace Hotel 34", "Kano", "893 Independence Ave", 32178, 16));
        hotels.add(new Hotel("H035", "Royal Suites 35", "Jos", "166 Independence Ave", 33032, 18));
        hotels.add(new Hotel("H036", "Prestige Stay 36", "Kano", "915 Unity Rd", 28379, 19));
        hotels.add(new Hotel("H037", "Skyline Hotel 37", "Lagos", "743 Unity Rd", 23326, 10));
        hotels.add(new Hotel("H038", "Golden Palace 38", "Calabar", "951 Independence Ave", 21323, 6));
        hotels.add(new Hotel("H039", "Golden Palace 39", "Calabar", "690 Independence Ave", 12196, 7));
        hotels.add(new Hotel("H040", "Palm Grove 40", "Port Harcourt", "863 Broadway", 15016, 13));
        hotels.add(new Hotel("H041", "Peace Hotel 41", "Jos", "625 Broadway", 17149, 19));
        hotels.add(new Hotel("H042", "Peace Hotel 42", "Lagos", "494 Independence Ave", 34236, 7));
        hotels.add(new Hotel("H043", "Prestige Stay 43", "Lagos", "176 Unity Rd", 24665, 9));
        hotels.add(new Hotel("H044", "Sunset Inn 44", "Abuja", "675 Main St", 24502, 6));
        hotels.add(new Hotel("H045", "Golden Palace 45", "Jos", "630 Main St", 15717, 18));
        hotels.add(new Hotel("H046", "Peace Hotel 46", "Lagos", "935 Main St", 14856, 15));
        hotels.add(new Hotel("H047", "Ocean Breeze 47", "Jos", "215 Main St", 28740, 5));
        hotels.add(new Hotel("H048", "Royal Suites 48", "Enugu", "325 Independence Ave", 34144, 18));
        hotels.add(new Hotel("H049", "Skyline Hotel 49", "Kano", "323 Independence Ave", 21460, 15));
        hotels.add(new Hotel("H050", "Peace Hotel 50", "Enugu", "933 Unity Rd", 19152, 6));
    }

    public List<Flight> searchFlights(String departureCity, String arrivalCity, LocalDate departureDate) {
        List<Flight> results = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getDepartureCity().equalsIgnoreCase(departureCity) &&
                    flight.getArrivalCity().equalsIgnoreCase(arrivalCity) &&
                    flight.getDepartureDateTime().toLocalDate().equals(departureDate)) {
                results.add(flight);
            }
        }
        return results;
    }

    public List<Hotel> searchHotels(String city, LocalDate checkInDate, LocalDate checkOutDate, int numberOfGuests) {
        List<Hotel> results = new ArrayList<>();
        for (Hotel hotel : hotels) {
            if (hotel.getCity().equalsIgnoreCase(city) && hotel.getAvailableRooms() >= 1) {
                results.add(hotel);
            }
        }
        return results;
    }

    public Booking bookFlight(Customer customer, Flight flight, int numberOfPassengers) {
        if (flight.getAvailableSeats() >= numberOfPassengers) {
            flight.setAvailableSeats(flight.getAvailableSeats() - numberOfPassengers);
            Booking booking = new Booking(customer, flight, null, null, null, numberOfPassengers);
            bookings.add(booking);
            return booking;
        }
        return null;
    }

    public Booking bookHotel(Customer customer, Hotel hotel, LocalDate checkInDate, LocalDate checkOutDate, int numberOfGuests) {
        if (hotel.getAvailableRooms() >= 1) {
            hotel.setAvailableRooms(hotel.getAvailableRooms() - 1); // Simplified
            Booking booking = new Booking(customer, null, hotel, checkInDate, checkOutDate, numberOfGuests);
            bookings.add(booking);
            return booking;
        }
        return null;
    }

    public List<Booking> getAllBookingsForCustomer(Customer customer) {
        List<Booking> results = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getCustomer().getCustomerId().equals(customer.getCustomerId())) {
                results.add(booking);
            }
        }
        return results;
    }

    public boolean cancelBooking(String bookingId) {
        Booking toRemove = null;

        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                // ✅ Refund seats if it was a flight booking
                if (booking.getFlight() != null) {
                    Flight flight = booking.getFlight();
                    flight.setAvailableSeats(flight.getAvailableSeats() + booking.getNumberOfGuests());
                }

                // ✅ Refund room if it was a hotel booking
                if (booking.getHotel() != null) {
                    Hotel hotel = booking.getHotel();
                    hotel.setAvailableRooms(hotel.getAvailableRooms() + 1); // 1 room per booking
                }

                toRemove = booking;
                break;
            }
        }

        if (toRemove != null) {
            bookings.remove(toRemove);
            return true;
        }

        return false;
    }
}
