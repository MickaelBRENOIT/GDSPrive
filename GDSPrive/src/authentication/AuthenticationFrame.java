package authentication;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import menu.IdIncorrect;
import menu.UserMenu;

// http://www.edu4java.com/en/swing/swing4.html
public class AuthenticationFrame extends JFrame implements ActionListener {

    private JTextField jtLogin;
    private JPasswordField jpPassword;

    private JLabel jlLogin;
    private JLabel jlPassword;

    private JButton jbSignin;

    private JPanel panelmain;

    private AuthenticationDAO authDAO;

    public AuthenticationFrame() {
        this.authDAO = new AuthenticationDAO();

        this.setTitle("Gestion des stocks");
        this.setSize(400, 200);

        panelmain = new JPanel();
        add(panelmain);

        initialize();
        disposition();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);

    }

    private void initialize() {
        jlLogin = new JLabel("Login : ");
        jlLogin.setBounds(10, 10, 80, 25);

        jlPassword = new JLabel("Password : ");
        jlPassword.setBounds(10, 40, 80, 25);

        jbSignin = new JButton("Se connecter");
        jbSignin.setBounds(180, 80, 150, 25);
        jbSignin.addActionListener(this);

        jtLogin = new JTextField(20);
        jtLogin.setBounds(100, 10, 230, 25);

        jpPassword = new JPasswordField(20);
        jpPassword.setBounds(100, 40, 230, 25);

    }

    private void disposition() {
        panelmain.setLayout(null);
        panelmain.add(jlLogin);
        panelmain.add(jlPassword);
        panelmain.add(jtLogin);
        panelmain.add(jpPassword);
        panelmain.add(jbSignin);
    }

    public void actionPerformed(ActionEvent ae) {

        Authentication authentication = null;

        try {
            if (ae.getSource() == jbSignin) {

                Authentication a = new Authentication(jtLogin.getText().toString(), jpPassword.getText().toString());
                authentication = authDAO.signIn(a);

                if (authentication != null) {
                    System.out.println("L'utilisateur est connecté");
                    if (authentication.getRole() == 1) {
                        System.out.println("\tJe suis un administrateur !");
                    } else {
                        //System.out.println("\tJe suis un utilisateur lambda !");
                        this.dispose();
                        UserMenu um = new UserMenu(a);
                    }
                } else {
                    //System.out.println("Mauvais mot de passe ou login");
                    IdIncorrect ii = new IdIncorrect();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
