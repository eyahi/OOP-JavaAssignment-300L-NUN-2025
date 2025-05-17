package com.musicplayer.model;

import java.util.*;

public class MusicPlayer {
    private List<Song> library;
    private Map<String, Playlist> playlists;
    private Song currentSong;
    private boolean isPlaying;

    public MusicPlayer() {
        library = new ArrayList<>();
        playlists = new HashMap<>();
    }

    public void addSongToLibrary(Song song) {
        library.add(song);
    }

    public Song getSongFromLibrary(String title) {
        for (Song song : library) {
            if (song.getTitle().equalsIgnoreCase(title)) return song;
        }
        return null;
    }

    public void createPlaylist(String name) {
        if (!playlists.containsKey(name)) {
            playlists.put(name, new Playlist(name));
        }
    }

    public Playlist getPlaylist(String name) {
        return playlists.get(name);
    }

    public void addSongToPlaylist(String playlistName, Song song) {
        Playlist playlist = playlists.get(playlistName);
        if (playlist != null) playlist.addSong(song);
    }

    public void removeSongFromPlaylist(String playlistName, String songTitle) {
        Playlist playlist = playlists.get(playlistName);
        if (playlist != null) playlist.removeSong(songTitle);
    }

    public void play(Song song) {
        if (isPlaying) stop();
        currentSong = song;
        isPlaying = true;
        System.out.println("Playing: " + song.getTitle() + " by " + song.getArtist());
    }

    public void pause() {
        if (isPlaying) {
            isPlaying = false;
            System.out.println("Paused.");
        } else {
            System.out.println("No song is currently playing.");
        }
    }

    public void stop() {
        if (isPlaying) {
            isPlaying = false;
            currentSong = null;
            System.out.println("Playback stopped.");
        } else {
            System.out.println("No song is playing.");
        }
    }

    public void displayAllSongs() {
        for (Song song : library) {
            System.out.println(song);
        }
    }

    public void displayAllPlaylists() {
        for (String name : playlists.keySet()) {
            System.out.println(name);
        }
    }
}
