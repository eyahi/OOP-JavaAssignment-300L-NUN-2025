package com.booking.ui;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.booking.model.Booking;
import com.booking.model.Flight;
import com.booking.model.Hotel;
import com.booking.service.TravelService;
import com.booking.utils.ReceiptGenerator;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReceiptController {
    @FXML private TableView<Booking> bookingTable;
    @FXML private TableColumn<Booking,String>  colId;
    @FXML private TableColumn<Booking,String>  colFlight;
    @FXML private TableColumn<Booking,String>  colHotel;
    @FXML private TableColumn<Booking,String>  colDate;
    @FXML private TableColumn<Booking,String>  colUser;
    @FXML private TableColumn<Booking,Integer> colSeats;
    @FXML private TableColumn<Booking,Double>  colCost;
    @FXML private Button btnGenerate;
    @FXML private Button btnBack;

    private TravelService service;

    @FXML
    public void initialize() throws IOException {
        service = TravelService.getInstance();
        colId    .setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        colUser  .setCellValueFactory(new PropertyValueFactory<>("username"));
        colFlight.setCellValueFactory(new PropertyValueFactory<>("flightId"));
        colHotel .setCellValueFactory(new PropertyValueFactory<>("hotelId"));
        colDate  .setCellValueFactory(new PropertyValueFactory<>("date"));
        colSeats .setCellValueFactory(new PropertyValueFactory<>("seats"));
        colCost  .setCellValueFactory(new PropertyValueFactory<>("totalCost"));

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
    private void onGenerate() {
        Booking b = bookingTable.getSelectionModel().getSelectedItem();
        if (b==null) {
            showAlert(AlertType.WARNING,"No selection","Select a booking first");
            return;
        }
        try {
            Flight f = service.findFlightById(b.getFlightId());
            Hotel h  = service.findHotelById(b.getHotelId());
            String out = ReceiptGenerator.generate(
                b,f,h,"data/receipt_"+b.getBookingId()+".pdf"
            );
            showAlert(AlertType.INFORMATION,"Receipt created","Saved to "+out);
        } catch(IOException ex) {
            showAlert(AlertType.ERROR,"Generation failed",ex.getMessage());
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
