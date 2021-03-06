package order.orderItem;

import authentication.Authentication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import product.ProductDAO;
import util.ErrorFrame;
import util.TemporaryOrderItem;
import util.TemporaryOrderItemDAO;

public class OrderItemAddFrame extends JDialog implements ActionListener {

    private JLabel jlProduct;
    private JLabel jlPrice;
    private JLabel jlQuantity;
    private JLabel jlTotal;

    private JComboBox jcbProduct;

    private SpinnerModel quantityModel;
    private JSpinner quantityJspinner;

    private JTextField jtPrice;
    private JTextField jtTotal;

    private JButton add;
    private JButton cancel;
    private JButton total;

    private JPanel panel;

    private Authentication authentication;
    private ProductDAO productDAO;
    private TemporaryOrderItemDAO temporaryOrderItemDAO;

    private String valueComboBox;
    private String[] splitString;
    private String company;
    private String product;
    private String result;

    /**
     *
     * @param auth affiche la fenetre d'ajout du produit en fonction de
     * l'utilisateur connecté
     */
    public OrderItemAddFrame(Authentication auth) {
        authentication = auth;
        this.setTitle("Ajout de produit(s) | " + auth.getLogin());
        this.setSize(580, 250);

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
        this.productDAO = new ProductDAO();
        this.temporaryOrderItemDAO = new TemporaryOrderItemDAO();

        jlProduct = new JLabel("Produit : ");
        jlProduct.setBounds(10, 10, 150, 25);

        jlPrice = new JLabel("Prix unitaire : ");
        jlPrice.setBounds(10, 50, 150, 25);

        jlQuantity = new JLabel("Quantité : ");
        jlQuantity.setBounds(10, 90, 150, 25);

        total = new JButton("Total TTC");
        total.setBounds(10, 130, 120, 25);
        total.addActionListener(this);

        jcbProduct = new JComboBox();
        jcbProduct.setBounds(150, 10, 400, 25);
        List<String> listOfSuppliersAndProducts = productDAO.getListOfAlluppliersAndProducts();
        for (String s : listOfSuppliersAndProducts) {
            jcbProduct.addItem(s);
        }

        jcbProduct.addActionListener(this);

        jtPrice = new JTextField();
        jtPrice.setBounds(150, 50, 200, 25);
        jtPrice.setEnabled(false);
        retrieveCompanyAndProduct();
        double unitPrice = productDAO.getUnitPriceByCompanyAndProduct(company, product);
        jtPrice.setText(String.valueOf(unitPrice));

        quantityModel = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
        quantityJspinner = new JSpinner(quantityModel);
        quantityJspinner.setBounds(150, 90, 200, 25);

        jtTotal = new JTextField();
        jtTotal.setBounds(150, 130, 200, 25);
        jtTotal.setEnabled(false);

        add = new JButton("Ajouter");
        add.setBounds(10, 170, 150, 25);
        add.addActionListener(this);

        cancel = new JButton("Annuler");
        cancel.setBounds(200, 170, 150, 25);
        cancel.addActionListener(this);

    }

    private void disposition() {
        panel.setLayout(null);

        panel.add(jlProduct);
        panel.add(jlQuantity);
        panel.add(jlPrice);
        panel.add(total);

        panel.add(jcbProduct);

        panel.add(jtPrice);
        panel.add(jtTotal);

        panel.add(quantityJspinner);

        panel.add(add);
        panel.add(cancel);
    }

    /**
     * Si on appuie sur le bouton "add", cela ajoute la commande. Si on appuie
     * sur le bouton "cancel", cela annule la modification. si on appuie sur le
     * bouton "total",cela donne le total Si on appuie sur "JcbProduct",cela
     * selectionne dans la combobox
     *
     * @param ae - évènements déclenchés lors de la pression d'un des boutons
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        int returnCode = 0;

        if (ae.getSource() == cancel) {
            this.dispose();

        } else if (ae.getSource() == add) {
            if (!jtTotal.getText().isEmpty() && !jtPrice.getText().isEmpty()) {
                retrieveCompanyAndProduct();
                Double unitPrice = Double.parseDouble(jtPrice.getText());
                int quantityItem = (Integer) quantityJspinner.getValue();
                Double totalPrice = Double.parseDouble(result);
                TemporaryOrderItem toi = new TemporaryOrderItem(0, company, product, unitPrice, quantityItem, totalPrice);
                returnCode = temporaryOrderItemDAO.addTemporaryOrderItem(toi);
                this.dispose();
            } else {
                ErrorFrame eef = new ErrorFrame("Un ou plusieurs champs sont vides");
            }

        } else if (ae.getSource() == jcbProduct) {
            retrieveCompanyAndProduct();
            double unitPrice = productDAO.getUnitPriceByCompanyAndProduct(company, product);
            jtPrice.setText(String.valueOf(unitPrice));

        } else if (ae.getSource() == total) {
            retrieveCompanyAndProduct();
            int quantity = productDAO.getQuantityOfAProduct(company, product);
            if ((Integer) quantityJspinner.getValue() > quantity) {
                ErrorFrame ef = new ErrorFrame("La quantité indiquée dépasse le stock restant du produit.");
            } else if (jtPrice.getText() != "") {
                Double res = Double.parseDouble(jtPrice.getText()) * (Integer) quantityJspinner.getValue();
                DecimalFormat df = new DecimalFormat("0.00");
                result = df.format(res).replace(',', '.');
                jtTotal.setText(result);
            }
        }
    }

    private void retrieveCompanyAndProduct() {
        valueComboBox = jcbProduct.getSelectedItem().toString();
        splitString = valueComboBox.split(" - ");
        company = splitString[0];
        product = splitString[1];
    }

}
