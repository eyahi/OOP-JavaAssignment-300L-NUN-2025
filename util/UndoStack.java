package com.texteditor.util;

import java.util.Stack;

import com.texteditor.command.Command;

public class UndoStack {
    private Stack<Command> undoStack;
    private Stack<Command> redoStack;

    public UndoStack() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void push(Command command) {
        undoStack.push(command);
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.undo();
            redoStack.push(command);
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        } else {
            System.out.println("Nothing to redo.");
        }
    }
}
