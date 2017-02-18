package customer;

import authentication.Authentication;
import customer.CustomerDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import menu.AdminMenu;

/**
 *
 * @author e1501601
 */
public class CustomerAdminFrame extends JFrame implements ActionListener {

    private JButton create;
    private JButton list;
    private JButton modify;
    private JButton delete;
    private JButton returnToPreviousFrame;
    private JButton quit;
    private JButton search;

    private Authentication authentication;
    private CustomerDAO customer;

    private JTextField searchField;

    private JScrollPane scrollCustomers;

    private JPanel main;
    private JPanel panelList;

    private JList<String> customersList;
    private DefaultListModel<String> listModel;

    public CustomerAdminFrame(Authentication auth) {
        authentication = auth;
        this.setTitle("Gestion des clients | " + auth.getLogin());
        this.setSize(1100, 300);

        main = new JPanel();
        add(main);

        initialize();
        disposition();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void initialize() {

        this.customer = new CustomerDAO();

        create = new JButton("Créer");
        create.setBounds(10, 50, 200, 30);
        create.addActionListener(this);

        modify = new JButton("Modifier");
        modify.setBounds(10, 100, 200, 30);
        modify.addActionListener(this);

        delete = new JButton("Supprimer");
        delete.setBounds(10, 150, 200, 30);
        delete.addActionListener(this);

        search = new JButton("Rechercher");
        search.setBounds(850, 50, 200, 30);
        search.addActionListener(this);

        list = new JButton("lister");
        list.setBounds(850, 100, 200, 30);
        list.addActionListener(this);

        returnToPreviousFrame = new JButton("Retour");
        returnToPreviousFrame.setBounds(850, 150, 200, 30);
        returnToPreviousFrame.addActionListener(this);

        quit = new JButton("Quitter");
        quit.setBounds(850, 200, 200, 30);
        quit.addActionListener(this);

        searchField = new JTextField("Champ de recherche");
        searchField.setBounds(275, 50, 500, 30);

        panelList = new JPanel();
        listModel = new DefaultListModel<>();
        customersList = new JList<>(listModel);
        panelList.add(customersList);
        scrollCustomers = new JScrollPane(panelList);
        scrollCustomers.setBounds(275, 80, 500, 150);
        scrollCustomers.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollCustomers.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    }

    private void disposition() {
        main.setLayout(null);

        main.add(create);
        main.add(list);
        main.add(modify);
        main.add(delete);
        main.add(returnToPreviousFrame);
        main.add(quit);
        main.add(searchField);
        main.add(search);
        main.add(scrollCustomers);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int returnCode = 0;
        try {
            if (ae.getSource() == create) {
                CustomerAddFrame saf = new CustomerAddFrame(authentication);
            } else if (ae.getSource() == list) {
                List<Customer> listOfCustomers = customer.getListOfAllCustomers();
                listModel.removeAllElements();
                for (Customer s : listOfCustomers) {
                    listModel.addElement(s.toString());
                }

            } else if (ae.getSource() == search) {

                String domain = searchField.getText().toString();
                List<Customer> listOfCustomers = customer.getListCustomersByADomain(domain);
                if (!listOfCustomers.isEmpty()) {
                    listModel.removeAllElements();
                    for (Customer s : listOfCustomers) {
                        listModel.addElement(s.toString());
                    }
                } else {
                    System.out.println("PAS DE DOMAINE POSSIBLE");
                }
            } else if (ae.getSource() == modify) {

            } else if (ae.getSource() == delete) {
                DefaultListModel<String> model = (DefaultListModel<String>) customersList.getModel();
                String[] splitString = customersList.getSelectedValue().toString().split(" ");
                String id = splitString[0];
                System.out.println("Split : " + id);
                // TODO - Delete in the database
                returnCode = customer.deleteCustomer(id);
                model.remove(customersList.getSelectedIndex());
            } else if (ae.getSource() == returnToPreviousFrame) {
                this.dispose();
                AdminMenu am = new AdminMenu(authentication);
            } else if (ae.getSource() == quit) {
                this.dispose();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
