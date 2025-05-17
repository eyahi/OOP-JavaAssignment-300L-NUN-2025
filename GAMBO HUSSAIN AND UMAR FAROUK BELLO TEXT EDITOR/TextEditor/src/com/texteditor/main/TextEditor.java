package com.texteditor.main;

import com.texteditor.command.*;
import com.texteditor.model.Document;
import com.texteditor.util.UndoStack;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class TextEditor {
    private Document document;
    private UndoStack undoStack;
    private Scanner scanner;

    public TextEditor() {
        document = new Document();
        undoStack = new UndoStack();
        scanner = new Scanner(System.in);
    }

    public void openFile() {
        System.out.print("Enter the file path to open: ");
        String filePath = scanner.nextLine();
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            document.setFilePath(filePath);
            document.setModified(false);
            System.out.println("File opened successfully.");
            System.out.println(content);
        } catch (IOException e) {
            System.out.println("Error opening the file: " + e.getMessage());
        }
    }

    public void saveFile() {
        if (document.getFilePath() != null) {
            try {
                Files.write(Paths.get(document.getFilePath()), document.getContent().getBytes());
                document.setModified(false);
                System.out.println("File saved successfully.");
            } catch (IOException e) {
                System.out.println("Error saving the file: " + e.getMessage());
            }
        } else {
            saveAsFile();
        }
    }

    public void saveAsFile() {
        System.out.print("Enter the file path to save as: ");
        String filePath = scanner.nextLine();
        try {
            Files.write(Paths.get(filePath), document.getContent().getBytes());
            document.setFilePath(filePath);
            document.setModified(false);
            System.out.println("File saved as " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving the file: " + e.getMessage());
        }
    }

    public void insertText() {
        System.out.print("Enter the position to insert text: ");
        int position = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the text to insert: ");
        String text = scanner.nextLine();
        InsertCommand command = new InsertCommand(document, position, text);
        command.execute();
        undoStack.push(command);
        document.setModified(true);
    }

    public void deleteText() {
        System.out.print("Enter the start position to delete text: ");
        int start = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the end position to delete text: ");
        int end = Integer.parseInt(scanner.nextLine());
        DeleteCommand command = new DeleteCommand(document, start, end);
        command.execute();
        undoStack.push(command);
        document.setModified(true);
    }

    public void undo() {
        undoStack.undo();
    }

    public void redo() {
        undoStack.redo();
    }

    public void displayDocument() {
        System.out.println("\nCurrent Document:");
        System.out.println(document.getContent());
    }

    public void run() {
        while (true) {
            System.out.println("\nText Editor Menu:");
            System.out.println("1. Open File");
            System.out.println("2. Save File");
            System.out.println("3. Save As File");
            System.out.println("4. Insert Text");
            System.out.println("5. Delete Text");
            System.out.println("6. Undo");
            System.out.println("7. Redo");
            System.out.println("8. Display Document");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    openFile();
                    break;
                case 2:
                    saveFile();
                    break;
                case 3:
                    saveAsFile();
                    break;
                case 4:
                    insertText();
                    break;
                case 5:
                    deleteText();
                    break;
                case 6:
                    undo();
                    break;
                case 7:
                    redo();
                    break;
                case 8:
                    displayDocument();
                    break;
                case 9:
                    if (document.isModified()) {
                        System.out.print("You have unsaved changes. Do you want to save before exiting (y/n)? ");
                        String saveChoice = scanner.nextLine();
                        if (saveChoice.equalsIgnoreCase("y")) {
                            saveFile();
                        }
                    }
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
        textEditor.run();
    }
}
// This code defines a TextEditor class that provides a simple command-line text editor with basic functionalities like opening, saving, inserting, deleting text, and undo/redo operations.
