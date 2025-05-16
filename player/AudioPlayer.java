package com.musicplayer.player;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import com.musicplayer.model.Song;

/**
 * The AudioPlayer class handles the actual audio playback functionality.
 * It uses the Java Sound API to play audio files.
 */
public class AudioPlayer {
    private Clip audioClip;
    private FloatControl volumeControl;
    private boolean isPaused;
    private long pausePosition;
    private Song currentSong;
    private final AudioPlayerListener listener;

    /**
     * Listener interface for AudioPlayer events.
     */
    public interface AudioPlayerListener {
        void onPlaybackStarted(Song song);
        void onPlaybackPaused();
        void onPlaybackStopped();
        void onPlaybackCompleted();
        void onPlaybackProgress(long currentPosition, long totalLength);
    }

    /**
     * Constructor to initialize the AudioPlayer with a listener.
     *
     * @param listener The listener for audio player events
     */
    public AudioPlayer(AudioPlayerListener listener) {
        this.listener = listener;
        this.isPaused = false;
        this.pausePosition = 0;
    }

    /**
     * Plays the specified song.
     *
     * @param song The song to play
     */
    public void play(Song song) {
        if (audioClip != null && audioClip.isRunning()) {
            stop();
        }

        try {
            currentSong = song;
            File audioFile = new File(song.getFilePath());
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);

            // Get volume control
            if (audioClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                volumeControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
            }

            // Add a listener to detect when playback is complete
            audioClip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP && !isPaused) {
                    if (listener != null) {
                        listener.onPlaybackCompleted();
                    }
                }
            });

            // Start playback
            audioClip.start();
            isPaused = false;

            // Notify the listener
            if (listener != null) {
                listener.onPlaybackStarted(song);
            }

            // Start a thread to update playback progress
            startProgressUpdater();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error playing audio: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Pauses the currently playing song.
     */
    public void pause() {
        if (audioClip != null && audioClip.isRunning()) {
            pausePosition = audioClip.getMicrosecondPosition();
            audioClip.stop();
            isPaused = true;

            if (listener != null) {
                listener.onPlaybackPaused();
            }
        }
    }

    /**
     * Resumes playback from a paused state.
     */
    public void resume() {
        if (audioClip != null && isPaused) {
            audioClip.setMicrosecondPosition(pausePosition);
            audioClip.start();
            isPaused = false;

            if (listener != null) {
                listener.onPlaybackStarted(currentSong);
            }

            startProgressUpdater();
        }
    }

    /**
     * Stops the currently playing song.
     */
    public void stop() {
        if (audioClip != null) {
            audioClip.stop();
            audioClip.close();
            audioClip = null;
            isPaused = false;

            if (listener != null) {
                listener.onPlaybackStopped();
            }
        }
    }

    /**
     * Sets the volume level.
     *
     * @param volumeLevel The volume level (0.0 to 1.0)
     */
    public void setVolume(float volumeLevel) {
        if (volumeControl != null) {
            // Convert the volume level (0.0 to 1.0) to decibels
            // The range depends on the control's min and max values
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();

            // Logarithmic scaling for more natural volume control
            // This maps 0.0 to min and 1.0 to max with logarithmic scaling
            if (volumeLevel == 0.0f) {
                volumeControl.setValue(min);
            } else {
                float range = max - min;
                float gain = min + (range * volumeLevel);
                volumeControl.setValue(gain);
            }
        }
    }

    /**
     * Sets the playback position.
     *
     * @param position The position in microseconds
     */
    public void setPosition(long position) {
        if (audioClip != null) {
            boolean wasPlaying = !isPaused && audioClip.isRunning();
            if (wasPlaying) {
                audioClip.stop();
            }

            audioClip.setMicrosecondPosition(position);

            if (wasPlaying) {
                audioClip.start();
            }
        }
    }

    /**
     * Gets the current playback position.
     *
     * @return The current position in microseconds
     */
    public long getPosition() {
        if (audioClip != null) {
            return audioClip.getMicrosecondPosition();
        }
        return 0;
    }

    /**
     * Gets the total length of the current audio clip.
     *
     * @return The total length in microseconds
     */
    public long getLength() {
        if (audioClip != null) {
            return audioClip.getMicrosecondLength();
        }
        return 0;
    }

    /**
     * Gets the current song being played.
     *
     * @return The current song
     */
    public Song getCurrentSong() {
        return currentSong;
    }

    /**
     * Checks if playback is paused.
     *
     * @return true if paused, false otherwise
     */
    public boolean isPaused() {
        return isPaused;
    }

    /**
     * Checks if a song is currently playing.
     *
     * @return true if a song is playing, false otherwise
     */
    public boolean isPlaying() {
        return audioClip != null && audioClip.isRunning();
    }

    /**
     * Starts a thread to update the playback progress.
     */
    private void startProgressUpdater() {
        Thread progressThread = new Thread(() -> {
            while (audioClip != null && audioClip.isRunning()) {
                if (listener != null) {
                    listener.onPlaybackProgress(audioClip.getMicrosecondPosition(), audioClip.getMicrosecondLength());
                }

                try {
                    Thread.sleep(100); // Update every 100ms
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        progressThread.setDaemon(true);
        progressThread.start();
    }
}