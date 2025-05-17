package com.musicplayer.main;

import com.musicplayer.model.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MusicPlayerApp {

    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer();
        Scanner scanner = new Scanner(System.in);

        // Add a sample song for testing
        player.addSongToLibrary(new Song(
                "Blinding Lights",
                "The Weeknd",
                "Pop",
                "C:/Music/BlindingLights.wav"
        ));

        int choice = -1;

        while (choice != 11) {
            System.out.println("\n=== MUSIC PLAYER MENU ===");
            System.out.println("1. Add Song to Library");
            System.out.println("2. View All Songs");
            System.out.println("3. Create Playlist");
            System.out.println("4. View All Playlists");
            System.out.println("5. Add Song to Playlist");
            System.out.println("6. Remove Song from Playlist");
            System.out.println("7. View Playlist");
            System.out.println("8. Play Song");
            System.out.println("9. Pause");
            System.out.println("10. Stop");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number from 1 to 11.");
                scanner.nextLine(); // consume the invalid input
                continue; // restart loop
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter artist: ");
                    String artist = scanner.nextLine();
                    System.out.print("Enter genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Enter file path: ");
                    String filePath = scanner.nextLine();

                    player.addSongToLibrary(new Song(title, artist, genre, filePath));
                    System.out.println("Song added to library.");
                }
                case 2 -> player.displayAllSongs();
                case 3 -> {
                    System.out.print("Enter playlist name: ");
                    String plName = scanner.nextLine();
                    player.createPlaylist(plName);
                }
                case 4 -> player.displayAllPlaylists();
                case 5 -> {
                    System.out.print("Enter playlist name: ");
                    String playlistName = scanner.nextLine();
                    System.out.print("Enter song title to add: ");
                    String songTitle = scanner.nextLine();
                    Song song = player.getSongFromLibrary(songTitle);
                    if (song != null) {
                        player.addSongToPlaylist(playlistName, song);
                    } else {
                        System.out.println("Song not found in library.");
                    }
                }
                case 6 -> {
                    System.out.print("Enter playlist name: ");
                    String playlistName = scanner.nextLine();
                    System.out.print("Enter song title to remove: ");
                    String songTitle = scanner.nextLine();
                    player.removeSongFromPlaylist(playlistName, songTitle);
                }
                case 7 -> {
                    System.out.print("Enter playlist name: ");
                    String playlistName = scanner.nextLine();
                    Playlist playlist = player.getPlaylist(playlistName);
                    if (playlist != null) {
                        playlist.displayPlaylist();
                    } else {
                        System.out.println("Playlist not found.");
                    }
                }
                case 8 -> {
                    System.out.print("Enter song title to play: ");
                    String songTitle = scanner.nextLine();
                    Song song = player.getSongFromLibrary(songTitle);
                    if (song != null) {
                        player.play(song);
                    } else {
                        System.out.println("Song not found.");
                    }
                }
                case 9 -> player.pause();
                case 10 -> player.stop();
                case 11 -> System.out.println("Exiting Music Player...");
                default -> System.out.println("Invalid choice. Please select 1-11.");
            }
        }
        scanner.close();
    }
}
