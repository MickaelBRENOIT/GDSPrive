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
    
    public int deleteCustomer(String reference){
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("DELETE FROM client WHERE id_client = ? ");

            statement.setString(1, reference);

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
    
    public Customer getACustomer(String reference) {
        Customer customer = null;
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT * FROM client WHERE id_client = ?");
            statement.setString(1, reference);
            rs = statement.executeQuery();

            if (rs.next()) {
                customer = new Customer(rs.getInt("id_client"), rs.getString("societe_client"), rs.getString("adresse_client"), rs.getString("domaine_client"), rs.getString("numero_client"), rs.getString("email_client"));
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception t) {
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception t) {
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception t) {
            }
        }

        return customer;
    }
    
    public int modifyCustomer(Customer customer) {
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("UPDATE client SET societe_client = ?, adresse_client = ?, domaine_client = ?, numero_client = ?, email_client = ? WHERE id_client = ?");

            statement.setString(1, customer.getCompany());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getField());
            statement.setString(4, customer.getPhoneNumber());
            statement.setString(5, customer.getMail());
            statement.setInt(6, customer.getReference());

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

    public List<String> getListOfAllCompanies() {
        List<String> companies = new ArrayList<String>();
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT societe_client FROM client ");
            rs = statement.executeQuery();

            while (rs.next()) {
                companies.add(rs.getString("societe_client"));
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception t) {
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception t) {
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception t) {
            }
        }

        return companies;
    }
    
    public int getReferenceCompanyByName(String compagny) {
        int id = 0;
        ResultSet rs = null;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT id_client FROM client "
                                                    + "WHERE societe_client = ?");

            statement.setString(1, compagny);

            //Ex�cution de la requ�te
            rs = statement.executeQuery();
            
            if(rs.next()){
                id = rs.getInt("id_client");
            }

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
        return id;
        
    }
    
    
}
