package com.musicplayer.model;

public class Song {
    private String title;
    private String artist;
    private String genre;
    private String filePath;

    public Song(String title, String artist, String genre, String filePath) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.filePath = filePath;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Artist: " + artist + ", Genre: " + genre;
    }
}
