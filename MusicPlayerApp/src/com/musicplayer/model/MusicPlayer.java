package com.musicplayer.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class MusicPlayer {
    private final List<Song> library = new ArrayList<>();
    private final Map<String, Playlist> playlists = new HashMap<>();
    private MediaPlayer fxPlayer;
    private Iterator<Song> playlistIterator;

    // Adds a song to the main library
    public void addSongToLibrary(Song song) {
        library.add(song);
    }

    // Returns an unmodifiable view of the library
    public List<Song> getLibrary() {
        return Collections.unmodifiableList(library);
    }

    // Returns all library songs whose title contains the given term
    public List<Song> searchByTitle(String term) {
        String lower = term.toLowerCase();
        return library.stream()
                      .filter(s -> s.getTitle().toLowerCase().contains(lower))
                      .collect(Collectors.toList());
    }

    // Creates a new empty playlist (if absent).
    public void createPlaylist(String name) {
        playlists.putIfAbsent(name, new Playlist(name));
    }

    // Returns the set of all playlist names
    public Set<String> getPlaylistNames() {
        return playlists.keySet();
    }

    // Returns the songs in the named playlist, or an empty list if none
    public List<Song> getPlaylistSongs(String name) {
        Playlist pl = playlists.get(name);
        return pl != null ? pl.getSongs() : List.of();
    }

    // Adds a song to the given playlist.
    public void addSongToPlaylist(String name, Song song) {
        Playlist pl = playlists.get(name);
        if (pl != null) pl.addSong(song);
    }

    // Removes a song (by title) from the given playlist.
    public void removeFromPlaylist(String name, Song song) {
        Playlist pl = playlists.get(name);
        if (pl != null) pl.removeSong(song.getTitle());
    }

    // Plays a single song (stopping any current playback)
    public void play(Song song) {
        if (song == null) return;
        stop();  // ensure no overlap
        fxPlayer = new MediaPlayer(new Media(new File(song.getFilePath()).toURI().toString()));
        fxPlayer.play();
    }
    public void pause() {
    if (fxPlayer != null && fxPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
        fxPlayer.pause();
    }
}

// Resumes playback from the paused position
public void resume() {
    if (fxPlayer != null && fxPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
        fxPlayer.play();
    }
}

    // Stops any current playback and clears playlist state
    public void stop() {
        if (fxPlayer != null) {
            fxPlayer.stop();
            fxPlayer = null;
            playlistIterator = null;
        }
    }

    /**
     * Starts streaming the named playlist in sequence.
     * Sets up an internal iterator and begins with the first song.
     */
    public void playPlaylist(String name) {
        List<Song> songs = getPlaylistSongs(name);
        if (!songs.isEmpty()) {
            playlistIterator = songs.iterator();
            playNextInPlaylist();
        }
    }
    public MediaPlayer.Status getFxPlayerStatus() {
    return fxPlayer == null ? null : fxPlayer.getStatus();
}

    // Internal helper: plays the next song in the current playlist iterator
    private void playNextInPlaylist() {
        if (playlistIterator != null && playlistIterator.hasNext()) {
            Song next = playlistIterator.next();
            // stop current, then set up new player
            stop();
            fxPlayer = new MediaPlayer(new Media(new File(next.getFilePath()).toURI().toString()));
            // when this song finishes, trigger the next one
            fxPlayer.setOnEndOfMedia(this::playNextInPlaylist);
            fxPlayer.play();
        } else {
            // no more songs
            playlistIterator = null;
        }
    }
}
