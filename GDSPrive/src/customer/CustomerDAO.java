/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mikael
 */
public class CustomerDAO {
    private Connection connection;
    private PreparedStatement statement;

    public CustomerDAO() {
        connection = null;
        statement = null;
    }
    
    public List<Customer> getListOfAllCustomers(){
        List<Customer> customers = new ArrayList<Customer>();
        ResultSet rs = null;
        
        try{
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT * FROM client");
            rs = statement.executeQuery();
                        
            while(rs.next()){
                customers.add(new Customer(rs.getInt("id_client"), rs.getString("societe_client"), rs.getString("adresse_client"), rs.getString("domaine_client"), rs.getString("numero_client"), rs.getString("email_client")));
            }
            
        }catch(Exception e){
            System.out.println(e);
        }finally{
            try {if (rs != null)rs.close();} catch (Exception t) {}
            try {if (statement != null)statement.close();} catch (Exception t) {}
            try {if (connection != null)connection.close();} catch (Exception t) {}
        }
        
        return customers;
        
    }
    
    public List<Customer> getListCustomersByADomain(String domain){
        List<Customer> customers = new ArrayList<Customer>();
        ResultSet rs = null;
        
        try{
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT * FROM client WHERE domaine_client = ?");
            statement.setString(1, domain);
            rs = statement.executeQuery();
                        
            while(rs.next()){
                customers.add(new Customer(rs.getInt("id_client"), rs.getString("societe_client"), rs.getString("adresse_client"), rs.getString("domaine_client"), rs.getString("numero_client"), rs.getString("email_client")));
            }
            
        }catch(Exception e){
            System.out.println(e);
        }finally{
            try {if (rs != null)rs.close();} catch (Exception t) {}
            try {if (statement != null)statement.close();} catch (Exception t) {}
            try {if (connection != null)connection.close();} catch (Exception t) {}
        }
        
        return customers;
    }
    
    public int addCustomer(Customer customer) {
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("INSERT INTO client (societe_client,adresse_client,domaine_client,numero_client,email_client) VALUES ( ?, ?, ?, ?,?)");

            statement.setString(1, customer.getCompany());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getField());
            statement.setString(4, customer.getPhoneNumber());
            statement.setString(5, customer.getMail());

            //Ex�cution de la requ�te
            returnCode = statement.executeUpdate();

        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            //fermeture du preparedStatement et de la connexion
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception t) {
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception t) {
            }
        }
        
        return returnCode;
    }
    
    
}
