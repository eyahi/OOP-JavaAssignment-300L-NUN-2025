package com.musicplayer.main;

import com.musicplayer.model.*;
import com.musicplayer.gui.MusicPlayerGUI;

import javax.swing.*;
import java.io.File;

/**
 * The main class for the Music Player application.
 * This class initializes the application and loads sample data.
 */
public class MusicPlayerApp {

    /**
     * The main method that starts the application.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Set the look and feel to the system's look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create the music player model
        MusicPlayer musicPlayer = new MusicPlayer();

        // Add some sample songs if they exist
        addSampleSongs(musicPlayer);

        // Create sample playlists
        createSamplePlaylists(musicPlayer);

        // Launch the GUI
        SwingUtilities.invokeLater(() -> {
            MusicPlayerGUI gui = new MusicPlayerGUI(musicPlayer);
            gui.setVisible(true);
        });
    }

    /**
     * Add sample songs to the music player library.
     * This method checks for audio files in a "samples" directory.
     *
     * @param musicPlayer The music player model
     */
    private static void addSampleSongs(MusicPlayer musicPlayer) {
        // Create a "samples" directory in the project folder if it doesn't exist
        File samplesDir = new File("samples");
        if (!samplesDir.exists()) {
            samplesDir.mkdir();
        }

        // Check for any audio files in the samples directory
        File[] audioFiles = samplesDir.listFiles((dir, name) ->
                name.toLowerCase().endsWith(".wav") ||
                        name.toLowerCase().endsWith(".mp3") ||
                        name.toLowerCase().endsWith(".aiff"));

        if (audioFiles != null && audioFiles.length > 0) {
            // Add audio files found in the samples directory
            for (File file : audioFiles) {
                String title = getFileNameWithoutExtension(file);
                musicPlayer.addSongToLibrary(new Song(title, "Sample Artist", "Sample Genre", file.getAbsolutePath()));
            }
        } else {
            // Add some dummy songs (these won't actually play but will demonstrate the UI)
            musicPlayer.addSongToLibrary(new Song("Sample Song 1", "Artist 1", "Rock", "samples/song1.mp3"));
            musicPlayer.addSongToLibrary(new Song("Sample Song 2", "Artist 2", "Pop", "samples/song2.mp3"));
            musicPlayer.addSongToLibrary(new Song("Sample Song 3", "Artist 3", "Jazz", "samples/song3.mp3"));
            musicPlayer.addSongToLibrary(new Song("Sample Song 4", "Artist 4", "Classical", "samples/song4.mp3"));
            musicPlayer.addSongToLibrary(new Song("Sample Song 5", "Artist 5", "Hip Hop", "samples/song5.mp3"));

            // Show a message informing the user about adding their own songs
            JOptionPane.showMessageDialog(null,
                    "No audio files found in the 'samples' directory.\n" +
                            "Sample songs have been added for demonstration purposes.\n" +
                            "You can add your own songs using the 'Add Song' button.",
                    "Sample Data", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Create sample playlists in the music player.
     *
     * @param musicPlayer The music player model
     */
    private static void createSamplePlaylists(MusicPlayer musicPlayer) {
        if (musicPlayer.getLibrary().isEmpty()) {
            return;
        }

        // Create "Favorites" playlist
        musicPlayer.createPlaylist("Favorites");

        // Add some songs to the playlist
        int count = Math.min(3, musicPlayer.getLibrary().size());
        for (int i = 0; i < count; i++) {
            musicPlayer.addSongToPlaylist("Favorites", musicPlayer.getLibrary().get(i));
        }

        // Create another playlist if we have enough songs
        if (musicPlayer.getLibrary().size() >= 4) {
            musicPlayer.createPlaylist("My Playlist");

            // Add different songs to this playlist
            int startIndex = musicPlayer.getLibrary().size() / 2;
            count = Math.min(2, musicPlayer.getLibrary().size() - startIndex);
            for (int i = 0; i < count; i++) {
                musicPlayer.addSongToPlaylist("My Playlist", musicPlayer.getLibrary().get(startIndex + i));
            }
        }
    }

    /**
     * Get the file name without its extension.
     *
     * @param file The file
     * @return The file name without extension
     */
    private static String getFileNameWithoutExtension(File file) {
        String fileName = file.getName();
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return fileName.substring(0, lastDotIndex);
        }
        return fileName;
    }
}