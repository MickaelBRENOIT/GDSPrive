/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resupply;

import authentication.Authentication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
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
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import product.Product;
import product.ProductDAO;
import supplier.SupplierDAO;
import util.DateFormat;
import util.ErrorFrame;

/**
 *
 * @author sandra
 */
public class ResupplyAddFrame extends JDialog implements ActionListener {

    private Authentication authentication;
    private ProductDAO productDAO;
    private ResupplyDAO resupplyDAO;

    private JPanel panel;
    private JComboBox nomProduit;
    private JTextField quantite;
    private JComboBox jFournisseur;

    private JLabel jlnomProduit;
    private JLabel jlquantite;
    private JLabel jldateCommande;
    private JLabel jlnomFournisseur;

    private UtilDateModel dateCommandeModel;
    private JDatePanelImpl dateCommandePanel;
    private JDatePickerImpl dateCommandePicker;
    private Properties properties;

    private JButton commande;
    private JButton cancel;
    private String valueComboBox;

    public ResupplyAddFrame(Authentication auth) {
        authentication = auth;
        this.setTitle("Se reapprovisionner | " + auth.getLogin());
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
        this.resupplyDAO = new ResupplyDAO();

        jlnomProduit = new JLabel("Nom Produit : ");
        jlnomProduit.setBounds(10, 10, 150, 25);

        jlquantite = new JLabel("Quantite :");
        jlquantite.setBounds(10, 50, 150, 25);

        jldateCommande = new JLabel("Date de commande :");
        jldateCommande.setBounds(10, 90, 150, 25);

        jlnomFournisseur = new JLabel("Nom Fournisseur :");
        jlnomFournisseur.setBounds(10, 130, 150, 25);

        nomProduit = new JComboBox();
        nomProduit.setBounds(140, 10, 200, 25);
        nomProduit.addActionListener(this);

        List<String> getListProducts = productDAO.getListOfAllProductsDistinct();

        for (String s : getListProducts) {
            nomProduit.addItem(s);

        }

        quantite = new JTextField(20);
        quantite.setBounds(140, 50, 200, 25);

        properties = new Properties();
        properties.put("text.year", "Year");
        properties.put("text.month", "Month");
        properties.put("text.day", "Day");

        dateCommandeModel = new UtilDateModel();

        dateCommandePanel = new JDatePanelImpl(dateCommandeModel, properties);
        dateCommandePicker = new JDatePickerImpl(dateCommandePanel, new DateFormat());

        dateCommandePicker.setBounds(140, 90, 200, 25);

        jFournisseur = new JComboBox();
        jFournisseur.setBounds(140, 130, 200, 25);
        jFournisseur.addActionListener(this);

        String nom = nomProduit.getSelectedItem().toString();

        List<String> getListFournisseurs = productDAO.getListOfFournisseursByProducts();

        for (String p : getListFournisseurs) {
            jFournisseur.addItem(p);

        }
        commande = new JButton("Commander");
        commande.setBounds(40, 240, 110, 25);
        commande.addActionListener(this);

        cancel = new JButton("Annuler");
        cancel.setBounds(240, 240, 110, 25);
        cancel.addActionListener(this);

    }

    private void disposition() {
        panel.setLayout(null);

        panel.add(jlnomProduit);
        panel.add(jlnomFournisseur);
        panel.add(jldateCommande);
        panel.add(jlquantite);

        panel.add(quantite);
        panel.add(nomProduit);

        panel.add(dateCommandePicker);
        panel.add(jFournisseur);
        panel.add(commande);
        panel.add(cancel);

    }

    public void selection() {

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int returnCode = 0;

        if (ae.getSource() == cancel) {
            ResupplyAdminFrame rf = new ResupplyAdminFrame(authentication);
            this.dispose();

        } else if (ae.getSource() == nomProduit) {

            valueComboBox = nomProduit.getSelectedItem().toString();

            List<String> getListFournisseurs = productDAO.getListOfFournisseursByProducts();

            if (!getListFournisseurs.isEmpty()) {
                for (String p : getListFournisseurs) {

                }
            }

        } else if (ae.getSource() == commande) {

            if (!this.nomProduit.getSelectedItem().toString().isEmpty() && !this.quantite.getText().isEmpty() && !dateCommandePicker.getJFormattedTextField().getText().isEmpty() && !jFournisseur.getSelectedItem().toString().isEmpty()) {
                String dateCommande = dateCommandePicker.getJFormattedTextField().getText();
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                java.sql.Date sqlCommande = null;

                try {
                    Date Commande = format.parse(dateCommande);
                    sqlCommande = new java.sql.Date(Commande.getTime());

                } catch (ParseException ex) {
                }

                String nom = (String) nomProduit.getSelectedItem();
                int ceProduit = productDAO.idProduct(nom);
                Resupply resupply = new Resupply(ceProduit, this.quantite.getText(), sqlCommande);

                returnCode = resupplyDAO.addProduct(resupply);
                System.out.println("code de retour : " + returnCode);
                this.dispose();

            } else {
                ErrorFrame eff = new ErrorFrame("Un ou plusieurs champs sont vides");
            }
        }
    }

}
