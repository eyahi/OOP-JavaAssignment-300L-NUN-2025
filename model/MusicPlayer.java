package com.musicplayer.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The MusicPlayer class manages the music library, playlists, and playback functionality.
 * It provides methods to manage songs, playlists, and control song playback.
 */
public class MusicPlayer {
    private List<Song> library;
    private Map<String, Playlist> playlists;
    private Song currentSong;
    private boolean isPlaying;

    /**
     * Constructor to initialize the MusicPlayer.
     */
    public MusicPlayer() {
        this.library = new ArrayList<>();
        this.playlists = new HashMap<>();
        this.currentSong = null;
        this.isPlaying = false;
    }

    /**
     * Add a song to the music library.
     *
     * @param song The song to add
     */
    public void addSongToLibrary(Song song) {
        library.add(song);
    }

    /**
     * Get a song from the library by its title.
     *
     * @param title The title of the song
     * @return The song if found, null otherwise
     */
    public Song getSongFromLibrary(String title) {
        for (Song song : library) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        return null;
    }

    /**
     * Create a new playlist with the given name.
     *
     * @param name The name of the playlist
     * @return true if the playlist was created, false if a playlist with the same name already exists
     */
    public boolean createPlaylist(String name) {
        if (playlists.containsKey(name)) {
            return false;
        }
        playlists.put(name, new Playlist(name));
        return true;
    }

    /**
     * Get a playlist by its name.
     *
     * @param name The name of the playlist
     * @return The playlist if found, null otherwise
     */
    public Playlist getPlaylist(String name) {
        return playlists.get(name);
    }

    /**
     * Add a song to a playlist.
     *
     * @param playlistName The name of the playlist
     * @param song The song to add
     * @return true if the song was added, false if the playlist was not found
     */
    public boolean addSongToPlaylist(String playlistName, Song song) {
        Playlist playlist = playlists.get(playlistName);
        if (playlist == null) {
            return false;
        }
        playlist.addSong(song);
        return true;
    }

    /**
     * Remove a song from a playlist.
     *
     * @param playlistName The name of the playlist
     * @param songTitle The title of the song to remove
     * @return true if the song was removed, false if the playlist was not found
     */
    public boolean removeSongFromPlaylist(String playlistName, String songTitle) {
        Playlist playlist = playlists.get(playlistName);
        if (playlist == null) {
            return false;
        }
        playlist.removeSong(songTitle);
        return true;
    }

    /**
     * Play a song.
     *
     * @param song The song to play
     */
    public void play(Song song) {
        if (isPlaying) {
            stop();
        }
        currentSong = song;
        isPlaying = true;
        System.out.println("Playing: " + song.getTitle() + " by " + song.getArtist());
    }

    /**
     * Pause the currently playing song.
     */
    public void pause() {
        if (isPlaying) {
            isPlaying = false;
            System.out.println("Paused: " + currentSong.getTitle());
        } else {
            System.out.println("No song is currently playing.");
        }
    }

    /**
     * Stop the currently playing song.
     */
    public void stop() {
        if (isPlaying || currentSong != null) {
            isPlaying = false;
            String songTitle = currentSong.getTitle();
            currentSong = null;
            System.out.println("Stopped: " + songTitle);
        } else {
            System.out.println("No song is currently playing.");
        }
    }

    /**
     * Display all songs in the library.
     */
    public void displayAllSongs() {
        if (library.isEmpty()) {
            System.out.println("The music library is empty.");
        } else {
            System.out.println("Music Library:");
            for (int i = 0; i < library.size(); i++) {
                System.out.println((i + 1) + ". " + library.get(i));
            }
        }
    }

    /**
     * Display all playlists.
     */
    public void displayAllPlaylists() {
        if (playlists.isEmpty()) {
            System.out.println("No playlists have been created.");
        } else {
            System.out.println("Playlists:");
            int count = 1;
            for (String playlistName : playlists.keySet()) {
                System.out.println(count + ". " + playlistName);
                count++;
            }
        }
    }

    /**
     * Get the list of all songs in the library.
     *
     * @return The list of songs
     */
    public List<Song> getLibrary() {
        return library;
    }

    /**
     * Get the map of all playlists.
     *
     * @return The map of playlists
     */
    public Map<String, Playlist> getPlaylists() {
        return playlists;
    }

    /**
     * Get the currently playing song.
     *
     * @return The current song
     */
    public Song getCurrentSong() {
        return currentSong;
    }

    /**
     * Check if a song is currently playing.
     *
     * @return true if a song is playing, false otherwise
     */
    public boolean isPlaying() {
        return isPlaying;
    }

    /**
     * Set the current song.
     *
     * @param currentSong The song to set as current
     */
    public void setCurrentSong(Song currentSong) {
        this.currentSong = currentSong;
    }

    /**
     * Set the playback state.
     *
     * @param isPlaying The playback state to set
     */
    public void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }
}