package com.musicplayer.main;

import com.musicplayer.model.*;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MusicPlayerAppGUI extends Application {

    private MusicPlayer player = new MusicPlayer();

    private ObservableList<Song> libraryObservable = FXCollections.observableArrayList();
    private ObservableList<Playlist> playlistObservable = FXCollections.observableArrayList();

    private ListView<Song> libraryListView = new ListView<>();
    private ListView<Playlist> playlistListView = new ListView<>();

    @Override
    public void start(Stage primaryStage) {
        libraryListView.setItems(libraryObservable);
        playlistListView.setItems(playlistObservable);

        // Buttons
        Button addSongBtn = new Button("Add Song");
        Button createPlaylistBtn = new Button("Create Playlist");
        Button playBtn = new Button("Play");
        Button pauseBtn = new Button("Pause");
        Button stopBtn = new Button("Stop");

        // Layout
        VBox libraryBox = new VBox(new Label("Library"), libraryListView, addSongBtn);
        VBox playlistBox = new VBox(new Label("Playlists"), playlistListView, createPlaylistBtn);
        HBox controls = new HBox(playBtn, pauseBtn, stopBtn);
        VBox root = new VBox(new HBox(libraryBox, playlistBox), controls);
        root.setSpacing(10);
        libraryBox.setSpacing(5);
        playlistBox.setSpacing(5);
        controls.setSpacing(10);

        // Add song event
        addSongBtn.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Enter song title and artist separated by comma");
            dialog.setContentText("Format: Title,Artist");
            dialog.showAndWait().ifPresent(input -> {
                String[] parts = input.split(",");
                if (parts.length == 2) {
                    Song newSong = new Song(parts[0].trim(), parts[1].trim());
                    player.addSongToLibrary(newSong);
                    libraryObservable.add(newSong);
                }
            });
        });

        // Create playlist event
        createPlaylistBtn.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Enter playlist name");
            dialog.showAndWait().ifPresent(name -> {
                Playlist newPlaylist = new Playlist(name.trim());
                player.addPlaylist(newPlaylist);
                playlistObservable.add(newPlaylist);
            });
        });

        // For now, play/pause/stop just print to console (you can expand later)
        playBtn.setOnAction(e -> System.out.println("Play pressed"));
        pauseBtn.setOnAction(e -> System.out.println("Pause pressed"));
        stopBtn.setOnAction(e -> System.out.println("Stop pressed"));

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Music Player");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
// This code defines a simple JavaFX GUI for a music player application. It allows users to add songs to a library and create playlists. The GUI includes buttons for playing, pausing, and stopping music, although these functionalities are not yet implemented.