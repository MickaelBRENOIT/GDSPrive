package util;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author e1500727
 */
public class ErrorFrame extends JDialog {

    private JPanel main;
    private JLabel text;
    private String txtError;
    private ImageIcon warningImg;
    private JLabel warningLabel;

    public ErrorFrame(String txtError) {
        
        this.txtError = txtError;
        this.setTitle("Une erreur a été provoquée");
        this.setSize(500, 100);

        main = new JPanel();
        this.add(main);

        initialize();
        disposition();
        playSound();

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setVisible(true);

    }

    private void initialize() {
        text = new JLabel(txtError, JLabel.CENTER);
        warningImg = new ImageIcon("images/warning.png");
        warningLabel = new JLabel(warningImg);
    }
 
    private void disposition() {
        main.setLayout(new BorderLayout());
        main.add(text, BorderLayout.CENTER);
        main.add(warningLabel, BorderLayout.SOUTH);
    }

    private void playSound() {
        try {
            // Open an audio input stream.
            String songName = "error.wav";
            String pathToWav = System.getProperty("user.dir") +"\\sounds\\"+ songName;
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(pathToWav).getAbsoluteFile());
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
