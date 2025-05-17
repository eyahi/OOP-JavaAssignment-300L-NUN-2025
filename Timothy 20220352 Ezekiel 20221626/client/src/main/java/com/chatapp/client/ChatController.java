package com.chatapp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ChatController {
    @FXML private ListView<String> messages;
    @FXML private TextField input;

    private PrintWriter out;

    @FXML
    public void initialize() throws IOException {
        // Connect to the server socket
        Socket sock = new Socket("localhost", 5555);
        out = new PrintWriter(sock.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(sock.getInputStream())
        );

        // Start a background thread to listen for messages
        new Thread(() -> {
            String line;
            try {
                while ((line = in.readLine()) != null) {
                    String msg = line;
                    Platform.runLater(() -> messages.getItems().add(msg));
                }
            } catch (IOException ignored) {
            }
        }).start();
    }

    @FXML
    public void send() {
        String text = input.getText();
        if (text != null && !text.isBlank()) {
            out.println(text);
            input.clear();
        }
    }
}
