/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author e1500727
 */
public class IdIncorrect extends JDialog implements ActionListener{
    
    private JPanel main;
    private JButton buttonOk;
    private JLabel text;
    private ImageIcon warningImg;
    private JLabel warningLabel;

    public IdIncorrect() {
        this.setTitle("Mauvais identifiants");
        this.setSize(300, 125);

        main = new JPanel();
        this.add(main);

        initialize();
        disposition();
        
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
        if(e.getSource() == buttonOk){
            this.dispose();
        }
    }
}
