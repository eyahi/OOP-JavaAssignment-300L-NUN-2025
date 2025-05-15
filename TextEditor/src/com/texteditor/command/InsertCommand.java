package com.texteditor.command;

import com.texteditor.model.Document;

public class InsertCommand implements Command {
    private Document document;
    private int position;
    private String text;

    public InsertCommand(Document document, int position, String text) {
        this.document = document;
        this.position = position;
        this.text = text;
    }

    @Override
    public void execute() {
        document.insertText(position, text);
    }

    @Override
    public void undo() {
        document.deleteText(position, position + text.length());
    }
}
