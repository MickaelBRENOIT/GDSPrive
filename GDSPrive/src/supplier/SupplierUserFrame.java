/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supplier;

import authentication.Authentication;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
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
public class SupplierUserFrame extends JFrame implements ActionListener {

    private JButton list;
    private JButton searchButton;
    private JButton returnToPreviousFrame;
    private JButton exit;

    private JTextField searchField;
    private JTextArea listSuppliers;
    private JScrollPane scrollSuppliers;

    private JPanel main;

    private Authentication authentication;
    private SupplierDAO supplier;

    public SupplierUserFrame(Authentication auth) {
        authentication = auth;
        this.setTitle("Gestion des fournisseurs | " + auth.getLogin());
        this.setSize(800, 300);

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

        this.supplier = new SupplierDAO();

        searchButton = new JButton("Rechercher par domaine");
        searchButton.setBounds(550, 50, 200, 30);
        searchButton.addActionListener(this);

        list = new JButton("Lister");
        list.setBounds(550, 100, 200, 30);
        list.addActionListener(this);

        returnToPreviousFrame = new JButton("Retour");
        returnToPreviousFrame.setBounds(550, 150, 200, 30);
        returnToPreviousFrame.addActionListener(this);

        exit = new JButton("Quitter");
        exit.setBounds(550, 200, 200, 30);
        exit.addActionListener(this);

        searchField = new JTextField("Champ de recherche");
        searchField.setBounds(50, 50, 400, 25);

        listSuppliers = new JTextArea();
        scrollSuppliers = new JScrollPane(listSuppliers);
        scrollSuppliers.setBounds(50, 75, 400, 155);
        scrollSuppliers.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        listSuppliers.setEditable(false);

    }

    private void disposition() {
        main.setLayout(null);

        main.add(searchButton);
        main.add(list);
        main.add(returnToPreviousFrame);
        main.add(exit);
        main.add(searchField);
        main.add(scrollSuppliers);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == searchButton) {
            listSuppliers.setText(null);
            String domain = searchField.getText().toString();
            List<Supplier> listOfSuppliers = supplier.getListSuppliersByADomain(domain);
            if (!listOfSuppliers.isEmpty()) {
                for (Supplier s : listOfSuppliers) {
                    listSuppliers.append(s.toString());
                    listSuppliers.append("\n");
                }
            }else{
                System.out.println("PAS DE DOMAINE POSSIBLE");
            }
        } else if (ae.getSource() == list) {
            listSuppliers.setText(null);
            List<Supplier> listOfSuppliers = supplier.getListOfAllSuppliers();
            for (Supplier s : listOfSuppliers) {
                listSuppliers.append(s.toString());
                listSuppliers.append("\n");
            }
        } else if (ae.getSource() == returnToPreviousFrame) {
            this.dispose();
            UserMenu um = new UserMenu(authentication);
        } else if (ae.getSource() == exit) {
            this.dispose();
        }
    }

}
