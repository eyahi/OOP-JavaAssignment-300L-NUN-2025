package com.booking.ui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.booking.model.Booking;
import com.booking.model.Flight;
import com.booking.model.Hotel;
import com.booking.service.TravelService;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;

public class SearchController {

    @FXML private TextField originField;
    @FXML private TextField destField;
    @FXML private TextField dateField;

    @FXML private TableView<Flight> flightTable;
    @FXML private Button btnBack;
    @FXML private Button btnBook;

    private TravelService service;
    private static final DateTimeFormatter DATE_FMT =
        DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    public void initialize() {
        service = TravelService.getInstance();

        // DEBUG: how many flights did we actually load?
        int total = service.searchFlights(
            null, null, null,
            Comparator.comparing(Flight::getFlightId)
        ).size();
        System.out.println("DEBUG: total flights loaded = " + total);
        
        // 2) *Now* that service is non‐null, print out how many flights it actually loaded:
        List<Flight> allFlights = service.searchFlights(
            null, null, null,
            Comparator.comparing(Flight::getFlightId)
        );
        System.out.println("DEBUG: total flights loaded = " + allFlights.size());
    
        // 3) Proceed to configure the table and populate it:
        flightTable.getColumns().get(0)
                    .setCellValueFactory(new PropertyValueFactory<>("flightId"));

        // 2) configure table columns
        flightTable.getColumns().get(0)
            .setCellValueFactory(new PropertyValueFactory<>("flightId"));
        flightTable.getColumns().get(1)
            .setCellValueFactory(new PropertyValueFactory<>("origin"));
        flightTable.getColumns().get(2)
            .setCellValueFactory(new PropertyValueFactory<>("destination"));
        flightTable.getColumns().get(3)
            .setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        flightTable.getColumns().get(4)
            .setCellValueFactory(new PropertyValueFactory<>("availableSeats"));
        flightTable.getColumns().get(5)
            .setCellValueFactory(new PropertyValueFactory<>("price"));

        // 3) initial load (all flights)
        List<Flight> all = service.searchFlights(
            null, null, null,
            Comparator.comparing(Flight::getFlightId)
        );
        flightTable.setItems(
      FXCollections.observableArrayList(
        service.searchFlights(null, null, null,
                              Comparator.comparing(Flight::getFlightId))
      )
    );
    }

    @FXML
    private void onSearchFlights() {
        String o  = originField.getText().trim();
        String d  = destField.getText().trim();
        String dt = dateField.getText().trim();

        System.out.printf(">> SEARCH CLICKED: origin='%s', dest='%s', date='%s'%n",
                        o, d, dt);

        List<Flight> results = service.searchFlights(
        o.isEmpty()  ? null : o,
        d.isEmpty()  ? null : d,
        dt.isEmpty() ? null : dt,
        Comparator.comparing(Flight::getFlightId)
        );

        System.out.println("   → " + results.size() + " flights found.");

        flightTable.setItems(FXCollections.observableArrayList(results));
    }

    

    @FXML
    private void onBookFlight() {
        Flight f = flightTable.getSelectionModel().getSelectedItem();
        if (f == null) {
            alert("Please select a flight first.");
            return;
        }

        // **1**: seats dialog
        TextInputDialog seatDlg = new TextInputDialog("1");
        seatDlg.setTitle("Book Seats");
        seatDlg.setHeaderText("Flight " + f.getFlightId());
        seatDlg.setContentText("Number of seats:");
        Optional<String> seatR = seatDlg.showAndWait();
        if (seatR.isEmpty()) return;

        int seats;
        try {
            seats = Integer.parseInt(seatR.get());
            if (seats < 1 || seats > f.getAvailableSeats()) {
                throw new IllegalArgumentException("Invalid seat count");
            }
        } catch (Exception ex) {
            alert("Invalid seat count");
            return;
        }

        // **2**: hotel choice
        List<Hotel> hotels = service.getHotelsByCity(f.getDestination());
        if (hotels.isEmpty()) {
            alert("No hotels available in " + f.getDestination());
            return;
        }
        ChoiceDialog<Hotel> hotelDlg =
            new ChoiceDialog<>(hotels.get(0), hotels);
        hotelDlg.setTitle("Choose Hotel");
        hotelDlg.setHeaderText("Hotel in " + f.getDestination());
        Optional<Hotel> hotelR = hotelDlg.showAndWait();
        if (hotelR.isEmpty()) return;
        Hotel h = hotelR.get();

        // **3**: build & save booking
        String bookingId = UUID.randomUUID().toString();
        String today     = LocalDate.now().format(DATE_FMT);
        double totalCost = seats * f.getPrice() + h.getPricePerNight();
        String username  = service.getCurrentUser().getUsername();
        Booking b = new Booking(
            bookingId,
            username,
            f.getFlightId(),
            h.getHotelId(),
            today,
            seats,
            totalCost);

        try {
            service.addBooking(b);
            alert("Booked " + seats + " seats on flight " + f.getFlightId()
                  + "\nHotel: " + h.getName());
            // update in‐memory seats & refresh table
            f.setAvailableSeats(f.getAvailableSeats() - seats);
            flightTable.refresh();
        } catch (IOException ioe) {
            showError("Failed to save booking", ioe);
        }
    }

    @FXML
    private void onBack() {
        try {
            URL url = getClass().getResource("/fxml/MainView.fxml");
            Parent root = FXMLLoader.load(url);
            btnBack.getScene().setRoot(root);
        } catch (IOException e) {
            showError("Cannot return to main menu", e);
        }
    }

    // helper to show info dialogs
    private void alert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }

    // helper to show error dialogs
    private void showError(String title, Exception ex) {
        ex.printStackTrace();
        new Alert(Alert.AlertType.ERROR,
                  title + ":\n" + ex.getMessage())
            .showAndWait();
    }

    private void showErr(String failed_to_load_data, IOException e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
