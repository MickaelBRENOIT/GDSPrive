/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import authentication.Authentication;
import customer.CustomerDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import order.orderItem.OrderItemAddFrame;
import order.orderItem.OrderItemModifyFrame;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import util.DateFormat;
import util.TemporaryOrderItem;
import util.TemporaryOrderItemDAO;

/**
 *
 * @author Mikael
 */
public class OrderAddFrame extends JDialog implements ActionListener, WindowFocusListener {

    private JLabel jlCompagny;
    private JLabel jlOrderDate;
    private JLabel jlDeliveryDeadline;
    private JLabel jlDeliveryDate;

    private JButton addOrder;
    private JButton cancel;
    private JButton addProduct;
    private JButton modifyProduct;
    private JButton deleteProduct;

    private JComboBox jcbCompagny;

    private Properties properties;

    private UtilDateModel orderDateModel;
    private JDatePanelImpl orderDatePanel;
    private JDatePickerImpl orderDatePicker;

    private UtilDateModel deliveryDeadlineModel;
    private JDatePanelImpl deliveryDeadlinePanel;
    private JDatePickerImpl deliveryDeadlinePicker;

    private UtilDateModel deliveryDateModel;
    private JDatePanelImpl deliveryDatePanel;
    private JDatePickerImpl deliveryDatePicker;

    private JPanel panel;
    private JPanel panelList;

    private JList<String> productsList;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollProducts;

    private Authentication authentication;
    private OrderDAO orderDAO;
    private CustomerDAO customerDAO;
    private TemporaryOrderItemDAO temporaryOrderItemDAO; 

    public OrderAddFrame(Authentication auth) {
        authentication = auth;
        this.setTitle("Créer des commandes | " + auth.getLogin());
        this.setSize(1000, 250);

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
        this.customerDAO = new CustomerDAO();
        this.temporaryOrderItemDAO = new TemporaryOrderItemDAO();

        jlCompagny = new JLabel("Société : ");
        jlCompagny.setBounds(10, 10, 150, 25);

        jlOrderDate = new JLabel("Date commande :");
        jlOrderDate.setBounds(10, 50, 150, 25);

        jlDeliveryDeadline = new JLabel("Date limite de livraison :");
        jlDeliveryDeadline.setBounds(10, 90, 150, 25);

        jlDeliveryDate = new JLabel("Date livraison :");
        jlDeliveryDate.setBounds(10, 130, 150, 25);

        jcbCompagny = new JComboBox();
        jcbCompagny.setBounds(150, 10, 200, 25);
        List<String> listOfCompanies = customerDAO.getListOfAllCompanies();
        for (String s : listOfCompanies) {
            jcbCompagny.addItem(s);
        }

        properties = new Properties();
        properties.put("text.year", "Year");
        properties.put("text.month", "Month");
        properties.put("text.day", "Day");

        orderDateModel = new UtilDateModel();
        orderDatePanel = new JDatePanelImpl(orderDateModel, properties);
        orderDatePicker = new JDatePickerImpl(orderDatePanel, new DateFormat());
        orderDatePicker.setBounds(150, 50, 200, 25);

        deliveryDeadlineModel = new UtilDateModel();
        deliveryDeadlinePanel = new JDatePanelImpl(deliveryDeadlineModel, properties);
        deliveryDeadlinePicker = new JDatePickerImpl(deliveryDeadlinePanel, new DateFormat());
        deliveryDeadlinePicker.setBounds(150, 90, 200, 25);

        deliveryDateModel = new UtilDateModel();
        deliveryDatePanel = new JDatePanelImpl(deliveryDateModel, properties);
        deliveryDatePicker = new JDatePickerImpl(deliveryDatePanel, new DateFormat());
        deliveryDatePicker.setBounds(150, 130, 200, 25);

        panelList = new JPanel();
        listModel = new DefaultListModel<>();
        productsList = new JList<>(listModel);
        panelList.add(productsList);
        scrollProducts = new JScrollPane(panelList);
        scrollProducts.setBounds(400, 50, 550, 150);
        scrollProducts.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollProducts.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        addProduct = new JButton("Ajouter produit");
        addProduct.setBounds(400, 10, 150, 25);
        addProduct.addActionListener(this);

        modifyProduct = new JButton("Modifier produit");
        modifyProduct.setBounds(600, 10, 150, 25);
        modifyProduct.addActionListener(this);

        deleteProduct = new JButton("Supprimer produit");
        deleteProduct.setBounds(800, 10, 150, 25);
        deleteProduct.addActionListener(this);

        addOrder = new JButton("Ajouter commande");
        addOrder.setBounds(10, 170, 150, 25);
        addOrder.addActionListener(this);

        cancel = new JButton("Annuler");
        cancel.setBounds(200, 170, 150, 25);
        cancel.addActionListener(this);

    }

