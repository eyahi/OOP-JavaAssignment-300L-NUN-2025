package com.texteditor.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import com.texteditor.command.Command;
import com.texteditor.command.DeleteCommand;
import com.texteditor.command.InsertCommand;
import com.texteditor.util.UndoStack;
import com.texteditor.model.Document;

public class TextEditor {
    private Document document;
    private UndoStack undoStack;
    private Scanner scanner;

    public TextEditor() {
        document = new Document();
        undoStack = new UndoStack();
        scanner = new Scanner(System.in);
    }

    private void openFile() {
        System.out.print("Enter file path to open: ");
        String path = scanner.nextLine();
        try {
            String content = Files.readString(Path.of(path));
            document = new Document();
            document.insertText(0, content);
            document.setFilePath(path);
            document.setModified(false);
            System.out.println("File opened.");
        } catch (IOException e) {
            System.out.println("Failed to read file.");
        }
    }

    private void saveFile() {
        if (document.getFilePath() != null) {
            try {
                Files.writeString(Path.of(document.getFilePath()), document.getContent());
                document.setModified(false);
                System.out.println("File saved.");
            } catch (IOException e) {
                System.out.println("Failed to save file.");
            }
        } else {
            saveAsFile();
        }
    }

    private void saveAsFile() {
        System.out.print("Enter file path to save as: ");
        String path = scanner.nextLine();
        try {
            Files.writeString(Path.of(path), document.getContent());
            document.setFilePath(path);
            document.setModified(false);
            System.out.println("File saved.");
        } catch (IOException e) {
            System.out.println("Failed to save file.");
        }
    }

    private void insertText() {
        System.out.print("Enter position: ");
        int pos = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter text to insert: ");
        String text = scanner.nextLine();
        Command cmd = new InsertCommand(document, pos, text);
        cmd.execute();
        undoStack.push(cmd);
    }

    private void deleteText() {
        System.out.print("Enter start position: ");
        int start = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter end position: ");
        int end = Integer.parseInt(scanner.nextLine());
        Command cmd = new DeleteCommand(document, start, end);
        cmd.execute();
        undoStack.push(cmd);
    }

    private void run() {
        while (true) {
            System.out.println("\nOptions: open | save | saveas | insert | delete | undo | redo | display | exit");
            System.out.print("Enter command: ");
            String choice = scanner.nextLine().trim().toLowerCase();

            switch (choice) {
                case "open" -> openFile();
                case "save" -> saveFile();
                case "saveas" -> saveAsFile();
                case "insert" -> insertText();
                case "delete" -> deleteText();
                case "undo" -> undoStack.undo();
                case "redo" -> undoStack.redo();
                case "display" -> System.out.println("Document Content:\n" + document.getContent());
                case "exit" -> {
                    if (document.isModified()) {
                        System.out.print("Save changes before exit? (yes/no): ");
                        if (scanner.nextLine().equalsIgnoreCase("yes")) saveFile();
                    }
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid command.");
            }
        }
    }

    public static void main(String[] args) {
        new TextEditor().run();
    }
}
