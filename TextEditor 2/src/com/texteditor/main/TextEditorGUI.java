
package com.texteditor.main;

import com.texteditor.command.*;
import com.texteditor.model.Document;
import com.texteditor.util.UndoStack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class TextEditorGUI extends JFrame {
    private JTabbedPane tabbedPane;
    private List<Document> documents = new ArrayList<>();
    private List<UndoStack> undoStacks = new ArrayList<>();
    private JLabel statusBar;

    public TextEditorGUI() {
        setTitle("Java Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setupUI();
        setVisible(true);
    }

    private void setupUI() {
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);

        statusBar = new JLabel("Ready");
        add(statusBar, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu viewMenu = new JMenu("View");

        JMenuItem newItem = new JMenuItem("New");
        newItem.addActionListener(e -> addNewTab("Untitled", "", null));

        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(e -> openFile());

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(e -> saveFile());

        JMenuItem saveAsItem = new JMenuItem("Save As");
        saveAsItem.addActionListener(e -> saveAsFile());

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.add(exitItem);

        JMenuItem insertItem = new JMenuItem("Insert");
        insertItem.addActionListener(e -> insertText());

        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.addActionListener(e -> deleteText());

        JMenuItem undoItem = new JMenuItem("Undo");
        undoItem.addActionListener(e -> undo());

        JMenuItem redoItem = new JMenuItem("Redo");
        redoItem.addActionListener(e -> redo());

        JMenuItem wordCountItem = new JMenuItem("Word Count");
        wordCountItem.addActionListener(e -> showWordCount());

        JMenuItem findReplaceItem = new JMenuItem("Find/Replace");
        findReplaceItem.addActionListener(e -> findAndReplace());

        JMenuItem cutItem = new JMenuItem("Cut");
        cutItem.addActionListener(e -> getActiveTextArea().cut());

        JMenuItem copyItem = new JMenuItem("Copy");
        copyItem.addActionListener(e -> getActiveTextArea().copy());

        JMenuItem pasteItem = new JMenuItem("Paste");
        pasteItem.addActionListener(e -> getActiveTextArea().paste());

        JMenuItem selectAllItem = new JMenuItem("Select All");
        selectAllItem.addActionListener(e -> getActiveTextArea().selectAll());

        editMenu.add(insertItem);
        editMenu.add(deleteItem);
        editMenu.add(undoItem);
        editMenu.add(redoItem);
        editMenu.add(wordCountItem);
        editMenu.add(findReplaceItem);
        editMenu.addSeparator();
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.add(selectAllItem);

        JMenuItem fontSizeItem = new JMenuItem("Set Font Size");
        fontSizeItem.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Font Size:");
            if (input != null) {
                try {
                    int size = Integer.parseInt(input);
                    getActiveTextArea().setFont(new Font("Monospaced", Font.PLAIN, size));
                } catch (NumberFormatException ex) {
                    statusBar.setText("Invalid size");
                }
            }
        });

        JMenuItem darkModeItem = new JMenuItem("Toggle Dark Mode");
        darkModeItem.addActionListener(e -> {
            JTextArea area = getActiveTextArea();
            boolean isDark = area.getBackground().equals(Color.DARK_GRAY);
            area.setBackground(isDark ? Color.WHITE : Color.DARK_GRAY);
            area.setForeground(isDark ? Color.BLACK : Color.LIGHT_GRAY);
        });

        viewMenu.add(fontSizeItem);
        viewMenu.add(darkModeItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        setJMenuBar(menuBar);
    }

    private void addNewTab(String title, String content, String filePath) {
        JTextArea area = new JTextArea(content);
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(area);
        tabbedPane.addTab(title, scrollPane);

        Document doc = new Document();
        doc.insertText(0, content);
        doc.setFilePath(filePath);
        doc.setModified(false);

        documents.add(doc);
        undoStacks.add(new UndoStack());

        area.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { doc.setModified(true); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { doc.setModified(true); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { doc.setModified(true); }
        });
    }

    private JTextArea getActiveTextArea() {
        int index = tabbedPane.getSelectedIndex();
        if (index == -1) return new JTextArea();
        JScrollPane scrollPane = (JScrollPane) tabbedPane.getComponentAt(index);
        return (JTextArea) ((JViewport) scrollPane.getViewport()).getView();
    }

    private void openFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                String content = Files.readString(file.toPath());
                addNewTab(file.getName(), content, file.getAbsolutePath());
                statusBar.setText("Opened: " + file.getName());
            } catch (IOException e) {
                statusBar.setText("Failed to open file.");
            }
        }
    }

    private void saveFile() {
        int index = tabbedPane.getSelectedIndex();
        if (index == -1) return;

        Document doc = documents.get(index);
        JTextArea area = getActiveTextArea();

        if (doc.getFilePath() != null) {
            try {
                Files.writeString(Path.of(doc.getFilePath()), area.getText());
                doc.setModified(false);
                statusBar.setText("Saved: " + doc.getFilePath());
            } catch (IOException e) {
                statusBar.setText("Failed to save.");
            }
        } else {
            saveAsFile();
        }
    }

    private void saveAsFile() {
        int index = tabbedPane.getSelectedIndex();
        if (index == -1) return;

        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                JTextArea area = getActiveTextArea();
                Files.writeString(file.toPath(), area.getText());
                documents.get(index).setFilePath(file.getAbsolutePath());
                documents.get(index).setModified(false);
                tabbedPane.setTitleAt(index, file.getName());
                statusBar.setText("Saved as: " + file.getName());
            } catch (IOException e) {
                statusBar.setText("Failed to save.");
            }
        }
    }

    private void insertText() {
        int index = tabbedPane.getSelectedIndex();
        if (index == -1) return;

        JTextArea area = getActiveTextArea();
        Document doc = documents.get(index);
        UndoStack stack = undoStacks.get(index);

        String text = JOptionPane.showInputDialog(this, "Text to insert:");
        if (text != null) {
            int pos = area.getCaretPosition();
            Command cmd = new InsertCommand(doc, pos, text);
            cmd.execute();
            stack.push(cmd);
            area.setText(doc.getContent());
        }
    }

    private void deleteText() {
        int index = tabbedPane.getSelectedIndex();
        if (index == -1) return;

        JTextArea area = getActiveTextArea();
        Document doc = documents.get(index);
        UndoStack stack = undoStacks.get(index);

        int start = area.getSelectionStart();
        int end = area.getSelectionEnd();
        if (start != end) {
            Command cmd = new DeleteCommand(doc, start, end);
            cmd.execute();
            stack.push(cmd);
            area.setText(doc.getContent());
        } else {
            statusBar.setText("Select text to delete.");
        }
    }

    private void undo() {
        int index = tabbedPane.getSelectedIndex();
        if (index == -1) return;
        undoStacks.get(index).undo();
        getActiveTextArea().setText(documents.get(index).getContent());
    }

    private void redo() {
        int index = tabbedPane.getSelectedIndex();
        if (index == -1) return;
        undoStacks.get(index).redo();
        getActiveTextArea().setText(documents.get(index).getContent());
    }

    private void showWordCount() {
        String content = getActiveTextArea().getText();
        int words = content.trim().isEmpty() ? 0 : content.trim().split("\s+").length;
        int chars = content.length();
        JOptionPane.showMessageDialog(this, "Words: " + words + ", Characters: " + chars);
    }

    private void findAndReplace() {
        JTextArea area = getActiveTextArea();
        String find = JOptionPane.showInputDialog(this, "Find:");
        if (find != null && !find.isEmpty()) {
            String replace = JOptionPane.showInputDialog(this, "Replace with:");
            if (replace != null) {
                String updated = area.getText().replace(find, replace);
                area.setText(updated);
                documents.get(tabbedPane.getSelectedIndex()).setModified(true);
                statusBar.setText("Replaced all occurrences of '" + find + "'");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TextEditorGUI::new);
    }
}
