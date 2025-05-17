package com.booking.ui;

import com.booking.service.TravelService;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    private static Stage primary;

    @Override
    public void start(Stage stg) throws Exception {
        new TravelService("data");
        primary = stg;
        loadLogin();
    }

    
    public static void loadLogin() throws Exception {
        Parent root = FXMLLoader.load(MainApp.class.getResource("/fxml/LoginView.fxml"));
        Scene scene = new Scene(root, 360, 640);
        scene.getStylesheets().add(MainApp.class.getResource("/css/style.css").toExternalForm());
        primary.setScene(scene);
        primary.setTitle("Travel Booking");
        primary.setResizable(false);
        primary.show();
    }

    public static void loadMainView() throws Exception {
        Parent root = FXMLLoader.load(MainApp.class.getResource("/fxml/MainView.fxml"));
        Scene scene = new Scene(root, 360, 640);
        scene.getStylesheets().add(MainApp.class.getResource("/css/style.css").toExternalForm());
        primary.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
