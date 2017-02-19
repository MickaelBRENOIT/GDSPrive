/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author e1500727
 */
public class AuthenticationIncorrectDialog extends JDialog implements ActionListener {

    private JPanel main;
    private JButton buttonOk;
    private JLabel text;
    private ImageIcon warningImg;
    private JLabel warningLabel;

    public AuthenticationIncorrectDialog() {
        this.setTitle("Mauvais identifiants");
        this.setSize(300, 125);

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
        text = new JLabel("Le login ou le mot de passe est incorrect");

        warningImg = new ImageIcon("images/warning.png");
        warningLabel = new JLabel("", warningImg, JLabel.CENTER);

        buttonOk = new JButton("Ok");
        buttonOk.addActionListener(this);
    }

    private void disposition() {
        main.add(text, BorderLayout.CENTER);
        main.add(warningLabel, BorderLayout.CENTER);
        main.add(buttonOk, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonOk) {
            this.dispose();
        }
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
