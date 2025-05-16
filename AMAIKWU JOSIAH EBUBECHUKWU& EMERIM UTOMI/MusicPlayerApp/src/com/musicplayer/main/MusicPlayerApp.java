package com.musicplayer.main;

import com.musicplayer.model.MusicPlayer;
import com.musicplayer.model.Song;

public class MusicPlayerApp {
    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer();

        Song song1 = new Song("Blinding Lights", "The Weeknd");
        Song song2 = new Song("Levitating", "Dua Lipa");

        player.addSongToLibrary(song1);
        player.addSongToLibrary(song2);

        System.out.println("📻 Songs in library:");
        for (Song song : player.getLibrary()) {
            System.out.println("- " + song.getTitle() + " by " + song.getArtist());
        }
    }
}
