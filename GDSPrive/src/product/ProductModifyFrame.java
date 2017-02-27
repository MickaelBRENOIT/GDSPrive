package product;

import authentication.Authentication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import supplier.SupplierDAO;
import util.DateFormat;
import util.ErrorEmptyFrame;

/**
 *
 * @author e1501601
 */
public class ProductModifyFrame extends JDialog implements ActionListener {

    private JTextField nomProduit;
    private JTextField prixUnitaire;
    private JTextField quantite;
    private JTextField dateExpiration;
    private JTextField stockMin;

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

    private SupplierDAO suppliersDAO;

    private ProductDAO productDAO;
    private Product products;

    public ProductModifyFrame(Authentication auth, Product prod) {
        authentication = auth;
        products = prod;
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
        this.suppliersDAO = new SupplierDAO();

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
        nomProduit.setText(products.getNomProduit());

        prixUnitaire = new JTextField(20);
        prixUnitaire.setBounds(140, 50, 200, 25);
        prixUnitaire.setText(Double.toString(products.getPrixUnitaire()));

        quantite = new JTextField(20);
        quantite.setBounds(140, 90, 200, 25);
        quantite.setText(products.getQuantite());

        properties = new Properties();
        properties.put("text.year", "Year");
        properties.put("text.month", "Month");
        properties.put("text.day", "Day");

        dateExpirationModel = new UtilDateModel();

        dateExpirationPanel = new JDatePanelImpl(dateExpirationModel, properties);
        dateExpirationPicker = new JDatePickerImpl(dateExpirationPanel, new DateFormat());
        dateExpirationPicker.setBounds(140, 130, 200, 25);
        dateExpirationModel.setValue(products.getDateExpiration());

        jFournisseur = new JComboBox();
        jFournisseur.setBounds(140, 170, 200, 25);

        List<Product> getListFournisseurs = productDAO.getListOfAllFournisseurs();

        for (Product p : getListFournisseurs) {
            jFournisseur.addItem(p.getNomFournisseur());

        }
        jFournisseur.setSelectedItem(products.getNomFournisseur());

        stockMin = new JTextField();
        stockMin.setBounds(140, 210, 200, 25);
        stockMin.setText(products.getStockMin());

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
        panel.add(dateExpirationPicker);
        panel.add(stockMin);
        panel.add(jFournisseur);
        panel.add(add);
        panel.add(cancel);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int returnCode = 0;

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
                Logger.getLogger(ProductModifyFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

            String nomSociete = jFournisseur.getSelectedItem().toString();
            int ceFournisseur = suppliersDAO.idSupplier(nomSociete);
            Product products = new Product(this.products.getReference(), nomProduit.getText(), Double.parseDouble(this.prixUnitaire.getText()), quantite.getText(), sqlExpiration, jFournisseur.getSelectedItem().toString(), ceFournisseur, this.stockMin.getText());

            System.out.println("code:" + this.products.getReference());

            returnCode = productDAO.modifyProduct(products);
            System.out.println("code de retour : " + returnCode);
            this.dispose();
            }else{
                ErrorEmptyFrame eff = new ErrorEmptyFrame();
            }
        } else if (ae.getSource() == cancel) {
            this.dispose();
        }

    }

}
