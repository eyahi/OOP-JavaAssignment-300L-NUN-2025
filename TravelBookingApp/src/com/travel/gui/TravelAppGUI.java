package com.travel.gui;

import com.travel.model.*;
import com.travel.service.TravelService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;

public class TravelAppGUI {

    private TravelService travelService;
    private Customer customer;

    public TravelAppGUI() {
        travelService = new TravelService();
        customer = new Customer("C001", "Default User");

        createMainWindow();
    }

    private void createMainWindow() {
        JFrame frame = new JFrame("Travel Booking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JButton searchFlightsBtn = new JButton("Search Flights");
        JButton searchHotelsBtn = new JButton("Search Hotels");
        JButton viewBookingsBtn = new JButton("View My Bookings");

        searchFlightsBtn.addActionListener(e -> showFlightSearch());
        searchHotelsBtn.addActionListener(e -> showHotelSearch());
        viewBookingsBtn.addActionListener(e -> showBookings());

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.add(searchFlightsBtn);
        panel.add(searchHotelsBtn);
        panel.add(viewBookingsBtn);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void showFlightSearch() {
        // Show input dialogs and search flights using travelService
        String from = JOptionPane.showInputDialog("Departure City:");
        String to = JOptionPane.showInputDialog("Arrival City:");
        String dateStr = JOptionPane.showInputDialog("Departure Date (yyyy-MM-dd):");

        try {
            LocalDate date = LocalDate.parse(dateStr);
            List<Flight> flights = travelService.searchFlights(from, to, date);
            if (flights.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No flights found.");
            } else {
                StringBuilder message = new StringBuilder("Available Flights:\n");
                for (int i = 0; i < flights.size(); i++) {
                    message.append(i + 1).append(". ").append(flights.get(i)).append("\n");
                }
                JOptionPane.showMessageDialog(null, message.toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid date format.");
        }
    }

    private void showHotelSearch() {
        // Similar structure: ask for city and dates, then show hotel list
        JOptionPane.showMessageDialog(null, "Hotel search GUI coming soon...");
    }

    private void showBookings() {
        List<Booking> bookings = travelService.getAllBookingsForCustomer(customer);
        if (bookings.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You have no bookings.");
        } else {
            StringBuilder message = new StringBuilder("Your Bookings:\n");
            for (Booking booking : bookings) {
                message.append(booking).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, message.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TravelAppGUI::new);
    }
}
