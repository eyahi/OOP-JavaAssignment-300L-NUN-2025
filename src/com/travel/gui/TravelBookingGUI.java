package com.travel.gui;

import com.travel.model.Booking;
import com.travel.model.Customer;
import com.travel.model.Flight;
import com.travel.model.Hotel;
import com.travel.service.TravelService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class TravelBookingGUI extends JFrame {

    private final TravelService travelService;
    private final Customer customer;
    private final JTextArea bookingsOutput = new JTextArea();

    public TravelBookingGUI() {
        travelService = new TravelService();
        customer = new Customer("C001", "Default User");

        setTitle("Travel Booking System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Book Flight", createFlightTab());
        tabs.add("Book Hotel", createHotelTab());
        tabs.add("My Bookings", createBookingsTab());

        add(tabs, BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel createFlightTab() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(5, 2, 5, 5));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField departureField = new JTextField();
        JTextField arrivalField = new JTextField();
        JTextField dateField = new JTextField("yyyy-MM-dd");
        JTextField passengersField = new JTextField();
        JButton searchBtn = new JButton("Search & Book");

        form.add(new JLabel("Departure City:"));
        form.add(departureField);
        form.add(new JLabel("Arrival City:"));
        form.add(arrivalField);
        form.add(new JLabel("Departure Date:"));
        form.add(dateField);
        form.add(new JLabel("Passengers:"));
        form.add(passengersField);
        form.add(new JLabel());
        form.add(searchBtn);

        JTextArea output = new JTextArea(10, 50);
        output.setEditable(false);
        JScrollPane scroll = new JScrollPane(output);

        searchBtn.addActionListener(e -> {
            try {
                String from = departureField.getText().trim();
                String to = arrivalField.getText().trim();
                LocalDate date = LocalDate.parse(dateField.getText().trim());
                int count = Integer.parseInt(passengersField.getText().trim());

                List<Flight> flights = travelService.searchFlights(from, to, date);
                if (flights.isEmpty()) {
                    output.setText("No flights found.");
                    return;
                }

                String[] options = flights.stream().map(Flight::toString).toArray(String[]::new);
                String selected = (String) JOptionPane.showInputDialog(this, "Select a flight:", "Flights",
                        JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                if (selected != null) {
                    for (Flight f : flights) {
                        if (f.toString().equals(selected)) {
                            Booking booking = travelService.bookFlight(customer, f, count);
                            output.setText(booking != null ?
                                    "Flight booked successfully:\n" + booking :
                                    "Booking failed. Not enough seats.");
                            break;
                        }
                    }
                }

            } catch (Exception ex) {
                output.setText("Error: " + ex.getMessage());
            }
        });

        panel.add(form, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createHotelTab() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(6, 2, 5, 5));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField cityField = new JTextField();
        JTextField checkInField = new JTextField("yyyy-MM-dd");
        JTextField checkOutField = new JTextField("yyyy-MM-dd");
        JTextField guestsField = new JTextField();
        JButton searchBtn = new JButton("Search & Book");

        form.add(new JLabel("City:"));
        form.add(cityField);
        form.add(new JLabel("Check-in Date:"));
        form.add(checkInField);
        form.add(new JLabel("Check-out Date:"));
        form.add(checkOutField);
        form.add(new JLabel("Guests:"));
        form.add(guestsField);
        form.add(new JLabel());
        form.add(searchBtn);

        JTextArea output = new JTextArea(10, 50);
        output.setEditable(false);
        JScrollPane scroll = new JScrollPane(output);

        searchBtn.addActionListener(e -> {
            try {
                String city = cityField.getText().trim();
                LocalDate checkIn = LocalDate.parse(checkInField.getText().trim());
                LocalDate checkOut = LocalDate.parse(checkOutField.getText().trim());
                int guests = Integer.parseInt(guestsField.getText().trim());

                List<Hotel> hotels = travelService.searchHotels(city, checkIn, checkOut, guests);
                if (hotels.isEmpty()) {
                    output.setText("No hotels found.");
                    return;
                }

                String[] options = hotels.stream().map(Hotel::toString).toArray(String[]::new);
                String selected = (String) JOptionPane.showInputDialog(this, "Select a hotel:", "Hotels",
                        JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                if (selected != null) {
                    for (Hotel h : hotels) {
                        if (h.toString().equals(selected)) {
                            Booking booking = travelService.bookHotel(customer, h, checkIn, checkOut, guests);
                            output.setText(booking != null ?
                                    "Hotel booked successfully:\n" + booking :
                                    "Booking failed. No rooms available.");
                            break;
                        }
                    }
                }

            } catch (Exception ex) {
                output.setText("Error: " + ex.getMessage());
            }
        });

        panel.add(form, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createBookingsTab() {
        JPanel panel = new JPanel(new BorderLayout());
        bookingsOutput.setEditable(false);
        JScrollPane scroll = new JScrollPane(bookingsOutput);

        JButton refreshBtn = new JButton("Refresh");
        JButton cancelBtn = new JButton("Cancel Booking");

        refreshBtn.addActionListener(e -> refreshBookings());
        cancelBtn.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(this, "Enter Booking ID to cancel:");
            if (id != null && !id.isEmpty()) {
                boolean success = travelService.cancelBooking(id.trim());
                JOptionPane.showMessageDialog(this,
                        success ? "Booking cancelled." : "Booking ID not found.");
                refreshBookings();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshBtn);
        buttonPanel.add(cancelBtn);

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        refreshBookings();
        return panel;
    }

    private void refreshBookings() {
        List<Booking> bookings = travelService.getAllBookingsForCustomer(customer);
        if (bookings.isEmpty()) {
            bookingsOutput.setText("You have no bookings.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Booking b : bookings) {
                sb.append(b).append("\n\n");
            }
            bookingsOutput.setText(sb.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TravelBookingGUI::new);
    }
}
