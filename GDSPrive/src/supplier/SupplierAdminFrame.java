package supplier;

import authentication.Authentication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import menu.AdminMenu;

/**
 *
 * @author e1501601
 */
public class SupplierAdminFrame extends JFrame implements ActionListener {

    private JButton create;
    private JButton list;
    private JButton modify;
    private JButton delete;
    private JButton returnToPreviousFrame;
    private JButton quit;
    private JButton search;

    private Authentication authentication;
    private SupplierDAO supplier;

    private JTextField searchField;

    private JTextArea listSuppliers;
    private JScrollPane scrollSuppliers;

    private JPanel main;

    public SupplierAdminFrame(Authentication auth) {
        authentication = auth;
        this.setTitle("Gestion des fournisseurs | " + auth.getLogin());
        this.setSize(1100, 300);

        main = new JPanel();
        add(main);

        initialize();
        disposition();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void initialize() {
        create = new JButton("Créer");
        create.setBounds(10, 50, 200, 30);
        create.addActionListener(this);

        modify = new JButton("Modifier");
        modify.setBounds(10, 100, 200, 30);
        modify.addActionListener(this);

        delete = new JButton("Supprimer");
        delete.setBounds(10, 150, 200, 30);
        delete.addActionListener(this);

        search = new JButton("Rechercher");
        search.setBounds(850, 50, 200, 30);
        search.addActionListener(this);

        list = new JButton("lister");
        list.setBounds(850, 100, 200, 30);
        list.addActionListener(this);

        returnToPreviousFrame = new JButton("Retour");
        returnToPreviousFrame.setBounds(850, 150, 200, 30);
        returnToPreviousFrame.addActionListener(this);

        quit = new JButton("Quitter");
        quit.setBounds(850, 200, 200, 30);
        quit.addActionListener(this);

        searchField = new JTextField("Champ de recherche");
        searchField.setBounds(275, 50, 500, 30);

        listSuppliers = new JTextArea();
        scrollSuppliers = new JScrollPane(listSuppliers);
        scrollSuppliers.setBounds(275, 80, 500, 150);
        scrollSuppliers.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollSuppliers.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        listSuppliers.setEditable(false);

    }

    private void disposition() {
        main.setLayout(null);

        main.add(create);
        main.add(list);
        main.add(modify);
        main.add(delete);
        main.add(returnToPreviousFrame);
        main.add(quit);
        main.add(searchField);
        main.add(search);
        main.add(scrollSuppliers);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Authentication authentication = null;

        try {
            if (ae.getSource() == create) {

                //new SuppliersFrame();
            } else if (ae.getSource() == list) {

                listSuppliers.setText(null);
                List<Supplier> listOfSuppliers = supplier.getListOfAllSuppliers();
                for (Supplier s : listOfSuppliers) {
                    listSuppliers.append(s.toString());
                    listSuppliers.append("\n");
                }

            } else if (ae.getSource() == search) {

                listSuppliers.setText(null);
                String domain = searchField.getText().toString();
                List<Supplier> listOfSuppliers = supplier.getListSuppliersByADomain(domain);
                if (!listOfSuppliers.isEmpty()) {
                    for (Supplier s : listOfSuppliers) {
                        listSuppliers.append(s.toString());
                        listSuppliers.append("\n");
                    }
                } else {
                    System.out.println("PAS DE DOMAINE POSSIBLE");
                }
            } else if (ae.getSource() == modify) {
                
            } else if (ae.getSource() == delete) {

            } else if (ae.getSource() == returnToPreviousFrame) {
                this.dispose();
                AdminMenu am = new AdminMenu(authentication);
            } else if (ae.getSource() == quit) {
                this.dispose();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
