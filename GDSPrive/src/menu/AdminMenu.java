package menu;

import authentication.Authentication;
import authentication.AuthenticationFrame;
import customer.CustomerAdminFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import order.OrderFrame;
import product.ProductAdminFrame;
import resupply.ResupplyAdminFrame;
import supplier.SupplierAdminFrame;
import user.UserFrame;

public class AdminMenu extends JFrame implements ActionListener {

    private JButton suppliers;
    private JButton costumers;
    private JButton products;
    private JButton orders;
    private JButton users;
    private JButton signOut;
    private JButton exit;
    private JButton resupply;

    private Authentication authentication;

    private JPanel main;

    /**
     * Permet de lancer la fenêtre menu d'un administrateur
     *
     * @param auth - informations de l'administrateur connecté
     */
    public AdminMenu(Authentication auth) {
        authentication = auth;
        this.setTitle("Administrateur | " + auth.getLogin());
        this.setSize(400, 300);
        main = new JPanel();
        add(main);

        initialize();
        disposition();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initialize() {
        suppliers = new JButton("Gestion des fournisseurs");
        suppliers.setBounds(90, 10, 220, 25);
        suppliers.addActionListener(this);

        costumers = new JButton("Gestion des clients");
        costumers.setBounds(90, 40, 220, 25);
        costumers.addActionListener(this);

        products = new JButton("Gestion des produits");
        products.setBounds(90, 70, 220, 25);
        products.addActionListener(this);

        orders = new JButton("Gestion des commandes");
        orders.setBounds(90, 100, 220, 25);
        orders.addActionListener(this);

        users = new JButton("Gestion des utilisateurs");
        users.setBounds(90, 130, 220, 25);
        users.addActionListener(this);

        resupply = new JButton("Se reapprovisionner");
        resupply.setBounds(90, 160, 220, 25);
        resupply.addActionListener(this);

        signOut = new JButton("Se déconnecter");
        signOut.setBounds(90, 190, 220, 25);
        signOut.addActionListener(this);

        exit = new JButton("Quitter");
        exit.setBounds(90, 220, 220, 25);
        exit.addActionListener(this);
    }

    private void disposition() {
        main.setLayout(null);

        main.add(suppliers);
        main.add(costumers);
        main.add(products);
        main.add(orders);
        main.add(users);
        main.add(resupply);
        main.add(signOut);
        main.add(exit);
    }

    /**
     * Si on appuie sur le bouton "suppliers", la fenêtre des fournisseurs
     * apparaît. Si on appuie sur le bouton "customers", la fenêtre des clients
     * apparaît. Si on appuie sur le bouton "products", la fenêtre des produits
     * apparaît. Si on appuie sur le bouton "orders", la fenêtre des commandes
     * apparaît. Si on appuie sur le bouton "users", la fenêtre des utilisateurs
     * apparait.Si on appuie sur le bouton "resupply"la fenêtre de
     * reaprovisionnement apparaît. Si on appuie sur le bouton "signOut", nous
     * sommes déconnectés et renvoyé vers la fenêtre de connexion. Si on appyue
     * sur le bouton "exit", nous quittons l'application.
     *
     * @param ae - évènements déclenchés lorsqu'un bouton est appuyé
     */
    @Override
    public void actionPerformed(ActionEvent ae) {

        try {
            if (ae.getSource() == suppliers) {
                this.dispose();
                SupplierAdminFrame saf = new SupplierAdminFrame(authentication);
            } else if (ae.getSource() == costumers) {
                this.dispose();
                CustomerAdminFrame caf = new CustomerAdminFrame(authentication);
            } else if (ae.getSource() == products) {
                this.dispose();
                ProductAdminFrame paf = new ProductAdminFrame(authentication);
            } else if (ae.getSource() == orders) {
                this.dispose();
                OrderFrame of = new OrderFrame(authentication);
            } else if (ae.getSource() == users) {
                this.dispose();
                UserFrame uf = new UserFrame(authentication);
            } else if (ae.getSource() == resupply) {
                this.dispose();
                ResupplyAdminFrame rf = new ResupplyAdminFrame(authentication);

            } else if (ae.getSource() == signOut) {
                this.dispose();
                AuthenticationFrame af = new AuthenticationFrame();
            } else if (ae.getSource() == exit) {
                this.dispose();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
