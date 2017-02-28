/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

import authentication.Authentication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import menu.UserMenu;
import util.ErrorFrame;

/**
 *
 * @author Mikael
 */
public class CustomerUserFrame extends JFrame implements ActionListener, WindowFocusListener {

    private JButton list;
    private JButton searchButton;
    private JButton returnToPreviousFrame;
    private JButton exit;

    private JTextField searchField;
    private JScrollPane scrollCustomers;

    private JPanel main;
    private JPanel panelList;

    private Authentication authentication;
    private CustomerDAO customer;

    private JList<String> customersList;
    private DefaultListModel<String> listModel;

    public CustomerUserFrame(Authentication auth) {
        authentication = auth;
        this.setTitle("Gestion des clients | " + auth.getLogin());
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

        this.customer = new CustomerDAO();

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
        customersList = new JList<>(listModel);
        panelList.add(customersList);
        scrollCustomers = new JScrollPane(panelList);
        scrollCustomers.setBounds(50, 75, 400, 155);
        scrollCustomers.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollCustomers.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    }

    private void disposition() {
        main.setLayout(null);

        main.add(searchButton);
        main.add(list);
        main.add(returnToPreviousFrame);
        main.add(exit);
        main.add(searchField);
        main.add(scrollCustomers);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == searchButton) {
            String domain = searchField.getText().toString();
            List<Customer> listOfCustomers = customer.getListCustomersByADomain(domain);
            if (!listOfCustomers.isEmpty()) {
                listModel.removeAllElements();
                for (Customer s : listOfCustomers) {
                    listModel.addElement(s.toString());
                }
            } else {
                ErrorFrame ef = new ErrorFrame("Le domaine que vous avez recherché n'existe pas.");
            }
        } else if (ae.getSource() == list) {
            List<Customer> listOfCustomers = customer.getListOfAllCustomers();
            listModel.removeAllElements();
            for (Customer s : listOfCustomers) {
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
        List<Customer> listOfCustomers = customer.getListOfAllCustomers();
        listModel.removeAllElements();
        for (Customer s : listOfCustomers) {
            listModel.addElement(s.toString());
        }
    }

    @Override
    public void windowLostFocus(WindowEvent we) {
    }

}
