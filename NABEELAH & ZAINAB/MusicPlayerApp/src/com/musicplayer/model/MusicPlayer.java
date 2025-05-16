package com.musicplayer.model;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayer {
    private List<Playlist> playlists = new ArrayList<>();
    private List<Song> songLibrary = new ArrayList<>();

    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
        System.out.println("Playlist created: " + playlist.getName()); 
    }
 
    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void addSongToLibrary(Song song) {
        songLibrary.add(song);
        System.out.println("Song added to library: " + song.getTitle());
    }

    public List<Song> getLibrary() {
        return songLibrary;
    }
}
// // This code defines a MusicPlayer class that manages a library of songs and playlists. It includes methods to add songs to the library and create playlists.