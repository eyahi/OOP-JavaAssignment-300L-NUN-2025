package com.musicplayer.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String name;
    private List<Song> songs = new ArrayList<>();

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public String getName() {
         return name; 
        }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(String title) {
        songs.removeIf(s -> s.getTitle().equalsIgnoreCase(title));
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void displayPlaylist() {
        if (songs.isEmpty()) {
            System.out.println("Playlist is empty.");
        } else {
            songs.forEach(s -> System.out.println(s));
        }
    }
}