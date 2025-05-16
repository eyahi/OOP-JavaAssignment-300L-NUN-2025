package com.musicplayer.controller;

import com.musicplayer.model.MusicPlayer;
import com.musicplayer.model.Playlist;
import com.musicplayer.model.Song;
import com.musicplayer.view.MusicPlayerView;

import javax.sound.sampled.AudioInputStream;       // Represents an audio stream from a file
import javax.sound.sampled.AudioSystem;            // Provides access to audio system resources (like audio input/output)
import javax.sound.sampled.Clip;                   // A special audio line to play sound clips
import javax.sound.sampled.FloatControl;           // Controls audio properties like volume (gain)
import javax.sound.sampled.LineUnavailableException;  // Thrown when the audio line (like a Clip) is not available
import javax.sound.sampled.UnsupportedAudioFileException; // Thrown when the audio file format is unsupported
import javax.swing.*;                              // Imports all standard Swing components (JFrame, JButton, etc.)
import java.awt.event.ActionEvent;                 // Represents an action event (e.g., a button being clicked)
import java.awt.event.ActionListener;              // Interface for receiving action events
import javax.swing.event.ChangeEvent;              // Represents a change in a component (like a slider value)
import javax.swing.event.ChangeListener;           // Interface for receiving state change events
import java.io.File;                               // Represents file and directory paths
import java.io.IOException;                        // Thrown when an I/O operation fails (like reading a file)

public class MusicPlayerController implements ActionListener, ChangeListener {
    private MusicPlayer model;
    private MusicPlayerView view;
    private Clip clip;//object used to play the audio.
    private FloatControl volumeControl;
    private Timer progressTimer;

