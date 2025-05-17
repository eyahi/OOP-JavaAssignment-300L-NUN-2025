

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayerGUI extends JFrame {
    private Clip clip;

    public MusicPlayerGUI(String audioFilePath) {
        setTitle("Music Player");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);

        JButton playButton = new JButton("Play");
        JButton pauseButton = new JButton("Pause");
        JButton stopButton = new JButton("Stop");

        panel.add(playButton);
        panel.add(pauseButton);
        panel.add(stopButton);

        add(panel, BorderLayout.CENTER);
        getContentPane().setBackground(Color.BLACK);

        try {
            File audioFile = new File(audioFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            JOptionPane.showMessageDialog(this, "Error loading audio: " + e.getMessage());
        }

        playButton.addActionListener(e -> {
            if (clip != null) {
                clip.setMicrosecondPosition(0);
                clip.start();
            }
        });

        pauseButton.addActionListener(e -> {
            if (clip != null && clip.isRunning()) {
                clip.pause ();
            }
        });

        stopButton.addActionListener(e -> {
            if (clip != null) {
                clip.stop();
                clip.setMicrosecondPosition(0);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MusicPlayerGUI("c:/Users/wrldc/Downloads/Yarden-Ft-Libianca-Wetin-Remix-(TrendyBeatz.com).wav"); // Replace with actual .wav file path
    }
}

