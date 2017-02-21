/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import authentication.Authentication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
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
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import util.DateFormat;

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
    
    private UtilDateModel OrderDateModel;
    private JDatePanelImpl OrderDatePanel;
    private JDatePickerImpl OrderDatePicker;
    
    private UtilDateModel deliveryDeadlineModel;
    private JDatePanelImpl deliveryDeadlinePanel;
    private JDatePickerImpl deliveryDeadlinePicker;
    
    private UtilDateModel DeliveryDateModel;
    private JDatePanelImpl DeliveryDatePanel;
    private JDatePickerImpl DeliveryDatePicker;
    
    private JPanel panel;
    private JPanel panelList;

    private JList<String> ProductsList;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollProducts;
    
    private Authentication authentication;
    //private OrderDAO orderDAO;

    public OrderAddFrame(Authentication auth) {
        authentication = auth;
        this.setTitle("Créer des commandes | " + auth.getLogin());
        this.setSize(1000, 250);

        panel = new JPanel();
        add(panel);

        initialize();
        disposition();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);
    }
    
    private void initialize() {
        //this.userDAO = new UserDAO();

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
        /*List<User> listOfRoles = userDAO.getListOfAllRoles();
        for (User u : listOfRoles) {
            jcbRole.addItem(u.getDesignation());
        }*/
        
        properties = new Properties();
        properties.put("text.year", "Year");
        properties.put("text.month", "Month");
        properties.put("text.day", "Day");
        
        OrderDateModel = new UtilDateModel();
        OrderDatePanel = new JDatePanelImpl(OrderDateModel, properties);
        OrderDatePicker = new JDatePickerImpl(OrderDatePanel, new DateFormat());
        OrderDatePicker.setBounds(150, 50, 200, 25);
        
        deliveryDeadlineModel = new UtilDateModel();
        deliveryDeadlinePanel = new JDatePanelImpl(deliveryDeadlineModel, properties);
        deliveryDeadlinePicker = new JDatePickerImpl(deliveryDeadlinePanel, new DateFormat());
        deliveryDeadlinePicker.setBounds(150, 90, 200, 25);
        
        DeliveryDateModel = new UtilDateModel();
        DeliveryDatePanel = new JDatePanelImpl(DeliveryDateModel, properties);
        DeliveryDatePicker = new JDatePickerImpl(DeliveryDatePanel, new DateFormat());
        DeliveryDatePicker.setBounds(150, 130, 200, 25);
        
        panelList = new JPanel();
        listModel = new DefaultListModel<>();
        ProductsList = new JList<>(listModel);
        panelList.add(ProductsList);
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
        
        panel.add(OrderDatePicker);
        panel.add(DeliveryDatePicker);
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
    }

    @Override
    public void windowGainedFocus(WindowEvent we) {
    }

    @Override
    public void windowLostFocus(WindowEvent we) {
    }
    
}
