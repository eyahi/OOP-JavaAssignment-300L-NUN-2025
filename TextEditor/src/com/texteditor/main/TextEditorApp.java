package com.texteditor.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.io.*;

import com.texteditor.util.UndoManager;

public class TextEditorApp extends Application {

    private TextArea textArea;
    private UndoManager undoManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        textArea = new TextArea();
        undoManager = new UndoManager(textArea);

        MenuBar menuBar = createMenuBar(primaryStage);

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(textArea);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("JavaFX Text Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private MenuBar createMenuBar(Stage stage) {
        // File Menu
        Menu fileMenu = new Menu("File");
        MenuItem openItem = new MenuItem("Open...");
        MenuItem saveItem = new MenuItem("Save As...");
        MenuItem exitItem = new MenuItem("Exit");

        openItem.setOnAction(e -> openFile(stage));
        saveItem.setOnAction(e -> saveFile(stage));
        exitItem.setOnAction(e -> stage.close());

        fileMenu.getItems().addAll(openItem, saveItem, new SeparatorMenuItem(), exitItem);

        // Edit Menu
        Menu editMenu = new Menu("Edit");
        MenuItem undoItem = new MenuItem("Undo");
        MenuItem redoItem = new MenuItem("Redo");

        undoItem.setOnAction(e -> undoManager.undo());
        redoItem.setOnAction(e -> undoManager.redo());

        editMenu.getItems().addAll(undoItem, redoItem);

        return new MenuBar(fileMenu, editMenu);
    }

    private void openFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text File");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.clear();
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.appendText(line + "\n");
                }
                undoManager.reset();
            } catch (IOException ex) {
                showAlert("Error", "Failed to open file: " + ex.getMessage());
            }
        }
    }

    private void saveFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Text File");
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
            } catch (IOException ex) {
                showAlert("Error", "Failed to save file: " + ex.getMessage());
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle(title);
        alert.showAndWait();
    }
}
