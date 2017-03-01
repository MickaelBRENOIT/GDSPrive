package user;

import authentication.Authentication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import menu.AdminMenu;

public class UserFrame extends JFrame implements ActionListener, WindowFocusListener {

    private JButton create;
    private JButton list;
    private JButton modify;
    private JButton delete;
    private JButton returnToPreviousFrame;
    private JButton quit;
    private JButton search;

    private Authentication authentication;
    private UserDAO user;

    private JComboBox searchField;

    private JScrollPane scrollUsers;

    private JPanel main;
    private JPanel panelList;

    private JList<String> UsersList;
    private DefaultListModel<String> listModel;

    /**
     *
     * @param auth ouvre la fenetre de gestions des utilisateurs en fonction de
     * l'utilisateur connecté
     */
    public UserFrame(Authentication auth) {
        authentication = auth;
        this.setTitle("Gestion des utilisateurs | " + auth.getLogin());
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

        this.user = new UserDAO();

        create = new JButton("Créer");
        create.setBounds(10, 50, 200, 30);
        create.addActionListener(this);

        modify = new JButton("Modifier");
        modify.setBounds(10, 100, 200, 30);
        modify.addActionListener(this);

        delete = new JButton("Supprimer");
        delete.setBounds(10, 150, 200, 30);
        delete.addActionListener(this);

        search = new JButton("Rechercher par rôle");
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

        searchField = new JComboBox();
        searchField.setBounds(275, 50, 500, 30);
        List<User> listOfRoles = user.getListOfAllRoles();
        for (User u : listOfRoles) {
            searchField.addItem(u.getDesignation());
        }

        panelList = new JPanel();
        listModel = new DefaultListModel<>();
        UsersList = new JList<>(listModel);
        panelList.add(UsersList);
        scrollUsers = new JScrollPane(panelList);
        scrollUsers.setBounds(275, 80, 500, 150);
        scrollUsers.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollUsers.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

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
        main.add(scrollUsers);
    }

    /**
     * Si on appuie sur le bouton "create", cela ouvre la fenetre de creation de
     * l'utilisateur. Si on appuie sur le bouton "liste", cela permet de lister.
     * Si on appuie sur le bouton "modify" cela modifie. Si on appuie sur
     * "delete" cela permet de supprimer si on appuie sur "quit" cela permet de
     * quitter si on appuie sur "search" cela permet la recherche si on appui
     * sur le boutton "returnTo PreviousFrame" cela permet de retourner sur la
     * page precedente
     *
     * @param ae - évènements déclenchés lors de la pression d'un des boutons
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        int returnCode = 0;
        try {
            if (ae.getSource() == create) {
                UserAddFrame uaf = new UserAddFrame(authentication);
            } else if (ae.getSource() == list) {
                List<User> listOfUsers = user.getListOfAllUsers();
                listModel.removeAllElements();
                for (User u : listOfUsers) {
                    listModel.addElement(u.toString());
                }

            } else if (ae.getSource() == search) {

                int role;
                if (String.valueOf(searchField.getSelectedItem().toString()).equals("administrateur")) {
                    role = 1;
                } else {
                    role = 2;
                }
                List<User> listOfUsers = user.getListUsersByARole(role);
                listModel.removeAllElements();
                for (User u : listOfUsers) {
                    listModel.addElement(u.toString());
                }

            } else if (ae.getSource() == modify) {
                //TODO - Database 1 : search id and send to DAO in order to return the supplier
                DefaultListModel<String> model = (DefaultListModel<String>) UsersList.getModel();
                String[] splitString = UsersList.getSelectedValue().toString().split(" ");
                String id = splitString[0];
                User userToModify = null;
                userToModify = user.getAUser(id);
                //TODO - Display the SupplierModifyFrame.java
                UserModifyFrame umf = new UserModifyFrame(authentication, userToModify);
                //TODO - Database 2 : Modify the supplier by passing him in the parameter of the constructor.

            } else if (ae.getSource() == delete) {
                DefaultListModel<String> model = (DefaultListModel<String>) UsersList.getModel();
                String[] splitString = UsersList.getSelectedValue().toString().split(" ");
                String id = splitString[0];
                // TODO - Delete in the database
                returnCode = user.deleteUser(id);
                model.remove(UsersList.getSelectedIndex());
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

    @Override
    public void windowGainedFocus(WindowEvent we) {
        List<User> listOfUsers = user.getListOfAllUsers();
        listModel.removeAllElements();
        for (User u : listOfUsers) {
            listModel.addElement(u.toString());
        }
    }

    @Override
    public void windowLostFocus(WindowEvent we) {
    }

}
