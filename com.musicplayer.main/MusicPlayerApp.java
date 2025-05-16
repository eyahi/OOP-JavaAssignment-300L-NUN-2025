package com.musicplayer.main;

import com.musicplayer.model.MusicPlayer;
import com.musicplayer.view.MusicPlayerView;
import com.musicplayer.controller.MusicPlayerController;
import javax.swing.SwingUtilities;

public class MusicPlayerApp {
    public static void main(String[] args) {
        // Launch the GUI on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MusicPlayer model = new MusicPlayer();
                MusicPlayerView view = new MusicPlayerView();
                // Initialize controller (this will wire up the view's listeners)
                MusicPlayerController controller = new MusicPlayerController(model, view);
                // Show the application window
                view.setVisible(true);
            }
        });
    }
}
