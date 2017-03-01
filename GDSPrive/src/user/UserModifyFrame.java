package user;

import util.DateFormat;
import authentication.Authentication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import util.JNumberTextField;

public class UserModifyFrame extends JDialog implements ActionListener {

    private JLabel jlName;
    private JLabel jlSurname;
    private JLabel jlAddress;
    private JLabel jlPassword;
    private JLabel jlMail;
    private JLabel jlPhone;
    private JLabel jlDateOfBirth;
    private JLabel jlHiringDate;
    private JLabel jlRole;

    private JTextField jtName;
    private JTextField jtSurname;
    private JTextField jtAddress;
    private JTextField jtPassword;
    private JTextField jtMail;
    private JNumberTextField jtPhone;

    private UtilDateModel birthModel;
    private JDatePanelImpl birthPanel;
    private JDatePickerImpl birthPicker;

    private UtilDateModel hiringModel;
    private JDatePanelImpl hiringPanel;
    private JDatePickerImpl hiringPicker;

    private Properties properties;

    private JComboBox jcbRole;

    private JButton modify;
    private JButton cancel;

    private JPanel panel;

    private Authentication authentication;
    private UserDAO userDAO;
    private User user;

    /**
     *
     * @param auth ouverture de la fenetre de modification des utilisateurs en
     * fonction de l'utilisateur connecté
     *
     */
    public UserModifyFrame(Authentication auth, User us) {
        authentication = auth;
        user = us;
        this.setTitle("Modifier des utilisateurs | " + auth.getLogin());
        this.setSize(400, 500);

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

        this.userDAO = new UserDAO();

        jlName = new JLabel("Nom : ");
        jlName.setBounds(10, 10, 150, 25);

        jlSurname = new JLabel("Prénom :");
        jlSurname.setBounds(10, 50, 150, 25);

        jlAddress = new JLabel("Adresse :");
        jlAddress.setBounds(10, 90, 150, 25);

        jlPassword = new JLabel("Mot de passe :");
        jlPassword.setBounds(10, 130, 150, 25);

        jlMail = new JLabel("Email :");
        jlMail.setBounds(10, 170, 150, 25);

        jlPhone = new JLabel("Téléphone :");
        jlPhone.setBounds(10, 210, 150, 25);

        jlDateOfBirth = new JLabel("Date de naissance :");
        jlDateOfBirth.setBounds(10, 250, 150, 25);

        jlHiringDate = new JLabel("Date d'embauche :");
        jlHiringDate.setBounds(10, 290, 150, 25);

        jlRole = new JLabel("Rôle :");
        jlRole.setBounds(10, 330, 150, 25);

        jtName = new JTextField();
        jtName.setBounds(140, 10, 200, 25);
        jtName.setText(user.getName());

        jtSurname = new JTextField();
        jtSurname.setBounds(140, 50, 200, 25);
        jtSurname.setText(user.getSurname());

        jtAddress = new JTextField();
        jtAddress.setBounds(140, 90, 200, 25);
        jtAddress.setText(user.getAddress());

        jtPassword = new JTextField();
        jtPassword.setBounds(140, 130, 200, 25);
        jtPassword.setText(user.getPassword());

        jtMail = new JTextField();
        jtMail.setBounds(140, 170, 200, 25);
        jtMail.setText(user.getMail());

        jtPhone = new JNumberTextField();
        jtPhone.setBounds(140, 210, 200, 25);
        jtPhone.setText(user.getNumber());

        properties = new Properties();
        properties.put("text.year", "Year");
        properties.put("text.month", "Month");
        properties.put("text.day", "Day");

        birthModel = new UtilDateModel();
        birthPanel = new JDatePanelImpl(birthModel, properties);
        birthPicker = new JDatePickerImpl(birthPanel, new DateFormat());
        birthPicker.setBounds(140, 250, 200, 25);
        birthModel.setValue(user.getBirth());

        hiringModel = new UtilDateModel();
        hiringPanel = new JDatePanelImpl(hiringModel, properties);
        hiringPicker = new JDatePickerImpl(hiringPanel, new DateFormat());
        hiringPicker.setBounds(140, 290, 200, 25);
        hiringModel.setValue(user.getHiring());

        jcbRole = new JComboBox();
        jcbRole.setBounds(140, 330, 200, 25);
        if (user.getRole() == 1) {
            jcbRole.addItem("administrateur");
        } else {
            jcbRole.addItem("utilisateur");
        }

        modify = new JButton("Modifier");
        modify.setBounds(40, 380, 100, 25);
        modify.addActionListener(this);

        cancel = new JButton("Annuler");
        cancel.setBounds(240, 380, 100, 25);
        cancel.addActionListener(this);
    }

    private void disposition() {
        panel.setLayout(null);

        panel.add(jlName);
        panel.add(jlSurname);
        panel.add(jlAddress);
        panel.add(jlPassword);
        panel.add(jlMail);
        panel.add(jlPhone);
        panel.add(jlDateOfBirth);
        panel.add(jlHiringDate);
        panel.add(jlRole);

        panel.add(jtName);
        panel.add(jtSurname);
        panel.add(jtAddress);
        panel.add(jtPassword);
        panel.add(jtMail);
        panel.add(jtPhone);
        panel.add(birthPicker);
        panel.add(hiringPicker);
        panel.add(jcbRole);

        panel.add(modify);
        panel.add(cancel);
    }

    /**
     *
     * appuie sur le bouton "cancel", cela annule la modification Si on appuie
     * sur "modify" cela permet de modifier.
     *
     * @param ae - évènements déclenchés lors de la pression d'un des boutons
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        int returnCode = 0;
        if (ae.getSource() == modify) {
            String dateBirth = birthPicker.getJFormattedTextField().getText();
            String dateHiring = hiringPicker.getJFormattedTextField().getText();
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            java.sql.Date sqlBirth = null, sqlHiring = null;

            try {
                Date birth = format.parse(dateBirth);
                sqlBirth = new java.sql.Date(birth.getTime());
                Date hiring = format.parse(dateHiring);
                sqlHiring = new java.sql.Date(hiring.getTime());
            } catch (ParseException ex) {
                Logger.getLogger(UserModifyFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            int role;
            if (String.valueOf(jcbRole.getSelectedItem().toString()).equals("administrateur")) {
                role = 1;
            } else {
                role = 2;
            }
            User user = new User(this.user.getReference_user(), jtName.getText(), jtSurname.getText(), jtPassword.getText(), jtMail.getText(), sqlBirth, sqlHiring, jtAddress.getText(), role, jtPhone.getText());
            returnCode = userDAO.modifyUser(user);
            this.dispose();
        } else if (ae.getSource() == cancel) {
            this.dispose();
        }
    }

}
