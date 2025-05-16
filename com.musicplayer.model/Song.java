package com.musicplayer.model;

public class Song {
    private final String title;
    private final String artist;
    private final String genre;
    private final String filePath;

    public Song(String title, String artist, String genre, String filePath) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.filePath = filePath;
    }

    // Getter methods for song properties
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
        // User-friendly string representation of the song
        return "Title: " + title + ", Artist: " + artist + ", Genre: " + genre;
    }
}