    private void disposition() {
        panel.setLayout(null);

        panel.add(jlCompagny);
        panel.add(jlDeliveryDate);
        panel.add(jlDeliveryDeadline);
        panel.add(jlOrderDate);

        panel.add(jcbCompagny);

        panel.add(orderDatePicker);
        panel.add(deliveryDatePicker);
        panel.add(deliveryDeadlinePicker);

        panel.add(scrollProducts);

        panel.add(addProduct);
        panel.add(modifyProduct);
        panel.add(deleteProduct);
        panel.add(addOrder);
        panel.add(cancel);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int returnCode = 0;
        try {
            if (ae.getSource() == cancel) {
                this.dispose();
            } else if (ae.getSource() == addOrder) {
                DefaultListModel<String> model = (DefaultListModel<String>) productsList.getModel();
                if (model.isEmpty()) {
                    System.out.println("La liste est vide. Veuillez ajouter au moins un produit pour passer commande.");
                } else {
                    System.out.println("ok");
                    String compagny = String.valueOf(jcbCompagny.getSelectedItem().toString());
                    int id = customerDAO.getReferenceCompanyByName(compagny);
                    String orderDate = orderDatePicker.getJFormattedTextField().getText();
                    String DeliveryDeadline = deliveryDeadlinePicker.getJFormattedTextField().getText();
                    String DeliveryDate = deliveryDatePicker.getJFormattedTextField().getText();
                    String pattern = "yyyy-MM-dd";
                    SimpleDateFormat format = new SimpleDateFormat(pattern);
                    java.sql.Date sqlOrderDate = null, sqlDeliveryDeadline = null, sqlDeliveryDate = null;

                    try {
                        java.util.Date order = format.parse(orderDate);
                        sqlOrderDate = new java.sql.Date(order.getTime());
                        java.util.Date deadline = format.parse(DeliveryDeadline);
                        sqlDeliveryDeadline = new java.sql.Date(deadline.getTime());
                        java.util.Date date = format.parse(DeliveryDate);
                        sqlDeliveryDate = new java.sql.Date(date.getTime());
                    } catch (ParseException ex) {
                        System.out.println(ex);
                    }

                    Order order = new Order(id, sqlOrderDate, sqlDeliveryDeadline, sqlDeliveryDate);
                    returnCode = orderDAO.addOrder(order);
                    System.out.println("Key : " + returnCode);
                    this.dispose();
                }
            } else if (ae.getSource() == addProduct) {
                OrderItemAddFrame oiaf = new OrderItemAddFrame(authentication);
            } else if (ae.getSource() == deleteProduct){
                DefaultListModel<String> model = (DefaultListModel<String>) productsList.getModel();
                String[] splitString = productsList.getSelectedValue().toString().split(" ");
                String id = splitString[2];
                System.out.println("Split : " + id);
                // TODO - Delete in the database
                returnCode = temporaryOrderItemDAO.deleteTemporaryOrderItem(id);
                model.remove(productsList.getSelectedIndex());
            } else if (ae.getSource() == modifyProduct){
                DefaultListModel<String> model = (DefaultListModel<String>) productsList.getModel();
                String[] splitString = productsList.getSelectedValue().toString().split(" ");
                String id = splitString[2];
                System.out.println("Split : " + id);
                // TODO - Delete in the database
                TemporaryOrderItem temporaryOrderItem = temporaryOrderItemDAO.selectTemporaryOrderItemById(id);
                OrderItemModifyFrame oimf = new OrderItemModifyFrame(authentication, temporaryOrderItem);
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void windowGainedFocus(WindowEvent we) {
        List<TemporaryOrderItem> listOfTemporaryOrderItems = temporaryOrderItemDAO.getListOfAllTemporaryOrderItems();
        listModel.removeAllElements();
        for (TemporaryOrderItem toi : listOfTemporaryOrderItems) {
            listModel.addElement(toi.toString());
        }
    }

    @Override
    public void windowLostFocus(WindowEvent we) {
    }

}
