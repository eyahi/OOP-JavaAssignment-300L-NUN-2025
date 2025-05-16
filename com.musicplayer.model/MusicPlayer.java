package com.musicplayer.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicPlayer {
    private List<Song> library;
    private Map<String, Playlist> playlists;
    private Song currentSong;
    private boolean isPlaying;

    public MusicPlayer() {
        library = new ArrayList<>();
        playlists = new HashMap<>();
        currentSong = null;
        isPlaying = false;
    }

    public void addSongToLibrary(Song song) {
        if (song != null) {
            library.add(song);
        }
    }

    public Song getSongFromLibrary(String title) {
        for (Song song : library) {
            if (song.getTitle().equals(title)) {
                return song;
            }
        }
        return null;
    }

    public List<Song> getAllSongs() {
        return library;
    }

    // ✅ Cleaner method name for general library access
    public List<Song> getLibrary() {
        return library;
    }

    public void createPlaylist(String name) {
        if (name != null && !name.isEmpty() && !playlists.containsKey(name)) {
            playlists.put(name, new Playlist(name));
        }
    }

    public Playlist getPlaylist(String name) {
        return playlists.get(name);
    }

    public void addSongToPlaylist(String playlistName, Song song) {
        Playlist playlist = playlists.get(playlistName);
        if (playlist != null && song != null) {
            playlist.addSong(song);
        }
    }

    public void removeSongFromPlaylist(String playlistName, String songTitle) {
        Playlist playlist = playlists.get(playlistName);
        if (playlist != null && songTitle != null) {
            playlist.removeSong(songTitle);
        }
    }

    public void play(Song song) {
        if (song != null) {
            currentSong = song;
            isPlaying = true;
            System.out.println("Playing: " + song.getTitle());
        }
    }

    public void pause() {
        if (currentSong != null && isPlaying) {
            isPlaying = false;
            System.out.println("Paused: " + currentSong.getTitle());
        }
    }

    public void stop() {
        if (currentSong != null) {
            isPlaying = false;
            System.out.println("Stopped: " + currentSong.getTitle());
            currentSong = null;
        }
    }

    public void displayAllSongs() {
        if (library.isEmpty()) {
            System.out.println("Library is empty.");
        } else {
            System.out.println("All Songs in Library:");
            for (Song song : library) {
                System.out.println(" - " + song);
            }
        }
    }

    public void displayAllPlaylists() {
        if (playlists.isEmpty()) {
            System.out.println("No playlists available.");
        } else {
            System.out.println("Playlists:");
            for (String name : playlists.keySet()) {
                System.out.println(" * " + name);
            }
        }
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
