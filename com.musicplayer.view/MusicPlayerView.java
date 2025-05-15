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
    private JButton previousButton;
    private JButton nextButton;
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
        JPanel playlistTopPanel = new JPanel(new BorderLayout());
        JLabel playlistLabel = new JLabel("Playlists:");
        playlistTopPanel.add(playlistLabel, BorderLayout.WEST);
        JPanel playlistTopRight = new JPanel();
        playlistTopRight.setLayout(new BoxLayout(playlistTopRight, BoxLayout.X_AXIS));
        playlistTopRight.add(playlistCombo);
        playlistTopRight.add(Box.createHorizontalStrut(5));
        playlistTopRight.add(createPlaylistButton);
        playlistTopPanel.add(playlistTopRight, BorderLayout.EAST);
        JPanel playlistCenterPanel = new JPanel(new BorderLayout());
        JLabel songsLabel = new JLabel("Playlist Songs:");
        songsLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        playlistCenterPanel.add(songsLabel, BorderLayout.NORTH);
        playlistCenterPanel.add(playlistScroll, BorderLayout.CENTER);
        addToPlaylistButton = new JButton("Add to Playlist");
        removeFromPlaylistButton = new JButton("Remove from Playlist");
        JPanel playlistBottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        playlistBottomPanel.add(addToPlaylistButton);
        playlistBottomPanel.add(removeFromPlaylistButton);
        playlistCenterPanel.add(playlistBottomPanel, BorderLayout.SOUTH);
        JPanel playlistPanel = new JPanel(new BorderLayout());
        playlistPanel.add(playlistTopPanel, BorderLayout.NORTH);
        playlistPanel.add(playlistCenterPanel, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, libraryPanel, playlistPanel);
        splitPane.setDividerLocation(300);
        add(splitPane, BorderLayout.CENTER);

        // Control panel at the bottom
        playButton = new JButton("Play");
        pauseButton = new JButton("Pause");
        stopButton = new JButton("Stop");
        previousButton = new JButton("Previous");
        nextButton = new JButton("Next");

        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);

        volumeSlider = new JSlider(0, 100, 80);
        volumeSlider.setEnabled(false);
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(false);

        JPanel controlsPanel = new JPanel(new BorderLayout());
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.add(previousButton);
        buttonsPanel.add(playButton);
        buttonsPanel.add(pauseButton);
        buttonsPanel.add(stopButton);
        buttonsPanel.add(nextButton);
        controlsPanel.add(buttonsPanel, BorderLayout.WEST);

        controlsPanel.add(progressBar, BorderLayout.CENTER);

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
        playButton.setActionCommand("Play");
        pauseButton.setActionCommand("Pause");
        stopButton.setActionCommand("Stop");
        previousButton.setActionCommand("Previous");
        nextButton.setActionCommand("Next");
        addSongButton.setActionCommand("AddSong");
        createPlaylistButton.setActionCommand("NewPlaylist");
        addToPlaylistButton.setActionCommand("AddToPlaylist");
        removeFromPlaylistButton.setActionCommand("RemoveFromPlaylist");

        playButton.addActionListener(controller);
        pauseButton.addActionListener(controller);
        stopButton.addActionListener(controller);
        previousButton.addActionListener(controller);
        nextButton.addActionListener(controller);
        addSongButton.addActionListener(controller);
        createPlaylistButton.addActionListener(controller);
        addToPlaylistButton.addActionListener(controller);
        removeFromPlaylistButton.addActionListener(controller);
        playlistCombo.addActionListener(controller);
        volumeSlider.addChangeListener(controller);
    }

    public void updateNowPlaying(Song song) {
        if (song == null) {
            nowPlayingLabel.setText("Now Playing: None");
        } else {
            nowPlayingLabel.setText("Now Playing: " + song.getTitle() + " by " + song.getArtist());
        }
    }

    public void addSongToLibraryList(Song song) {
        libraryListModel.addElement(song);
    }

    public void addPlaylistName(String name) {
        playlistCombo.addItem(name);
        playlistCombo.setSelectedItem(name);
    }

    public void showPlaylistSongs(Playlist playlist) {
        playlistListModel.clear();
        if (playlist != null) {
            for (Song song : playlist.getSongs()) {
                playlistListModel.addElement(song);
            }
        }
    }

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
