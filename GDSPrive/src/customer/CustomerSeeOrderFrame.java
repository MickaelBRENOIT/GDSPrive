/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

import authentication.Authentication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import order.Order;
import order.OrderDAO;

/**
 *
 * @author sandra
 */
public class CustomerSeeOrderFrame  extends JFrame implements ActionListener,WindowFocusListener {
    private Authentication authentication;
    private JPanel panel;
    private JPanel panelList;
    private CustomerDAO customerDAO;
    private JLabel Jclient;
    private JLabel JdateSortie;
    private JTextField jtnom;
    private JTextField jtdate;
    private JButton modifier;
    private JButton enregistrer;
    private JButton retour;
    private OrderDAO orderDAO;
    private Order orders;
   
    private JList<String>  OrdersList ;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollOrders;

    
     public CustomerSeeOrderFrame(Authentication auth) {
    
        authentication = auth;
        this.setTitle("Afficher les commandes | " + auth.getLogin());
        this.setSize(900, 300);

        panel = new JPanel();
        add(panel);

        initialize();
        disposition();

        addWindowFocusListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);

    }
     
      private void initialize() {
        
        this.customerDAO = new CustomerDAO();
        this.orderDAO=new OrderDAO();
        this.orders=new Order();
        
        Jclient = new JLabel("Nom Client : ");
        Jclient.setBounds(10, 10, 100, 25);
        
        

        JdateSortie = new JLabel("Date de sortie :");
        JdateSortie.setBounds(10, 90, 100, 25);

        
        jtnom = new JTextField(20);
        jtnom.setBounds(100, 10, 100, 25);
        //jtnom.setText(order.getCustomer_name());
        
       
       

        jtdate = new JTextField(20);
        jtdate.setBounds(100, 90, 100, 25);

        

        modifier = new JButton("Modifier");
        modifier.setBounds(750, 10, 100, 25);
        modifier.addActionListener(this);

        enregistrer = new JButton("Enregistrer");
        enregistrer.setBounds(750, 90, 100, 25);
        enregistrer.addActionListener(this);
        
        retour = new JButton("Retour");
        retour.setBounds(750, 170, 100, 25);
        retour.addActionListener(this);
        
        panelList = new JPanel();
        listModel = new DefaultListModel<>();
        OrdersList  = new JList<>(listModel);
        panelList.add(OrdersList);
        scrollOrders= new JScrollPane(panelList);
        scrollOrders.setBounds(230, 20, 500, 150);
        scrollOrders.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollOrders.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        
      
        

    }
  private void disposition() {
        panel.setLayout(null);

        panel.add(Jclient);
        panel.add(JdateSortie);
        panel.add(jtnom);
        panel.add(jtdate);
        panel.add(modifier);
        panel.add(enregistrer);
        panel.add(retour);
        panel.add(scrollOrders);
        
        
        
    }
     
     

    @Override
    public void actionPerformed(ActionEvent ae) {
       int returnCode = 0;
        try {
            if (ae.getSource() == modifier) {
                 DefaultListModel<String> model = (DefaultListModel<String>) OrdersList.getModel();
                  if (!model.isEmpty()) {    
                  String[] splitString = OrdersList.getSelectedValue().toString().split(" ");
                  String id = splitString[0];
                  System.out.println("Split : " + id);
                  
                  jtnom.setText(orderDAO.getAOrder(id).toString());
                  jtdate.setText(orderDAO.getAOrderDate(id).toString());
                  System.out.println(orderDAO.getAOrder(id).toString());
                  System.out.println(orderDAO.getAOrderDate(id).toString());
                  
                  
                       
                  }
                
             
                }
                else if (ae.getSource() == enregistrer) {
                    String[] splitString = OrdersList.getSelectedValue().toString().split(" ");
                  String id = splitString[0];
                  //System.out.println("Split : " + id);
                Order orders = new Order(Integer.parseInt(id),this.jtnom.getText(),this.jtdate.getText());
                        returnCode =orderDAO.modifyOrder(orders);
           
            } else if (ae.getSource() == retour) {
                this.dispose();
                CustomerAdminFrame am = new  CustomerAdminFrame(authentication);
            } 
        
        }catch (Exception e) {
            System.out.println(e);
        }

    }

    
    
     public void windowGainedFocus(WindowEvent we) {
        List<Order> listOfOrders = orderDAO.getListOfAllOrders();
        System.out.println(listOfOrders);
        listModel.removeAllElements();
        for (Order o : listOfOrders) {
            listModel.addElement(o.toString());
            
        }
    }

    public void windowLostFocus(WindowEvent e) {
         
    }

}
