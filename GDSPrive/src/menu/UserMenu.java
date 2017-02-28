package menu;

import authentication.Authentication;
import authentication.AuthenticationFrame;
import customer.CustomerUserFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import supplier.SupplierUserFrame;

public class UserMenu extends JFrame implements ActionListener {

    private JButton suppliers;
    private JButton costumers;
    private JButton exit;
    private JButton signOut;

    private final JPanel main;

    private final Authentication authentication;

    /**
     * Permet d'afficher la fenêtre menu d'un utilisateur
     *
     * @param auth - Informations concernant l'utilisateur connecté
     */
    public UserMenu(Authentication auth) {
        authentication = auth;
        this.setTitle("Utilisateur | " + authentication.getLogin());
        this.setSize(400, 200);

        main = new JPanel();
        add(main);

        initialize();
        disposition();

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initialize() {
        suppliers = new JButton("Gestion des fournisseurs");
        suppliers.setBounds(90, 10, 220, 25);
        suppliers.addActionListener(this);

        costumers = new JButton("Gestion des clients");
        costumers.setBounds(90, 40, 220, 25);
        costumers.addActionListener(this);

        signOut = new JButton("Se déconnecter");
        signOut.setBounds(90, 70, 220, 25);
        signOut.addActionListener(this);

        exit = new JButton("Quitter");
        exit.setBounds(90, 100, 220, 25);
        exit.addActionListener(this);
    }

    private void disposition() {
        main.setLayout(null);

        main.add(suppliers);
        main.add(costumers);
        main.add(signOut);
        main.add(exit);

    }

    /**
     * Si on appuie sur le bouton "suppliers", la fenêtre des fournisseurs
     * apparaît. Si on appuie sur le bouton "customers", la fenêtre des clients
     * apparaît. Si on appuie sur le bouton "signOut", nous nous déconnectons et
     * revenons sur la fenêtre de connexion. Si on appuie sur le bouton "exit",
     * nous quittons l'application.
     *
     * @param ae - évènements déclenchés lors de l'appui d'un bouton
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == suppliers) {
                this.dispose();
                SupplierUserFrame suf = new SupplierUserFrame(authentication);
            } else if (ae.getSource() == costumers) {
                this.dispose();
                CustomerUserFrame suf = new CustomerUserFrame(authentication);
            } else if (ae.getSource() == signOut) {
                this.dispose();
                AuthenticationFrame af = new AuthenticationFrame();
            } else if (ae.getSource() == exit) {
                this.dispose();
            }
        } catch (Exception e) {
        }
    }

}
