/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supplier;

import authentication.Authentication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import menu.UserMenu;

/**
 *
 * @author Mikael
 */
public class SupplierUserFrame extends JFrame implements ActionListener{
    
    private JButton list;
    private JButton searchButton;
    private JButton returnToPreviousFrame;
    private JButton exit;
    
    private JTextField searchField;
    private JTextArea listSuppliers;
    private JScrollPane scrollSuppliers;
    
    private JPanel main;
    
    private Authentication authentication;

    public SupplierUserFrame(Authentication auth) {
        authentication = auth;
        this.setTitle("Gestion des fournisseurs | " + auth.getLogin());
        this.setSize(800, 800);
        
        main = new JPanel();
        add(main);
        
        initialize();
        disposition();
        
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initialize() {
        searchButton = new JButton("Rechercher");
        searchButton.setBounds(550, 50, 200, 40);
        searchButton.addActionListener(this);
        
        list = new JButton("Lister");
        list.setBounds(550, 150, 200, 40);
        list.addActionListener(this);
        
        returnToPreviousFrame = new JButton("Retour");
        returnToPreviousFrame.setBounds(550, 250, 200, 40);
        returnToPreviousFrame.addActionListener(this);
        
        exit = new JButton("Quitter");
        exit.setBounds(550, 350, 200, 40);
        exit.addActionListener(this);
        
        searchField = new JTextField("Champ de recherche");
        searchField.setBounds(50, 50, 400, 25);
        
        listSuppliers = new JTextArea();
        listSuppliers.setBounds(50, 75, 400, 100);
        
        scrollSuppliers = new JScrollPane(listSuppliers);
        
    }

    private void disposition() {
        main.setLayout(null);
        
        main.add(searchButton);
        main.add(list);
        main.add(returnToPreviousFrame);
        main.add(exit);
        main.add(searchField);
        main.add(listSuppliers);
        main.add(scrollSuppliers);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == searchButton){
            System.out.println("Cliqué sur le bouton rechercher");
        }else if(ae.getSource() == list){
            System.out.println("Cliqué sur le bouton listé");
        }else if(ae.getSource() == returnToPreviousFrame){
            this.dispose();
            UserMenu um = new UserMenu(authentication);
        }else if(ae.getSource() == exit){
            this.dispose();
        }
    }
    
}
