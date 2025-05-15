package com.musicplayer.view;

import com.musicplayer.controller.MusicPlayerController;
import com.musicplayer.model.Playlist;
import com.musicplayer.model.Song;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class MusicPlayerView extends JFrame {
    private JList<Song> libraryList;
    private DefaultListModel<Song> libraryListModel;
    private JList<Song> playlistSongsList;
    private DefaultListModel<Song> playlistListModel;
    private JComboBox<String> playlistCombo;
    private JButton playButton;
    private JButton pauseButton;
    private JButton stopButton;
    private JButton addSongButton;
    private JButton addToPlaylistButton;
    private JButton removeFromPlaylistButton;
    private JButton createPlaylistButton;
    private JSlider volumeSlider;
    private JProgressBar progressBar;
    private JLabel nowPlayingLabel;

    public MusicPlayerView() {
        super("Music Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Now Playing label at the top
        nowPlayingLabel = new JLabel("Now Playing: None");
        nowPlayingLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(nowPlayingLabel, BorderLayout.NORTH);

        // Library panel (left side)
        libraryListModel = new DefaultListModel<>();
        libraryList = new JList<>(libraryListModel);
        JScrollPane libraryScroll = new JScrollPane(libraryList);
        JPanel libraryPanel = new JPanel(new BorderLayout());
        // Top sub-panel with "Library" label and "Add Song" button
        JLabel libraryLabel = new JLabel("Library");
        addSongButton = new JButton("Add Song");
        JPanel libTopPanel = new JPanel(new BorderLayout());
        libTopPanel.add(libraryLabel, BorderLayout.WEST);
        libTopPanel.add(addSongButton, BorderLayout.EAST);
        libraryPanel.add(libTopPanel, BorderLayout.NORTH);
        libraryPanel.add(libraryScroll, BorderLayout.CENTER);

        // Playlist panel (right side)
        playlistListModel = new DefaultListModel<>();
        playlistSongsList = new JList<>(playlistListModel);
        JScrollPane playlistScroll = new JScrollPane(playlistSongsList);
        playlistCombo = new JComboBox<>();
        createPlaylistButton = new JButton("New Playlist");
        // Top sub-panel for playlist selection and creation
        JPanel playlistTopPanel = new JPanel(new BorderLayout());
        JLabel playlistLabel = new JLabel("Playlists:");
        playlistTopPanel.add(playlistLabel, BorderLayout.WEST);
        JPanel playlistTopRight = new JPanel();
        playlistTopRight.setLayout(new BoxLayout(playlistTopRight, BoxLayout.X_AXIS));
        playlistTopRight.add(playlistCombo);
        playlistTopRight.add(Box.createHorizontalStrut(5));
        playlistTopRight.add(createPlaylistButton);
        playlistTopPanel.add(playlistTopRight, BorderLayout.EAST);
        // Center part of playlist panel: label and list of songs in selected playlist
        JPanel playlistCenterPanel = new JPanel(new BorderLayout());
        JLabel songsLabel = new JLabel("Playlist Songs:");
        songsLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        playlistCenterPanel.add(songsLabel, BorderLayout.NORTH);
        playlistCenterPanel.add(playlistScroll, BorderLayout.CENTER);
        // Bottom sub-panel with "Add to Playlist" and "Remove from Playlist" buttons
        addToPlaylistButton = new JButton("Add to Playlist");
        removeFromPlaylistButton = new JButton("Remove from Playlist");
        JPanel playlistBottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        playlistBottomPanel.add(addToPlaylistButton);
        playlistBottomPanel.add(removeFromPlaylistButton);
        playlistCenterPanel.add(playlistBottomPanel, BorderLayout.SOUTH);
        // Assemble playlist panel
        JPanel playlistPanel = new JPanel(new BorderLayout());
        playlistPanel.add(playlistTopPanel, BorderLayout.NORTH);
        playlistPanel.add(playlistCenterPanel, BorderLayout.CENTER);

        // Split pane to divide library and playlist panels
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, libraryPanel, playlistPanel);
        splitPane.setDividerLocation(300);  // initial divider position (300px for library)
        add(splitPane, BorderLayout.CENTER);

        // Control panel at the bottom
        playButton = new JButton("Play");
        pauseButton = new JButton("Pause");
        stopButton = new JButton("Stop");
        // Initially disable Pause and Stop (no song playing yet)
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
        // Volume slider and progress bar
        volumeSlider = new JSlider(0, 100, 80);
        volumeSlider.setEnabled(false);
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(false);

        // Layout controls: Buttons on left, progress bar center, volume on right
        JPanel controlsPanel = new JPanel(new BorderLayout());
        // Left: Play/Pause/Stop buttons
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.add(playButton);
        buttonsPanel.add(pauseButton);
        buttonsPanel.add(stopButton);
        controlsPanel.add(buttonsPanel, BorderLayout.WEST);
        // Center: progress bar
        controlsPanel.add(progressBar, BorderLayout.CENTER);
        // East: volume label and slider
        JPanel volPanel = new JPanel();
        volPanel.setLayout(new BoxLayout(volPanel, BoxLayout.X_AXIS));
        JLabel volLabel = new JLabel("Volume:");
        volLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        volPanel.add(volLabel);
        volPanel.add(volumeSlider);
        volPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        controlsPanel.add(volPanel, BorderLayout.EAST);

        add(controlsPanel, BorderLayout.SOUTH);
    }

    public void setController(MusicPlayerController controller) {
        // Set action commands for identification in the controller
        playButton.setActionCommand("Play");
        pauseButton.setActionCommand("Pause");
        stopButton.setActionCommand("Stop");
        addSongButton.setActionCommand("AddSong");
        createPlaylistButton.setActionCommand("NewPlaylist");
        addToPlaylistButton.setActionCommand("AddToPlaylist");
        removeFromPlaylistButton.setActionCommand("RemoveFromPlaylist");
        // Connect controller as listeners for UI events
        playButton.addActionListener(controller);
        pauseButton.addActionListener(controller);
        stopButton.addActionListener(controller);
        addSongButton.addActionListener(controller);
        createPlaylistButton.addActionListener(controller);
        addToPlaylistButton.addActionListener(controller);
        removeFromPlaylistButton.addActionListener(controller);
        playlistCombo.addActionListener(controller);
        volumeSlider.addChangeListener(controller);
    }

    // Update the "Now Playing" label (called by controller when song changes)
    public void updateNowPlaying(Song song) {
        if (song == null) {
            nowPlayingLabel.setText("Now Playing: None");
        } else {
            nowPlayingLabel.setText("Now Playing: " + song.getTitle() + " by " + song.getArtist());
        }
    }

    // Add a song to the library list UI
    public void addSongToLibraryList(Song song) {
        libraryListModel.addElement(song);
    }

    // Add a new playlist name to the playlist dropdown
    public void addPlaylistName(String name) {
        playlistCombo.addItem(name);
        playlistCombo.setSelectedItem(name);
    }

    // Show (refresh) the songs in the given playlist on the UI list
    public void showPlaylistSongs(Playlist playlist) {
        playlistListModel.clear();
        if (playlist != null) {
            for (Song song : playlist.getSongs()) {
                playlistListModel.addElement(song);
            }
        }
    }

    // Show a file chooser dialog to select a WAV file (returns the chosen File or null)
    public File showFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("WAV audio files", "wav", "wave");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    // Getters for components needed by the controller (for event handling or state updates)
    public JList<Song> getLibraryList() {
        return libraryList;
    }
    public JList<Song> getPlaylistSongsList() {
        return playlistSongsList;
    }
    public JComboBox<String> getPlaylistCombo() {
        return playlistCombo;
    }
    public JSlider getVolumeSlider() {
        return volumeSlider;
    }
    public JProgressBar getProgressBar() {
        return progressBar;
    }
    public JButton getPauseButton() {
        return pauseButton;
    }
    public JButton getStopButton() {
        return stopButton;
    }
}
