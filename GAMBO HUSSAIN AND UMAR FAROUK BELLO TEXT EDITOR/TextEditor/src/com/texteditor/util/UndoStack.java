package com.texteditor.util;

import com.texteditor.command.Command;
import java.util.Stack;

public class UndoStack {
    private Stack<Command> undoStack;
    private Stack<Command> redoStack;

    public UndoStack() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void push(Command command) {
        undoStack.push(command);
        redoStack.clear(); // new action invalidates redos
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
// This code defines an UndoStack class that manages undo and redo operations using two stacks. The push method adds a command to the undo stack and clears the redo stack. The undo method pops a command from the undo stack, executes its undo operation, and pushes it onto the redo stack. The redo method pops a command from the redo stack, executes it, and pushes it back onto the undo stack. If there are no commands to undo or redo, it prints a message indicating that.
