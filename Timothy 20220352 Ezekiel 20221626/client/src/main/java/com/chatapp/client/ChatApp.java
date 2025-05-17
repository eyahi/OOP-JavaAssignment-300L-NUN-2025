package com.chatapp.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML from the resources folder using an absolute classpath
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/com/chatapp/client/chat.fxml")
        );

        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Chat Client");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
