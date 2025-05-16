/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.travelbookingsystem;

/**
 *
 * @author Abubakar Kana
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class TravelBookingSystem {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JTextField usernameField;
    private JPasswordField passwordField;

    private JTextField nameField;
    private JTextField destinationField;
    private JTextField dateField;

    private JTextArea ticketArea;

    private final ArrayList<Booking> bookings;

    public TravelBookingSystem() {
        bookings = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("SafeTravels - Flight Booking");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 450);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createWelcomePanel(), "Welcome");
        mainPanel.add(createBookingPanel(), "Booking");
        mainPanel.add(createTicketPanel(), "Ticket");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 250, 255));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel title = new JLabel("Welcome to SafeTravels");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(new Color(0, 51, 102));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createVerticalStrut(30));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 250, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.LINE_END;

    
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Username:"), gbc);

     
        gbc.gridx = 1;
        usernameField = new JTextField(15);
        formPanel.add(usernameField, gbc);

    
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Password:"), gbc);

       
        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        formPanel.add(passwordField, gbc);

        panel.add(formPanel);
        panel.add(Box.createVerticalStrut(20));

        JButton proceedButton = new JButton("LOGIN");
        proceedButton.setBackground(new Color(0, 102, 204));
        proceedButton.setForeground(Color.WHITE);
        proceedButton.setFocusPainted(false);
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        proceedButton.addActionListener((ActionEvent e) -> {
            cardLayout.show(mainPanel, "Booking");
        });

        panel.add(proceedButton);
        return panel;
    }

    private JPanel createBookingPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(new Color(255, 255, 255));

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Destination:"));
        destinationField = new JTextField();
        panel.add(destinationField);

        panel.add(new JLabel("Date of Departure:"));
        dateField = new JTextField();
        panel.add(dateField);

        panel.add(new JLabel(""));

        JButton finishButton = new JButton("Finish Booking");
        finishButton.setBackground(new Color(0, 153, 76));
        finishButton.setForeground(Color.WHITE);
        finishButton.setFocusPainted(false);
        finishButton.addActionListener((ActionEvent e) -> {
            bookTravel();
            cardLayout.show(mainPanel, "Ticket");
        });
        panel.add(finishButton);

        return panel;
    }

    private JPanel createTicketPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(230, 240, 255));

        JPanel ticketCard = new JPanel();
        ticketCard.setLayout(new BoxLayout(ticketCard, BoxLayout.Y_AXIS));
        ticketCard.setPreferredSize(new Dimension(400, 250));
        ticketCard.setBackground(Color.WHITE);
        ticketCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 51, 102), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel heading = new JLabel("BOARDING PASS");
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);
        heading.setForeground(new Color(0, 51, 102));
        ticketCard.add(heading);
        ticketCard.add(Box.createVerticalStrut(15));

        ticketArea = new JTextArea();
        ticketArea.setEditable(false);
        ticketArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        ticketArea.setBackground(Color.WHITE);
        ticketArea.setForeground(Color.DARK_GRAY);
        ticketArea.setHighlighter(null);
        ticketArea.setBorder(null);
        ticketCard.add(ticketArea);

        JLabel thankYouLabel = new JLabel("Thank you for choosing SafeTravels!");
        thankYouLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        thankYouLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        thankYouLabel.setForeground(new Color(0, 102, 153));
        ticketCard.add(Box.createVerticalStrut(10));
        ticketCard.add(thankYouLabel);

        JButton backButton = new JButton("Back to Login");
        backButton.setBackground(new Color(0, 102, 204));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener((ActionEvent e) -> {
            cardLayout.show(mainPanel, "Welcome");
            clearFields();
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(ticketCard, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(20, 0, 0, 0);
        panel.add(backButton, gbc);

        return panel;
    }

    private void bookTravel() {
        String name = nameField.getText();
        String destination = destinationField.getText();
        String date = dateField.getText();

        if (name.isEmpty() || destination.isEmpty() || date.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
            return;
        }

        Booking booking = new Booking(name, destination, date);
        bookings.add(booking);
        updateTicket();
    }

    private void updateTicket() {
        StringBuilder sb = new StringBuilder();
        Booking latest = bookings.get(bookings.size() - 1);

        sb.append("Passenger: ").append(latest.getName()).append("\n");
        sb.append("Destination: ").append(latest.getDestination()).append("\n");
        sb.append("Flight Date: ").append(latest.getDate()).append("\n");
        sb.append("------------------------------------\n");

        ticketArea.setText(sb.toString());
    }

    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        nameField.setText("");
        destinationField.setText("");
        dateField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TravelBookingSystem());
    }
}

class Booking {
    private final String name;
    private final String destination;
    private final String date;

    public Booking(String name, String destination, String date) {
        this.name = name;
        this.destination = destination;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate() {
        return date;
    }
}