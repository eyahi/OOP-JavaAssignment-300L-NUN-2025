package com.musicplayer.Main;

import com.musicplayer.model.*;
import com.musicplayer.util.LocalLibraryLoader;
import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.util.List;

public class MusicPlayerApp extends Application {
    private MusicPlayer player = new MusicPlayer();
    private ListView<Song> libraryView = new ListView<>();
    private ListView<Song> playlistView = new ListView<>();
    private ComboBox<String> playlistCombo = new ComboBox<>();

    public void start(Stage stage) {
        LocalLibraryLoader.load(player);

        TextField search = new TextField();
        search.setPromptText("Search title...");
        search.textProperty().addListener((o,v,n) -> updateLibrary(player.searchByTitle(n)));

        Button 
        add = new Button("Add"),
         play = new Button("▶"),
          pause = new Button("⏸"),
           stop = new Button("⏹");
        add.setOnAction(e -> { Song s = SongDialog.show(); if (s != null) {
             player.addSongToLibrary(s); refresh(); 
            }});
        play.setOnAction(e -> {
    Song s = libraryView.getSelectionModel().getSelectedItem();
    if (player.getFxPlayerStatus() == null  // i.e. nothing loaded
        || player.getFxPlayerStatus() == MediaPlayer.Status.STOPPED) {
        player.play(s);
    } else {
        player.resume();  // if paused
    }
});
        pause.setOnAction(e -> player.pause());
        stop.setOnAction(e -> player.stop());

        Button newPl = new Button("New Playlist");
        newPl.setOnAction(e -> {
            String name = TextInputDialogHelper.show("New Playlist", "Enter name");
            if (name != null) {
                player.createPlaylist(name);
                playlistCombo.getItems().setAll(player.getPlaylistNames());
            }
        });
        Button
         addTo = new Button("➕ Playlist"),
          removeFrom = new Button("➖ Playlist"),
           playPl = new Button("▶ Playlist");
        
        addTo.setOnAction(e -> player.addSongToPlaylist(playlistCombo.getValue(),
         libraryView.getSelectionModel().getSelectedItem()));
        removeFrom.setOnAction(e -> player.removeFromPlaylist(playlistCombo.getValue(), playlistView.getSelectionModel().getSelectedItem()));
        playPl.setOnAction(e -> player.playPlaylist(playlistCombo.getValue()));

        playlistCombo.setPromptText("Select Playlist");
        playlistCombo.setOnAction(e -> updatePlaylist());

        VBox root = new VBox(10,
            search, libraryView,
            new HBox(5, add, play, pause, stop),
            new HBox(5, newPl, playlistCombo, playPl),
            new HBox(5, addTo, removeFrom),
            playlistView
        );
        root.setPadding(new Insets(10));

        stage.setScene(new Scene(root, 600, 600));
        stage.setTitle("Music Player");
        stage.show();
        refresh();
    }

    private void updateLibrary(List<Song> songs) {
        libraryView.setItems(FXCollections.observableArrayList(songs));
        libraryView.setCellFactory(lv -> new ListCell<>() {
            protected void updateItem(Song s, boolean empty) {
                super.updateItem(s, empty);
                setText(empty || s == null ? null : s.toString());
            }
        });
    }

    private void updatePlaylist() {
        List<Song> songs = player.getPlaylistSongs(playlistCombo.getValue());
        playlistView.setItems(FXCollections.observableArrayList(songs));
        playlistView.setCellFactory(libraryView.getCellFactory());
    }

    private void refresh() {
        updateLibrary(player.getLibrary());
        playlistCombo.getItems().setAll(player.getPlaylistNames());
        updatePlaylist();
    }

    public static void main(String[] args) { 
        launch(args); }
}