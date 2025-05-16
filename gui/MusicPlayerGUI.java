package com.musicplayer.gui;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;
import com.musicplayer.model.*;
import com.musicplayer.player.AudioPlayer;
/**
 * The MusicPlayerGUI class provides a graphical user interface for the music player application.
 * It allows users to manage songs, playlists, and control playback.
 */
public class MusicPlayerGUI extends JFrame implements AudioPlayer.AudioPlayerListener {

    // Model
    private final MusicPlayer musicPlayer;
    private final AudioPlayer audioPlayer;

    // Main panels
    private JPanel mainPanel;
    private JPanel controlPanel;
    private JPanel libraryPanel;
    private JPanel playlistPanel;

    // Library components
    private JList<Song> songList;
    private DefaultListModel<Song> songListModel;
    private JButton addSongButton;
    private JButton removeSongButton;

    // Playlist components
    private JComboBox<String> playlistComboBox;
    private JList<Song> playlistSongList;
    private DefaultListModel<Song> playlistSongListModel;
    private JButton createPlaylistButton;
    private JButton addToPlaylistButton;
    private JButton removeFromPlaylistButton;

    // Playback controls
    private JButton playButton;
    private JButton pauseButton;
    private JButton stopButton;
    private JButton prevButton;
    private JButton nextButton;
    private JSlider volumeSlider;
    private JSlider progressSlider;
    private JLabel songInfoLabel;
    private JLabel timeLabel;

    // Current playlist and song index for prev/next functionality
    private Playlist currentPlaylist;
    private int currentSongIndex = -1;
    private boolean updatingProgressSlider = false;

