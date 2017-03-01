/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resupply;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import product.Product;

/**
 *
 * @author sandra
 */
public class ResupplyDAO {
    
    
     private Connection connection;
    private PreparedStatement statement;

    public ResupplyDAO() {
        connection = null;
        statement = null;
    }
    
    
     public int addProduct(Resupply resupply) {
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("INSERT INTO commande_fournisseur (ce_produit,date_commande,quantite) VALUES ( ?, ?, ?)");

            statement.setInt(1, resupply.getCe_produit());
            statement.setDate(2, (Date) resupply.getDateCommande());
            statement.setString(3, resupply.getQuantite());
            
            

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
    
    public List<String> getListOfAllOrderFournisseur() {
        List<String> orderFournisseur = new ArrayList<String>();
        ResultSet rs = null;
        
        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT id_commande_fournisseur, fournisseur.societe_fournisseur, produit.nom_produit,commande_fournisseur.date_commande,commande_fournisseur.quantite FROM produit "
                                                    + "INNER JOIN fournisseur ON produit.ce_fournisseur = fournisseur.id_fournisseur "
                                                    + "INNER JOIN commande_fournisseur ON commande_fournisseur.ce_produit=produit.id_produit ");
            
            
            
            rs = statement.executeQuery();

        
            while (rs.next()) {
       orderFournisseur.add(rs.getInt("id_commande_fournisseur")+" "+"nom fournisseur: "  +rs.getString("fournisseur.societe_fournisseur")+"   Nom produit : "  +rs.getString("nom_produit")+"  date commande : "  +rs.getDate("date_commande")+"  quantite:  "+rs.getInt("quantite"));
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

        return orderFournisseur;
    }
    
    public int deleteResupply(String reference){
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("DELETE FROM commande_fournisseur WHERE id_commande_fournisseur = ? ");

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
    

   
}

