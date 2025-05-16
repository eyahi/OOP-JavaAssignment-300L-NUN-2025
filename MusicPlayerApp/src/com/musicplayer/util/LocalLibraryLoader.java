package com.musicplayer.util;

import com.musicplayer.model.*;

public class LocalLibraryLoader {
    public static void load(MusicPlayer musicPlayer) {
        //Song 1
        musicPlayer.addSongToLibrary(new Song(
            "MIND OVER",
             "Nien, Level Nine",
              "Hard Rock",
               "resources/music/MIND OVER.mp3"
               ));
        //Song 2
        musicPlayer.addSongToLibrary(new Song(
            "I Fly (feat. Faouzia)",
             "Galantis",
              "Dance",
               "resources/music/I Fly (feat. Faouzia).mp3"
               ));
        //Song 3
        musicPlayer.addSongToLibrary(new Song(
            "Mississippi Queen",
             "Joyous Wolf",
              "Rock",
               "resources/music/Mississippi Queen.mp3"
               ));
        //Song 4
        musicPlayer.addSongToLibrary(new Song(
            "Ready to go",
             "Nien, Level Nine",
              "Hard Rock",
               "resources/music/Ready to go.mp3"
               ));
        //Song 5
        musicPlayer.addSongToLibrary(new Song(
            "Stronger (Champion Remix)",
             "Lemon Fight, Champion",
              "Electronica",
               "resources/music/Stronger (Champion Remix).mp3"
               ));
        //Song 6
        musicPlayer.addSongToLibrary(new Song(
            "Sins of The Father",
             "Donna Burke",
              "Orchestral",
               "resources/music/Sins of The Father.mp3"
               ));
        //Song 7
        musicPlayer.addSongToLibrary(new Song(
            "Thumbs",
             "Sabrina Carpenter",
              "R&B pop",
               "resources/music/Thumbs.mp3"
               ));
    }
}