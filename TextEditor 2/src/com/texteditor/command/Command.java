package com.texteditor.command;

public interface Command {
    void execute();
    void undo();
}
