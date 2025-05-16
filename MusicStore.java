package com.musicplayer.ui;

import com.musicplayer.model.MusicPlayer;

public class MusicStore {
    private static final MusicPlayer musicPlayer = new MusicPlayer();

    public static MusicPlayer getInstance() {
        return musicPlayer;
    }
}
