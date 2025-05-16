package com.musicplayer.Main;

import com.musicplayer.model.Song;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import java.io.File;

public class SongDialog {
    public static Song show() {
        Dialog<Song> dialog = new Dialog<>();
        dialog.setTitle("Add New Song");

        TextField tfFile = new TextField();
        Button browse = new Button("Browse");
        browse.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio", "*.mp3", "*.wav"));
            File f = fc.showOpenDialog(null);
            if (f != null) tfFile.setText(f.getAbsolutePath());
        });

        TextField tfTitle = new TextField(), tfArtist = new TextField(), tfGenre = new TextField();
        tfTitle.setPromptText("Title"); tfArtist.setPromptText("Artist"); tfGenre.setPromptText("Genre");

        GridPane grid = new GridPane();
        grid.setHgap(10); grid.setVgap(10);
        grid.addRow(0, new Label("File:"), tfFile, browse);
        grid.addRow(1, new Label("Title:"), tfTitle);
        grid.addRow(2, new Label("Artist:"), tfArtist);
        grid.addRow(3, new Label("Genre:"), tfGenre);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.setResultConverter(btn -> btn == ButtonType.OK ? new Song(
            tfTitle.getText(),
             tfArtist.getText(),
              tfGenre.getText(),
               tfFile.getText()) : null);

        return dialog.showAndWait().orElse(null);
    }
}