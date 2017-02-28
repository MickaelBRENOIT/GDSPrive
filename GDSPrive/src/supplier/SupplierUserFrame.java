/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supplier;

import authentication.Authentication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import menu.UserMenu;
import util.ErrorFrame;

/**
 *
 * @author Mikael
 */
public class SupplierUserFrame extends JFrame implements ActionListener, WindowFocusListener {

    private JButton list;
    private JButton searchButton;
    private JButton returnToPreviousFrame;
    private JButton exit;

    private JTextField searchField;
    private JScrollPane scrollSuppliers;

    private JPanel main;
    private JPanel panelList;

    private Authentication authentication;
    private SupplierDAO supplier;

    private JList<String> suppliersList;
    private DefaultListModel<String> listModel;

    public SupplierUserFrame(Authentication auth) {
        authentication = auth;
        this.setTitle("Gestion des fournisseurs | " + auth.getLogin());
        this.setSize(800, 300);

        main = new JPanel();
        add(main);

        initialize();
        disposition();

        addWindowFocusListener(this);
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

        panelList = new JPanel();
        listModel = new DefaultListModel<>();
        suppliersList = new JList<>(listModel);
        panelList.add(suppliersList);
        scrollSuppliers = new JScrollPane(panelList);
        scrollSuppliers.setBounds(50, 75, 400, 155);
        scrollSuppliers.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollSuppliers.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

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
            String domain = searchField.getText().toString();
            List<Supplier> listOfSuppliers = supplier.getListSuppliersByADomain(domain);
            if (!listOfSuppliers.isEmpty()) {
                listModel.removeAllElements();
                for (Supplier s : listOfSuppliers) {
                    listModel.addElement(s.toString());
                }
            } else {
                ErrorFrame ef = new ErrorFrame("Le domaine que vous avez recherché n'existe pas.");
            }

        } else if (ae.getSource() == list) {
            // http://www.codejava.net/java-se/swing/jlist-basic-tutorial-and-examples
            List<Supplier> listOfSuppliers = supplier.getListOfAllSuppliers();
            listModel.removeAllElements();
            for (Supplier s : listOfSuppliers) {
                listModel.addElement(s.toString());
            }

        } else if (ae.getSource() == returnToPreviousFrame) {
            this.dispose();
            UserMenu um = new UserMenu(authentication);
        } else if (ae.getSource() == exit) {
            this.dispose();
        }
    }

    @Override
    public void windowGainedFocus(WindowEvent we) {
        List<Supplier> listOfSuppliers = supplier.getListOfAllSuppliers();
        listModel.removeAllElements();
        for (Supplier s : listOfSuppliers) {
            listModel.addElement(s.toString());
        }
    }

    @Override
    public void windowLostFocus(WindowEvent we) {
    }

}
