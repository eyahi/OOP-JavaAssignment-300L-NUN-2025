package com.musicplayer.main;

import com.musicplayer.R;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.content.Intent;

import com.musicplayer.model.*;
import com.musicplayer.ui.MusicStore;
import com.musicplayer.ui.ViewSongsActivity;
import com.musicplayer.ui.ViewPlaylistsActivity;

import java.util.List;

public class MusicPlayerApp extends AppCompatActivity {

    private MusicPlayer musicPlayer;
    private EditText inputTitle, inputArtist, inputGenre, inputFilePath, inputPlaylist;
    private TextView output;
    private Button addSongBtn, createPlaylistBtn, playBtn, pauseBtn, stopBtn, viewSongsBtn, viewPlaylistsBtn;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player_app);

        musicPlayer = MusicStore.getInstance();

        inputTitle = findViewById(R.id.inputTitle);
        inputArtist = findViewById(R.id.inputArtist);
        inputGenre = findViewById(R.id.inputGenre);
        inputFilePath = findViewById(R.id.inputFilePath);
        inputPlaylist = findViewById(R.id.inputPlaylist);
        output = findViewById(R.id.outputText);

        addSongBtn = findViewById(R.id.addSongBtn);
        createPlaylistBtn = findViewById(R.id.createPlaylistBtn);
        playBtn = findViewById(R.id.playBtn);
        pauseBtn = findViewById(R.id.pauseBtn);
        stopBtn = findViewById(R.id.stopBtn);
        viewSongsBtn = findViewById(R.id.viewSongsBtn);
        viewPlaylistsBtn = findViewById(R.id.viewPlaylistsBtn);

        addSongBtn.setOnClickListener(v -> {
            Song song = new Song(
                    inputTitle.getText().toString(),
                    inputArtist.getText().toString(),
                    inputGenre.getText().toString(),
                    inputFilePath.getText().toString()
            );
            musicPlayer.addSongToLibrary(song);
            output.setText("Song added: " + song);
        });

        createPlaylistBtn.setOnClickListener(v -> {
            String name = inputPlaylist.getText().toString();
            musicPlayer.createPlaylist(name);
            output.setText("Playlist created: " + name);
        });

        playBtn.setOnClickListener(v -> {
            int resId = getResources().getIdentifier("sample1", "raw", getPackageName());
            if (resId != 0) {
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                }
                mediaPlayer = MediaPlayer.create(this, resId);
                mediaPlayer.start();
                output.setText("Now playing: Kilobizzy by Mavo");
            } else {
                output.setText("sample1.mp3 not found in res/raw");
            }
        });

        pauseBtn.setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                output.setText("Paused.");
            }
        });

        stopBtn.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
                output.setText("Stopped.");
            }
        });

        viewSongsBtn.setOnClickListener(v -> {
            startActivity(new Intent(MusicPlayerApp.this, ViewSongsActivity.class));
        });

        viewPlaylistsBtn.setOnClickListener(v -> {
            startActivity(new Intent(MusicPlayerApp.this, ViewPlaylistsActivity.class));
        });
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}