    /**
     * Constructor to initialize the MusicPlayerGUI.
     *
     * @param musicPlayer The music player model
     */
    public MusicPlayerGUI(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
        this.audioPlayer = new AudioPlayer(this);

        setTitle("Music Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setMinimumSize(new Dimension(700, 500));

        initComponents();
        layoutComponents();

        setLocationRelativeTo(null);
    }

    /**
     * Initialize the GUI components.
     */
    private void initComponents() {
        // Initialize main panels
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Library panel
        libraryPanel = new JPanel(new BorderLayout(5, 5));
        libraryPanel.setBorder(BorderFactory.createTitledBorder("Library"));

        songListModel = new DefaultListModel<>();
        // Load songs from the model
        for (Song song : musicPlayer.getLibrary()) {
            songListModel.addElement(song);
        }

        songList = new JList<>(songListModel);
        songList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        songList.setCellRenderer(new SongCellRenderer());

        JScrollPane songScrollPane = new JScrollPane(songList);

        JPanel libraryButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addSongButton = new JButton("Add Song");
        removeSongButton = new JButton("Remove Song");

        libraryButtonPanel.add(addSongButton);
        libraryButtonPanel.add(removeSongButton);

        libraryPanel.add(songScrollPane, BorderLayout.CENTER);
        libraryPanel.add(libraryButtonPanel, BorderLayout.SOUTH);

        // Playlist panel
        playlistPanel = new JPanel(new BorderLayout(5, 5));
        playlistPanel.setBorder(BorderFactory.createTitledBorder("Playlists"));

        playlistComboBox = new JComboBox<>();
        // Load playlists from the model
        for (String playlistName : musicPlayer.getPlaylists().keySet()) {
            playlistComboBox.addItem(playlistName);
        }

        playlistSongListModel = new DefaultListModel<>();
        playlistSongList = new JList<>(playlistSongListModel);
        playlistSongList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playlistSongList.setCellRenderer(new SongCellRenderer());

        JScrollPane playlistScrollPane = new JScrollPane(playlistSongList);

        JPanel playlistTopPanel = new JPanel(new BorderLayout(5, 5));
        playlistTopPanel.add(new JLabel("Select Playlist:"), BorderLayout.WEST);
        playlistTopPanel.add(playlistComboBox, BorderLayout.CENTER);

        JPanel playlistButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        createPlaylistButton = new JButton("Create Playlist");
        addToPlaylistButton = new JButton("Add to Playlist");
        removeFromPlaylistButton = new JButton("Remove from Playlist");

        playlistButtonPanel.add(createPlaylistButton);
        playlistButtonPanel.add(addToPlaylistButton);
        playlistButtonPanel.add(removeFromPlaylistButton);

        playlistPanel.add(playlistTopPanel, BorderLayout.NORTH);
        playlistPanel.add(playlistScrollPane, BorderLayout.CENTER);
        playlistPanel.add(playlistButtonPanel, BorderLayout.SOUTH);

        // Control panel
        controlPanel = new JPanel(new BorderLayout(5, 5));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Playback Controls"));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        prevButton = new JButton("PREV");
        playButton = new JButton("PLAY");
        pauseButton = new JButton("PAUSE");
        stopButton = new JButton("STOP");
        nextButton = new JButton("NEXT");

        // Set button font and size
        Font buttonFont = new Font("Arial", Font.PLAIN, 20);
        prevButton.setFont(buttonFont);
        playButton.setFont(buttonFont);
        pauseButton.setFont(buttonFont);
        stopButton.setFont(buttonFont);
        nextButton.setFont(buttonFont);

        // Set preferred button size
        Dimension buttonSize = new Dimension(120, 50);
        prevButton.setPreferredSize(buttonSize);
        playButton.setPreferredSize(buttonSize);
        pauseButton.setPreferredSize(buttonSize);
        stopButton.setPreferredSize(buttonSize);
        nextButton.setPreferredSize(buttonSize);

        buttonPanel.add(prevButton);
        buttonPanel.add(playButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(nextButton);

//        JPanel sliderPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        songInfoLabel = new JLabel("No song playing");
        songInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        progressSlider = new JSlider(0, 100, 0);
        progressSlider.setMajorTickSpacing(10);
        progressSlider.setPaintTicks(true);

        timeLabel = new JLabel("0:00 / 0:00");
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel volumePanel = new JPanel(new BorderLayout(5, 5));
        volumePanel.add(new JLabel("Volume:"), BorderLayout.WEST);

        volumeSlider = new JSlider(0, 100, 70);  // Default volume at 70%
        volumeSlider.setMajorTickSpacing(20);
        volumeSlider.setPaintTicks(true);
        volumePanel.add(volumeSlider, BorderLayout.CENTER);

//        sliderPanel.add(songInfoLabel);

        JPanel progressPanel = new JPanel(new BorderLayout(5, 5));
        progressPanel.add(progressSlider, BorderLayout.CENTER);
        progressPanel.add(timeLabel, BorderLayout.EAST);
//        sliderPanel.add(progressPanel);

//        sliderPanel.add(volumePanel);

        controlPanel.add(buttonPanel, BorderLayout.NORTH);
//        controlPanel.add(sliderPanel, BorderLayout.CENTER);

        // Set up action listeners
        setupActionListeners();
    }

    /**
     * Set up action listeners for the GUI components.
     */
    private void setupActionListeners() {
        // Add song button
        addSongButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select Audio File");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Audio Files", "wav", "mp3", "aiff"));

            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                // Show dialog to get song info
                JTextField titleField = new JTextField(20);
                JTextField artistField = new JTextField(20);
                JTextField genreField = new JTextField(20);

                JPanel panel = new JPanel(new GridLayout(0, 1));
                panel.add(new JLabel("Title:"));
                panel.add(titleField);
                panel.add(new JLabel("Artist:"));
                panel.add(artistField);
                panel.add(new JLabel("Genre:"));
                panel.add(genreField);

                int option = JOptionPane.showConfirmDialog(this, panel, "Enter Song Details",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (option == JOptionPane.OK_OPTION) {
                    String title = titleField.getText().trim();
                    String artist = artistField.getText().trim();
                    String genre = genreField.getText().trim();

                    if (title.isEmpty()) {
                        title = selectedFile.getName();
                    }
                    if (artist.isEmpty()) {
                        artist = "Unknown Artist";
                    }
                    if (genre.isEmpty()) {
                        genre = "Unknown Genre";
                    }

                    Song newSong = new Song(title, artist, genre, selectedFile.getAbsolutePath());
                    musicPlayer.addSongToLibrary(newSong);
                    songListModel.addElement(newSong);
                }
            }
        });

        // Remove song button
        removeSongButton.addActionListener(e -> {
            int selectedIndex = songList.getSelectedIndex();
            if (selectedIndex != -1) {
                Song selectedSong = songListModel.getElementAt(selectedIndex);

                // Confirm before removing
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to remove \"" + selectedSong.getTitle() + "\" from the library?",
                        "Confirm Removal", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    // Remove from playlists first
                    for (Playlist playlist : musicPlayer.getPlaylists().values()) {
                        playlist.removeSong(selectedSong.getTitle());
                    }

                    // Update playlist view if necessary
                    if (currentPlaylist != null) {
                        updatePlaylistSongList();
                    }

                    // Remove from library and model
                    musicPlayer.getLibrary().remove(selectedSong);
                    songListModel.removeElementAt(selectedIndex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a song to remove.",
                        "No Selection", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Create playlist button
        createPlaylistButton.addActionListener(e -> {
            String playlistName = JOptionPane.showInputDialog(this, "Enter playlist name:");
            if (playlistName != null && !playlistName.trim().isEmpty()) {
                playlistName = playlistName.trim();

                if (musicPlayer.createPlaylist(playlistName)) {
                    playlistComboBox.addItem(playlistName);
                    playlistComboBox.setSelectedItem(playlistName);
                } else {
                    JOptionPane.showMessageDialog(this, "A playlist with that name already exists.",
                            "Duplicate Name", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Add to playlist button
        addToPlaylistButton.addActionListener(e -> {
            int selectedIndex = songList.getSelectedIndex();
            String selectedPlaylist = (String) playlistComboBox.getSelectedItem();

            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Please select a song from the library.",
                        "No Selection", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if (selectedPlaylist == null) {
                JOptionPane.showMessageDialog(this, "Please select or create a playlist.",
                        "No Playlist", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            Song selectedSong = songListModel.getElementAt(selectedIndex);
            if (musicPlayer.addSongToPlaylist(selectedPlaylist, selectedSong)) {
                updatePlaylistSongList();
                JOptionPane.showMessageDialog(this, "Song added to playlist.",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Remove from playlist button
        removeFromPlaylistButton.addActionListener(e -> {
            int selectedIndex = playlistSongList.getSelectedIndex();
            String selectedPlaylist = (String) playlistComboBox.getSelectedItem();

            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Please select a song from the playlist.",
                        "No Selection", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if (selectedPlaylist == null) {
                return;
            }

            Song selectedSong = playlistSongListModel.getElementAt(selectedIndex);
            if (musicPlayer.removeSongFromPlaylist(selectedPlaylist, selectedSong.getTitle())) {
                updatePlaylistSongList();
            }
        });

        // Playlist combo box
        playlistComboBox.addActionListener(e -> {
            updatePlaylistSongList();
        });

        // Double-click to play song from library
        songList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = songList.locationToIndex(e.getPoint());
                    if (index != -1) {
                        Song selectedSong = songListModel.getElementAt(index);
                        playSong(selectedSong);
                        currentPlaylist = null;
                        currentSongIndex = -1;
                    }
                }
            }
        });

        // Double-click to play song from playlist
        playlistSongList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = playlistSongList.locationToIndex(e.getPoint());
                    if (index != -1) {
                        String playlistName = (String) playlistComboBox.getSelectedItem();
                        if (playlistName != null) {
                            currentPlaylist = musicPlayer.getPlaylist(playlistName);
                            currentSongIndex = index;
                            playSong(playlistSongListModel.getElementAt(index));
                        }
                    }
                }
            }
        });

        // Playback control buttons
        playButton.addActionListener(e -> {
            if (audioPlayer.isPaused()) {
                audioPlayer.resume();
            } else {
                Song selectedSong = songList.getSelectedValue();
                if (selectedSong != null) {
                    playSong(selectedSong);
                }
            }
        });

        pauseButton.addActionListener(e -> {
            audioPlayer.pause();
        });

        stopButton.addActionListener(e -> {
            audioPlayer.stop();
            progressSlider.setValue(0);
            timeLabel.setText("0:00 / 0:00");
            songInfoLabel.setText("No song playing");
        });

        // Previous button
        prevButton.addActionListener(e -> {
            if (currentPlaylist != null && currentSongIndex > 0) {
                currentSongIndex--;
                playSong(currentPlaylist.getSongs().get(currentSongIndex));
            }
        });

        // Next button
        nextButton.addActionListener(e -> {
            if (currentPlaylist != null && currentSongIndex < currentPlaylist.getSongs().size() - 1) {
                currentSongIndex++;
                playSong(currentPlaylist.getSongs().get(currentSongIndex));
            }
        });

        // Volume slider
        volumeSlider.addChangeListener(e -> {
            float volume = volumeSlider.getValue() / 100.0f;
            audioPlayer.setVolume(volume);
        });

        // Progress slider
        progressSlider.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                updatingProgressSlider = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (audioPlayer.getCurrentSong() != null) {
                    float percentage = progressSlider.getValue() / 100.0f;
                    long newPosition = (long) (audioPlayer.getLength() * percentage);
                    audioPlayer.setPosition(newPosition);
                }
                updatingProgressSlider = false;
            }
        });
    }

    /**
     * Update the playlist song list when a playlist is selected.
     */
    private void updatePlaylistSongList() {
        playlistSongListModel.clear();
        String selectedPlaylist = (String) playlistComboBox.getSelectedItem();

        if (selectedPlaylist != null) {
            Playlist playlist = musicPlayer.getPlaylist(selectedPlaylist);
            if (playlist != null) {
                List<Song> songs = playlist.getSongs();
                for (Song song : songs) {
                    playlistSongListModel.addElement(song);
                }
            }
        }
    }

    /**
     * Layout the GUI components.
     */
    private void layoutComponents() {
        // Split panel for library and playlist
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, libraryPanel, playlistPanel);
        splitPane.setResizeWeight(0.5);

        mainPanel.add(splitPane, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
    }

    /**
     * Play a song.
     *
     * @param song The song to play
     */
    private void playSong(Song song) {
        if (song != null) {
            audioPlayer.play(song);
        }
    }

    /**
     * Format the time in microseconds to a readable string (mm:ss).
     *
     * @param microseconds The time in microseconds
     * @return A formatted time string
     */
    private String formatTime(long microseconds) {
        long seconds = microseconds / 1000000;
        long minutes = seconds / 60;
        seconds = seconds % 60;

        return String.format("%d:%02d", minutes, seconds);
    }

    // AudioPlayerListener Implementation

    @Override
    public void onPlaybackStarted(Song song) {
        SwingUtilities.invokeLater(() -> {
            songInfoLabel.setText(song.getTitle() + " - " + song.getArtist());
        });
    }

    @Override
    public void onPlaybackPaused() {
        // Update UI if needed
    }

    @Override
    public void onPlaybackStopped() {
        SwingUtilities.invokeLater(() -> {
            songInfoLabel.setText("No song playing");
            progressSlider.setValue(0);
            timeLabel.setText("0:00 / 0:00");
        });
    }

    @Override
    public void onPlaybackCompleted() {
        SwingUtilities.invokeLater(() -> {
            // Automatically play next song if in a playlist
            if (currentPlaylist != null && currentSongIndex < currentPlaylist.getSongs().size() - 1) {
                currentSongIndex++;
                playSong(currentPlaylist.getSongs().get(currentSongIndex));
            } else {
                audioPlayer.stop();
                progressSlider.setValue(0);
                timeLabel.setText("0:00 / 0:00");
                songInfoLabel.setText("No song playing");
            }
        });
    }

    @Override
    public void onPlaybackProgress(long currentPosition, long totalLength) {
        if (!updatingProgressSlider) {
            SwingUtilities.invokeLater(() -> {
                int percentage = (int) ((currentPosition * 100) / totalLength);
                progressSlider.setValue(percentage);
                timeLabel.setText(formatTime(currentPosition) + " / " + formatTime(totalLength));
            });
        }
    }

    /**
     * Custom cell renderer for Song objects in JList.
     */
    private static class SongCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof Song) {
                Song song = (Song) value;
                setText(song.getTitle() + " - " + song.getArtist());
            }

            return this;
        }
    }
}