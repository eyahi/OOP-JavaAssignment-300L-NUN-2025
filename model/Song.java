package com.musicplayer.model;

/**
 * The Song class represents a music track in the music player application.
 * It stores information about the song such as title, artist, genre, and file path.
 */
public class Song {
    private String title;
    private String artist;
    private String genre;
    private String filePath;

    /**
     * Constructor to initialize a Song object with all its attributes.
     *
     * @param title    The title of the song
     * @param artist   The artist of the song
     * @param genre    The genre of the song
     * @param filePath The path to the song file
     */
    public Song(String title, String artist, String genre, String filePath) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.filePath = filePath;
    }

    /**
     * Get the title of the song.
     *
     * @return The title of the song
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the artist of the song.
     *
     * @return The artist of the song
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Get the genre of the song.
     *
     * @return The genre of the song
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Get the file path of the song.
     *
     * @return The file path of the song
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Override the toString method to provide a user-friendly string
     * representation of the song.
     *
     * @return A string representation of the song
     */
    @Override
    public String toString() {
        return "Title: " + title + ", Artist: " + artist + ", Genre: " + genre;
    }
}