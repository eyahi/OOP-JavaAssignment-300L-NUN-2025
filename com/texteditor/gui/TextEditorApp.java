package com.texteditor.gui;

import com.texteditor.model.Document;
import com.texteditor.command.*;
import com.texteditor.util.UndoStack;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

public class TextEditorApp extends Application {
    private Document document = new Document();
    private UndoStack undoStack = new UndoStack();
    private TextArea textArea = new TextArea();
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        BorderPane root = new BorderPane();

        MenuBar menuBar = createMenuBar();
        root.setTop(menuBar);
        root.setCenter(textArea);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Text Editor");
        primaryStage.show();
    }

    private MenuBar createMenuBar() {
        Menu fileMenu = new Menu("File");
        MenuItem openItem = new MenuItem("Open");
        openItem.setOnAction(e -> openFile());
        MenuItem saveItem = new MenuItem("Save");
        saveItem.setOnAction(e -> saveFile());
        MenuItem saveAsItem = new MenuItem("Save As");
        saveAsItem.setOnAction(e -> saveAsFile());

        fileMenu.getItems().addAll(openItem, saveItem, saveAsItem);

        Menu editMenu = new Menu("Edit");
        MenuItem undoItem = new MenuItem("Undo");
        undoItem.setOnAction(e -> undo());
        MenuItem redoItem = new MenuItem("Redo");
        redoItem.setOnAction(e -> redo());

        editMenu.getItems().addAll(undoItem, redoItem);

        return new MenuBar(fileMenu, editMenu);
    }

    private void openFile() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            try {
                String content = new String(Files.readAllBytes(file.toPath()));
                document.setContent(content);
                document.setFilePath(file.getAbsolutePath());
                document.setModified(false);
                textArea.setText(content);
            } catch (IOException e) {
                showAlert("Error", "Could not open file: " + e.getMessage());
            }
        }
    }

    private void saveFile() {
        if (document.getFilePath() == null) {
            saveAsFile();
        } else {
            try {
                Files.write(new File(document.getFilePath()).toPath(), textArea.getText().getBytes());
                document.setModified(false);
            } catch (IOException e) {
                showAlert("Error", "Could not save file: " + e.getMessage());
            }
        }
    }

    private void saveAsFile() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(primaryStage);
        if (file != null) {
            try {
                Files.write(file.toPath(), textArea.getText().getBytes());
                document.setFilePath(file.getAbsolutePath());
                document.setModified(false);
            } catch (IOException e) {
                showAlert("Error", "Could not save file: " + e.getMessage());
            }
        }
    }

    private void undo() {
        undoStack.undo();
        textArea.setText(document.getContent());
    }

    private void redo() {
        undoStack.redo();
        textArea.setText(document.getContent());
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
