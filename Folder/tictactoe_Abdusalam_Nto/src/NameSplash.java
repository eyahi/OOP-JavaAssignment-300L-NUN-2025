import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NameSplash {
  public static void show(String name, Runnable onClose) {
    JFrame splashFrame = new JFrame();
    splashFrame.setUndecorated(true); // Remove window borders
    splashFrame.setSize(600, 300);
    splashFrame.setLocationRelativeTo(null); // Center on screen

    JPanel panel = new JPanel(new BorderLayout());
    panel.setBackground(new Color(30, 30, 30)); // Dark background

    JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
    nameLabel.setForeground(Color.WHITE);
    nameLabel.setFont(new Font("Arial", Font.BOLD, 32));

    panel.add(nameLabel, BorderLayout.CENTER);
    splashFrame.add(panel);

    // Close after 4 seconds
    Timer timer = new Timer(2000, e -> {
      splashFrame.dispose();
      onClose.run(); // Start main game after
    });
    timer.setRepeats(false);
    timer.start();

    splashFrame.setVisible(true);
  }
}
