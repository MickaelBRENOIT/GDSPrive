/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Mikael
 */
public class TemporaryOrderItemDAO {
    
    private Connection connection;
    private PreparedStatement statement;

    public TemporaryOrderItemDAO() {
        connection = null;
        statement = null;
    }

    public int addTemporaryOrderItem(String company, String product, int quantityItem, Double totalPrice) {
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("INSERT INTO commande_temporaire (nom_societe, nom_produit, quantite, total) VALUES ( ?, ?, ?, ?)");

            statement.setString(1, company);
            statement.setString(2, product);
            statement.setInt(3, quantityItem);
            statement.setDouble(4, totalPrice);
            

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
