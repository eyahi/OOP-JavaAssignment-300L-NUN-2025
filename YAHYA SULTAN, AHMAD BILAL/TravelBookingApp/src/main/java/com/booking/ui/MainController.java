package com.booking.ui;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class MainController {
    @FXML private Button btnSearch;
    @FXML private Button btnView;
    @FXML private Button btnReceipt;

    @FXML
    private void onSearch() {
        swap("/fxml/SearchView.fxml", "Search screen");
    }

    @FXML
    private void onView() {
        swap("/fxml/BookingsView.fxml", "Bookings screen");
    }

    @FXML
    private void onReceipt() {
        swap("/fxml/ReceiptView.fxml", "Receipt screen");
    }

    private void swap(String resource, String desc) {
        try {
            URL url = getClass().getResource(resource);
            if (url == null)
                throw new IllegalStateException(resource + " not found");
            Parent root = FXMLLoader.load(url);
            btnSearch.getScene().setRoot(root);
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Failed to load " + desc + ":\n" + ex)
                .showAndWait();
        }
    }
}
