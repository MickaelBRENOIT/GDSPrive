/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mikael
 */
public class SupplierDAO {
    private Connection connection;
    private PreparedStatement statement;

    public SupplierDAO() {
        connection = null;
        statement = null;
    }
    
    public List<Supplier> getListOfAllSuppliers(){
        List<Supplier> suppliers = new ArrayList<Supplier>();
        ResultSet rs = null;
        
        try{
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT * FROM fournisseur");
            rs = statement.executeQuery();
                        
            while(rs.next()){
                suppliers.add(new Supplier(rs.getInt("id_fournisseur"), rs.getString("societe_fournisseur"), rs.getString("adresse_fournisseur"), rs.getString("domaine_fournisseur"), rs.getString("numero_fournisseur")));
            }
            
        }catch(Exception e){
            System.out.println(e);
        }finally{
            try {if (rs != null)rs.close();} catch (Exception t) {}
            try {if (statement != null)statement.close();} catch (Exception t) {}
            try {if (connection != null)connection.close();} catch (Exception t) {}
        }
        
        return suppliers;
        
    }
    
    public List<Supplier> getListSuppliersByADomain(String domain){
        List<Supplier> suppliers = new ArrayList<Supplier>();
        ResultSet rs = null;
        
        try{
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT * FROM fournisseur WHERE domaine_fournisseur = ?");
            statement.setString(1, domain);
            rs = statement.executeQuery();
                        
            while(rs.next()){
                suppliers.add(new Supplier(rs.getInt("id_fournisseur"), rs.getString("societe_fournisseur"), rs.getString("adresse_fournisseur"), rs.getString("domaine_fournisseur"), rs.getString("numero_fournisseur")));
            }
            
        }catch(Exception e){
            System.out.println(e);
        }finally{
            try {if (rs != null)rs.close();} catch (Exception t) {}
            try {if (statement != null)statement.close();} catch (Exception t) {}
            try {if (connection != null)connection.close();} catch (Exception t) {}
        }
        
        return suppliers;
    }
    
    
}
