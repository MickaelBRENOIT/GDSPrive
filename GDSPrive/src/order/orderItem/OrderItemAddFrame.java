/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order.orderItem;

import authentication.Authentication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Mikael
 */
public class OrderItemAddFrame extends JDialog implements ActionListener, ChangeListener{
    
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
    
    private JPanel panel;
    
    private Authentication authentication;

    public OrderItemAddFrame(Authentication auth) {
        authentication = auth;
        this.setTitle("Ajout de produit(s) | " + auth.getLogin());
        this.setSize(380, 250);

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
        jlProduct = new JLabel("Produit : ");
        jlProduct.setBounds(10, 10, 150, 25);
        
        jlPrice = new JLabel("Prix unitaire : ");
        jlPrice.setBounds(10, 50, 150, 25);
        
        jlQuantity = new JLabel("Quantité : ");
        jlQuantity.setBounds(10, 90, 150, 25);
        
        jlTotal = new JLabel("Total TTC : ");
        jlTotal.setBounds(10, 130, 150, 25);
        
        jcbProduct = new JComboBox();
        jcbProduct.setBounds(150, 10, 200, 25);
        /* TODO */
        jcbProduct.addActionListener(this);
        
        jtPrice = new JTextField();
        jtPrice.setBounds(150, 50, 200, 25);
        
        quantityModel = new SpinnerNumberModel(1, 1, 100, 1);
        /* TODO */
        quantityJspinner = new JSpinner(quantityModel);
        quantityJspinner.setBounds(150, 90, 200, 25);
        quantityJspinner.addChangeListener(this);
                
        jtTotal = new JTextField();
        jtTotal.setBounds(150, 130, 200, 25);
        
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
        panel.add(jlTotal);
        
        panel.add(jcbProduct);
        
        panel.add(jtPrice);
        panel.add(jtTotal);
        
        panel.add(quantityJspinner);
        
        panel.add(add);
        panel.add(cancel);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
