package com.texteditor.model;

public class Document {
    private StringBuilder content;
    private String filePath;
    private boolean isModified;

    public Document() {
        this.content = new StringBuilder();
        this.filePath = null;
        this.isModified = false;
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

    public void setContent(String content) {
        this.content = new StringBuilder(content);
        isModified = true;
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