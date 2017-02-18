package customer;

import authentication.Authentication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author e1501601
 */
public class CustomerModifyFrame extends JDialog implements ActionListener {

    private JTextField jtSociety;
    private JTextField jtPhone;
    private JTextField jtMail;
    private JTextField jtAddress;
    private JTextField jtField;

    private JLabel jlSociety;
    private JLabel jlPhone;
    private JLabel jlMail;
    private JLabel jlAddress;
    private JLabel jlField;

    private JButton modify;
    private JButton cancel;

    private JPanel panel;
    
    private Authentication authentication;
    private Customer customer;
    private CustomerDAO customerDAO;

    public CustomerModifyFrame(Authentication auth, Customer cust) {
        customer = cust;
        authentication = auth;
        this.setTitle("Modifier des clients | " + auth.getLogin());
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
        
        this.customerDAO = new CustomerDAO();

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
        jtSociety.setText(customer.getCompany());

        jtAddress = new JTextField(20);
        jtAddress.setBounds(140, 50, 200, 25);
        jtAddress.setText(customer.getAddress());

        jtField = new JTextField(20);
        jtField.setBounds(140, 90, 200, 25);
        jtField.setText(customer.getField());

        jtPhone = new JTextField(20);
        jtPhone.setBounds(140, 130, 200, 25);
        jtPhone.setText(customer.getPhoneNumber());

        jtMail = new JTextField(20);
        jtMail.setBounds(140, 170, 200, 25);
        jtMail.setText(customer.getMail());

        modify = new JButton("Modifier");
        modify.setBounds(40, 220, 100, 25);
        modify.addActionListener(this);

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

        panel.add(modify);
        panel.add(cancel);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int returnCode = 0;
        try {
            if (ae.getSource() == modify) {
                Customer customer = new Customer(this.customer.getReference(), this.jtSociety.getText().toString(), this.jtAddress.getText().toString(), this.jtField.getText().toString(), this.jtPhone.getText().toString(), this.jtMail.getText().toString());
                returnCode = customerDAO.modifyCustomer(customer);
                System.out.println("code de retour modifier: " + returnCode);
                this.dispose();
            } else if (ae.getSource() == cancel) {
                this.dispose();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
