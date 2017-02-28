/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supplier;

import authentication.Authentication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import util.ErrorFrame;
import util.JNumberTextField;

/**
 *
 * @author e1501601
 */
public class SupplierAddFrame extends JDialog implements ActionListener {

    private JTextField jtSociety;
    private JNumberTextField jtPhone;
    private JTextField jtMail;
    private JTextField jtAddress;
    private JTextField jtField;

    private JLabel jlSociety;
    private JLabel jlPhone;
    private JLabel jlMail;
    private JLabel jlAddress;
    private JLabel jlField;

    private JButton add;
    private JButton cancel;

    private JPanel panel;

    private Authentication authentication;
    private SupplierDAO supplierDAO;

    public SupplierAddFrame(Authentication auth) {
        authentication = auth;
        this.setTitle("Créer des fournisseurs | " + auth.getLogin());
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

        this.supplierDAO = new SupplierDAO();

        jlSociety = new JLabel("Nom Société : ");
        jlSociety.setBounds(10, 10, 150, 25);

        jlAddress = new JLabel("Adresse :");
        jlAddress.setBounds(10, 50, 150, 25);

        jlField = new JLabel("Domaine :");
        jlField.setBounds(10, 90, 150, 25);

        jlMail = new JLabel("Email :");
        jlMail.setBounds(10, 130, 150, 25);

        jlPhone = new JLabel("Telephone :");
        jlPhone.setBounds(10, 170, 150, 25);

        jtSociety = new JTextField(20);
        jtSociety.setBounds(140, 10, 200, 25);

        jtAddress = new JTextField(20);
        jtAddress.setBounds(140, 50, 200, 25);

        jtField = new JTextField(20);
        jtField.setBounds(140, 90, 200, 25);

        jtPhone = new JNumberTextField();
        jtPhone.setBounds(140, 170, 200, 25);

        jtMail = new JTextField(20);
        jtMail.setBounds(140, 130, 200, 25);

        add = new JButton("Ajouter");
        add.setBounds(40, 220, 100, 25);
        add.addActionListener(this);

        cancel = new JButton("Annuler");
        cancel.setBounds(240, 220, 100, 25);
        cancel.addActionListener(this);

    }

    private void disposition() {
        panel.setLayout(null);

        panel.add(jlSociety);
        panel.add(jlField);
        panel.add(jlAddress);
        panel.add(jlPhone);
        panel.add(jlMail);

        panel.add(jtSociety);
        panel.add(jtAddress);
        panel.add(jtField);
        panel.add(jtPhone);
        panel.add(jtMail);

        panel.add(add);
        panel.add(cancel);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int returnCode = 0;
        try {
            if (ae.getSource() == add) {
                if (!this.jtSociety.getText().isEmpty() && !this.jtAddress.getText().isEmpty() && !this.jtField.getText().isEmpty() && !this.jtPhone.getText().isEmpty() && !this.jtMail.getText().isEmpty()) {
                    Supplier supplier = new Supplier(this.jtSociety.getText(), this.jtAddress.getText(), this.jtField.getText(), this.jtPhone.getText(), this.jtMail.getText());
                    returnCode = supplierDAO.addSupplier(supplier);
                    System.out.println("code de retour : " + returnCode);
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
