package com.texteditor.model;

public class Document {
    private StringBuilder content;
    private String filePath;
    private boolean isModified;

    public Document() {
        content = new StringBuilder();
        filePath = null;
        isModified = false;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isModified() {
        return isModified;
    }

    public void setModified(boolean modified) {
        isModified = modified;
    }

    public String getContent() {
        return content.toString();
    }

    public void insertText(int position, String text) {
        content.insert(position, text);
        isModified = true;
    }

    public void deleteText(int start, int end) {
        content.delete(start, end);
        isModified = true;
    }
}
