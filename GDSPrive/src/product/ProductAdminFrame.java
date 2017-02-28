package product;

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
import util.ErrorFrame;

/**
 *
 * @author e1501601
 */
public class ProductAdminFrame extends JFrame implements ActionListener,WindowFocusListener {

    private JButton create;
    private JButton list;
    private JButton modify;
    private JButton delete;
    private JButton returnToPreviousFrame;
    private JButton quit;
    private JButton search;

    private Authentication authentication;
    private ProductDAO products;

    private JTextField searchField;

    private JScrollPane scrollProducts;

    private JPanel main;
    private JPanel panelList;

    private JList<String> ProductList;
    private DefaultListModel<String> listModel;

    public ProductAdminFrame(Authentication auth) {
        authentication = auth;
        this.setTitle("Gestion des produits | " + auth.getLogin());
        this.setSize(1100, 300);

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

        create = new JButton("Créer");
        create.setBounds(10, 50, 200, 30);
        create.addActionListener(this);

        modify = new JButton("Modifier");
        modify.setBounds(10, 100, 200, 30);
        modify.addActionListener(this);

        delete = new JButton("Supprimer");
        delete.setBounds(10, 150, 200, 30);
        delete.addActionListener(this);

        search = new JButton("Rechercher par Nom");
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

        panelList = new JPanel();
        listModel = new DefaultListModel<>();
        ProductList = new JList<>(listModel);
        panelList.add(ProductList);
        scrollProducts = new JScrollPane(panelList);
        scrollProducts.setBounds(275, 80, 500, 150);
        scrollProducts.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollProducts.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

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
        main.add(scrollProducts);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int returnCode = 0;
        try {
            if (ae.getSource() == create) {
                ProductAddFrame saf = new ProductAddFrame(authentication);
            }
            if (ae.getSource() == list) {
                List<Product> listOfProducts = products.getListOfAllProducts();
                listModel.removeAllElements();
                for (Product p : listOfProducts) {
                    listModel.addElement(p.toString());
                }

            }
            if (ae.getSource() == search) {

                String nom = searchField.getText().toString();
                List<Product> listOfProducts = products.getListProductsByNom(nom);
                if (!listOfProducts.isEmpty()) {
                    listModel.removeAllElements();
                    for (Product p : listOfProducts) {
                        listModel.addElement(p.toString());
                    }
                } else {
                    ErrorFrame ef = new ErrorFrame("Le nom que vous avez recherché n'existe pas.");
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
            } else if (ae.getSource() == delete) {
                DefaultListModel<String> model = (DefaultListModel<String>) ProductList.getModel();
                String[] splitString = ProductList.getSelectedValue().toString().split(" ");
                String id = splitString[0];
                System.out.println("Split : " + id);
                // TODO - Delete in the database
                returnCode = products.deleteProducts(id);
                model.remove(ProductList.getSelectedIndex());
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
         List<Product> listOfProducts = products.getListOfAllProducts();
                listModel.removeAllElements();
                for (Product p : listOfProducts) {
                    listModel.addElement(p.toString());
                }
     }

    @Override
    public void windowLostFocus(WindowEvent e) {
        
    }

}
