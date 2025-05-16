package com.texteditor.command;

import com.texteditor.model.Document;

public class DeleteCommand implements Command{
    private Document document;
    private int start;
    private int end;
    private String deletedText;

    public DeleteCommand(Document document, int start, int end) {
        this.document = document;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute() {
        deletedText = document.getContent().substring(start, end);
        document.deleteText(start, end);
    }

    @Override
    public void undo() {
        document.insertText(start, deletedText);
    }
}
