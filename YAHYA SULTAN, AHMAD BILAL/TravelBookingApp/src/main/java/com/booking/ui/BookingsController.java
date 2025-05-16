package com.booking.ui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import com.booking.model.Booking;
import com.booking.service.TravelService;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookingsController {
    @FXML private TableView<Booking>              bookingTable;
    @FXML private TableColumn<Booking,String>     colBookingId;
    @FXML private TableColumn<Booking,String>     colFlightId;
    @FXML private TableColumn<Booking,String>     colHotelId;
    @FXML private TableColumn<Booking,String>     colDate;
    @FXML private TableColumn<Booking,Integer>    colSeats;
    @FXML private TableColumn<Booking,String>     Coluser;
    @FXML private TableColumn<Booking,Double>     colTotalCost;
    @FXML private Button btnModify;
    @FXML private Button btnCancel;
    @FXML private Button btnBack;

    private TravelService service;

    @FXML
    public void initialize() throws IOException {
        service = TravelService.getInstance();
        colBookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        Coluser.     setCellValueFactory(new PropertyValueFactory<>("username"));
        colFlightId. setCellValueFactory(new PropertyValueFactory<>("flightId"));
        colHotelId.  setCellValueFactory(new PropertyValueFactory<>("hotelId"));
        colDate.     setCellValueFactory(new PropertyValueFactory<>("date"));
        colSeats.    setCellValueFactory(new PropertyValueFactory<>("seats"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        bookingTable.setItems(FXCollections.observableArrayList(service.getAllBookings()));

        refreshTable();
    }

    private void refreshTable() {
        try {
            List<Booking> all = service.getAllBookings();
            bookingTable.setItems(FXCollections.observableArrayList(all));
        } catch(IOException ex) {
            showAlert(AlertType.ERROR,"Load failed",ex.getMessage());
        }
    }

    @FXML
    private void onModifyBooking() {
        Booking sel = bookingTable.getSelectionModel().getSelectedItem();
        if (sel==null) {
            showAlert(AlertType.WARNING,"No selection","Select a booking to modify.");
            return;
        }

        TextInputDialog dlg = new TextInputDialog(Integer.toString(sel.getSeats()));
        dlg.setHeaderText("Seats for "+sel.getBookingId());
        Optional<String> r = dlg.showAndWait();
        if (r.isEmpty()) return;

        int newSeats;
        try {
            newSeats = Integer.parseInt(r.get());
            if (newSeats<1) throw new IllegalArgumentException();
        } catch(Exception ex) {
            showAlert(AlertType.ERROR,"Invalid seats","Enter a valid number");
            return;
        }
        sel.setSeats(newSeats);
        // recalc total
        try {
            double nt = service.findFlightById(sel.getFlightId()).getPrice()*newSeats
                      + service.findHotelById(sel.getHotelId()).getPricePerNight()*newSeats;
            sel.setTotalCost(nt);
            service.updateBooking(sel);
            refreshTable();
        } catch(IOException ex) {
            showAlert(AlertType.ERROR,"Update failed",ex.getMessage());
        }
    }

    @FXML
    private void onCancelBooking() {
        Booking sel = bookingTable.getSelectionModel().getSelectedItem();
        if (sel==null) {
            showAlert(AlertType.WARNING,"No selection","Select a booking to cancel.");
            return;
        }
        Alert confirm = new Alert(AlertType.CONFIRMATION,
            "Really delete booking "+sel.getBookingId()+"?",
            ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> resp = confirm.showAndWait();
        if (resp.orElse(ButtonType.NO)!=ButtonType.YES) return;

        try {
            service.deleteBooking(sel);
            refreshTable();
        } catch(IOException ex) {
            showAlert(AlertType.ERROR,"Delete failed",ex.getMessage());
        }
    }

    @FXML
    private void onBack() {
        try {
            URL u = getClass().getResource("/fxml/MainView.fxml");
            Parent root = FXMLLoader.load(u);
            btnBack.getScene().setRoot(root);
        } catch(IOException ex) {
            showAlert(AlertType.ERROR,"Navigation error",ex.getMessage());
        }
    }

    private void showAlert(AlertType t, String hdr, String txt) {
        Alert a = new Alert(t);
        a.setHeaderText(hdr);
        a.setContentText(txt);
        a.showAndWait();
    }
}
