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
    
    
}
