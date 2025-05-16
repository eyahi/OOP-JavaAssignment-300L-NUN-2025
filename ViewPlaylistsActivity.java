package com.musicplayer.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.musicplayer.R;
import com.musicplayer.model.*;

import java.util.ArrayList;
import java.util.List;

public class ViewPlaylistsActivity extends AppCompatActivity {

    private ListView listView;
    private MusicPlayer musicPlayer = MusicStore.getInstance();
    private ArrayAdapter<String> adapter;
    private String selectedPlaylist = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_playlists);

        listView = findViewById(R.id.playlistListView);
        showPlaylists();
    }

    private void showPlaylists() {
        selectedPlaylist = null;
        List<String> playlistNames = new ArrayList<>(musicPlayer.getAllPlaylistNames());

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, playlistNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            selectedPlaylist = playlistNames.get(position);
            showSongsInPlaylist(selectedPlaylist);
        });
    }

    private void showSongsInPlaylist(String playlistName) {
        Playlist playlist = musicPlayer.getPlaylist(playlistName);
        if (playlist == null) {
            Toast.makeText(this, "Playlist not found", Toast.LENGTH_SHORT).show();
            return;
        }

        List<Song> songs = playlist.getSongs();
        List<String> songStrings = new ArrayList<>();
        for (Song s : songs) songStrings.add(s.toString());

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songStrings);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Song selectedSong = songs.get(position);

            new AlertDialog.Builder(this)
                    .setTitle("Remove Song?")
                    .setMessage("Remove \"" + selectedSong.getTitle() + "\" from playlist?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        musicPlayer.removeSongFromPlaylist(playlistName, selectedSong.getTitle());
                        Toast.makeText(this, "Removed", Toast.LENGTH_SHORT).show();
                        showSongsInPlaylist(playlistName);
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }
}