    public MusicPlayerController(MusicPlayer model, MusicPlayerView view) {
        this.model = model;
        this.view = view;
        // Register this controller as the event listener for the view
        this.view.setController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {// This is called when a button is pressed
        String command = e.getActionCommand();
        if ("Play".equals(command)) {
            handlePlayAction();
        } else if ("Pause".equals(command)) {
            handlePauseAction();
        } else if ("Stop".equals(command)) {
            handleStopAction();
        } else if ("AddSong".equals(command)) {
            handleAddSong();
        } else if ("NewPlaylist".equals(command)) {
            handleCreatePlaylist();
        } else if ("AddToPlaylist".equals(command)) {
            handleAddToPlaylist();
        } else if ("RemoveFromPlaylist".equals(command)) {
            handleRemoveFromPlaylist();
        } else if ("Previous".equals(command)) {
            handlePrevious();
        } else if ("Next".equals(command)) {
            handleNext();
        } else {
            // If no action command matched, check if it's from the playlist selection combo box
            if (e.getSource() == view.getPlaylistCombo()) {
                handlePlaylistSelection();
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // Handle volume slider adjustments
        if (e.getSource() == view.getVolumeSlider()) {
            if (volumeControl != null) {
                int sliderVal = view.getVolumeSlider().getValue();
                float volumePercent = sliderVal / 100.0f;
                float range = volumeControl.getMaximum() - volumeControl.getMinimum();
                float gain = volumeControl.getMinimum() + range * volumePercent;
                volumeControl.setValue(gain);
            }
        }
    }

    private void handlePlayAction() {
        Song selectedSong = null;
        // Determine which list the user selected a song from (playlist or library)
        Song libSelected = view.getLibraryList().getSelectedValue();
        Song plSelected = view.getPlaylistSongsList().getSelectedValue();
        if (plSelected != null) {
            selectedSong = plSelected;
        } else if (libSelected != null) {
            selectedSong = libSelected;
        }
        if (selectedSong == null) {
            // No song selected in either list
            JOptionPane.showMessageDialog(view, "Please select a song to play.");
            return;
        }
        // If a song is already playing:
        if (model.isPlaying()) {
            Song current = model.getCurrentSong();
            if (current != null && current.getTitle().equals(selectedSong.getTitle())) {
                // The selected song is already playing; do nothing
                return;
            } else {
                // A different song is playing; stop it before playing the new selection
                stopClip();
            }
        }
        // Handle play or resume logic
        Song currentSong = model.getCurrentSong();
        if (!model.isPlaying() && currentSong != null && currentSong.getTitle().equals(selectedSong.getTitle())) {
            // Resume the paused song
            if (clip != null) {
                clip.start();
                model.play(selectedSong);  // update model state (isPlaying = true)
                view.getPauseButton().setEnabled(true);
                view.getStopButton().setEnabled(true);
                startProgressTimer();
                view.updateNowPlaying(selectedSong);
            }
        } else {
            // Play a new song selection
            playNewSong(selectedSong);
        }
    }

    private void playNewSong(Song song) {
        try {
            // Prepare audio input stream from the song's file path
            File audioFile = new File(song.getFilePath());
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            // Obtain a Clip to play the audio
            if (clip != null) {
                clip.close();
            }
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            audioStream.close();
            // Set up volume control if available
            try {
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                // Map the current volume (in decibels) to the slider range 0-100
                float volRange = volumeControl.getMaximum() - volumeControl.getMinimum();
                float volValue = volumeControl.getValue();
                int sliderPos = (int) (((volValue - volumeControl.getMinimum()) / volRange) * 100);
                view.getVolumeSlider().setValue(sliderPos);
                view.getVolumeSlider().setEnabled(true);
            } catch (IllegalArgumentException ex) {
                // Volume control not supported on this system
                volumeControl = null;
                view.getVolumeSlider().setEnabled(false);
            }
            // Set up progress bar maximum based on song length in seconds
            long microLength = clip.getMicrosecondLength();
            int totalSeconds = (int) (microLength / 1000000);
            if (totalSeconds < 1) {
                totalSeconds = 1;
            }
            view.getProgressBar().setMaximum(totalSeconds);
            view.getProgressBar().setValue(0);
            // Start playback
            clip.start();
            // Enable pause and stop buttons now that playback started
            view.getPauseButton().setEnabled(true);
            view.getStopButton().setEnabled(true);
            // Update model state and UI
            model.play(song);
            view.updateNowPlaying(song);
            // Start timer to update progress bar during playback
            startProgressTimer();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            JOptionPane.showMessageDialog(view, "Error playing file: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void startProgressTimer() {
        // Stop any existing timer before starting a new one
        if (progressTimer != null && progressTimer.isRunning()) {
            progressTimer.stop();
        }
        // Update progress bar ~ twice per second
        progressTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip != null && model.isPlaying()) {
                    long microPos = clip.getMicrosecondPosition();
                    int currentSec = (int) (microPos / 1000000);
                    view.getProgressBar().setValue(currentSec);
                    // Check if playback reached the end of the track
                    if (currentSec >= view.getProgressBar().getMaximum()) {
                        progressTimer.stop();
                        model.stop();
                        view.getProgressBar().setValue(0);
                        view.updateNowPlaying(null);
                        view.getPauseButton().setEnabled(false);
                        view.getStopButton().setEnabled(false);
                        if (clip != null) {
                            clip.stop();
                            clip.close();
                        }
                    }
                }
            }
        });
        progressTimer.start();
    }

    private void handlePauseAction() {
        if (model.isPlaying() && clip != null) {
            // Pause playback
            clip.stop();
            model.pause();
            // Stop updating progress (playback is paused)
            if (progressTimer != null) {
                progressTimer.stop();
            }
            // (Pause button remains enabled; user can press Play to resume)
        }
    }

    private void handleStopAction() {
        if (clip != null) {
            // Stop playback and reset clip to beginning, then close it
            clip.stop();
            clip.setFramePosition(0);
            clip.close();
        }
        model.stop();
        if (progressTimer != null) {
            progressTimer.stop();
        }
        // Reset UI elements
        view.getProgressBar().setValue(0);
        view.updateNowPlaying(null);
        // Disable volume slider and playback control buttons since no song is active
        view.getVolumeSlider().setEnabled(false);
        view.getPauseButton().setEnabled(false);
        view.getStopButton().setEnabled(false);
    }

    private void stopClip() {
        // Helper method to stop and release the current clip (used when switching songs)
        if (clip != null) {
            clip.stop();
            clip.close();
        }
        if (progressTimer != null) {
            progressTimer.stop();
        }
        model.stop();
        view.getProgressBar().setValue(0);
        view.updateNowPlaying(null);
        view.getVolumeSlider().setEnabled(false);
        view.getPauseButton().setEnabled(false);
        view.getStopButton().setEnabled(false);
    }

    private void handleAddSong() {
        // Show file chooser to pick a WAV or MP3 file
        File file = view.showFileChooser();
        if (file != null) {
            String filename = file.getName();
            String title = filename.replaceFirst("\\.[^.]+$", ""); // Remove extension

            // Prompt the user for artist and genre
            String artist = JOptionPane.showInputDialog(view, "Enter artist name:");
            if (artist == null || artist.trim().isEmpty()) {
                artist = "Unknown Artist";
            }

            String genre = JOptionPane.showInputDialog(view, "Enter genre:");
            if (genre == null || genre.trim().isEmpty()) {
                genre = "Unknown Genre";
            }

            // Create and add the song
            Song newSong = new Song(title, artist.trim(), genre.trim(), file.getAbsolutePath());
            model.addSongToLibrary(newSong);
            view.addSongToLibraryList(newSong);
        }
    }


    private void handleCreatePlaylist() {
        // Prompt for a new playlist name
        String playlistName = JOptionPane.showInputDialog(view, "Enter playlist name:");
        if (playlistName != null) {
            playlistName = playlistName.trim();
            if (!playlistName.isEmpty()) {
                if (model.getPlaylist(playlistName) != null) {
                    // Playlist name already exists
                    JOptionPane.showMessageDialog(view, "Playlist \"" + playlistName + "\" already exists.");
                } else {
                    model.createPlaylist(playlistName);
                    view.addPlaylistName(playlistName);
                }
            }
        }
    }

    private void handlePlaylistSelection() {
        // When a playlist is selected from the combo box, display its songs
        String selectedPlaylist = (String) view.getPlaylistCombo().getSelectedItem();
        if (selectedPlaylist != null) {
            Playlist pl = model.getPlaylist(selectedPlaylist);
            view.showPlaylistSongs(pl);
        }
    }

    private void handleAddToPlaylist() {
        // Add the selected library song to the currently selected playlist
        String playlistName = (String) view.getPlaylistCombo().getSelectedItem();
        Song selectedSong = view.getLibraryList().getSelectedValue();
        if (playlistName == null) {
            JOptionPane.showMessageDialog(view, "Please create or select a playlist first.");
            return;
        }
        if (selectedSong == null) {
            JOptionPane.showMessageDialog(view, "Please select a song from the library to add.");
            return;
        }
        model.addSongToPlaylist(playlistName, selectedSong);
        Playlist pl = model.getPlaylist(playlistName);
        view.showPlaylistSongs(pl);
    }

    private void handleRemoveFromPlaylist() {
        // Remove the selected song from the currently selected playlist
        String playlistName = (String) view.getPlaylistCombo().getSelectedItem();
        Song selectedSong = view.getPlaylistSongsList().getSelectedValue();
        if (playlistName == null || selectedSong == null) {
            return;
        }
        model.removeSongFromPlaylist(playlistName, selectedSong.getTitle());
        Playlist pl = model.getPlaylist(playlistName);
        view.showPlaylistSongs(pl);
    }
    private void handlePrevious() {
        Song current = model.getCurrentSong();
        if (current == null) return;

        java.util.List<Song> contextList = getContextSongList();
        int index = contextList.indexOf(current);
        if (index > 0) {
            Song previousSong = contextList.get(index - 1);
            stopClip();
            playNewSong(previousSong);
        } else {
            JOptionPane.showMessageDialog(view, "This is the first song in the list.");
        }
    }

    private void handleNext() {
        Song current = model.getCurrentSong();
        if (current == null) return;

        java.util.List<Song> contextList = getContextSongList();
        int index = contextList.indexOf(current);
        if (index >= 0 && index < contextList.size() - 1) {
            Song nextSong = contextList.get(index + 1);
            stopClip();
            playNewSong(nextSong);
        } else {
            JOptionPane.showMessageDialog(view, "This is the last song in the list.");
        }
    }

    private java.util.List<Song> getContextSongList() {
        Song current = model.getCurrentSong();
        if (current == null) return model.getLibrary();

        String playlistName = (String) view.getPlaylistCombo().getSelectedItem();
        if (playlistName != null) {
            Playlist pl = model.getPlaylist(playlistName);
            if (pl != null && pl.getSongs().contains(current)) {
                return pl.getSongs();
            }
        }

        return model.getLibrary();
    }

}
