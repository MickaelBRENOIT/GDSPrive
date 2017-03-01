package resupply;

import product.*;
import customer.*;
import authentication.Authentication;
import customer.CustomerDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import menu.AdminMenu;

/**
 *
 * @author e1501601
 */
public class ResupplyAdminFrame extends JFrame implements ActionListener, WindowFocusListener {

    private JButton create;

    private JButton delete;
    private JButton returnToPreviousFrame;
    private JButton quit;

    private Authentication authentication;
    private ProductDAO products;
    private ResupplyDAO resupplyDAO;

   

    private JScrollPane scrollProducts;
    private JPanel main;
    private JPanel panelList;
    private JList<String> ResupplyList;
    private DefaultListModel<String> listModel;

    public ResupplyAdminFrame(Authentication auth) {
        authentication = auth;
        this.setTitle("Se reapprovisionner | " + auth.getLogin());
        this.setSize(900, 300);

        main = new JPanel();
        add(main);

        initialize();
        disposition();

        addWindowFocusListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void initialize() {

        this.products = new ProductDAO();
        this.resupplyDAO = new ResupplyDAO();

        create = new JButton("Créer");
        create.setBounds(10, 50, 200, 30);
        create.addActionListener(this);

        delete = new JButton("Supprimer");
        delete.setBounds(10, 100, 200, 30);
        delete.addActionListener(this);

        returnToPreviousFrame = new JButton("Retour");
        returnToPreviousFrame.setBounds(10, 150, 200, 30);
        returnToPreviousFrame.addActionListener(this);

        quit = new JButton("Quitter");
        quit.setBounds(10, 200, 200, 30);
        quit.addActionListener(this);

        panelList = new JPanel();
        listModel = new DefaultListModel<>();
        ResupplyList = new JList<>(listModel);
        panelList.add(ResupplyList);
        scrollProducts = new JScrollPane(panelList);
        scrollProducts.setBounds(275, 50, 500, 180);
        scrollProducts.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollProducts.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    }

    private void disposition() {
        main.setLayout(null);

        main.add(create);
        main.add(delete);
        main.add(returnToPreviousFrame);
        main.add(quit);
        main.add(scrollProducts);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int returnCode = 0;
        try {
            if (ae.getSource() == create) {
             
                ResupplyAddFrame saf = new ResupplyAddFrame(authentication);

            }

            /*if (ae.getSource() == search) {

                String nom = searchField.getText().toString();
                List<Product> listOfProducts = products.getListProductsByNom(nom);
                if (!listOfProducts.isEmpty()) {
                    listModel.removeAllElements();
                    for (Product p : listOfProducts) {
                        listModel.addElement(p.toString());
                    }
                } else {
                    System.out.println("PAS DE NOM POSSIBLE");
                }
            } else if (ae.getSource() == modify) {
                
                
                DefaultListModel<String> model = (DefaultListModel<String>) ProductList.getModel();
                 if (!model.isEmpty()) {
                String[] splitString = ProductList.getSelectedValue().toString().split(" ");
                String id = splitString[0];
                System.out.println("Split : " + id);
                Product productToModify = null;
                productToModify = products.getAProduct(id);
                ProductModifyFrame s = new ProductModifyFrame(authentication, productToModify);}
                 else{
                   System.out.println("choisir l'élement à modifier");  
                 }
            }*/ else if (ae.getSource() == delete) {
                DefaultListModel<String> model = (DefaultListModel<String>) ResupplyList.getModel();
                String[] splitString = ResupplyList.getSelectedValue().toString().split(" ");
                String id = splitString[0];
                System.out.println("Split : " + id);
                // TODO - Delete in the database
                returnCode = resupplyDAO.deleteResupply(id);
                model.remove(ResupplyList.getSelectedIndex());
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

    public void windowGainedFocus(WindowEvent we) {
        List<String> listOfOrderFournisseurs = resupplyDAO.getListOfAllOrderFournisseur();
        listModel.removeAllElements();
        for (String r : listOfOrderFournisseurs) {
            listModel.addElement(r);
        }
    }

    @Override
    public void windowLostFocus(WindowEvent e) {

    }

}
