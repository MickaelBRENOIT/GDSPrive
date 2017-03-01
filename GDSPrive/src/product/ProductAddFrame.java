package product;

import authentication.Authentication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import supplier.SupplierDAO;
import util.DateFormat;
import util.ErrorFrame;
import util.JNumberTextField;

public class ProductAddFrame extends JDialog implements ActionListener {

    private JTextField nomProduit;
    private JTextField prixUnitaire;
    private JNumberTextField quantite;
    private JNumberTextField stockMin;

    private JLabel jlnomProduit;
    private JLabel jlPrixUnitaire;
    private JLabel jlquantite;
    private JLabel jldateExpiration;
    private JLabel jlnomFournisseur;
    private JLabel jstockMin;

    private UtilDateModel dateExpirationModel;
    private JDatePanelImpl dateExpirationPanel;
    private JDatePickerImpl dateExpirationPicker;
    private Properties properties;

    private JComboBox jFournisseur;

    private JButton add;
    private JButton cancel;

    private JPanel panel;
    private Authentication authentication;
    private ProductDAO productDAO;
    private SupplierDAO supplierDAO;

    /**
     *
     * @param auth qui ouvre une fenetre de creation de produit en fonction de
     * l'utilisateur connecté
     */
    public ProductAddFrame(Authentication auth) {
        authentication = auth;
        this.setTitle("Créer des produits | " + auth.getLogin());
        this.setSize(400, 300);

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
        this.supplierDAO = new SupplierDAO();

        jlnomProduit = new JLabel("Nom Produit : ");
        jlnomProduit.setBounds(10, 10, 150, 25);

        jlPrixUnitaire = new JLabel("Prix Unitaire :");
        jlPrixUnitaire.setBounds(10, 50, 150, 25);

        jlquantite = new JLabel("Quantite :");
        jlquantite.setBounds(10, 90, 150, 25);

        jldateExpiration = new JLabel("Date d'expiration :");
        jldateExpiration.setBounds(10, 130, 150, 25);

        jlnomFournisseur = new JLabel("Nom Fournisseur :");
        jlnomFournisseur.setBounds(10, 170, 150, 25);

        jstockMin = new JLabel("Stock minimal :");
        jstockMin.setBounds(10, 210, 150, 25);

        nomProduit = new JTextField(20);
        nomProduit.setBounds(140, 10, 200, 25);

        prixUnitaire = new JTextField(20);
        prixUnitaire.setBounds(140, 50, 200, 25);

        quantite = new JNumberTextField();
        quantite.setBounds(140, 90, 200, 25);

        properties = new Properties();
        properties.put("text.year", "Year");
        properties.put("text.month", "Month");
        properties.put("text.day", "Day");

        dateExpirationModel = new UtilDateModel();

        dateExpirationPanel = new JDatePanelImpl(dateExpirationModel, properties);
        dateExpirationPicker = new JDatePickerImpl(dateExpirationPanel, new DateFormat());

        dateExpirationPicker.setBounds(140, 130, 200, 25);

        jFournisseur = new JComboBox();
        jFournisseur.setBounds(140, 170, 200, 25);
        List<Product> getListFournisseurs = productDAO.getListOfAllFournisseurs();

        for (Product p : getListFournisseurs) {
            jFournisseur.addItem(p.getNomFournisseur());

        }

        stockMin = new JNumberTextField();
        stockMin.setBounds(140, 210, 200, 25);

        add = new JButton("Ajouter");
        add.setBounds(40, 240, 100, 25);
        add.addActionListener(this);

        cancel = new JButton("Annuler");
        cancel.setBounds(240, 240, 100, 25);
        cancel.addActionListener(this);

    }

    private void disposition() {
        panel.setLayout(null);

        panel.add(jlnomProduit);
        panel.add(jlnomFournisseur);
        panel.add(jldateExpiration);
        panel.add(jlPrixUnitaire);
        panel.add(jlquantite);
        panel.add(jstockMin);

        panel.add(nomProduit);
        panel.add(prixUnitaire);
        panel.add(quantite);
        panel.add(stockMin);

        panel.add(dateExpirationPicker);
        panel.add(jFournisseur);
        panel.add(add);
        panel.add(cancel);

    }

    /**
     * Si on appuie sur le bouton "add", cela ajoute le produit. Si on appuie
     * sur le bouton "cancel", cela annule la modification.
     *
     * @param ae - évènements déclenchés lors de la pression d'un des boutons
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        int returnCode = 0;
        try {
            if (ae.getSource() == add) {

                if (!this.nomProduit.getText().isEmpty() && !this.prixUnitaire.getText().isEmpty() && !this.quantite.getText().isEmpty() && !dateExpirationPicker.getJFormattedTextField().getText().isEmpty() && !jFournisseur.getSelectedItem().toString().isEmpty() && !this.stockMin.getText().isEmpty()) {

                    String dateExpiration = dateExpirationPicker.getJFormattedTextField().getText();
                    String pattern = "yyyy-MM-dd";
                    SimpleDateFormat format = new SimpleDateFormat(pattern);
                    java.sql.Date sqlExpiration = null;

                    try {
                        Date Expiration = format.parse(dateExpiration);
                        sqlExpiration = new java.sql.Date(Expiration.getTime());

                    } catch (ParseException ex) {
                    }

                    String nom = (String) jFournisseur.getSelectedItem();
                    int ceFournisseur = supplierDAO.idSupplier(nom);

                    Product product = new Product(this.nomProduit.getText(), Double.parseDouble(this.prixUnitaire.getText()), this.quantite.getText(), sqlExpiration, jFournisseur.getSelectedItem().toString(), ceFournisseur, Integer.parseInt(this.stockMin.getText()));
                    returnCode = productDAO.addProduct(product);
                    this.dispose();
                } else {
                    ErrorFrame eff = new ErrorFrame("Un ou plusieurs champs sont vides");
                }
            } else if (ae.getSource() == cancel) {
                this.dispose();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
