package customer;

import authentication.Authentication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import order.Order;
import order.OrderDAO;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import util.DateFormat;
import util.ErrorFrame;

public class CustomerSeeOrderFrame extends JDialog implements ActionListener, WindowFocusListener {

    private Authentication authentication;
    private JPanel panel;
    private JPanel panelList;
    private JLabel Jclient;
    private JLabel JdateSortie;
    private JTextField jtnom;
    private JButton modifier;
    private JButton enregistrer;
    private JButton retour;
    private OrderDAO orderDAO;

    private UtilDateModel dateModel;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;
    private Properties properties;

    private JList<String> OrdersList;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollOrders;

    /**
     * Permet d'afficher la fenêtre qui visionne toutes les commandes clients.
     *
     * @param auth - informations de l'administrateur connecté
     */
    public CustomerSeeOrderFrame(Authentication auth) {

        authentication = auth;
        this.setTitle("Afficher les commandes | " + auth.getLogin());
        this.setSize(1000, 220);

        panel = new JPanel();
        add(panel);

        initialize();
        disposition();

        addWindowFocusListener(this);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);

    }

    private void initialize() {

        this.orderDAO = new OrderDAO();

        Jclient = new JLabel("Nom Client : ");
        Jclient.setBounds(10, 10, 100, 25);

        properties = new Properties();
        properties.put("text.year", "Year");
        properties.put("text.month", "Month");
        properties.put("text.day", "Day");

        JdateSortie = new JLabel("Date livraison :");
        JdateSortie.setBounds(10, 135, 100, 25);

        jtnom = new JTextField(20);
        jtnom.setBounds(100, 10, 150, 25);

        dateModel = new UtilDateModel();
        datePanel = new JDatePanelImpl(dateModel, properties);
        datePicker = new JDatePickerImpl(datePanel, new DateFormat());
        datePicker.setBounds(100, 135, 150, 25);

        modifier = new JButton("Modifier");
        modifier.setBounds(800, 10, 150, 25);
        modifier.addActionListener(this);

        enregistrer = new JButton("Enregistrer");
        enregistrer.setBounds(800, 70, 150, 25);
        enregistrer.addActionListener(this);

        retour = new JButton("Retour");
        retour.setBounds(800, 135, 150, 25);
        retour.addActionListener(this);

        panelList = new JPanel();
        listModel = new DefaultListModel<>();
        OrdersList = new JList<>(listModel);
        panelList.add(OrdersList);
        scrollOrders = new JScrollPane(panelList);
        scrollOrders.setBounds(280, 10, 500, 150);
        scrollOrders.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollOrders.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    }

    private void disposition() {
        panel.setLayout(null);

        panel.add(datePicker);
        panel.add(Jclient);
        panel.add(JdateSortie);
        panel.add(jtnom);
        panel.add(modifier);
        panel.add(enregistrer);
        panel.add(retour);
        panel.add(scrollOrders);

    }

    /**
     * Si on appuie sur le bouton "modifier", cela permet de modifier la
     * commande sélctionnée dans la liste. Si on appuie sur le bouton
     * "enregistrer", cela permet d'enregistrer les modifications. Si on appuie
     * sur le bouton "retour", cela permet de retourner sur la fenêtre de
     * gestion des clients.
     *
     * @param ae - évènements déclenchés lorsqu'on appuie sur un des boutons de
     * la fenêtre
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        int returnCode = 0;
        try {
            if (ae.getSource() == modifier) {
                DefaultListModel<String> model = (DefaultListModel<String>) OrdersList.getModel();
                if (!model.isEmpty()) {
                    String[] splitString = OrdersList.getSelectedValue().toString().split(" ");
                    String id = splitString[0];

                    jtnom.setText(orderDAO.getAOrder(id).toString());
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    dateModel.setValue(formatter.parse(orderDAO.getAOrderDate(id).toString()));

                }

            } else if (ae.getSource() == enregistrer) {
                String[] splitString = OrdersList.getSelectedValue().toString().split(" ");
                String id = splitString[0];

                String date = datePicker.getJFormattedTextField().getText();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                java.sql.Date sqlDate = null;

                try {
                    Date dateFormat = format.parse(date);
                    sqlDate = new java.sql.Date(dateFormat.getTime());
                } catch (ParseException ex) {
                    Logger.getLogger(CustomerSeeOrderFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

                Order orders = new Order(Integer.parseInt(id), this.jtnom.getText(), sqlDate);
                returnCode = orderDAO.modifyOrder(orders);
                if (returnCode != 0) {
                    List<Order> listOfOrders = orderDAO.getListOfAllOrders();
                    listModel.removeAllElements();
                    for (Order o : listOfOrders) {
                        listModel.addElement(o.toString());

                    }
                } else {
                    ErrorFrame ef = new ErrorFrame("Un des champs est mal renseigné. La modification n'a pas été effectuée.");   
                }

            } else if (ae.getSource() == retour) {
                this.dispose();
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Permet de mettre à jour la liste de commandes.
     *
     * @param we - évènement appelé lorsque cette fenêtre passe en premier plan
     */
    public void windowGainedFocus(WindowEvent we) {
        List<Order> listOfOrders = orderDAO.getListOfAllOrders();
        listModel.removeAllElements();
        for (Order o : listOfOrders) {
            listModel.addElement(o.toString());

        }
    }

    /**
     *
     * @param e
     */
    public void windowLostFocus(WindowEvent e) {

    }

}
