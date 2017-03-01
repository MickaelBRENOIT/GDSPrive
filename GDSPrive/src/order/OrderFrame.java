package order;

import authentication.Authentication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import menu.AdminMenu;
import order.orderItem.OrderItemDAO;
import product.ProductDAO;

public class OrderFrame extends JFrame implements ActionListener, WindowFocusListener {

    private JButton create;
    private JButton list;
    private JButton modify;
    private JButton delete;
    private JButton returnToPreviousFrame;
    private JButton quit;
    private JButton search;

    private Authentication authentication;
    private OrderDAO order;
    private OrderItemDAO orderItemDAO;
    private ProductDAO productDAO;

    private JComboBox searchField;

    private JScrollPane scrollOrders;

    private JPanel main;
    private JPanel panelList;

    private JList<String> OrdersList;
    private DefaultListModel<String> listModel;

    /**
     * Permet d'afficher la fenêtre qui gère les commandes
     *
     * @param auth - Informations sur l'administrateur connecté
     */
    public OrderFrame(Authentication auth) {
        authentication = auth;
        this.setTitle("Gestion des commandes | " + auth.getLogin());
        this.setSize(1100, 300);

        main = new JPanel();
        add(main);

        initialize();
        disposition();

        addWindowFocusListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void initialize() {

        this.order = new OrderDAO();
        this.orderItemDAO = new OrderItemDAO();
        this.productDAO = new ProductDAO();

        create = new JButton("Créer");
        create.setBounds(10, 50, 200, 30);
        create.addActionListener(this);

        modify = new JButton("Modifier");
        modify.setBounds(10, 100, 200, 30);
        modify.setEnabled(false);
        modify.addActionListener(this);

        delete = new JButton("Supprimer");
        delete.setBounds(10, 150, 200, 30);
        delete.addActionListener(this);

        search = new JButton("Rechercher par société");
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

        searchField = new JComboBox();
        searchField.setBounds(275, 50, 500, 30);

        panelList = new JPanel();
        listModel = new DefaultListModel<>();
        OrdersList = new JList<>(listModel);
        panelList.add(OrdersList);
        scrollOrders = new JScrollPane(panelList);
        scrollOrders.setBounds(275, 80, 500, 150);
        scrollOrders.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollOrders.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

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
        main.add(scrollOrders);
    }

    /**
     * Si on appuie sur le bouton "create", cela affiche la fenêtre pour créer
     * une commande. Si on appuie sur le bouton "list", cela liste les commandes
     * passées. Si on appuie sur le bouton "search", cela cherche les commandes
     * passées par la société sélectionnée. Si on appuie sur le bouton "delete",
     * cela supprime la commande choisie dans la liste. Si on appuie sur le
     * bouton "returnToPreviousFrame", cela permet de revenir sur le menu
     * administrateur. Si on appuie sur le bouton "quit", cela permet de quitter
     * l'aaplication.
     *
     * @param ae - évènements déclenchés lorsqu'on appuie sur un bouton
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        int returnCode = 0;
        try {
            if (ae.getSource() == create) {
                OrderAddFrame uaf = new OrderAddFrame(authentication);
            } else if (ae.getSource() == list) {
                List<Order> listOfOrders = order.getListOfAllOrders();
                listModel.removeAllElements();
                for (Order o : listOfOrders) {
                    listModel.addElement(o.toString());
                }

            } else if (ae.getSource() == search) {

                String compagny = String.valueOf(searchField.getSelectedItem().toString());
                List<Order> listOfOrders = order.getListOrdersByACompagny(compagny);
                listModel.removeAllElements();
                for (Order o : listOfOrders) {
                    listModel.addElement(o.toString());
                }

            } else if (ae.getSource() == delete) {
                DefaultListModel<String> model = (DefaultListModel<String>) OrdersList.getModel();
                String[] splitString = OrdersList.getSelectedValue().toString().split(" ");
                String id = splitString[0];
                returnCode = productDAO.increaseProductQuantity(id);
                returnCode = orderItemDAO.deleteOrderItem(id);
                returnCode = order.deleteOrder(id);
                model.remove(OrdersList.getSelectedIndex());
            } else if (ae.getSource() == returnToPreviousFrame) {
                this.dispose();
                AdminMenu am = new AdminMenu(authentication);
            } else if (ae.getSource() == quit) {
                this.dispose();
            }

        } catch (Exception e) {
        }
    }

    /**
     * Permet de mettre à jour la liste des commandes ainsi que la liste
     * déroulante avec les noms des sociétés
     *
     * @param we - Méthode appelée lorsque cette fenêtre est en premier plan
     */
    @Override
    public void windowGainedFocus(WindowEvent we) {
        List<String> listOfOrdersInSearchField = order.getListOfAllCompanies();
        for (String s : listOfOrdersInSearchField) {
            searchField.addItem(s);
        }

        List<Order> listOfOrders = order.getListOfAllOrders();
        listModel.removeAllElements();
        for (Order o : listOfOrders) {
            listModel.addElement(o.toString());
        }
    }

    /**
     *
     * @param we
     */
    @Override
    public void windowLostFocus(WindowEvent we) {
    }

}
