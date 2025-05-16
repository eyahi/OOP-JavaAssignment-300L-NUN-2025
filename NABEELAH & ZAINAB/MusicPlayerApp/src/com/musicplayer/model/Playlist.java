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
        songs.add(song);
        System.out.println("Added " + song.getTitle() + " to playlist " + name);
    }

    public void removeSong(Song song) {
        songs.remove(song);
        System.out.println("Removed " + song.getTitle() + " from playlist " + name);
    }
}
// This code defines a Playlist class that represents a music playlist. It contains methods to add and remove songs from the playlist.