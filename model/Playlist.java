package com.musicplayer.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Playlist class represents a collection of songs that can be played together.
 * It provides methods to add, remove, and display songs in the playlist.
 */
public class Playlist {
    private String name;
    private List<Song> songs;

    /**
     * Constructor to initialize a Playlist with a name.
     *
     * @param name The name of the playlist
     */
    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    /**
     * Get the name of the playlist.
     *
     * @return The name of the playlist
     */
    public String getName() {
        return name;
    }

    /**
     * Add a song to the playlist.
     *
     * @param song The song to add
     */
    public void addSong(Song song) {
        songs.add(song);
    }

    /**
     * Remove a song from the playlist by its title.
     *
     * @param title The title of the song to remove
     */
    public void removeSong(String title) {
        songs.removeIf(song -> song.getTitle().equalsIgnoreCase(title));
    }

    /**
     * Get the list of songs in the playlist.
     *
     * @return The list of songs
     */
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * Display all songs in the playlist.
     */
    public void displayPlaylist() {
        if (songs.isEmpty()) {
            System.out.println("Playlist '" + name + "' is empty.");
        } else {
            System.out.println("Playlist: " + name);
            for (int i = 0; i < songs.size(); i++) {
                System.out.println((i + 1) + ". " + songs.get(i));
            }
        }
    }
}