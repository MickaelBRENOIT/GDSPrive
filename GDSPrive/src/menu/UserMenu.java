package menu;

import authentication.Authentication;
import authentication.AuthenticationFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UserMenu extends JFrame implements ActionListener{
    private JButton suppliers;
    private JButton costumers;
    private JButton exit;
    
    private JPanel main;

    public UserMenu(Authentication auth) {
        this.setTitle("Utilisateur | "+auth.getLogin());
        this.setSize(400, 150);
        
        main = new JPanel();
        add(main);
        
        initialize();
        disposition();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
    }

    private void initialize() {
        suppliers = new JButton("Gestion des fournisseurs");
        suppliers.setBounds(10, 10, 220, 25);
        suppliers.addActionListener(this);
        
        costumers = new JButton("Gestion des clients");
        costumers.setBounds(10, 40, 220, 25);
        costumers.addActionListener(this);
        
        exit = new JButton("Quitter");
        exit.setBounds(10, 70, 220, 25);
        exit.addActionListener(this);
    }

    private void disposition() {
        main.setLayout(null);
        
        main.add(suppliers);
        main.add(costumers);
        main.add(exit);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == suppliers) {
                System.out.println("Cliqué pour les fournisseurs");
            }else if (ae.getSource() == costumers){
                System.out.println("Cliqué pour les clients");
            }else if (ae.getSource() == exit){
                AuthenticationFrame af = new AuthenticationFrame();
                this.dispose();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
}
