package order;

import authentication.Authentication;
import customer.CustomerDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
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
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import order.orderItem.OrderItem;
import order.orderItem.OrderItemAddFrame;
import order.orderItem.OrderItemDAO;
import order.orderItem.OrderItemModifyFrame;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import product.Product;
import product.ProductDAO;
import util.DateFormat;
import util.ErrorFrame;
import util.SupplierOrder;
import util.SupplierOrderDAO;
import util.TemporaryOrderItem;
import util.TemporaryOrderItemDAO;

public class OrderAddFrame extends JDialog implements ActionListener, WindowFocusListener {

    private JLabel jlCompagny;
    private JLabel jlOrderDate;
    private JLabel jlDeliveryDeadline;
    private JLabel jlDeliveryDate;
    private JLabel jlPriceOrder;

    private JTextField jtPriceOrder;

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
    private ProductDAO productDAO;
    private OrderItemDAO orderItemDAO;
    private SupplierOrderDAO supplierOrderDAO;

    private double totalPriceOrder = 0.0;

    /**
     * Permet d'afficher la fenêtre qui ajoute gère l'ajout de commande
     *
     * @param auth - Informations de l'administrateur connecté
     */
    public OrderAddFrame(Authentication auth) {
        authentication = auth;
        this.setTitle("Créer des commandes | " + auth.getLogin());
        this.setSize(1000, 280);

        panel = new JPanel();
        add(panel);

        initialize();
        disposition();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                clearTemporaryDatabase();
                we.getWindow().dispose();
            }

        });
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
        this.productDAO = new ProductDAO();
        this.orderItemDAO = new OrderItemDAO();
        this.supplierOrderDAO = new SupplierOrderDAO();

        jlCompagny = new JLabel("Société : ");
        jlCompagny.setBounds(10, 10, 150, 25);

        jlOrderDate = new JLabel("Date commande :");
        jlOrderDate.setBounds(10, 50, 150, 25);

        jlDeliveryDeadline = new JLabel("Date limite de livraison :");
        jlDeliveryDeadline.setBounds(10, 90, 150, 25);

        jlDeliveryDate = new JLabel("Date livraison :");
        jlDeliveryDate.setBounds(10, 130, 150, 25);

        jlPriceOrder = new JLabel("Prix de la commande - Total TTC : ");
        jlPriceOrder.setBounds(400, 210, 250, 25);

        jtPriceOrder = new JTextField();
        jtPriceOrder.setBounds(600, 210, 100, 25);
        jtPriceOrder.setEditable(false);

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
        panel.add(jlPriceOrder);

        panel.add(jtPriceOrder);

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

    /**
     * Si on appuie sur le bouton "cancel", nous annulons l'ajout d'une
     * commande. Si on appuie sur le bouton "addOrder", nous ajoutons la
     * commande. Si on appuie sur le bouton "addProduct", nous ajoutons un
     * produit à la commande. Si on appuie sur le bouton "deleteProduct", nous
     * supprimons un produit présent dans la liste de produits. Si on appuie sur
     * le bouton "modifyProduct", nous modifions un produit présent dans la
     * liste de produits.
     *
     * @param ae - évènements déclenchés lors d'un appui sur un bouton
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        int returnCode = 0;
        int last_insert_id = 0;
        int quantity = 0;
        int productId = 0;
        try {
            if (ae.getSource() == cancel) {
                clearTemporaryDatabase();
                this.dispose();
            } else if (ae.getSource() == addOrder) {
                DefaultListModel<String> model = (DefaultListModel<String>) productsList.getModel();
                if (model.isEmpty()) {
                    ErrorFrame ef = new ErrorFrame("La liste est vide. Veuillez ajouter au moins un produit pour passer commande.");
                } else if (!orderDatePicker.getJFormattedTextField().getText().isEmpty() && !deliveryDeadlinePicker.getJFormattedTextField().getText().isEmpty() && !deliveryDatePicker.getJFormattedTextField().getText().isEmpty()) {

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
                    }

                    Order order = new Order(id, sqlOrderDate, sqlDeliveryDeadline, sqlDeliveryDate, totalPriceOrder);
                    last_insert_id = orderDAO.addOrder(order);

                    List<TemporaryOrderItem> listOfTemporaryOrderItems = temporaryOrderItemDAO.getListOfAllTemporaryOrderItems();
                    for (TemporaryOrderItem toi : listOfTemporaryOrderItems) {
                        productId = productDAO.getProductIdByCompanyAndProductNames(toi.getCompany_name(), toi.getProduct_name());
                        quantity = toi.getQuantity();
                        returnCode = orderItemDAO.addOrderItems(new OrderItem(0, last_insert_id, productId, quantity));
                    }
                    returnCode = productDAO.decreaseProductQuantity(last_insert_id);

                    List<Product> listOfProductsWhichHaveQuantityInferiorStock = productDAO.getListOfAllProductsWhichHaveQuantityInferiorStock(last_insert_id);
                    for (Product p : listOfProductsWhichHaveQuantityInferiorStock) {
                        returnCode = supplierOrderDAO.addSupplierOrder(new SupplierOrder(p.getReference(), p.getStockMin()));
                        returnCode = productDAO.increaseProductQuantityByReplenishment(p.getReference(), p.getStockMin() * 10);
                    }
                    clearTemporaryDatabase();
                    this.dispose();
                } else {
                    ErrorFrame eef = new ErrorFrame("Un ou plusieurs champs sont vides");
                }
            } else if (ae.getSource() == addProduct) {
                OrderItemAddFrame oiaf = new OrderItemAddFrame(authentication);
            } else if (ae.getSource() == deleteProduct) {
                DefaultListModel<String> model = (DefaultListModel<String>) productsList.getModel();
                String[] splitString = productsList.getSelectedValue().toString().split(" ");
                String id = splitString[2];
                returnCode = temporaryOrderItemDAO.deleteTemporaryOrderItem(id);
                model.remove(productsList.getSelectedIndex());
            } else if (ae.getSource() == modifyProduct) {
                DefaultListModel<String> model = (DefaultListModel<String>) productsList.getModel();
                String[] splitString = productsList.getSelectedValue().toString().split(" ");
                String id = splitString[2];
                TemporaryOrderItem temporaryOrderItem = temporaryOrderItemDAO.selectTemporaryOrderItemById(id);
                OrderItemModifyFrame oimf = new OrderItemModifyFrame(authentication, temporaryOrderItem);
            }
        } catch (Exception e) {

        }
    }

    /**
     * Permet de mettre à jour la liste des produits ainsi que le prix total de
     * la commande.
     *
     * @param we - Méthode appelée lorsque cette fenêtre se trouve au premier
     * plan
     */
    @Override
    public void windowGainedFocus(WindowEvent we) {
        totalPriceOrder = 0.0;
        List<TemporaryOrderItem> listOfTemporaryOrderItems = temporaryOrderItemDAO.getListOfAllTemporaryOrderItems();
        listModel.removeAllElements();
        for (TemporaryOrderItem toi : listOfTemporaryOrderItems) {
            listModel.addElement(toi.toString());
            totalPriceOrder += toi.getTotal_price();
        }
        jtPriceOrder.setText(String.valueOf(totalPriceOrder));
    }

    /**
     *
     * @param we
     */
    @Override
    public void windowLostFocus(WindowEvent we) {
    }

    private void clearTemporaryDatabase() {
        temporaryOrderItemDAO.clearTemporaryDatabase();
    }

}
