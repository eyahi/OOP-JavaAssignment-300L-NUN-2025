package com.musicplayer.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.musicplayer.R;
import com.musicplayer.model.*;

import java.util.List;

public class ViewSongsActivity extends AppCompatActivity {
    private ListView songListView;
    private MusicPlayer musicPlayer = MusicStore.getInstance(); // Singleton instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_songs);

        songListView = findViewById(R.id.songListView);
        refreshSongList();

        songListView.setOnItemClickListener((adapterView, view, position, id) -> {
            Song selectedSong = musicPlayer.getLibrary().get(position);

            new AlertDialog.Builder(this)
                    .setTitle("Choose Action")
                    .setMessage("What would you like to do with:\n" + selectedSong.getTitle())
                    .setPositiveButton("Add to Playlist", (dialog, which) -> showAddToPlaylistDialog(selectedSong))
                    .setNegativeButton("Remove from Library", (dialog, which) -> {
                        musicPlayer.getLibrary().remove(selectedSong);
                        Toast.makeText(this, "Removed from library", Toast.LENGTH_SHORT).show();
                        refreshSongList();
                    })
                    .setNeutralButton("Cancel", null)
                    .show();
        });
    }

    private void showAddToPlaylistDialog(Song song) {
        List<String> playlists = musicPlayer.getAllPlaylistNames();

        if (playlists.isEmpty()) {
            Toast.makeText(this, "No playlists available. Create one first.", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] playlistArray = playlists.toArray(new String[0]);

        new AlertDialog.Builder(this)
                .setTitle("Select Playlist")
                .setItems(playlistArray, (dialog, which) -> {
                    String selectedPlaylist = playlistArray[which];
                    musicPlayer.addSongToPlaylist(selectedPlaylist, song);
                    Toast.makeText(this, "Added to playlist: " + selectedPlaylist, Toast.LENGTH_SHORT).show();
                })
                .show();
    }

    private void refreshSongList() {
        List<Song> songs = musicPlayer.getLibrary();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                songs.stream().map(Song::toString).toList()
        );
        songListView.setAdapter(adapter);
    }
}
