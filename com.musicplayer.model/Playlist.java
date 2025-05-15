package com.musicplayer.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String name;
    private List<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        if (song != null) {
            songs.add(song);
        }
    }

    public void removeSong(String title) {
        // Remove the first song that matches the given title (if any)
        songs.removeIf(song -> song.getTitle().equals(title));
    }

    public void displayPlaylist() {
        if (songs.isEmpty()) {
            System.out.println("Playlist \"" + name + "\" is empty.");
        } else {
            System.out.println("Playlist: " + name);
            for (Song song : songs) {
                System.out.println(" - " + song.toString());
            }
        }
    }
}
