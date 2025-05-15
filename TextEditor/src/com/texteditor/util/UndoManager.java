package com.texteditor.util;

import javafx.scene.control.TextArea;
import java.util.Stack;

public class UndoManager {

    private final TextArea textArea;
    private final Stack<String> undoStack = new Stack<>();
    private final Stack<String> redoStack = new Stack<>();
    private String lastText = "";

    public UndoManager(TextArea textArea) {
        this.textArea = textArea;
        this.lastText = textArea.getText();

        textArea.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.equals(lastText)) {
                undoStack.push(lastText);
                lastText = newText;
                redoStack.clear();
            }
        });
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(textArea.getText());
            String previousText = undoStack.pop();
            textArea.setText(previousText);
            lastText = previousText;
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(textArea.getText());
            String nextText = redoStack.pop();
            textArea.setText(nextText);
            lastText = nextText;
        }
    }

    public void reset() {
        undoStack.clear();
        redoStack.clear();
        lastText = textArea.getText();
    }
}
