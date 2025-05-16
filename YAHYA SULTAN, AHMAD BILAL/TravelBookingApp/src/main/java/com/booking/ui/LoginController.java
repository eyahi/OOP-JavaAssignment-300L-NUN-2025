package com.booking.ui;

import java.io.IOException;
import java.util.Optional;

import com.booking.model.User;
import com.booking.service.TravelService;
import com.booking.utils.PasswordHasher;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField     usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button        btnDefault;
    @FXML private Button        btnCreate;
    @FXML private Button        btnLogin;

    private TravelService service;

    @FXML
    private void initialize() throws IOException {

        service = TravelService.getInstance();
    }

    private void showAlert(Alert.AlertType type, String header, String content) {
        Alert a = new Alert(type);
        a.setHeaderText(header);
        a.setContentText(content);
        a.showAndWait();
    }

    @FXML
    private void onDefault() {
        // 1) pre‐fill the default credentials
        usernameField.setText("admin");
        passwordField.setText("password");

        try {
            // 2) hash + authenticate
            String user     = usernameField.getText().trim();
            String passHash = PasswordHasher.sha256(passwordField.getText());
            Optional<User> ok = service.authenticate(user, passHash);

            if (ok.isEmpty()) {
                showAlert(Alert.AlertType.ERROR,
                          "Login Failed",
                          "Default account not recognized");
                return;
            }

            // 3) on success, load the main view
            MainApp.loadMainView();



        } catch (Exception e) {
            // this will catch both IOExceptions from authenticate(...) and the Exception
            // thrown by loadMainView(...)
            showAlert(Alert.AlertType.ERROR,
                      "Login Error",
                      e.getMessage());
        }
    }

    @FXML
    private void onCreate() {
        String u = usernameField.getText().trim();
        String p = passwordField.getText();
        if (u.isEmpty() || p.isEmpty()) {
            showAlert(Alert.AlertType.WARNING,
                      "Missing Fields",
                      "Please fill both username and password");
            return;
        }
        try {
            String hash = PasswordHasher.sha256(p);
            service.addUser(new User(u, hash));
            showAlert(Alert.AlertType.INFORMATION,
                      "User Created",
                      "Account for '" + u + "' has been saved.");
        } catch (IOException ex) {
            showAlert(Alert.AlertType.ERROR,
                      "Could not save user",
                      ex.getMessage());
        }
    }

    @FXML private void onLogin() {
  String u = usernameField.getText().trim();
  String p = passwordField.getText();
  // … validation …
  try {
    String hash = PasswordHasher.sha256(p);
    Optional<User> ok = service.authenticate(u, hash);
    if (ok.isEmpty()) {
      showAlert(Alert.AlertType.ERROR, "Invalid credentials", "Username or password is incorrect");
      return;
    }
    User me = ok.get();
    service.setCurrentUser(me);            // ← store it in the singleton
    MainApp.loadMainView();                // ← no args
  } catch (Exception e) {
    showAlert(Alert.AlertType.ERROR, "Login failed", e.getMessage());
  }
}


}
