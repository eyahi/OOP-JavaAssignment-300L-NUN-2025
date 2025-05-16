import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {
    public SplashScreen()//method
         {
        // Set splash screen size
        int width = 500, height = 400;//field
        setSize(width, height); //attributes

        // Center on screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2);

        // Create a panel for content
        JPanel panel = new JPanel();
        panel.setBackground(Color.green);//attribute

        JLabel label = new JLabel("Elrufai & AJ Project", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 40));
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);

        getContentPane().add(panel);
    }

    public void showSplash(int duration)//method
     {
        setVisible(true);
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setVisible(false);
    }
}
