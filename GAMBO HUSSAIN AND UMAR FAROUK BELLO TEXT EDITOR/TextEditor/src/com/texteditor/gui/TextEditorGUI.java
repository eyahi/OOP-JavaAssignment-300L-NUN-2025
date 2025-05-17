package com.texteditor.gui;

import com.texteditor.model.Document;
import com.texteditor.command.*;
import com.texteditor.util.UndoStack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;

public class TextEditorGUI extends JFrame {
    private JTextArea textArea;
    private Document document;
    private UndoStack undoStack;

    public TextEditorGUI() {
        document = new Document();
        undoStack = new UndoStack();
        initUI();
    }

    private void initUI() {
        setTitle("Java Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        createMenuBar();

        setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem saveAsItem = new JMenuItem("Save As");
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);

        // Edit Menu
        JMenu editMenu = new JMenu("Edit");
        JMenuItem insertItem = new JMenuItem("Insert");
        JMenuItem deleteItem = new JMenuItem("Delete");
        JMenuItem undoItem = new JMenuItem("Undo");
        JMenuItem redoItem = new JMenuItem("Redo");
        editMenu.add(insertItem);
        editMenu.add(deleteItem);
        editMenu.add(undoItem);
        editMenu.add(redoItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        setJMenuBar(menuBar);

        // Listeners
        openItem.addActionListener(e -> openFile());
        saveItem.addActionListener(e -> saveFile());
        saveAsItem.addActionListener(e -> saveAsFile());
        insertItem.addActionListener(e -> insertText());
        deleteItem.addActionListener(e -> deleteText());
        undoItem.addActionListener(e -> undo());
        redoItem.addActionListener(e -> redo());
    }

    private void openFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                String content = Files.readString(file.toPath());
                document = new Document(); // reset
                document.insertText(0, content);
                document.setFilePath(file.getAbsolutePath());
                document.setModified(false);
                textArea.setText(content);
            } catch (IOException ex) {
                showError("Error opening file.");
            }
        }
    }

    private void saveFile() {
        if (document.getFilePath() == null) {
            saveAsFile();
            return;
        }
        try {
            Files.writeString(Path.of(document.getFilePath()), textArea.getText());
            document.setModified(false);
        } catch (IOException ex) {
            showError("Error saving file.");
        }
    }

    private void saveAsFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                Files.writeString(file.toPath(), textArea.getText());
                document.setFilePath(file.getAbsolutePath());
                document.setModified(false);
            } catch (IOException ex) {
                showError("Error saving file.");
            }
        }
    }

    private void insertText() {
        String input = JOptionPane.showInputDialog(this, "Enter position and text (e.g. 5 Hello):");
        if (input == null || !input.contains(" ")) return;
        String[] parts = input.split(" ", 2);
        try {
            int position = Integer.parseInt(parts[0]);
            String text = parts[1];
            Command cmd = new InsertCommand(document, position, text);
            cmd.execute();
            undoStack.push(cmd);
            textArea.setText(document.getContent());
        } catch (Exception e) {
            showError("Invalid input.");
        }
    }

    private void deleteText() {
        String input = JOptionPane.showInputDialog(this, "Enter start and end position to delete (e.g. 5 10):");
        if (input == null || !input.contains(" ")) return;
        String[] parts = input.split(" ");
        try {
            int start = Integer.parseInt(parts[0]);
            int end = Integer.parseInt(parts[1]);
            Command cmd = new DeleteCommand(document, start, end);
            cmd.execute();
            undoStack.push(cmd);
            textArea.setText(document.getContent());
        } catch (Exception e) {
            showError("Invalid input.");
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

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TextEditorGUI::new);
    }
}

// This code defines a simple text editor GUI using Swing. It allows users to open, save, and edit text files, as well as perform undo and redo operations. The editor uses a Document model to manage the text content and an UndoStack to handle command history for undo/redo functionality.


//javac -d out src/com/texteditor/**/*.java this compiles the code
//java -cp out com.texteditor.gui.TextEditorGUI this executes the code(runs the code)