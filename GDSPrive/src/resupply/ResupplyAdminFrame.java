package resupply;

import product.*;

import authentication.Authentication;
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
import menu.AdminMenu;

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

    /**
     *
     * @param auth qui ouvre la fenetre de gestion de rapprovisionnement en
     * fonction de l'utilisateur connecté
     */
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

    /**
     * Si on appuie sur le bouton "create", cela ouvre la fenetre de creation du
     * reaprovisionnement. Si on appuie sur le bouton "cancel", cela annule
     * l'action. Si on appuie sur le bouton "delete" cela supprime le champ
     * selectionné Si on appuie sur e bouton "quit" on quitte Si on appuie sur
     * le bouton "return" sera ramène sur la fenetre precedendte
     *
     * @param ae - évènements déclenchés lors de la pression d'un des boutons
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        int returnCode = 0;
        try {
            if (ae.getSource() == create) {
                ResupplyAddFrame saf = new ResupplyAddFrame(authentication);
            } else if (ae.getSource() == delete) {
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
