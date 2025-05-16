package com.musicplayer.Main;

import javafx.scene.control.TextInputDialog;

public class TextInputDialogHelper {
    public static String show(String title, String header) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        return dialog.showAndWait().orElse(null);
    }
}